package product;

/**
 * Class representing a health potion product.
 */
public class HealthPotion extends Salable {
    private int healingAmount; // The amount of HP this potion restores.

    /**
     * Constructor to create a Health Potion.
     * @param name The name of the potion.
     * @param description A short description of the potion.
     * @param price The price of the potion in gold.
     * @param quantity How many are available in stock.
     * @param healingAmount The amount of health this potion restores.
     */
    public HealthPotion(String name, String description, int price, int quantity, int healingAmount) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() { return healingAmount; }

    @Override
    public String toString() {
        return String.format(
            "Health Potion: %s | %s | %d gold | Healing: %d HP | Qty: %d",
            name, description, price, healingAmount, quantity
        );
    }
}
