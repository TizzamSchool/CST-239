package product;

public class HealthPotion extends Salable {
    private int healingAmount;
    private String potionType;

    // No-argument constructor for Jackson deserialization
    public HealthPotion() {}

    public HealthPotion(String name, String description, int price, int quantity, int healingAmount, String potionType) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
        this.potionType = potionType;
    }

    public int getHealingAmount() { return healingAmount; }
    public String getPotionType() { return potionType; }

    @Override
    public String toString() {
        return String.format("Health Potion: %s | %s | %d gold | Healing: %d HP | Type: %s | Qty: %d",
                             name, description, price, healingAmount, potionType, quantity);
    }
}
