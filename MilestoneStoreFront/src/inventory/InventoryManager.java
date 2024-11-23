package inventory;

import product.Salable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the store's inventory of Salable products.
 */
public class InventoryManager {
    private ArrayList<Salable> products;

    public InventoryManager() {
        products = new ArrayList<>();
    }

    /**
     * Initializes the inventory from default or loads from a JSON file.
     */
    public void initializeInventory() {
        // Load or populate default items
        products = FileService.readInventoryFromFile("inventory.json");
        if (products == null || products.isEmpty()) {
            products = getDefaultInventory();
            saveInventoryToFile();
        }
    }

    /**
     * Retrieves the product with the specified name.
     *
     * @param name The name of the product to find.
     * @return The Salable product, or null if not found.
     */
    public Salable getProductByName(String name) {
        for (Salable product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null; // Product not found
    }

    public ArrayList<Salable> getInventory() {
        return products;
    }

    public void addProduct(Salable product) {
        products.add(product);
    }

    public boolean removeProduct(Salable product) {
        return products.remove(product);
    }

    /**
     * Sorts the inventory based on order: ascending or descending.
     *
     * @param order The sorting order, either "asc" or "desc".
     */
    public void sortInventory(String order) {
        Collections.sort(products);
        if ("desc".equalsIgnoreCase(order)) {
            Collections.reverse(products);
        }
    }

    /**
     * Saves the current inventory to a JSON file.
     */
    public void saveInventoryToFile() {
        FileService.writeInventoryToFile("inventory.json", products);
    }

    /**
     * Provides a default inventory of products if no inventory file is available.
     *
     * @return An ArrayList of default Salable products.
     */
    private ArrayList<Salable> getDefaultInventory() {
        ArrayList<Salable> defaultProducts = new ArrayList<>();
        defaultProducts.add(new product.Weapon("Sword", "A sharp steel blade", 50, 5, 10, "Melee"));
        defaultProducts.add(new product.Weapon("Axe", "A heavy axe", 60, 3, 15, "Melee"));
        defaultProducts.add(new product.Armor("Shield", "Protective wooden shield", 40, 3, 8, "Wood"));
        defaultProducts.add(new product.Armor("Helmet", "Iron helmet for head protection", 30, 5, 5, "Metal"));
        defaultProducts.add(new product.HealthPotion("Health Potion", "Restores 50 HP", 10, 10, 50, "Small"));
        defaultProducts.add(new product.HealthPotion("Large Health Potion", "Restores 100 HP", 20, 5, 100, "Large"));
        return defaultProducts;
    }
}
