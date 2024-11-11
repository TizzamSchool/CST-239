package product;

public class Weapon extends Salable {
    private int damage;
    private String weaponType;

    // No-argument constructor for Jackson deserialization
    public Weapon() {}

    public Weapon(String name, String description, int price, int quantity, int damage, String weaponType) {
        super(name, description, price, quantity);
        this.damage = damage;
        this.weaponType = weaponType;
    }

    public int getDamage() { return damage; }
    public String getWeaponType() { return weaponType; }

    @Override
    public String toString() {
        return String.format("Weapon: %s | %s | %d gold | Damage: %d | Type: %s | Qty: %d",
                             name, description, price, damage, weaponType, quantity);
    }
}
