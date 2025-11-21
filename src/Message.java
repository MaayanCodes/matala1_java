package src;

import java.util.Date;
import java.util.ArrayList;

/**
 * Abstract base class for all message types (e.g., BoardMessage, EmailMessage).
 * It provides common fields and fundamental functionality shared across the system.
 */
public abstract class Message {

    // --- Static Fields ---
    // Counter used to assign a unique ID to each new message.
    private static int nextId = 1;

    // --- Protected Instance Fields ---
    protected String sender;
    protected String content;
    protected Date sendDate;
    protected int messageId;
    protected boolean isHighImportance;


    // --- Private Utility Method for Validation ---

    /**
     * Checks if a String is null or contains only whitespace.
     * Throws an exception if the value is invalid.
     * @param value The String to validate.
     * @param fieldName The name of the field for the error message.
     */
    private void validateString(String value, String fieldName) throws IllegalArgumentException {
        // Ensure the string isn't null or empty after trimming spaces.
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
    }

    // --- Constructor: Full Initialization ---

    /**
     * Initializes all core message fields after performing validation.
     * @param sender The sender's name.
     * @param content The message content.
     * @param sendDate The message send date.
     * @param isHighImportance The high importance status.
     */
    public Message(String sender, String content, Date sendDate, boolean isHighImportance) throws IllegalArgumentException {
        // Validate required String fields.
        validateString(sender, "Sender");
        validateString(content, "Content");

        // Ensure the Date object is not null.
        if (sendDate == null) {
            throw new IllegalArgumentException("Send Date cannot be null.");
        }

        this.sender = sender;
        this.content = content;
        this.sendDate = sendDate;
        this.isHighImportance = isHighImportance;

        // Assign the unique ID and prepare the counter for the next message.
        this.messageId = nextId++;
    }

    // --- Constructor: Auto-Generated Send Date ---

    /**
     * Initializes a new message, setting the send date to the current system time.
     * @param sender The sender's name.
     * @param content The message content.
     * @param isHighImportance The high importance status.
     */
    public Message(String sender, String content, boolean isHighImportance) {
        // Delegate to the main constructor, using a new Date() object for the current time.
        this(sender, content, new Date(), isHighImportance);
    }

            // --- Getters ---

    public int getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    // --- Override: toString() ---

    /**
     * Provides a readable String representation of the message object's state.
     */
    @Override
    public String toString() {
        return "Message{" +
                "id=" + messageId +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", sendDate=" + sendDate +
                ", isHighImportance=" + isHighImportance +
                '}';
    }

    // --- Method: find(ArrayList<String> words) ---

    /**
     * Checks if the message content contains any of the provided search words (case-insensitive).
     * @param words List of words to search for.
     * @return true if a match is found, otherwise false.
     */
    public boolean find(ArrayList<String> words) {
        if (words == null || words.isEmpty()) {
            return false;
        }

        // Convert content to lowercase once to improve loop efficiency.
        final String lowerContent = this.content.toLowerCase();

        // Iterate through the search words.
        for (String word : words) {
            // Check if content contains the current word (converted to lowercase for comparison).
            if (lowerContent.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // --- Abstract Method: generatePreview() ---

    /**
     * Forces subclasses to implement a method that generates a short, message-type-specific preview.
     */
    public abstract String generatePreview();
}