package product;

/**
 * Class representing a weapon product in the store.
 * Inherits from the Salable abstract class.
 */
public class Weapon extends Salable {
    private int damage; // Damage dealt by the weapon

    /**
     * Constructor to create a Weapon.
     * 
     * @param name The name of the weapon.
     * @param description A short description of the weapon.
     * @param price The price of the weapon in gold.
     * @param quantity The quantity of the weapon in stock.
     * @param damage The damage dealt by the weapon.
     */
    public Weapon(String name, String description, int price, int quantity, int damage) {
        super(name, description, price, quantity);
        this.damage = damage;
    }

    /**
     * Returns the damage value of the weapon.
     * 
     * @return The damage of the weapon.
     */
    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return String.format("Weapon: %s | %s | %d gold | Damage: %d | Qty: %d",
                             name, description, price, damage, quantity);
    }
}
