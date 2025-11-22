package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * The Main class serves as the entry point and controller for the Messaging System.
 * It manages an ArrayList of Message objects and provides a user menu for interaction.
 */
public class Main {

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    // Dynamic array  to hold all Message objects
    private static final ArrayList<Message> messageRepository = new ArrayList<>();

    // Utility Methods for Main Logic

    /**
     * Prints the user menu options to the console.
     */
    private static void printMenu() {
        System.out.println("\n--- Messaging System Menu ---");
        System.out.println("1. Add Message");
        System.out.println("2. Delete Message");
        System.out.println("3. Print All Messages");
        System.out.println("4. Count Messages containing specific words");
        System.out.println("5. Print Digital Messages Only");
        System.out.println("6. Print Message Previews (Bonus Option)");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Initializes the message repository with at least two objects of each message type.
     * All constructors are called with the correct parameter signature.
     */
    private static void initializeData() {
        System.out.println("Initializing system data...");

        // BoardMessage
        // Calling the FULL constructor (6 parameters, including new Date())
        messageRepository.add(new BoardMessage("Nati", "Important update regarding the new policy.",
                new Date(),true, PriorityType.URGENT, "HR"));
        // Calling the PARTIAL constructor (4 parameters + 2 inherited) - Priority is defaulted to REGULAR
        messageRepository.add(new BoardMessage("Team Lead", "Reminder: Meeting in Room 301 today.",
                false, "Development"));

        // EmailMessage
        ArrayList<File> emailFiles1 = new ArrayList<>();
        emailFiles1.add(new File("Report.pdf", "PDF"));
        emailFiles1.add(new File("Image.jpg", "JPG"));

        // Calling the FULL constructor (6 parameters, including new Date() and ArrayList)
        messageRepository.add(new EmailMessage("Maayan", "Please find the final report attached below.",
                new Date(), true, "Project Submission", emailFiles1));

        // Calling the PARTIAL constructor (4 parameters) - Date and Attachments are defaulted/auto-generated
        messageRepository.add(new EmailMessage("Support", "Your ticket has been updated.",
                false, "Ticket #205 Update"));

        // EncryptedMessage
        // Calling the FULL constructor (6 parameters, including new Date() and algorithm)
        messageRepository.add(new EncryptedMessage("Agent X", "Secret message content...",
                new Date(),  true, "key-1234", "AES-256"));

        // Calling the PARTIAL constructor (4 parameters) - Date and Algorithm are defaulted/auto-generated
        messageRepository.add(new EncryptedMessage("Security", "Test run of encrypted communication.",
                false, "dev-key"));

        System.out.println("Data initialization complete. Total messages: " + messageRepository.size());
    }

    /**
     * Prompts the user for details, creates a new Message object, and adds it to the repository.
     */
    private static void addNewMessage() {
        System.out.println("\n--- Add New Message ---");
        System.out.println("Select message type:");
        System.out.println("  1. Board Message");
        System.out.println("  2. Email Message");
        System.out.println("  3. Encrypted Message");
        System.out.print("Enter type choice: ");

        int typeChoice = -1;

        // Try-Catch for overall message creation logic and exception handling
        try {
            // Read type choice
            typeChoice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            System.out.print("Enter Sender Name: ");
            String sender = scanner.nextLine().trim();
            System.out.print("Enter Content: ");
            String content = scanner.nextLine().trim();
            System.out.print("Is High Importance (true/false)? ");
            boolean isHighImportance = Boolean.parseBoolean(scanner.nextLine().trim());

            Message newMessage = null;

            // Collect type-specific fields and create the object
            switch (typeChoice) {
                case 1:
                    // BoardMessage requires PriorityType and Department
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine().trim();
                    System.out.print("Enter Priority (URGENT, REGULAR, NOT_URGENT): ");
                    // Use valueOf to convert String input to the Enum type, throws IllegalArgumentException if invalid
                    PriorityType priority = PriorityType.valueOf(scanner.nextLine().toUpperCase().trim());

                    // Use the FULL constructor (new Date() will be generated in the partial constructor call within BoardMessage)
                    newMessage = new BoardMessage(sender, content, new Date(), isHighImportance, priority, department);
                    break;
                case 2:
                    // EmailMessage requires Subject
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine().trim();

                    // Use the PARTIAL constructor (attachments list is initialized empty)
                    newMessage = new EmailMessage(sender, content, isHighImportance, subject);
                    break;
                case 3:
                    // EncryptedMessage requires Encryption Key and Algorithm (optional default)
                    System.out.print("Enter Encryption Key: ");
                    String key = scanner.nextLine().trim();
                    System.out.print("Enter Algorithm (e.g., AES-256) or leave blank for default: ");
                    String algorithm = scanner.nextLine().trim();

                    // Choose constructor based on whether the algorithm was provided
                    if (algorithm.isEmpty()) {
                        // PARTIAL constructor (default algorithm)
                        newMessage = new EncryptedMessage(sender, content, isHighImportance, key);
                    } else {
                        // FULL constructor
                        newMessage = new EncryptedMessage(sender, content, new Date(), isHighImportance, key, algorithm);
                    }
                    break;
                default:
                    System.out.println("Invalid message type choice. Aborting.");
                    return;
            }

            // Final Add to Repository
            if (newMessage != null) {
                messageRepository.add(newMessage);
                System.out.println("Message added successfully! ID: " + newMessage.getMessageId());
            }

            // Catch validation errors (like null/empty strings or invalid Enum values)
        } catch (IllegalArgumentException e) {
            System.out.println("\n!!! ERROR: Invalid Data Input. " + e.getMessage() + " !!!");
            System.out.println("Message creation failed. Please check field requirements.");
        }
        // Catch general scanner errors if the user provided non-numeric input for the typeChoice
        catch (Exception e) {
            System.out.println("\n!!! CRITICAL ERROR: Input format error occurred. Aborting message creation. !!!");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }


    /**
     * Handles the 'Delete Message' menu option by attempting to read a message ID,
     * searching the repository, and removing the matching message.
     */
    private static void deleteMessage() {
        System.out.print("Enter the ID of the message to delete: ");
        int DeleteId = -1;

        // Try-Catch for Input Validation and Deletion Logic
        try {
            // Attempt to read an integer. If input is non-numeric, an exception is thrown.
            DeleteId = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character after nextInt()

            Message messageToRemove = null;

            // Search for the message by ID in the repository.
            for (Message msg : messageRepository) {
                if (msg.getMessageId() == DeleteId) {
                    messageToRemove = msg;
                    break;
                }
            }

            // Perform deletion if the message was found.
            if (messageToRemove != null) {
                messageRepository.remove(messageToRemove);
                System.out.println("Message ID " + DeleteId + " deleted successfully.");
            } else {
                System.out.println("Error: Message with ID " + DeleteId + " not found.");
            }

            // Catch any Exception
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a numerical ID.");

            // Cleanup the Scanner's input buffer to prevent infinite loop.
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }

    /**
     * Handles the 'Count Messages containing specific words' menu option (uses find() method).
     */
    private static void countMessagesByContent() {
        System.out.print("Enter comma-separated words to search (e.g., policy, update, report): ");
        String inputLine = scanner.nextLine();

        // Convert input string to ArrayList of words
        String[] wordsArray = inputLine.split(",");
        ArrayList<String> searchWords = new ArrayList<>();
        for (String word : wordsArray) {
            searchWords.add(word.trim());
        }

        int count = 0;
        for (Message msg : messageRepository) {
            // Uses the polymorphic find() method implemented in the Message base class
            if (msg.find(searchWords)) {
                count++;
            }
        }

        System.out.println("Total messages containing at least one of the words: " + count);
    }

    /**
     * Handles the 'Print Digital Messages Only' menu option.
     * Uses 'instanceof' and the IDigital interface for filtering.
     */
    private static void printDigitalMessages() {
        System.out.println("\n--- Digital Messages ---");
        int digitalCount = 0;
        for (Message msg : messageRepository) {
            // Check if the current message object implements the IDigital interface
            if (msg instanceof IDigital) {
                // Cast the object to the interface type to call the specific method
                IDigital digitalMsg = (IDigital) msg;
                System.out.println(msg.toString());
                System.out.println("  -> Communication Method: " + digitalMsg.printCommunicationMethod());
                digitalCount++;
            }
        }
        System.out.println("Total digital messages found: " + digitalCount);
    }

    /**
     * Handles the optional 'Print Message Previews' menu option (uses generatePreview() method).
     */
    private static void printPreviews() {
        System.out.println("\n--- Message Previews ---");
        for (Message msg : messageRepository) {
            // Uses the polymorphic generatePreview() method
            System.out.println("ID " + msg.getMessageId() + ": " + msg.generatePreview());
        }
    }


    // ^^^ Main Method ^^^

    public static void main(String[] args) {
        // Load initial messages into the repository.
        initializeData();

        int choice = -1;
        // Main application loop runs until the user chooses '0' (Exit).
        while (choice != 0) {
            printMenu();

            // Try-Catch for Input Validation and Menu Selection
            try {
                // Attempt to read an integer from the user.
                // If the input is non-numeric, a RuntimeException is thrown.
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character after reading the integer

                // Execute the action based on the user's choice.6
                switch (choice) {
                    case 1:
                        addNewMessage(); // Replaced demo message with full logic
                        break;
                    case 2:
                        deleteMessage();
                        break;
                    case 3:
                        System.out.println("\n--- All Messages in Repository ---");
                        // Prints the toString() method for every object in the repository.
                        messageRepository.forEach(System.out::println);
                        break;
                    case 4:
                        countMessagesByContent();
                        break;
                    case 5:
                        printDigitalMessages();
                        break;
                    case 6:
                        printPreviews();
                        break;
                    case 0:
                        System.out.println("Exiting the Messaging System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose an option between 0 and 6.");
                }

                // Catch any Exception thrown during input reading (e.g., if user enters text instead of a number).
            } catch (Exception e) {
                System.out.println("\n--- ERROR: Invalid choice. Please enter a number from the menu. ---");

                // Cleanup the Scanner's input buffer to allow the next loop iteration to start cleanly.
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                // Reset choice to an invalid value to ensure the loop continues running.
                choice = -1;
            }
        }
        // Close the Scanner
        scanner.close();
    }
}