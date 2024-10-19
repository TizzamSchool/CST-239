package product;

/**
 * Concrete class representing a Health Potion product.
 */
public class HealthPotion extends Salable {

    /**
     * Constructor to create a Health Potion product.
     */
    public HealthPotion(String name, String description, int price, int quantity) {
        super(name, description, price, quantity);
    }

    @Override
    public String toString() {
        return String.format("Health Potion: %s | %s | %d gold | Qty: %d", 
                              name, description, price, quantity);
    }
}
