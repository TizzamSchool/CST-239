package product;

/**
 * Class representing a weapon product.
 */
public class Weapon extends Salable {
    private int damage; // The damage this weapon deals.

    /**
     * Constructor to create a Weapon.
     * @param name The name of the weapon.
     * @param description A short description of the weapon.
     * @param price The price of the weapon in gold.
     * @param quantity How many are available in stock.
     * @param damage The amount of damage this weapon deals.
     */
    public Weapon(String name, String description, int price, int quantity, int damage) {
        super(name, description, price, quantity);
        this.damage = damage;
    }

    public int getDamage() { return damage; }

    @Override
    public String toString() {
        return String.format(
            "Weapon: %s | %s | %d gold | Damage: %d | Qty: %d",
            name, description, price, damage, quantity
        );
    }
}
