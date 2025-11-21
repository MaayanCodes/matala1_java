package src;

import java.util.Date;

/**
 * The EncryptedMessage class represents a message sent with an encryption layer.
 * It inherits properties from Message and implements the IDigital interface for digital context.
 * It manages the encryption key and algorithm type.
 */
public class EncryptedMessage extends Message implements IDigital {

    // Fields
    // Required unique field: Key used for encryption/decryption.
    private String encryptionKey;
    // Additional chosen field: The type of encryption algorithm used.
    private String algorithm;

    // Private Utility Method for String Validation
    /**
     * Validates if a String value is not null or empty (after trimming spaces).
     * @param value The String value to check.
     * @param fieldName The name of the field (for the exception message).
     * @throws IllegalArgumentException if the value is null or contains only whitespace.
     */
    private void validateStringLocal(String value, String fieldName) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
    }


    // Full Constructor
    /**
     * Full constructor for EncryptedMessage. Calls the base Message constructor for common fields.
     * @param sender The sender's name.
     * @param encryptedContent The message content (which is already encrypted).
     * @param sendDate The message send date.
     * @param isHighImportance The high importance status (inherited from Message).
     * @param encryptionKey The key used to encrypt the content.
     * @param algorithm The encryption algorithm used (e.g., "AES-256").
     * @throws IllegalArgumentException if any mandatory field is invalid.
     */
    public EncryptedMessage(String sender, String encryptedContent, Date sendDate, boolean isHighImportance,
                            String encryptionKey, String algorithm) throws IllegalArgumentException {

        // Call to the base class (Message) full constructor
        super(sender, encryptedContent, sendDate, isHighImportance);

        // Validation for specific fields
        validateStringLocal(encryptionKey, "Encryption Key");
        validateStringLocal(algorithm, "Algorithm");

        // Initialization of specific fields
        this.encryptionKey = encryptionKey;
        this.algorithm = algorithm;
    }

    // Partial Constructor (Auto-Generated Date and Default Algorithm)
    /**
     * Partial constructor automatically sets the send date to the current date and
     * uses a default encryption algorithm ("None Specified").
     * It calls the full constructor using 'this' to prevent code duplication.
     * @param sender The sender's name.
     * @param encryptedContent The message content (encrypted).
     * @param isHighImportance The high importance status (inherited from Message).
     * @param encryptionKey The key used to encrypt the content.
     */
    public EncryptedMessage(String sender, String encryptedContent, boolean isHighImportance,
                            String encryptionKey) {
        // Calls the full constructor (this) setting default values:
        // new Date() for the current date, and "None Specified" for the algorithm.
        this(sender, encryptedContent, new Date(), isHighImportance, encryptionKey, "None Specified");
    }

    // Method that uses the Custom Exception
    /**
     * Illustrative method to simulate decryption of the content.
     * Throws DecryptionFailureException if the provided key does not match the stored key.
     * @param providedKey The key attempting to decrypt the message.
     * @return The decrypted content (simulated).
     * @throws DecryptionFailureException if the provided key is incorrect.
     */
    public String decryptContent(String providedKey) throws DecryptionFailureException {
        // Ror demonstration purposes
        if (!providedKey.equals(this.encryptionKey)) {
            // Throw the custom checked exception
            throw new DecryptionFailureException("Key mismatch. Decryption failed for algorithm: " + this.algorithm);
        }

        // Simulate returning the decrypted content
        return "DECRYPTED: " + super.content + " (Using " + this.algorithm + ")";
    }

    // Getters and Setters

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    // Setters for specific fields with validation
    public void setEncryptionKey(String encryptionKey) throws IllegalArgumentException {
        validateStringLocal(encryptionKey, "Encryption Key");
        this.encryptionKey = encryptionKey;
    }

    public void setAlgorithm(String algorithm) throws IllegalArgumentException {
        validateStringLocal(algorithm, "Algorithm");
        this.algorithm = algorithm;
    }


    // Method: toString
    /**
     * Overrides the inherited toString() method.
     * Returns a String representation including all base Message properties and EncryptedMessage specific fields.
     * Note: Does NOT print the full key for security reasons, only its length.
     * @return A String containing all EncryptedMessage properties.
     */
    @Override
    public String toString() {
        // Calls the base class toString() using 'super' keyword and appends specific data.
        return super.toString() +
                ", EncryptedMessage{" +
                "algorithm='" + algorithm + '\'' +
                ", key_length=" + encryptionKey.length() +
                '}';
    }

    // Method: generatePreview (Implementation of abstract method)
    /**
     * Implements the abstract generatePreview() method.
     * Returns a preview string indicating the message is encrypted, along with the sender and algorithm.
     * Format: [ENCRYPTED] From: Noa | Algorithm: AES-256
     * @return A shortened String preview of the message.
     */
    @Override
    public String generatePreview() {
        // Construct the final string in the required format.
        return "[ENCRYPTED] From: " + this.sender + " | Algorithm: " + this.algorithm;
    }

    // Implementation of IDigital Interface
    /**
     * Implements the printCommunicationMethod() from the IDigital interface.
     * Returns a predefined string describing the digital communication method.
     * @return The string "Sent securely over digital channel".
     */
    @Override
    public String printCommunicationMethod() {
        return "Sent securely over digital channel";
    }
}