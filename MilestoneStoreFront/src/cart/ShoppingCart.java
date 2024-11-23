package cart;

import product.Salable;
import java.util.ArrayList;

/**
 * Manages the shopping cart for a user session.
 */
public class ShoppingCart {
    private ArrayList<Salable> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addItem(Salable item) {
        cartItems.add(item);
    }

    public Salable removeItem(String itemName) {
        for (Salable item : cartItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                cartItems.remove(item);
                return item;
            }
        }
        return null;
    }

    public ArrayList<Salable> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void emptyCart() {
        cartItems.clear();
    }
}
