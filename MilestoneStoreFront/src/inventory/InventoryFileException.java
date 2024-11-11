package inventory;

/**
 * Custom exception class for handling file-related errors in InventoryManager.
 */
public class InventoryFileException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor for InventoryFileException.
     * 
     * @param message The error message for the exception.
     */
    public InventoryFileException(String message) {
        super(message);
    }
}
