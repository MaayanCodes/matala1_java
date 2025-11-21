package src;

/**
 * The File class represents an attachment that can be sent with messages (like EmailMessage).
 * It enforces that the file name and type are not empty.
 */
public class File {

    // Fields
    private String fileName;
    private String fileType;

    // Private Utility Method for Validation

    /**
     * Validates if a String value is not null or empty (after trimming spaces).
     * Throws an IllegalArgumentException if the validation fails, as required.
     * @param value The String value to check.
     * @param fieldName The name of the field (for the exception message).
     * @throws IllegalArgumentException if the value is null or contains only whitespace.
     */
    private void validateString(String value, String fieldName) throws IllegalArgumentException {
        // Checks if the string is null or empty using trim() to handle whitespace.
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
    }

    // Full Constructor

    /**
     * Constructor initializes all fields and performs validation for non-null/empty values.
     * Fields are initialized only if the input is valid.
     * @param fileName The name of the file.
     * @param fileType The type/extension of the file (e.g., "pdf", "jpg").
     * @throws IllegalArgumentException if fileName or fileType is invalid.
     */
    public File(String fileName, String fileType) throws IllegalArgumentException {
        // Validation for required fields.
        validateString(fileName, "File Name");
        validateString(fileType, "File Type");

        // Initialization after validation.
        this.fileName = fileName;
        this.fileType = fileType;
    }

    // Getters and Setters

    // Getters are needed as the fields are private.
    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    // Setters are provided to allow modification after creation, with validation.
    public void setFileName(String fileName) throws IllegalArgumentException {
        validateString(fileName, "File Name");
        this.fileName = fileName;
    }

    public void setFileType(String fileType) throws IllegalArgumentException {
        validateString(fileType, "File Type");
        this.fileType = fileType;
    }

    // toString

    /**
     * Overrides the inherited toString() method from Object.
     * Returns a String representation of the object's data (file name and type).
     * @return A String containing all File properties.
     */
    @Override
    public String toString() {
        return "File{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}