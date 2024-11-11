package inventory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import product.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileService {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print JSON

        // Register custom serializer and deserializer for Salable
        SimpleModule module = new SimpleModule();
        module.addSerializer(Salable.class, new SalableSerializer());
        module.addDeserializer(Salable.class, new SalableDeserializer());
        mapper.registerModule(module);
    }

    /**
     * Reads inventory data from a JSON file and deserializes it into a list of Salable objects.
     * 
     * @param filePath The path of the JSON file to read from.
     * @return List of Salable products.
     * @throws InventoryFileException if there is an issue reading or parsing the file.
     */
    public static List<Salable> readInventoryFromFile(String filePath) throws InventoryFileException {
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<Salable>>() {});
        } catch (IOException e) {
            throw new InventoryFileException("Error reading inventory file: " + e.getMessage());
        }
    }

    /**
     * Writes the inventory data to a JSON file, serializing the list of Salable objects.
     * 
     * @param filePath The path of the JSON file to write to.
     * @param products List of Salable products to write to the file.
     * @throws InventoryFileException if there is an issue writing to the file.
     */
    public static void writeInventoryToFile(String filePath, List<Salable> products) throws InventoryFileException {
        try {
            mapper.writeValue(new File(filePath), products);
        } catch (IOException e) {
            throw new InventoryFileException("Error writing inventory file: " + e.getMessage());
        }
    }
}
