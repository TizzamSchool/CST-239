package product;

public class HealthPotion extends Salable {
    private int healingAmount;
    private String potionType;

    public HealthPotion(String name, String description, double price, int quantity, int healingAmount, String potionType) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
        this.potionType = potionType;
    }

    // Default constructor (needed for deserialization)
    public HealthPotion() {
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public void setHealingAmount(int healingAmount) {
        this.healingAmount = healingAmount;
    }

    public String getPotionType() {
        return potionType;
    }

    public void setPotionType(String potionType) {
        this.potionType = potionType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Healing: %d, Type: %s]", healingAmount, potionType);
    }
}
