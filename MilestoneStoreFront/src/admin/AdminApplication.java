package admin;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Main class representing the Administration Application.
 * Provides a menu for sending commands to the StoreFront application.
 */
public class AdminApplication {
    private AdminService adminService;

    public static void main(String[] args) {
        AdminApplication app = new AdminApplication();
        app.start();
    }

    /**
     * Starts the admin application and displays the menu.
     */
    public void start() {
        try {
            adminService = new AdminService("localhost", 8080); // Connect to StoreFront server
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: // Update Inventory
                        System.out.println("Enter new inventory as JSON payload (end input with an empty line):");
                        String jsonPayload = readMultiLineInput(scanner);
                        adminService.sendCommand("U", jsonPayload);
                        String response = adminService.receiveResponse();
                        System.out.println("Response from server: " + response);
                        break;
                    case 2: // Retrieve Inventory
                        adminService.sendCommand("R", null);
                        String responseR = adminService.receiveResponse();
                        System.out.println("StoreFront Inventory:");
                        System.out.println(responseR); // Display inventory in JSON format
                        break;
                    case 3: // Exit
                        System.out.println("Exiting Admin Application...");
                        adminService.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Admin Application: " + e.getMessage());
        }
    }

    /**
     * Reads multi-line input from the user until an empty line is entered.
     *
     * @param scanner The Scanner object used to read input.
     * @return The full multi-line input as a single string.
     */
    private String readMultiLineInput(Scanner scanner) {
        StringBuilder inputBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) { // Stop reading when an empty line is entered
                break;
            }
            inputBuilder.append(line);
        }
        return inputBuilder.toString().trim(); // Ensure no extra spaces or newlines
    }

    /**
     * Displays the admin menu.
     */
    private void displayMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Update StoreFront Inventory");
        System.out.println("2. Retrieve StoreFront Inventory");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }
}
