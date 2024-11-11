package cart;

import product.Salable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Salable> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart.
     * @param item The Salable item to add.
     */
    public void addItem(Salable item) {
        cartItems.add(item);
    }

    /**
     * Removes an item from the shopping cart by name.
     * @param itemName The name of the item to remove.
     * @return The removed Salable item, or null if the item was not found in the cart.
     */
    public Salable removeItem(String itemName) {
        for (Salable item : cartItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                cartItems.remove(item);
                return item;
            }
        }
        return null;  // Item not found
    }

    /**
     * Returns the contents of the shopping cart.
     * @return List of Salable items in the cart.
     */
    public List<Salable> getCartItems() {
        return new ArrayList<>(cartItems);  // Return a copy to prevent modification
    }

    /**
     * Empties the shopping cart.
     */
    public void emptyCart() {
        cartItems.clear();
    }
}
