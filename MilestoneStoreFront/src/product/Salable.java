package product;

/**
 * Abstract class representing a salable product in the store.
 * Implements the Comparable interface to enable sorting by name (case-insensitive)
 * and then by price in ascending order.
 */
public abstract class Salable implements Comparable<Salable> {
    protected String name;
    protected String description;
    protected int price; // Price in gold pieces
    protected int quantity; // Available quantity in stock

    /**
     * Constructor to initialize a Salable product.
     * 
     * @param name The name of the product.
     * @param description A brief description of the product.
     * @param price The price of the product in gold pieces.
     * @param quantity The available quantity of the product in stock.
     */
    public Salable(String name, String description, int price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the name of the product.
     * 
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the product.
     * 
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of the product in gold pieces.
     * 
     * @return The price of the product.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the quantity of the product available in stock.
     * 
     * @return The available quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of the product in stock.
     * 
     * @param newQuantity The new quantity to set.
     */
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Compares this product with another product for sorting.
     * Primary comparison is by name (case-insensitive alphabetical order).
     * Secondary comparison is by price (ascending).
     * 
     * @param other The other Salable product to compare to.
     * @return A negative integer, zero, or a positive integer as this product
     *         is less than, equal to, or greater than the specified product.
     */
    @Override
    public int compareTo(Salable other) {
        int nameComparison = this.name.compareToIgnoreCase(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return Integer.compare(this.price, other.price);
    }

    /**
     * Abstract method to return a string representation of the product.
     * Subclasses must implement this method.
     * 
     * @return A string representing the product details.
     */
    public abstract String toString();
}
