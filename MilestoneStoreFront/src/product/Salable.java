package product;

/**
 * Abstract class representing a salable product in the store.
 */
public abstract class Salable {
    protected String name;
    protected String description;
    protected int price; // In gold pieces
    protected int quantity; // Available quantity

    /**
     * Constructor to initialize a Salable product.
     * @param name The product's name.
     * @param description A short description of the product.
     * @param price The product's price in gold.
     * @param quantity The available quantity of the product.
     */
    public Salable(String name, String description, int price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /** @return The product name. */
    public String getName() {
        return name;
    }

    /** @return The product description. */
    public String getDescription() {
        return description;
    }

    /** @return The product price in gold. */
    public int getPrice() {
        return price;
    }

    /** @return The quantity of the product available. */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of the product.
     * @param newQuantity The new quantity to set.
     */
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Abstract method to return a string representation of the product.
     * Subclasses must implement this method.
     * @return String representation of the product.
     */
    public abstract String toString();
}
