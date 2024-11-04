package product;

/**
 * Class representing an armor product in the store.
 * Inherits from the Salable abstract class.
 */
public class Armor extends Salable {
    private int defense; // Defense provided by the armor

    /**
     * Constructor to create an Armor.
     * 
     * @param name The name of the armor.
     * @param description A short description of the armor.
     * @param price The price of the armor in gold.
     * @param quantity The quantity of the armor in stock.
     * @param defense The defense provided by the armor.
     */
    public Armor(String name, String description, int price, int quantity, int defense) {
        super(name, description, price, quantity);
        this.defense = defense;
    }

    /**
     * Returns the defense value of the armor.
     * 
     * @return The defense of the armor.
     */
    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return String.format("Armor: %s | %s | %d gold | Defense: %d | Qty: %d",
                             name, description, price, defense, quantity);
    }
}
