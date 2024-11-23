package product;

/**
 * Class representing a Weapon in the store.
 */
public class Weapon extends Salable {
    private int damage;
    private String weaponType;

    public Weapon(String name, String description, int price, int quantity, int damage, String weaponType) {
        super(name, description, price, quantity);
        this.damage = damage;
        this.weaponType = weaponType;
    }

    public int getDamage() { return damage; }
    public String getWeaponType() { return weaponType; }

    @Override
    public String toString() {
        return super.toString() + " | Damage: " + damage + " | Type: " + weaponType;
    }
}
