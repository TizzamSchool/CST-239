package store;

import java.util.Scanner;
import cart.ShoppingCart;
import inventory.InventoryManager;
import product.Salable;


/**
 * Main class for the Store Front Application.
 * Manages interactions between the user, inventory, and shopping cart.
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
        inventoryManager.initializeInventory();
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

    /** Runs the store front application. */
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
                    viewCart();
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

    /** Displays all products available in the inventory. */
    private void viewProducts() {
        System.out.println("Available Products:");
        for (Salable product : inventoryManager.getInventory()) {
            System.out.println(product);
        }
    }

    /** Allows the user to purchase a product and adds it to the shopping cart. */
    private void purchaseProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        Salable product = inventoryManager.getProductByName(name);

        if (product != null && product.getQuantity() > 0) {
            shoppingCart.addProductToCart(product);
            inventoryManager.removeProduct(product);
            System.out.println("Product added to cart.");
        } else {
            System.out.println("Product not available.");
        }
    }

    /** Displays the contents of the shopping cart. */
    private void viewCart() {
        System.out.println("Shopping Cart:");
        for (Salable item : shoppingCart.getCartContents()) {
            System.out.println(item);
        }
    }

    /** Cancels a purchase by removing an item from the cart and returning it to inventory. */
    private void cancelPurchase() {
        System.out.print("Enter product name to remove: ");
        String name = scanner.nextLine();
        Salable product = inventoryManager.getProductByName(name);

        if (product != null && shoppingCart.getCartContents().contains(product)) {
            shoppingCart.removeProductFromCart(product);
            inventoryManager.addProduct(product);
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    /** Completes the purchase, clears the cart, and displays a thank you message. */
    private void checkout() {
        viewCart();
        shoppingCart.clearCart();
        System.out.println("Checkout complete. Thank you for your purchase!");
    }

    /** Main method to start the application. */
    public static void main(String[] args) {
        StoreFront store = new StoreFront();
        store.run();
    }
}
