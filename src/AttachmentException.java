package src;

/**
 * Custom Exception class for handling errors related to file attachments.
 * It is typically thrown when an attempt is made to remove an attachment that does not exist.
 * This extends java.lang.Exception, making it a "checked" exception, requiring calling methods
 * (like removeAttachment) to explicitly declare or handle it.
 */
public class AttachmentException extends Exception { // Change from RuntimeException to Exception

    // Constructor
    /**
     * Constructor that receives a descriptive message about the exception.
     * @param message The detailed message explaining the reason for the exception.
     */
    public AttachmentException(String message) {
        // Calls the constructor of the base class (Exception) to initialize the exception message.
        super(message);
    }
}