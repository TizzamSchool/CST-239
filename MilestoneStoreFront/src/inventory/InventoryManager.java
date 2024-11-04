package inventory;

import java.util.ArrayList;
import java.util.List;
import product.Armor;
import product.HealthPotion;
import product.Salable;
import product.Weapon;


/**
 * Class to manage the inventory of products in the store.
 * Provides methods to initialize, add, remove, and retrieve products.
 */
public class InventoryManager {
    private List<Salable> products;

    /** Constructor to initialize the product inventory. */
    public InventoryManager() {
        products = new ArrayList<>();
    }

    /** Loads the initial products into the inventory. */
    public void initializeInventory() {
        products.add(new Weapon("Sword", "A sharp steel blade", 50, 5, 10));
        products.add(new Weapon("Axe", "A heavy double-edged axe", 60, 3, 15));
        products.add(new Armor("Shield", "Protective wooden shield", 40, 4, 8));
        products.add(new Armor("Helmet", "Iron helmet for head protection", 30, 6, 5));
        products.add(new HealthPotion("Health Potion", "Restores 50 HP", 10, 10, 50));
    }

    /**
     * Returns a list of all products in the inventory.
     * 
     * @return A list of Salable products.
     */
    public List<Salable> getInventory() {
        return products;
    }

    /**
     * Finds and returns a product by name in the inventory.
     * 
     * @param name The name of the product to search for.
     * @return The Salable product if found, otherwise null.
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
     * Removes a product from the inventory by reducing its quantity.
     * 
     * @param product The product to remove.
     * @return True if the product was successfully removed or updated, false if not.
     */
    public boolean removeProduct(Salable product) {
        if (product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            return true;
        }
        return false;
    }

    /**
     * Adds a product back to the inventory by increasing its quantity.
     * 
     * @param product The product to add.
     */
    public void addProduct(Salable product) {
        product.setQuantity(product.getQuantity() + 1);
    }
}
