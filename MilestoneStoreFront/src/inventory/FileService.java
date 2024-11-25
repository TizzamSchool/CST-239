package inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import product.Salable;

import java.io.File;
import java.util.ArrayList;

/**
 * Service class for reading and writing inventory data to and from a JSON file.
 */
public class FileService {

    /**
     * Writes the inventory to a JSON file.
     *
     * @param fileName  The name of the file to write to.
     * @param inventory The inventory list to be saved.
     */
    public static void writeInventoryToFile(String fileName, ArrayList<Salable> inventory) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Enable default typing to preserve subclass information
            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            mapper.writeValue(new File(fileName), inventory);
            System.out.println("FileService: Inventory successfully written to " + fileName);
        } catch (Exception e) {
            System.out.println("FileService: Error writing inventory file: " + e.getMessage());
        }
    }

    /**
     * Reads the inventory from a JSON file.
     *
     * @param fileName The name of the file to read from.
     * @return The inventory list, or null if the file could not be read or does not exist.
     */
    public static ArrayList<Salable> readInventoryFromFile(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Enable default typing to handle subclass information during deserialization
            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println("FileService: Reading inventory from " + fileName);
                return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Salable.class));
            } else {
                System.out.println("FileService: File " + fileName + " does not exist.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("FileService: Error reading inventory file: " + e.getMessage());
            return null;
        }
    }
}
