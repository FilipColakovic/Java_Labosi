package hr.java.restaurant.exception;

/**
 * Custom exception class that represents invalid values in the restaurant application.
 * It extends {@link RuntimeException} to indicate a runtime exception when an invalid value is encountered.
 */
public class InvalidValueException extends RuntimeException {

    /**
     * Default constructor for {@link InvalidValueException}.
     */
    public InvalidValueException() {
        super();
    }

    /**
     * Constructor for {@link InvalidValueException} with a custom error message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidValueException(String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidValueException} with a custom error message and a cause.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the cause of the exception (a {@link Throwable} that caused this exception to be thrown)
     */
    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link InvalidValueException} with a cause.
     *
     * @param cause the cause of the exception (a {@link Throwable} that caused this exception to be thrown)
     */
    public InvalidValueException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for {@link InvalidValueException} with a custom error message, cause, suppression flag, and writable stack trace flag.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause the cause of the exception (a {@link Throwable} that caused this exception to be thrown)
     * @param enableSuppression whether or not suppression is enabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public InvalidValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
