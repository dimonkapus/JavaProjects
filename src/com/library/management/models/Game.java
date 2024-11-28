package com.library.management.models;

/**
 * Game resource.
 */
public class Game extends Resource {
    private final String genre;

    public Game(String title, String genre) {
        super(title);
        this.genre = genre;
    }

    @Override
    public String getDetails() {
        return "Game [Title=" + getTitle() + ", Genre=" + genre + "]";
    }
}
