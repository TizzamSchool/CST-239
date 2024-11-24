package product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract base class for salable products.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Weapon.class, name = "product.Weapon"),
    @JsonSubTypes.Type(value = Armor.class, name = "product.Armor"),
    @JsonSubTypes.Type(value = HealthPotion.class, name = "product.HealthPotion")
})
public abstract class Salable implements Comparable<Salable> {
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Salable(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Default constructor (needed for deserialization)
    public Salable() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (Price: %.2f, Quantity: %d)", name, description, price, quantity);
    }

    /**
     * Compares this Salable product to another based on name (case-insensitive)
     * and then by price.
     */
    @Override
    public int compareTo(Salable other) {
        // Compare by name (case-insensitive)
        int nameComparison = this.name.compareToIgnoreCase(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        // If names are the same, compare by price
        return Double.compare(this.price, other.price);
    }
}
