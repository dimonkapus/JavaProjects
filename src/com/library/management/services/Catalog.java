package com.library.management.services;

import com.library.management.models.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Catalog system for resources.
 */
public class Catalog {
    private final List<Resource> resources = new ArrayList<>();

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public List<Resource> searchByTitle(String title) {
        List<Resource> results = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getTitle().contains(title)) {
                results.add(resource);
            }
        }
        return results;
    }
}
