package product;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class SalableDeserializer extends StdDeserializer<Salable> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SalableDeserializer() {
        this(null);
    }

    public SalableDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Salable deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        
        String name = node.get("name").asText();
        String description = node.get("description").asText();
        int price = node.get("price").asInt();
        int quantity = node.get("quantity").asInt();

        switch (type) {
            case "weapon":
                int damage = node.get("damage").asInt();
                String weaponType = node.get("weaponType").asText();
                return new Weapon(name, description, price, quantity, damage, weaponType);
            case "armor":
                int defense = node.get("defense").asInt();
                String material = node.get("material").asText();
                return new Armor(name, description, price, quantity, defense, material);
            case "healthPotion":
                int healingAmount = node.get("healingAmount").asInt();
                String potionType = node.get("potionType").asText();
                return new HealthPotion(name, description, price, quantity, healingAmount, potionType);
            default:
                throw new IOException("Unknown type: " + type);
        }
    }
}
