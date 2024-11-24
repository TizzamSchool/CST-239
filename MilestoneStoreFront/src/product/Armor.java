package product;

public class Armor extends Salable {
    private int defense;
    private String material;

    public Armor(String name, String description, double price, int quantity, int defense, String material) {
        super(name, description, price, quantity);
        this.defense = defense;
        this.material = material;
    }

    // Default constructor (needed for deserialization)
    public Armor() {
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Defense: %d, Material: %s]", defense, material);
    }
}
