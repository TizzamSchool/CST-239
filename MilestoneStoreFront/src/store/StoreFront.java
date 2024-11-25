package store;

import inventory.InventoryManager;
import cart.ShoppingCart;
import cart.Order;
import product.Salable;
import java.util.Scanner;

/**
 * The main StoreFront application class for Game Users.
 * This application allows users to interact with a store where they can view, sort, purchase,
 * and cancel purchases of items. The application also includes an admin server that listens
 * for inventory updates or retrieval commands sent by the AdminApplication.
 */
public class StoreFront {
    private InventoryManager inventoryManager; // Manages the store's inventory
    private ShoppingCart shoppingCart;        // Manages the user's shopping cart
    private ServerThread serverThread;        // Server thread for admin commands

    /**
     * Constructor for the StoreFront class.
     * Initializes the inventory manager, shopping cart, and starts the server thread.
     */
    public StoreFront() {
        inventoryManager = new InventoryManager();
        shoppingCart = new ShoppingCart();
        serverThread = new ServerThread(inventoryManager);
        serverThread.start(); // Start the server in the background

        try {
            inventoryManager.initializeInventory();
        } catch (Exception e) {
            System.out.println("Error initializing inventory: " + e.getMessage());
        }
    }

    /**
     * Runs the StoreFront application.
     * Displays the menu and handles user input to perform store operations.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // Controls the main menu loop

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    sortAndDisplayInventory("asc");
                    break;
                case 3:
                    sortAndDisplayInventory("desc");
                    break;
                case 4:
                    sortAndDisplayInventory("priceAsc");
                    break;
                case 5:
                    sortAndDisplayInventory("priceDesc");
                    break;
                case 6:
                    System.out.print("Enter item to purchase: ");
                    purchaseItem(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Enter item to cancel: ");
                    cancelPurchase(scanner.nextLine());
                    break;
                case 8:
                    viewCart();
                    break;
                case 9:
                    checkout();
                    break;
                case 10: // Exit the application
                    System.out.println("Exiting StoreFront.");
                    running = false; // Stop the loop
                    scanner.close();
                    terminateServerThread(); // Stop the server thread
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Displays the main menu to the user.
     */
    private void displayMenu() {
        System.out.println("\nWelcome to the Store Front");
        System.out.println("1. View Inventory");
        System.out.println("2. Sort Inventory by Name Asc");
        System.out.println("3. Sort Inventory by Name Desc");
        System.out.println("4. Sort Inventory by Price Asc");
        System.out.println("5. Sort Inventory by Price Desc");
        System.out.println("6. Purchase Item");
        System.out.println("7. Cancel Purchase");
        System.out.println("8. View Cart");
        System.out.println("9. Checkout");
        System.out.println("10. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Displays the current inventory to the user.
     * Iterates through all products in the inventory and prints their details.
     */
    private void viewInventory() {
        for (Salable product : inventoryManager.getInventory()) {
            System.out.println(product);
        }
    }

    /**
     * Sorts and displays the inventory based on the given sort order.
     *
     * @param sortOrder The sort order to apply (e.g., "asc", "desc", "priceAsc", "priceDesc").
     */
    private void sortAndDisplayInventory(String sortOrder) {
        inventoryManager.sortInventory(sortOrder);
        viewInventory();
    }

    /**
     * Allows the user to purchase an item by name.
     * Updates the shopping cart and inventory accordingly.
     *
     * @param itemName The name of the item to purchase.
     */
    private void purchaseItem(String itemName) {
        Salable product = inventoryManager.getProductByName(itemName);
        if (product != null && product.getQuantity() > 0) {
            shoppingCart.addItem(product);
            inventoryManager.removeProduct(product);
            System.out.println(itemName + " added to cart.");
        } else {
            System.out.println("Item not available.");
        }
    }

    /**
     * Allows the user to cancel a purchase and return the item to the inventory.
     *
     * @param itemName The name of the item to cancel.
     */
    private void cancelPurchase(String itemName) {
        Salable product = shoppingCart.removeItem(itemName);
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
            inventoryManager.saveInventoryToFile(); // Save updated inventory to file
            System.out.println(itemName + " returned to inventory.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    /**
     * Displays the contents of the user's shopping cart.
     */
    private void viewCart() {
        for (Salable item : shoppingCart.getCartItems()) {
            System.out.println(item);
        }
    }

    /**
     * Completes the user's purchase and clears the shopping cart.
     * Displays the order details to the user.
     */
    private void checkout() {
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            Order order = new Order(shoppingCart);
            order.displayOrderDetails(); // Show order details
            shoppingCart.emptyCart(); // Clear the cart
            System.out.println("Thank you for shopping!");
        }
    }

    /**
     * Stops the ServerThread gracefully.
     * Called when the application exits.
     */
    private void terminateServerThread() {
        System.out.println("Shutting down ServerThread...");
        serverThread.stopServer(); // Signal the server thread to stop
    }

    /**
     * Main entry point for the StoreFront application.
     * Initializes and runs the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new StoreFront().run();
    }
}
