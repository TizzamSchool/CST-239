package inventory;

import product.Salable;
import com.fasterxml.jackson.databind.ObjectMapper;

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
     * Initializes the inventory from a JSON file or creates a default inventory.
     */
    public void initializeInventory() {
        try {
            products = FileService.readInventoryFromFile("inventory.json");
            if (products == null || products.isEmpty()) {
                products = getDefaultInventory();
                saveInventoryToFile();
            }
        } catch (Exception e) {
            System.out.println("Error initializing inventory: " + e.getMessage());
        }
    }

    /**
     * Updates the inventory by adding new products from a JSON payload.
     *
     * @param jsonPayload The JSON string containing the list of new Salable products.
     */
    public void updateInventory(String jsonPayload) {
        try {
            System.out.println("Received JSON payload: " + jsonPayload); // Debug log
            System.out.println("Payload length: " + jsonPayload.length());
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Salable> newProducts = mapper.readValue(jsonPayload,
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, Salable.class));
            products.addAll(newProducts); // Add new products to the current inventory
            saveInventoryToFile(); // Save the updated inventory to file
        } catch (Exception e) {
            System.out.println("Error updating inventory: " + e.getMessage());
        }
    }

    /**
     * Retrieves the inventory as a JSON string.
     *
     * @return A JSON string representation of the current inventory.
     */
    public String getInventoryAsJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(products); // Convert the products list to JSON
        } catch (Exception e) {
            return "Error retrieving inventory as JSON: " + e.getMessage();
        }
    }

    /**
     * Saves the current inventory to a JSON file.
     */
    public void saveInventoryToFile() {
        FileService.writeInventoryToFile("inventory.json", products);
    }

    /**
     * Sorts the inventory based on the specified sort order.
     *
     * @param sortOrder The sorting order (e.g., "asc", "desc", "priceAsc", "priceDesc").
     */
    public void sortInventory(String sortOrder) {
        // Default sorting by name (ascending)
        products.sort(Salable::compareTo);

        if (sortOrder.equalsIgnoreCase("desc")) {
            Collections.reverse(products); // Reverse for descending order
        }
    }

    /**
     * Finds a product in the inventory by name.
     *
     * @param name The name of the product to find.
     * @return The matching product, or null if not found.
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
     * Removes a product from the inventory by decreasing its quantity or removing it entirely.
     *
     * @param product The product to remove.
     * @return True if the product was successfully removed, false otherwise.
     */
    public boolean removeProduct(Salable product) {
        if (products.contains(product)) {
            product.setQuantity(product.getQuantity() - 1);
            if (product.getQuantity() <= 0) {
                products.remove(product);
            }
            saveInventoryToFile(); // Persist changes to the inventory file
            return true;
        }
        return false;
    }

    /**
     * Retrieves the inventory as a list.
     *
     * @return The list of Salable products in the inventory.
     */
    public ArrayList<Salable> getInventory() {
        return products;
    }

    /**
     * Provides a default inventory of products if no inventory file is available.
     *
     * @return A default list of Salable products.
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
