package com.library.management.services;

import com.library.management.models.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Rental system to manage rented resources.
 */

public class RentalSystem {
    public final Map<Resource, Date> rentedResources = new HashMap<>();
    public final Map<String, Double> penalties = new HashMap<>();

    public void rentResource(Resource resource, String user) throws Exception {
        if (rentedResources.containsKey(resource)) {
            throw new Exception("Resource already rented!");
        }
        rentedResources.put(resource, new Date());
    }

    public void returnResource(Resource resource, String user) throws Exception {
        if (!rentedResources.containsKey(resource)) {
            throw new Exception("Resource not rented!");
        }
        Date dueDate = rentedResources.get(resource);
        long overdue = (new Date().getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
        if (overdue > 7) {
            penalties.put(user, penalties.getOrDefault(user, 0.0) + (overdue - 7) * 5.0);
        }
        rentedResources.remove(resource);
    }

    public boolean isUserBanned(String user) {
        return penalties.getOrDefault(user, 0.0) > 50.0;
    }
}
