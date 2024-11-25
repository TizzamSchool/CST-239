package admin;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Main class representing the Administration Application.
 * Provides a menu for sending commands to the StoreFront application.
 */
public class AdminApplication {
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public static void main(String[] args) {
        AdminApplication app = new AdminApplication();
        app.start();
    }

    /**
     * Starts the admin application and displays the menu.
     */
    public void start() {
        try {
            connectToServer(); // Establish connection to the StoreFront server
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter new inventory as JSON payload (end input with an empty line):");
                        String jsonPayload = readMultiLineInput(scanner);
                        sendCommand("U", jsonPayload);
                        break;
                    case 2:
                        sendCommand("R", null);
                        break;
                    case 3:
                        System.out.println("Exiting Admin Application...");
                        disconnectFromServer(); // Close the connection
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
     * Connects to the StoreFront server.
     */
    private void connectToServer() throws IOException {
        System.out.println("Connecting to StoreFront server on localhost:8080...");
        clientSocket = new Socket("localhost", 8080);
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connected to StoreFront server.");
    }

    /**
     * Disconnects from the StoreFront server.
     */
    private void disconnectFromServer() throws IOException {
        output.close();
        input.close();
        clientSocket.close();
        System.out.println("Disconnected from StoreFront server.");
    }

    /**
     * Sends a command to the StoreFront server and displays the response.
     *
     * @param command The command to send (e.g., "U", "R").
     * @param payload The optional payload to send with the command.
     */
    private void sendCommand(String command, String payload) {
        try {
            String fullCommand = command + "|" + (payload != null ? payload : "");
            output.println(fullCommand);
            System.out.println("Sending command to server: " + fullCommand);

            String response = input.readLine();
            System.out.println("Response from server: " + response);
        } catch (IOException e) {
            System.out.println("Error sending command to server: " + e.getMessage());
        }
    }

    /**
     * Reads multi-line input from the user until an empty line is entered.
     *
     * @param scanner The Scanner object for reading input.
     * @return The full multi-line input as a single string.
     */
    private String readMultiLineInput(Scanner scanner) {
        StringBuilder inputBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                break;
            }
            inputBuilder.append(line);
        }
        return inputBuilder.toString().trim();
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
