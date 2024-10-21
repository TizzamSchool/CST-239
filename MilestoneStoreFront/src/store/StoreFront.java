package store;

import java.util.Scanner;
import cart.ShoppingCart;
import inventory.InventoryManager;
import product.Salable;

/**
 * Main class for the Store Front Application.
 */
public class StoreFront {
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;
    private Scanner scanner;

    /** Constructor to initialize the store front. */
    public StoreFront() {
        inventoryManager = new InventoryManager();
        shoppingCart = new ShoppingCart();
        scanner = new Scanner(System.in);
    }

    /** Initializes the store with products. */
    public void initializeStore() {
        inventoryManager.loadProducts();
        System.out.println("Welcome to the Arena Store Front!");
    }

    /** Displays the main menu to the user. */
    public void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View Products");
        System.out.println("2. Purchase Product");
        System.out.println("3. View Shopping Cart");
        System.out.println("4. Cancel Purchase");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
    }

    /** Handles user interactions with the store. */
    public void run() {
        initializeStore();
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    purchaseProduct();
                    break;
                case 3:
                    shoppingCart.viewCartItems();
                    break;
                case 4:
                    cancelPurchase();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        System.out.println("Thank you for visiting the Arena Store!");
    }

    private void viewProducts() {
        System.out.println("Available Products:");
        for (Salable product : inventoryManager.listAllProducts()) {
            System.out.println(product);
        }
    }

    private void purchaseProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        Salable product = inventoryManager.getProductByName(name);

        if (product != null && product.getQuantity() > 0) {
            shoppingCart.addProductToCart(product);
            inventoryManager.updateQuantity(product, product.getQuantity() - 1);
            System.out.println("Product added to cart.");
        } else {
            System.out.println("Product not available.");
        }
    }

    private void cancelPurchase() {
        System.out.print("Enter product name to remove: ");
        String name = scanner.nextLine();
        Salable product = inventoryManager.getProductByName(name);

        if (product != null) {
            shoppingCart.removeProductFromCart(product);
            inventoryManager.updateQuantity(product, product.getQuantity() + 1);
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private void checkout() {
        shoppingCart.viewCartItems();
        shoppingCart.clearCart();
        System.out.println("Checkout complete. Thank you for your purchase!");
    }

    public static void main(String[] args) {
        StoreFront store = new StoreFront();
        store.run();
    }
}
