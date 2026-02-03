package com.wipro.mbs.service;

import java.util.ArrayList;
import com.wipro.mbs.entity.*;
import com.wipro.mbs.util.*;

public class BookingService {
    
    // Data storage
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;
    private ArrayList<Booking> bookings;

    // Constructor to load data
    public BookingService(ArrayList<User> users, ArrayList<Movie> movies, ArrayList<Show> shows, ArrayList<Booking> bookings) {
        this.users = users;
        this.movies = movies;
        this.shows = shows;
        this.bookings = bookings;
    }

    // 1. Validate User
    public boolean validateUser(String userId) throws InvalidUserException {
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                return true;
            }
        }
        throw new InvalidUserException();
    }

    // 2. Find Show Helper
    public Show findShow(String showId) throws ShowNotFoundException {
        for (Show s : shows) {
            if (s.getShowId().equals(showId)) {
                return s;
            }
        }
        throw new ShowNotFoundException();
    }

    // 3. Book Ticket
    public Booking bookTicket(String userId, String showId, int seats) throws Exception {
        // Step A: Validate User
        validateUser(userId);

        // Step B: Find Show
        Show show = findShow(showId);

        // Step C: Check Availability
        if (show.getAvailableSeats() < seats) {
            throw new ShowFullException();
        }

        // Step D: Calculate Fare & Update Seats
        double fare = show.getTicketPrice() * seats;
        show.setAvailableSeats(show.getAvailableSeats() - seats);

        // Step E: Create Booking Object
        // Using System time to generate a random unique ID
        String bookingId = "BKG-" + System.currentTimeMillis(); 
        Booking newBooking = new Booking(bookingId, userId, showId, seats, fare);

        // Step F: Save to list
        bookings.add(newBooking);
        
        return newBooking;
    }

    // 4. Cancel Booking
    public void cancelBooking(String bookingId) throws BookingNotFoundException, ShowNotFoundException {
        Booking bookingToRemove = null;

        // Find the booking
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                bookingToRemove = b;
                break;
            }
        }

        if (bookingToRemove == null) {
            throw new BookingNotFoundException();
        }

        // Restore seats
        Show show = findShow(bookingToRemove.getShowId());
        show.setAvailableSeats(show.getAvailableSeats() + bookingToRemove.getSeatsBooked());

        // Remove from list
        bookings.remove(bookingToRemove);
    }

    // 5. Print User History
    public void printUserBookings(String userId) {
        System.out.println("History for User: " + userId);
        boolean found = false;
        for (Booking b : bookings) {
            if (b.getUserId().equals(userId)) {
                System.out.println(" - Booking ID: " + b.getBookingId() + 
                                   " | Show ID: " + b.getShowId() + 
                                   " | Seats: " + b.getSeatsBooked() + 
                                   " | Cost: " + b.getTotalFare());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found.");
        }
    }
}