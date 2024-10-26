package cart;

import java.util.ArrayList;
import java.util.List;
import product.Salable;

/**
 * Class to manage the user's shopping cart.
 */
public class ShoppingCart {
    private List<Salable> cartItems;

    /** Constructor to initialize the shopping cart. */
    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addProductToCart(Salable product) { cartItems.add(product); }

    public void removeProductFromCart(Salable product) { cartItems.remove(product); }

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

    public void clearCart() { cartItems.clear(); }
}