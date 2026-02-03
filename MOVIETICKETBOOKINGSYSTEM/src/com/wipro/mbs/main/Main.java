package com.wipro.mbs.main;

import java.util.ArrayList;
import com.wipro.mbs.entity.*;
import com.wipro.mbs.service.BookingService;
import com.wipro.mbs.util.*;

public class Main {
    public static void main(String[] args) {
        
        // 1. Setup Data
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("U001", "Rohit Mehta", "9876543210"));
        users.add(new User("U002", "Ananya Roy", "9123456780"));

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("M001", "Inception", "Sci-Fi", 148));
        movies.add(new Movie("M002", "RRR", "Action", 182));

        ArrayList<Show> shows = new ArrayList<>();
        shows.add(new Show("S101", "M001", "7:00 PM", 120, 120, 200.0));
        shows.add(new Show("S202", "M002", "10:00 PM", 150, 150, 250.0));

        ArrayList<Booking> bookings = new ArrayList<>();
        
        // 2. Initialize Service
        BookingService service = new BookingService(users, movies, shows, bookings);

        try {
            // 3. Attempt Booking
            System.out.println("--- Attempting Booking ---");
            Booking b1 = service.bookTicket("U001", "S101", 3);
            System.out.println("Booking Successful! ID: " + b1.getBookingId());

            // 4. View History
            System.out.println("\n--- Bookings for User U001 ---");
            service.printUserBookings("U001");

            // 5. Cancel Booking
            System.out.println("\n--- Cancelling the booking ---");
            service.cancelBooking(b1.getBookingId());
            System.out.println("Booking Cancelled Successfully!");
            
            // 6. Verify Cancellation
            System.out.println("\n--- Verify History After Cancel ---");
            service.printUserBookings("U001");

        } catch (InvalidUserException | ShowFullException | 
                 BookingNotFoundException | ShowNotFoundException | 
                 BookingOperationException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println("UNEXPECTED ERROR: " + ex);
            ex.printStackTrace();
        }
    }
}