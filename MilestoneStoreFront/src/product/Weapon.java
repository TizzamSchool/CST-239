package product;

public class Weapon extends Salable {
    private int damage;
    private String weaponType;

    public Weapon(String name, String description, double price, int quantity, int damage, String weaponType) {
        super(name, description, price, quantity);
        this.damage = damage;
        this.weaponType = weaponType;
    }

    // Default constructor (needed for deserialization)
    public Weapon() {
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Damage: %d, Type: %s]", damage, weaponType);
    }
}
