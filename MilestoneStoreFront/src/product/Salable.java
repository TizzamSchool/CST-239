package product;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
  use = Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "type"
)
@JsonSubTypes({
  @Type(value = Weapon.class, name = "weapon"),
  @Type(value = Armor.class, name = "armor"),
  @Type(value = HealthPotion.class, name = "healthPotion")
})
public abstract class Salable implements Comparable<Salable> {
    protected String name;
    protected String description;
    protected int price;
    protected int quantity;

    // Protected no-argument constructor for Jackson deserialization
    protected Salable() {}

    public Salable(String name, String description, int price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int newQuantity) { this.quantity = newQuantity; }

    @Override
    public int compareTo(Salable other) {
        int nameComparison = this.name.compareToIgnoreCase(other.name);
        return nameComparison != 0 ? nameComparison : Integer.compare(this.price, other.price);
    }

    public abstract String toString();
}
