package product;

/**
 * Class representing a health potion product in the store.
 * Inherits from the Salable abstract class.
 */
public class HealthPotion extends Salable {
    private int healingAmount; // HP restored by the potion

    /**
     * Constructor to create a Health Potion.
     * 
     * @param name The name of the potion.
     * @param description A short description of the potion.
     * @param price The price of the potion in gold.
     * @param quantity The quantity of the potion in stock.
     * @param healingAmount The HP restored by the potion.
     */
    public HealthPotion(String name, String description, int price, int quantity, int healingAmount) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
    }

    /**
     * Returns the healing amount of the potion.
     * 
     * @return The healing amount of the potion.
     */
    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public String toString() {
        return String.format("Health Potion: %s | %s | %d gold | Healing: %d HP | Qty: %d",
                             name, description, price, healingAmount, quantity);
    }
}
