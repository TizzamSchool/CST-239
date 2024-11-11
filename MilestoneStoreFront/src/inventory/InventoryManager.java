package inventory;

import product.Salable;
import product.Weapon;
import product.Armor;
import product.HealthPotion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Salable> products;
    private static final String DEFAULT_FILE_PATH = "inventory.json";

    public InventoryManager() {
        products = new ArrayList<>();
    }

    /**
     * Initializes the inventory from file if it exists; otherwise, creates the default inventory.
     */
    public void initializeInventory() throws InventoryFileException {
        initializeInventoryFromFile(DEFAULT_FILE_PATH);
    }

    /**
     * Loads inventory from file if it exists; otherwise, creates and saves the default inventory.
     */
    private void initializeInventoryFromFile(String filePath) throws InventoryFileException {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Inventory file not found. Creating a new file with default inventory.");
            products = getDefaultInventory();
            saveInventoryToFile();  // Save default inventory to file
        } else {
            products = FileService.readInventoryFromFile(filePath);  // Load existing inventory
        }
    }

    /**
     * Returns a default list of Salable products to be used if no inventory file exists.
     */
    private List<Salable> getDefaultInventory() {
        List<Salable> defaultInventory = new ArrayList<>();
        
        // Adding Weapon items
        defaultInventory.add(new Weapon("Sword", "A sharp steel blade", 50, 5, 10, "Melee"));
        defaultInventory.add(new Weapon("Axe", "A heavy axe", 60, 3, 15, "Melee"));

        // Adding Armor items
        defaultInventory.add(new Armor("Shield", "Protective wooden shield", 40, 3, 8, "Wood"));
        defaultInventory.add(new Armor("Helmet", "Iron helmet for head protection", 30, 5, 5, "Metal"));

        // Adding HealthPotion items
        defaultInventory.add(new HealthPotion("Health Potion", "Restores 50 HP", 10, 10, 50, "Small"));
        defaultInventory.add(new HealthPotion("Large Health Potion", "Restores 100 HP", 20, 5, 100, "Large"));

        return defaultInventory;
    }

    public List<Salable> getInventory() { return products; }

    public Salable getProductByName(String name) {
        for (Salable product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Decreases the quantity of a product if available and returns true if successful.
     */
    public boolean removeProduct(Salable product) {
        if (product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            return true;
        }
        return false;
    }

    /**
     * Saves the current inventory state to a JSON file.
     */
    public void saveInventoryToFile() throws InventoryFileException {
        FileService.writeInventoryToFile(DEFAULT_FILE_PATH, products);
    }
}
