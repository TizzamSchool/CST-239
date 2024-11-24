package store;

import inventory.InventoryManager;
import cart.ShoppingCart;
import cart.Order;
import product.Salable;
import java.util.Scanner;

/**
 * Main StoreFront application class for Game Users.
 */
public class StoreFront {
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;
    private ServerThread serverThread;

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

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: viewInventory(); break;
                case 2: sortAndDisplayInventory("asc"); break;
                case 3: sortAndDisplayInventory("desc"); break;
                case 4: sortAndDisplayInventory("priceAsc"); break;
                case 5: sortAndDisplayInventory("priceDesc"); break;
                case 6:
                    System.out.print("Enter item to purchase: ");
                    purchaseItem(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Enter item to cancel: ");
                    cancelPurchase(scanner.nextLine());
                    break;
                case 8: viewCart(); break;
                case 9: checkout(); break;
                case 10:
                    System.out.println("Exiting StoreFront.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

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

    private void viewInventory() {
        for (Salable product : inventoryManager.getInventory()) {
            System.out.println(product);
        }
    }

    private void sortAndDisplayInventory(String sortOrder) {
        inventoryManager.sortInventory(sortOrder);
        viewInventory();
    }

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

    private void cancelPurchase(String itemName) {
        Salable product = shoppingCart.removeItem(itemName);
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
            inventoryManager.saveInventoryToFile();
            System.out.println(itemName + " returned to inventory.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    private void viewCart() {
        for (Salable item : shoppingCart.getCartItems()) {
            System.out.println(item);
        }
    }

    private void checkout() {
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            Order order = new Order(shoppingCart);
            order.displayOrderDetails();
            shoppingCart.emptyCart();
            System.out.println("Thank you for shopping!");
        }
    }

    public static void main(String[] args) {
        new StoreFront().run();
    }
}
