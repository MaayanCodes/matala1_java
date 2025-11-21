package src;

import java.util.Date;

/**
 * The BoardMessage class represents a message displayed on a bulletin board.
 * It inherits common properties from Message and adds specific properties like priority
 * and the department the message is addressed to (the additional chosen field).
 */
public class BoardMessage extends Message {

    // Fields
    private PriorityType priority;
    private String department;

    // Private Utility Method for String Validation
    /**
     * Reuses the validation logic for String fields.
     * Note: This method is functionally equivalent to the validation in Message, but is placed here
     * to handle the specific validation of the 'department' field directly without creating a redundant method.
     * @param value The String value to check.
     * @param fieldName The name of the field (for the exception message).
     * @throws IllegalArgumentException if the value is null or contains only whitespace.
     */
    private void validateStringLocal(String value, String fieldName) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty for a Board Message.");
        }
    }


    // Full Constructor
    /**
     * Full constructor for BoardMessage. Calls the base Message constructor for common fields.
     * @param sender The sender's name.
     * @param content The message content.
     * @param sendDate The message send date.
     * @param isHighImportance The high importance status (inherited from Message).
     * @param priority The priority level (PriorityType).
     * @param department The department the message belongs to.
     * @throws IllegalArgumentException if any mandatory field is invalid.
     */
    public BoardMessage(String sender, String content, Date sendDate, boolean isHighImportance,
                        PriorityType priority, String department) throws IllegalArgumentException {

        // Call to the base class (Message) full constructor.
        super(sender, content, sendDate, isHighImportance);

        // Validation for specific fields
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null.");
        }
        // Use the local utility method to validate the department field.
        validateStringLocal(department, "Department");

        // Initialization of specific fields
        this.priority = priority;
        this.department = department;
    }

    // Partial Constructor (Auto-Generated Date and Default Priority)
    /**
     * Partial constructor automatically sets the send date to the current date and
     * the priority to REGULAR (default value, as required).
     * It calls the full constructor using the 'this' keyword to prevent code duplication.
     * @param sender The sender's name.
     * @param content The message content.
     * @param isHighImportance The high importance status (inherited from Message).
     * @param department The department the message belongs to.
     */
    public BoardMessage(String sender, String content, boolean isHighImportance, String department) {
        // Calls the full constructor (this) setting default values:
        // PriorityType.REGULAR as the default priority.
        this(sender, content, new Date(), isHighImportance, PriorityType.REGULAR, department);
    }

    // Getters and Setters
    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null.");
        }
        this.priority = priority;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        // Validation for modification
        validateStringLocal(department, "Department");
        this.department = department;
    }


    // toString
    /**
     * Overrides the inherited toString() method.
     * Returns a String representation including all base Message properties and BoardMessage specific fields.
     * @return A String containing all BoardMessage properties.
     */
    @Override
    public String toString() {
        // Calls the base class toString() using 'super' keyword and appends specific data.
        return super.toString() +
                ", BoardMessage{" +
                "priority=" + priority +
                ", department='" + department + '\'' +
                '}';
    }

    // Method: generatePreview ( a must from the abstract class).
    /**
     * Implements the abstract generatePreview() method.
     * Returns a preview string containing the sender's name and the first 15 characters of the content.
     * Format: [Board] SenderName: First 15 chars....
     * @return A shortened String preview of the message.
     */
    @Override
    public String generatePreview() {
        // Determine the length for the preview: the minimum between 15 and the actual content length.
        int previewLength = Math.min(15, this.content.length());

        // Use substring to get the first 15 characters, or less if the content is short.
        String previewContent = this.content.substring(0, previewLength);

        // End of the short message
        if (this.content.length() > 15) {
            previewContent += "...";
        }

        // Construct the final string in the required format.
        return "[Board] " + this.sender + ": " + previewContent;
    }

    // Additional Method - DisplayColor
    /**
     * Returns a recommended display color for the message based on its priority.
     * @return A String with the recommended color.
     */
    public String getDisplayColor() {
        switch (this.priority) {
            case URGENT:
                return "Red";
            case REGULAR:
                return "Blue";
            case NOT_URGENT:
                return "Green";
            default:
                return "Black";
        }
    }
}