package store;

import cart.ShoppingCart;
import inventory.InventoryManager;
import product.Salable;
import cart.Order;

import java.util.Scanner;

/**
 * Main class representing the console-based Store Front for user interactions.
 * Handles viewing inventory, purchasing items, canceling purchases, and checkout.
 */
public class StoreFront {
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;

    /**
     * Initializes the StoreFront application, setting up the InventoryManager and ShoppingCart.
     */
    public StoreFront() {
        inventoryManager = new InventoryManager();
        shoppingCart = new ShoppingCart();
        try {
            inventoryManager.initializeInventory();
        } catch (Exception e) {
            System.out.println("Error initializing inventory: " + e.getMessage());
        }
    }

    /**
     * Handles purchasing an item by reducing its quantity in inventory and adding it to the cart.
     *
     * @param itemName The name of the item to purchase.
     */
    public void purchaseItem(String itemName) {
        Salable product = inventoryManager.getProductByName(itemName);
        
        if (product != null && product.getQuantity() > 0) {
            inventoryManager.removeProduct(product);
            shoppingCart.addItem(product);  // Adds the item to the cart
            
            try {
                inventoryManager.saveInventoryToFile();
            } catch (Exception e) {
                System.out.println("Error saving inventory: " + e.getMessage());
            }

            System.out.println("Item added to cart.");
        } else {
            System.out.println("Item not available.");
        }
    }

    /**
     * Handles canceling a purchase by removing an item from the cart and adding it back to inventory.
     *
     * @param itemName The name of the item to cancel.
     */
    public void cancelPurchase(String itemName) {
        Salable product = shoppingCart.removeItem(itemName);  // Removes the item from the cart
        
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
            
            try {
                inventoryManager.saveInventoryToFile();
            } catch (Exception e) {
                System.out.println("Error saving inventory: " + e.getMessage());
            }

            System.out.println("Purchase canceled, item returned to inventory.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    /**
     * Handles sorting of the inventory and displays it in the specified order.
     *
     * @param sortOrder The order to sort by ("asc" for ascending, "desc" for descending).
     */
    public void sortAndDisplayInventory(String sortOrder) {
        inventoryManager.sortInventory(sortOrder);
        viewInventory();
    }

    /**
     * Displays the inventory list.
     */
    public void viewInventory() {
        System.out.println("Available Products:");
        for (Salable product : inventoryManager.getInventory()) {
            System.out.println(product);
        }
    }

    /**
     * Displays the shopping cart contents.
     */
    public void viewCart() {
        System.out.println("Items in Cart:");
        for (Salable item : shoppingCart.getCartItems()) {
            System.out.println(item);
        }
    }

    /**
     * Handles checkout, creating an Order from the cart contents, displaying the order details, and clearing the cart.
     */
    public void checkout() {
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
        } else {
            Order order = new Order(shoppingCart);
            order.displayOrderDetails();
            
            shoppingCart.emptyCart();  // Clear the cart after checkout
            System.out.println("Checkout complete. Thank you for your purchase!");
        }
    }

    /**
     * Main method to run the StoreFront application with a simple console-based menu.
     */
    public static void main(String[] args) {
        StoreFront storeFront = new StoreFront();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Store Front");
            System.out.println("1. View Inventory");
            System.out.println("2. Sort Inventory by Name Ascending");
            System.out.println("3. Sort Inventory by Name Descending");
            System.out.println("4. Sort Inventory by Price Ascending");
            System.out.println("5. Sort Inventory by Price Descending");
            System.out.println("6. Purchase Item");
            System.out.println("7. Cancel Purchase");
            System.out.println("8. View Cart");
            System.out.println("9. Checkout");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    storeFront.viewInventory();
                    break;
                case 2:
                    storeFront.sortAndDisplayInventory("asc");  // Sort by name ascending
                    break;
                case 3:
                    storeFront.sortAndDisplayInventory("desc"); // Sort by name descending
                    break;
                case 4:
                    storeFront.sortAndDisplayInventory("asc");  // Sort by price ascending
                    break;
                case 5:
                    storeFront.sortAndDisplayInventory("desc"); // Sort by price descending
                    break;
                case 6:
                    System.out.print("Enter item name to purchase: ");
                    String purchaseItem = scanner.nextLine();
                    storeFront.purchaseItem(purchaseItem);
                    break;
                case 7:
                    System.out.print("Enter item name to cancel purchase: ");
                    String cancelItem = scanner.nextLine();
                    storeFront.cancelPurchase(cancelItem);
                    break;
                case 8:
                    storeFront.viewCart();
                    break;
                case 9:
                    storeFront.checkout();
                    break;
                case 10:
                    System.out.println("Exiting Store Front. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
