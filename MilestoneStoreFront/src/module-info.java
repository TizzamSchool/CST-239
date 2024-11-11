/**
 * 
 */
/**
 * 
 */
module MilestoneStoreFront {
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    opens product to com.fasterxml.jackson.databind;
}