package src;

/**
 * Custom Exception class for handling errors that occur during the decryption process.
 * This extends java.lang.Exception, making it a "checked" exception, requiring calling methods
 * (like decryptContent) to explicitly declare or handle it.
 */
public class DecryptionFailureException extends Exception {
    /**
     * Constructor that receives a descriptive message about the exception.
     * @param message The detailed message explaining the reason for the decryption failure.
     */
    public DecryptionFailureException(String message) {
        // Calls the constructor of the base class (Exception) to initialize the exception message.
        super(message);
    }
}