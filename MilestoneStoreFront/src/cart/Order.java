package cart;

import cart.ShoppingCart;
import product.Salable;
import java.util.ArrayList;

/**
 * Represents a finalized order during checkout.
 */
public class Order {
    private ArrayList<Salable> items;
    private double totalPrice;

    public Order(ShoppingCart shoppingCart) {
        this.items = shoppingCart.getCartItems();
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Salable item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayOrderDetails() {
        System.out.println("\nOrder Details:");
        for (Salable item : items) {
            System.out.println(item);
        }
        System.out.printf("Total Price: %.2f gold pieces%n", totalPrice);
    }
}
