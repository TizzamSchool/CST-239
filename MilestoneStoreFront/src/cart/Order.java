package cart;

import product.Salable;

import java.util.List;

public class Order {
    private List<Salable> items;
    private double totalPrice;

    /**
     * Constructor for Order, initializes with the current contents of the shopping cart.
     * @param shoppingCart The shopping cart from which items are copied into the order.
     */
    public Order(ShoppingCart shoppingCart) {
        // Use the getCartItems() method to get items from ShoppingCart
        this.items = shoppingCart.getCartItems();
        this.totalPrice = calculateTotalPrice();
    }

    /**
     * Calculates the total price of all items in the order.
     * @return The total price.
     */
    private double calculateTotalPrice() {
        double total = 0;
        for (Salable item : items) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Displays order details, including each item and the total price.
     */
    public void displayOrderDetails() {
        System.out.println("Order Details:");
        for (Salable item : items) {
            System.out.println(item);
        }
        System.out.printf("Total Price: %.2f gold pieces%n", totalPrice);
    }

    /**
     * Gets the list of items in the order.
     * @return List of Salable items in the order.
     */
    public List<Salable> getItems() {
        return items;
    }

    /**
     * Gets the total price of the order.
     * @return The total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
}
