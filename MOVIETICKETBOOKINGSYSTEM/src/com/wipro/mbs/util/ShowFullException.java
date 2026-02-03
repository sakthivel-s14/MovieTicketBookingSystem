package com.wipro.mbs.util;
public class ShowFullException extends Exception {
 public String toString() {
     return "Error: Show is fully booked. Cannot reserve seats.";
 }
}