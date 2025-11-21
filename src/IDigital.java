package src;

/**
 * The IDigital interface represents behavior for entities that use digital communication
 * methods (e.g., Email, Chat).
 * Any class that implements this interface must define how the message is transmitted digitally.
 */
public interface IDigital {

    /**
     * Returns a string describing the nature of the digital medium through which the message was sent.
     * Required implementation for classes using digital communication.
     * @return A String describing the digital communication method.
     */
    String printCommunicationMethod();
}