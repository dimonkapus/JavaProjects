package com.library.management.models;

/**
 * Abstract class for all resources.
 */
public abstract class Resource {
    private final String title;

    protected Resource(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract String getDetails();
}
