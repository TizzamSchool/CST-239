package store;

import inventory.InventoryManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Background server thread for handling admin commands.
 */
public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private InventoryManager inventoryManager;
    private boolean running;

    public ServerThread(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        try {
            serverSocket = new ServerSocket(8080); // Start the server on port 8080
            running = true; // Set the thread to running
            System.out.println("ServerThread started on port 8080");
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("ServerThread is running and listening for admin commands...");
        while (running) {
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Connected to AdminApplication.");
                handleClient(clientSocket);
            } catch (IOException e) {
                if (running) {
                    System.out.println("Error accepting client connection: " + e.getMessage());
                } else {
                    System.out.println("ServerThread shutting down...");
                }
            }
        }

        try {
            serverSocket.close(); // Close the server socket
        } catch (IOException e) {
            System.out.println("Error closing ServerThread: " + e.getMessage());
        }
    }

    /**
     * Handles commands from a connected client (AdminApplication).
     *
     * @param clientSocket The connected client socket.
     */
    private void handleClient(Socket clientSocket) {
        try (
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String commandPayload;
            while ((commandPayload = input.readLine()) != null) {
                System.out.println("Received command from admin: " + commandPayload);

                String[] parts = commandPayload.split("\\|", 2);
                String command = parts[0];
                String payload = parts.length > 1 ? parts[1] : null;

                String response = processCommand(command, payload);
                System.out.println("Processing command: " + command);
                output.println(response); // Send response back to the client
            }
        } catch (IOException e) {
            System.out.println("Error processing admin command: " + e.getMessage());
        }
    }

    /**
     * Processes a received command and performs the requested action.
     *
     * @param command The command string (e.g., "U", "R").
     * @param payload The optional payload data (e.g., JSON string for inventory update).
     * @return A response string to be sent back to the client.
     */
    private String processCommand(String command, String payload) {
        switch (command) {
            case "U": // Update inventory
                if (payload != null) {
                    System.out.println("Updating inventory with payload: " + payload);
                    inventoryManager.updateInventory(payload);
                    return "Inventory updated successfully.";
                } else {
                    return "Error: No payload provided for update command.";
                }
            case "R": // Retrieve inventory
                System.out.println("Retrieving inventory as JSON...");
                return inventoryManager.getInventoryAsJSON();
            default:
                return "Error: Unknown command.";
        }
    }

    /**
     * Stops the server thread gracefully.
     */
    public void stopServer() {
        running = false; // Stop the thread loop
        try {
            serverSocket.close(); // Force the server socket to close
        } catch (IOException e) {
            System.out.println("Error stopping ServerThread: " + e.getMessage());
        }
    }
}
