package src;

import java.util.Date;
import java.util.ArrayList;

/**
 * The EmailMessage class represents an email, which is a type of message sent digitally.
 * It inherits properties from Message and implements the IDigital interface.
 * It manages a dynamic array (ArrayList) of File attachments.
 */
public class EmailMessage extends Message implements IDigital {

    // Fields ---
    private String subject;
    // Dynamic array (ArrayList) to hold the attachments (File objects).
    private ArrayList<File> attachments;

    // Private Utility Method for String Validation
    /**
     * Validates if a String value (subject) is not null or empty (after trimming spaces).
     * @param value The String value to check.
     * @param fieldName The name of the field (for the exception message).
     * @throws IllegalArgumentException if the value is null or contains only whitespace.
     */
    private void validateStringLocal(String value, String fieldName) throws IllegalArgumentException {
        // Validation for the subject field as required.
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
    }

    // Full Constructor
    /**
     * Full constructor for EmailMessage. Calls the base Message constructor for common fields.
     * @param sender The sender's name.
     * @param content The message content.
     * @param sendDate The message send date.
     * @param isHighImportance The high importance status (inherited from Message).
     * @param subject The subject of the email.
     * @param attachments Dynamic array of files attached to the email.
     * @throws IllegalArgumentException if any mandatory field is invalid.
     */
    public EmailMessage(String sender, String content, Date sendDate, boolean isHighImportance,
                        String subject, ArrayList<File> attachments) throws IllegalArgumentException {

        // Call to the base class (Message) full constructor
        super(sender, content, sendDate, isHighImportance);

        // Validation for the specific subject field
        validateStringLocal(subject, "Subject");

        // Initialization of specific fields
        this.subject = subject;
        // Check if the attachments is empty and if so then create a new list
        if (attachments == null) {
            // If null is passed, initialize an empty ArrayList.
            this.attachments = new ArrayList<File>();
        }
        else {
            // Otherwise, use the list received as a parameter.
            this.attachments = attachments;
        }
    }

    // Partial Constructor (Auto-Generated Date and No Attachments)

    /**
     * Partial constructor automatically sets the send date to the current date and
     * initializes an empty ArrayList for attachments (default behavior).
     * It calls the full constructor using the 'this' keyword to prevent code duplication.
     * @param sender The sender's name.
     * @param content The message content.
     * @param isHighImportance The high importance status (inherited from Message).
     * @param subject The subject of the email.
     */
    public EmailMessage(String sender, String content, boolean isHighImportance, String subject) {
        // Calls the full constructor (this) setting default values:
        this(sender, content, new Date(), isHighImportance, subject, null);
    }

    // Getters and Setters

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) throws IllegalArgumentException {
        validateStringLocal(subject, "Subject");
        this.subject = subject;
    }

    public ArrayList<File> getAttachments() {
        // Returns the reference to the list of attachments.
        return attachments;
    }

    // Method: addAttachment

    /**
     * Adds a File object to the dynamic array (ArrayList) of attachments.
     * @param f The File object to be added.
     */
    public void addAttachment(File f) {
        if (f != null) {
            this.attachments.add(f);
        }
    }

    // Method: removeAttachment

    /**
     * Removes ALL occurrences of the given File object from the attachments list.
     * Throws AttachmentException if the file is not found in the list.
     * @param f The File object to be removed.
     * @throws AttachmentException if the file is not found in the list.
     */
    public void removeAttachment(File f) throws AttachmentException {
        if (f == null) return;

        // Check if the file exists BEFORE attempting removal.
        boolean fileExists = this.attachments.contains(f);

        // Throws the custom checked exception if the file is not found.
        if (!fileExists) {
            throw new AttachmentException("Cannot remove file: " + f.getFileName() + ". It does not exist in the attachments list.");
        }


        // A while loop to remove all the items in the array
        while (this.attachments.remove(f)) { }
            // Loop body is empty. The removal happens within the while condition itself.
    }

    // toString

    /**
     * Overrides the inherited toString() method.
     * Returns a String representation including all base Message properties and EmailMessage specific fields.
     * @return A String containing all EmailMessage properties.
     */
    @Override
    public String toString() {
        // Calls the base class toString() using super and appends specific data.
        return super.toString() +
                ", EmailMessage{" +
                "subject='" + subject + '\'' +
                ", attachments count=" + attachments.size() +
                ", attachments=" + attachments +
                '}';
    }

    // Implementation of IDigital Interface
    /**
     * Implements the printCommunicationMethod() from the IDigital interface.
     * Returns a predefined string describing the digital communication method.
     * @return The string "Sent via Email Server" as required.
     */
    @Override
    public String printCommunicationMethod() {
        return "Sent via Email";
    }

    // Method: generatePreview (Implementation of abstract method)
    /**
     * Implements the abstract generatePreview() method.
     * Returns a preview string containing the subject and the sender's name.
     * Format: [Email] Subject: Project Submission | From: Noa
     * @return A shortened String preview of the email message.
     */
    @Override
    public String generatePreview() {
        // Construct the final string in the required format.
        return "[Email] Subject: " + this.subject + " | From: " + this.sender;
    }
}