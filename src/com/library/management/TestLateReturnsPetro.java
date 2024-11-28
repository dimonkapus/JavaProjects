package com.library.management;

import com.library.management.models.*;
import com.library.management.services.*;
import com.library.management.utils.*;

import java.util.*;

/**
 * TestLateReturnsPetro
 * ---------------------
 * Test class for checking Petro's rental scenario.
 * Includes checks for timely and late returns of resources.
 *
 * Author: Kapustian Dmytro
 * Date: 24.11.2024
 * Time: 17:30
 */
public class TestLateReturnsPetro {

    public static void main(String[] args) {
        // Initialize catalog and rental system
        Catalog catalog = new Catalog();
        RentalSystem rentalSystem = new RentalSystem();

        // Add resources to the catalog
        catalog.addResource(new Book("Java Programming", 450, "1234567890"));
        catalog.addResource(new Book("Cosmos", 300, "9876543210"));
        catalog.addResource(new Game("S.T.A.L.K.E.R 2", "Horror"));

        System.out.println("Catalog filled with test data.\n");

        // Petro's rental scenario
        testPetroRentalScenario(catalog, rentalSystem);

        // Save catalog to files
        saveDataToFiles(catalog);
    }

    /**
     * Test Petro's rental scenario.
     *
     * @param catalog       the catalog to choose resources from.
     * @param rentalSystem  the rental system for checking.
     */
    private static void testPetroRentalScenario(Catalog catalog, RentalSystem rentalSystem) {
        System.out.println("Petro's rental scenario:");

        try {
            // Petro rents three resources
            Resource programmingBook = catalog.searchByTitle("Java").get(0);
            Resource cosmosBook = catalog.searchByTitle("Cosmos").get(0);
            Resource horrorGame = catalog.searchByTitle("S.T.A.L.K.E.R").get(0);

            rentalSystem.rentResource(programmingBook, "Petro");
            rentalSystem.rentResource(cosmosBook, "Petro");
            rentalSystem.rentResource(horrorGame, "Petro");

            System.out.println("Resources successfully rented by Petro:\n"
                    + programmingBook.getDetails() + "\n"
                    + cosmosBook.getDetails() + "\n"
                    + horrorGame.getDetails());

            // Simulate returns with different delays
            simulateReturn(rentalSystem, programmingBook, "Petro", 12); // 5-day delay
            simulateReturn(rentalSystem, cosmosBook, "Petro", 7);  // On time
            simulateReturn(rentalSystem, horrorGame, "Petro", 37); // 30-day delay

            // Check Petro's fines
            double petroFine = rentalSystem.isUserBanned("Petro") ?
                    rentalSystem.penalties.get("Petro") : 0.0;
            System.out.println("\nTotal fine for Petro: " + petroFine + " UAH.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nScenario completed.\n");
    }

    /**
     * Simulate return of a resource with delay.
     *
     * @param rentalSystem the rental system.
     * @param resource     the resource to return.
     * @param user         the user returning the resource.
     * @param days         the number of days passed.
     */
    private static void simulateReturn(RentalSystem rentalSystem, Resource resource, String user, int days) {
        try {
            System.out.println("Simulating return of resource with " + days + " days delay: " + resource.getTitle());
            // Manually set the rental date to 'days' days ago
            rentalSystem.rentedResources.put(resource, new Date(System.currentTimeMillis() - (long) days * 24 * 60 * 60 * 1000));
            // Return the resource
            rentalSystem.returnResource(resource, user);
        } catch (Exception e) {
            System.err.println("Error during resource return: " + e.getMessage());
        }
    }

    /**
     * Save catalog data to CSV and XML files.
     *
     * @param catalog the catalog to save.
     */
    private static void saveDataToFiles(Catalog catalog) {
        System.out.println("Saving data to files...");
        try {
            DataStorage.saveToCSV(catalog, "catalog.csv");
            System.out.println("Data saved to catalog.csv");

            DataStorage.saveToXML(catalog, "catalog.xml");
            System.out.println("Data saved to catalog.xml");
        } catch (Exception e) {
            System.err.println("Error during data saving: " + e.getMessage());
        }
        System.out.println("Saving completed.\n");
    }
}
