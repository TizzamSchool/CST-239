package inventory;

import product.Salable;
import java.util.ArrayList;

/**
 * FileService handles reading and writing inventory data to a JSON file.
 */
public class FileService {
    public static ArrayList<Salable> readInventoryFromFile(String filePath) {
        // Read JSON and return ArrayList of Salable products
        return new ArrayList<>();
    }

    public static void writeInventoryToFile(String filePath, ArrayList<Salable> products) {
        // Write ArrayList of Salable products to JSON
    }
}
