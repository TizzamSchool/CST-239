package product;

/**
 * Abstract class representing a Salable product in the store.
 */
public abstract class Salable {
    protected String name;
    protected String description;
    protected int price; // In gold pieces
    protected int quantity;

    /**
     * Constructor to initialize a Salable product.
     */
    public Salable(String name, String description, int price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /** @return the product name. */
    public String getName() {
        return name;
    }

    /** @return the product description. */
    public String getDescription() {
        return description;
    }

    /** @return the product price in gold pieces. */
    public int getPrice() {
        return price;
    }

    /** @return the available quantity of the product. */
    public int getQuantity() {
        return quantity;
    }

    /** Updates the quantity of the product. */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** Abstract method to be implemented by concrete classes. */
    public abstract String toString();
}

