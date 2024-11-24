package store;

import inventory.InventoryManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerThread handles incoming admin commands via a background thread.
 */
public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private InventoryManager inventoryManager;

    public ServerThread(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        try {
            serverSocket = new ServerSocket(8080); // Start the server on port 8080
            System.out.println("ServerThread started on port 8080");
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("ServerThread is running and listening for admin commands...");
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String commandPayload = input.readLine();
                System.out.println("Received command from admin: " + commandPayload); // Debug log

                if (commandPayload != null) {
                    String[] parts = commandPayload.split("\\|", 2);
                    String command = parts[0];
                    String payload = parts.length > 1 ? parts[1] : null;

                    String response = processCommand(command, payload);
                    System.out.println("Processing command: " + command); // Debug log
                    output.println(response);
                }
            } catch (IOException e) {
                System.out.println("Error processing admin command: " + e.getMessage());
            }
        }
    }

    private String processCommand(String command, String payload) {
        switch (command) {
            case "U":
                if (payload != null) {
                    System.out.println("Updating inventory with payload: " + payload);
                    inventoryManager.updateInventory(payload);
                    return "Inventory updated successfully.";
                } else {
                    return "Error: No payload provided for update command.";
                }
            case "R":
                System.out.println("Retrieving inventory as JSON...");
                return inventoryManager.getInventoryAsJSON();
            default:
                return "Error: Unknown command.";
        }
    }
}
