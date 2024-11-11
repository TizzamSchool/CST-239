package store;

import cart.ShoppingCart;
import inventory.InventoryManager;
import product.Salable;
import cart.Order;

import java.util.Scanner;

public class StoreFront {
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;

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
     * Handles purchasing an item by reducing its quantity and adding it to the cart.
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
     * Handles cancellation by removing items from the cart and adding them back to the inventory.
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
     * Handles checkout by finalizing the purchase and clearing the shopping cart.
     */
    public void checkout() {
        if (shoppingCart.getCartItems().isEmpty()) {  // Now using getCartItems
            System.out.println("Cart is empty. Nothing to checkout.");
        } else {
            // Create a new order with the current contents of the shopping cart
            Order order = new Order(shoppingCart);
            order.displayOrderDetails();  // Display order details to the user
            
            shoppingCart.emptyCart();  // Clear the cart after checkout
            System.out.println("Checkout complete. Thank you for your purchase!");
        }
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
        for (Salable item : shoppingCart.getCartItems()) {  // Now using getCartItems
            System.out.println(item);
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
            System.out.println("2. Purchase Item");
            System.out.println("3. Cancel Purchase");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    storeFront.viewInventory();
                    break;
                case 2:
                    System.out.print("Enter item name to purchase: ");
                    String purchaseItem = scanner.nextLine();
                    storeFront.purchaseItem(purchaseItem);
                    break;
                case 3:
                    System.out.print("Enter item name to cancel purchase: ");
                    String cancelItem = scanner.nextLine();
                    storeFront.cancelPurchase(cancelItem);
                    break;
                case 4:
                    storeFront.viewCart();
                    break;
                case 5:
                    storeFront.checkout();
                    break;
                case 6:
                    System.out.println("Exiting Store Front. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
