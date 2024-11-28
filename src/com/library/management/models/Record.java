package com.library.management.models;

/**
 * Record resource.
 */
public class Record extends Resource {
    private final String artist;

    public Record(String title, String artist) {
        super(title);
        this.artist = artist;
    }

    @Override
    public String getDetails() {
        return "Record [Title=" + getTitle() + ", Artist=" + artist + "]";
    }
}
