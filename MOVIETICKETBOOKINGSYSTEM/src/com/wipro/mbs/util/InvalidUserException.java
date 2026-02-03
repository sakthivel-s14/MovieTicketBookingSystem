package com.wipro.mbs.util;

public class InvalidUserException extends Exception {
    public String toString() {
        return "Error: User ID not found in the system.";
    }
}