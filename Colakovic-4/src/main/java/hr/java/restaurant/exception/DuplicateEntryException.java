package hr.java.restaurant.exception;

/**
 * Custom exception class that represents duplicate entry errors in the restaurant application.
 * It extends {@link Exception} to indicate a checked exception, used when an attempt is made to add an entity
 * (e.g., person, meal, restaurant) that already exists.
 */
public class DuplicateEntryException extends Exception {

    /**
     * Default constructor for {@link DuplicateEntryException}.
     */
    public DuplicateEntryException() {
        super();
    }

    /**
     * Constructor for {@link DuplicateEntryException} with a custom error message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DuplicateEntryException(String message) {
        super(message);
    }

    /**
     * Constructor for {@link DuplicateEntryException} with a custom error message and a cause.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the cause of the exception (a {@link Throwable} that caused this exception to be thrown)
     */
    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link DuplicateEntryException} with a cause.
     *
     * @param cause the cause of the exception (a {@link Throwable} that caused this exception to be thrown)
     */
    public DuplicateEntryException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for {@link DuplicateEntryException} with a custom error message, cause, suppression flag, and writable stack trace flag.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the cause of the exception (a {@link Throwable} that caused this exception to be thrown)
     * @param enableSuppression whether suppression is enabled
     * @param writableStackTrace whether the stack trace should be writable
     */
    public DuplicateEntryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
