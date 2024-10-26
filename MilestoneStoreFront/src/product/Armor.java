package product;

/**
 * Class representing an armor product.
 */
public class Armor extends Salable {
    private int defense; // The amount of defense this armor provides.

    /**
     * Constructor to create an Armor piece.
     * @param name The name of the armor.
     * @param description A short description of the armor.
     * @param price The price of the armor in gold.
     * @param quantity How many are available in stock.
     * @param defense The amount of defense this armor provides.
     */
    public Armor(String name, String description, int price, int quantity, int defense) {
        super(name, description, price, quantity);
        this.defense = defense;
    }

    public int getDefense() { return defense; }

    @Override
    public String toString() {
        return String.format(
            "Armor: %s | %s | %d gold | Defense: %d | Qty: %d",
            name, description, price, defense, quantity
        );
    }
}
