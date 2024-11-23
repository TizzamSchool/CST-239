package product;

/**
 * Class representing a Health Potion in the store.
 */
public class HealthPotion extends Salable {
    private int healingAmount;
    private String potionType;

    public HealthPotion(String name, String description, int price, int quantity, int healingAmount, String potionType) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
        this.potionType = potionType;
    }

    public int getHealingAmount() { return healingAmount; }
    public String getPotionType() { return potionType; }

    @Override
    public String toString() {
        return super.toString() + " | Healing: " + healingAmount + " | Type: " + potionType;
    }
}
