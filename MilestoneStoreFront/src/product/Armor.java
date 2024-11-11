package product;

public class Armor extends Salable {
    private int defense;
    private String material;

    // No-argument constructor for Jackson deserialization
    public Armor() {}

    public Armor(String name, String description, int price, int quantity, int defense, String material) {
        super(name, description, price, quantity);
        this.defense = defense;
        this.material = material;
    }

    public int getDefense() { return defense; }
    public String getMaterial() { return material; }

    @Override
    public String toString() {
        return String.format("Armor: %s | %s | %d gold | Defense: %d | Material: %s | Qty: %d",
                             name, description, price, defense, material, quantity);
    }
}
