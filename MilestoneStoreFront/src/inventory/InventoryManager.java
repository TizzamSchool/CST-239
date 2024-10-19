package inventory;

import java.util.ArrayList;
import java.util.List;

import product.HealthPotion;
import product.Salable;

/**
 * Class to manage the inventory of products in the store.
 */
public class InventoryManager {
    private List<Salable> products;

    /** Constructor to initialize the product inventory. */
    public InventoryManager() {
        products = new ArrayList<>();
    }

    /** Loads initial products into the inventory. */
    public void loadProducts() {
        products.add(new HealthPotion("Small Health Potion", "Restores 50 HP", 10, 10));
        products.add(new HealthPotion("Large Health Potion", "Restores 100 HP", 20, 5));
    }

    /**
     * Returns a list of all products in the inventory.
     * @return List of Salable products.
     */
    public List<Salable> listAllProducts() {
        return products;
    }

    /**
     * Finds a product by name in the inventory.
     * @param name Product name.
     * @return Salable product if found, null otherwise.
     */
    public Salable getProductByName(String name) {
        for (Salable product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Updates the quantity of a product in the inventory.
     * @param product Salable product to update.
     * @param newQuantity New quantity to set.
     */
    public void updateQuantity(Salable product, int newQuantity) {
        product.setQuantity(newQuantity);
    }
}
