package com.wipro.mbs.util;

public class BookingNotFoundException extends Exception {
    public String toString() {
        return "Error: Booking ID does not exist.";
    }
}