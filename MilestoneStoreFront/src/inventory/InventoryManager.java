package inventory;

import java.util.ArrayList;
import java.util.List;
import product.Armor;
import product.HealthPotion;
import product.Salable;
import product.Weapon;


/**
 * Class to manage the inventory of products in the store.
 */
public class InventoryManager {
    private List<Salable> products;

    /** Constructor to initialize the product inventory. */
    public InventoryManager() {
        products = new ArrayList<>();
    }

    /** Loads the initial products into the inventory. */
    public void loadInitialInventory() {
        products.add(new Weapon("Sword", "A sharp steel blade", 50, 5, 10));
        products.add(new Weapon("Axe", "A heavy double-edged axe", 60, 3, 15));
        products.add(new Armor("Shield", "Protective wooden shield", 40, 4, 8));
        products.add(new Armor("Helmet", "Iron helmet for head protection", 30, 6, 5));
        products.add(new HealthPotion("Health Potion", "Restores 50 HP", 10, 10, 50));
    }

    public List<Salable> listAllProducts() { return products; }

    public Salable getProductByName(String name) {
        for (Salable product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public void updateQuantity(Salable product, int newQuantity) {
        product.setQuantity(newQuantity);
    }
}
