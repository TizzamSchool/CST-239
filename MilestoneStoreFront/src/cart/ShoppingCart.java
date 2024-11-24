package cart;

import product.Salable;
import java.util.ArrayList;

/**
 * Manages the shopping cart for a Game User.
 */
public class ShoppingCart {
    private ArrayList<Salable> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart.
     * 
     * @param item The item to add.
     */
    public void addItem(Salable item) {
        cartItems.add(item);
    }

    /**
     * Removes an item from the shopping cart by name.
     * 
     * @param itemName The name of the item to remove.
     * @return The removed item, or null if not found.
     */
    public Salable removeItem(String itemName) {
        for (Salable item : cartItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                cartItems.remove(item);
                return item;
            }
        }
        return null;
    }

    /**
     * Retrieves the contents of the shopping cart.
     * 
     * @return A list of items in the cart.
     */
    public ArrayList<Salable> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    /**
     * Clears all items from the shopping cart.
     */
    public void emptyCart() {
        cartItems.clear();
    }
}
