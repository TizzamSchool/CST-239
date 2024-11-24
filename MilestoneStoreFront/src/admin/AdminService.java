package admin;

import java.io.*;
import java.net.Socket;

/**
 * Service class for handling network communication with the StoreFront application.
 */
public class AdminService {
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public AdminService(String host, int port) throws IOException {
        System.out.println("Connecting to StoreFront server on " + host + ":" + port);
        clientSocket = new Socket(host, port);
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendCommand(String command, String payload) {
        String fullCommand = command + "|" + (payload != null ? payload.trim() : ""); // Trim payload
        System.out.println("Sending command to server: " + fullCommand); // Debug log
        System.out.println("Payload length: " + fullCommand.length());
        output.println(fullCommand);
    }

    public String receiveResponse() throws IOException {
        String response = input.readLine();
        System.out.println("Received response from server: " + response); // Debug log
        return response;
    }

    public void close() throws IOException {
        System.out.println("Closing connection to server.");
        output.close();
        input.close();
        clientSocket.close();
    }
}
