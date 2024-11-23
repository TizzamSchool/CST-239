package product;

/**
 * Class representing Armor in the store.
 */
public class Armor extends Salable {
    private int defense;
    private String material;

    public Armor(String name, String description, int price, int quantity, int defense, String material) {
        super(name, description, price, quantity);
        this.defense = defense;
        this.material = material;
    }

    public int getDefense() { return defense; }
    public String getMaterial() { return material; }

    @Override
    public String toString() {
        return super.toString() + " | Defense: " + defense + " | Material: " + material;
    }
}
