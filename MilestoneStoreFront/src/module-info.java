/**
 * 
 */
/**
 * 
 */
module MilestoneStoreFront {
	requires java.base; // Automatically included but can be listed explicitly
    requires com.fasterxml.jackson.databind; // Add this for Jackson Databind
    requires com.fasterxml.jackson.core; // Required by Jackson Databind
    requires com.fasterxml.jackson.annotation; // Required for Jackson annotations
    
    opens product to com.fasterxml.jackson.databind; // Allows Jackson to access product classes via reflection
}