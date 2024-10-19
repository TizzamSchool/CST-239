package cart;

import java.util.ArrayList;
import java.util.List;

import product.Salable;

/**
 * Class to manage the shopping cart.
 */
public class ShoppingCart {
    private List<Salable> cartItems;

    /** Constructor to initialize the shopping cart. */
    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    /**
     * Adds a product to the shopping cart.
     * @param product Salable product to add.
     */
    public void addProductToCart(Salable product) {
        cartItems.add(product);
    }

    /**
     * Removes a product from the shopping cart.
     * @param product Salable product to remove.
     */
    public void removeProductFromCart(Salable product) {
        cartItems.remove(product);
    }

    /**
     * Displays all items in the cart.
     */
    public void viewCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Salable item : cartItems) {
                System.out.println(item);
            }
        }
    }

    /** Clears all items from the cart. */
    public void clearCart() {
        cartItems.clear();
    }
}
