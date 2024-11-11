package product;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class SalableSerializer extends StdSerializer<Salable> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SalableSerializer() {
        this(null);
    }

    public SalableSerializer(Class<Salable> t) {
        super(t);
    }

    @Override
    public void serialize(Salable value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        
        // Write the "type" field based on the subclass type
        if (value instanceof Weapon) {
            gen.writeStringField("type", "weapon");
        } else if (value instanceof Armor) {
            gen.writeStringField("type", "armor");
        } else if (value instanceof HealthPotion) {
            gen.writeStringField("type", "healthPotion");
        }

        // Write common fields from Salable
        gen.writeStringField("name", value.getName());
        gen.writeStringField("description", value.getDescription());
        gen.writeNumberField("price", value.getPrice());
        gen.writeNumberField("quantity", value.getQuantity());

        // Write subclass-specific fields
        if (value instanceof Weapon) {
            Weapon weapon = (Weapon) value;
            gen.writeNumberField("damage", weapon.getDamage());
            gen.writeStringField("weaponType", weapon.getWeaponType());
        } else if (value instanceof Armor) {
            Armor armor = (Armor) value;
            gen.writeNumberField("defense", armor.getDefense());
            gen.writeStringField("material", armor.getMaterial());
        } else if (value instanceof HealthPotion) {
            HealthPotion potion = (HealthPotion) value;
            gen.writeNumberField("healingAmount", potion.getHealingAmount());
            gen.writeStringField("potionType", potion.getPotionType());
        }

        gen.writeEndObject();
    }
}
