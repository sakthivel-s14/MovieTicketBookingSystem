package com.wipro.mbs.entity;

public class Movie {
    private String movieId;
    private String title;
    private String genre;
    private int durationMinutes;

    public Movie(String movieId, String title, String genre, int durationMinutes) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    public String getTitle() { return title; }
}