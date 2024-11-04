package cart;

import java.util.ArrayList;
import java.util.List;
import product.Salable;


/**
 * Class to manage the user's shopping cart.
 * Provides methods to add, remove, view, and clear cart items.
 */
public class ShoppingCart {
    private List<Salable> cartItems;

    /** Constructor to initialize the shopping cart. */
    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    /**
     * Adds a product to the shopping cart.
     * 
     * @param product The Salable product to add to the cart.
     */
    public void addProductToCart(Salable product) {
        cartItems.add(product);
    }

    /**
     * Removes a product from the shopping cart.
     * 
     * @param product The Salable product to remove from the cart.
     */
    public void removeProductFromCart(Salable product) {
        cartItems.remove(product);
    }

    /**
     * Returns the current contents of the shopping cart.
     * 
     * @return A list of Salable items in the cart.
     */
    public List<Salable> getCartContents() {
        return cartItems;
    }

    /** Clears all items from the shopping cart. */
    public void clearCart() {
        cartItems.clear();
    }
}
