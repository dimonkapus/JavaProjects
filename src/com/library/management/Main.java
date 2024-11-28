package com.library.management;

import com.library.management.models.*;
import com.library.management.services.*;
import com.library.management.utils.*;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        RentalSystem rentalSystem = new RentalSystem();

        catalog.addResource(new Book("Java Programming", 450, "1234567890"));
        catalog.addResource(new Record("Abbey Road", "The Beatles"));
        catalog.addResource(new Instrument("Guitar", "String"));
        catalog.addResource(new Game("The Witcher 3", "RPG"));

        try {
            rentalSystem.rentResource(catalog.searchByTitle("Java").get(0), "user1");
            System.out.println("Resource rented successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DataStorage.saveToCSV(catalog, "catalog.csv");
            DataStorage.saveToXML(catalog, "catalog.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
