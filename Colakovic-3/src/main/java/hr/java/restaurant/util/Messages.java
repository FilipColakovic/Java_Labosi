package hr.java.restaurant.util;

/**
 * This class contains constant string messages used for input validation errors in the restaurant application.
 * These messages are displayed to the user when invalid input is detected (such as non-numeric or negative values).
 */
public class Messages {

    /**
     * Error message displayed when the user enters a non-numeric value or a negative value for a BigDecimal.
     */
    public static final String INVALID_BIGDECIMAL_INPUT_AND_NEGATIVE_ERROR = "Unijeli ste vrijednost koja nije numerička ili je negativna!";

    /**
     * Error message displayed when the user enters a non-numeric value or a negative value for an integer.
     */
    public static final String INVALID_INT_INPUT_AND_NEGATIVE_ERROR = "Unijeli ste vrijednost koja nije numerička ili je negativna!";

}
