package com.wipro.mbs.entity;

public class Show {
    private String showId;
    private String movieId;
    private String showTime;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;

    public Show(String showId, String movieId, String showTime, int totalSeats, int availableSeats, double ticketPrice) {
        this.showId = showId;
        this.movieId = movieId;
        this.showTime = showTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
    }

    public String getShowId() { return showId; }
    public String getMovieId() { return movieId; }
    public String getShowTime() { return showTime; }
    public int getAvailableSeats() { return availableSeats; }
    public double getTicketPrice() { return ticketPrice; }
    
    // Method to update seats when booking or cancelling
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}