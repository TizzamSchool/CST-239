package product;

/**
 * Abstract class representing a salable product in the store.
 */
public abstract class Salable implements Comparable<Salable> {
    private String name;
    private String description;
    private int price;
    private int quantity;

    /**
     * Constructor for Salable.
     *
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param price       The price of the product.
     * @param quantity    The quantity of the product available.
     */
    public Salable(String name, String description, int price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Compares this Salable product to another by name (primary) and price (secondary).
     * 
     * @param other The other Salable product to compare to.
     * @return Comparison result for sorting.
     */
    @Override
    public int compareTo(Salable other) {
        int nameCompare = this.name.compareToIgnoreCase(other.name);
        return nameCompare != 0 ? nameCompare : Integer.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return name + " - " + description + " | Price: " + price + " | Quantity: " + quantity;
    }
}
