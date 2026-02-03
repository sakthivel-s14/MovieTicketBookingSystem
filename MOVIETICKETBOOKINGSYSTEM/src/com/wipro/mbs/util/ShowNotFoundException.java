package com.wipro.mbs.util;

public class ShowNotFoundException extends Exception {
    public String toString() {
        return "Error: Show ID not found.";
    }
}