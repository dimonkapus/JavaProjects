package com.library.management.models;

/**
 * Instrument resource.
 */
public class Instrument extends Resource {
    private final String type;

    public Instrument(String title, String type) {
        super(title);
        this.type = type;
    }

    @Override
    public String getDetails() {
        return "Instrument [Name=" + getTitle() + ", Type=" + type + "]";
    }
}
