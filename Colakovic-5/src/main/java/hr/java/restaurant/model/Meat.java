package hr.java.restaurant.model;

/**
 * Represents a general meat type with methods to check for spices
 * and suggest a recommended side dish.
 * <p>
 * This interface is sealed and can only be implemented by the {@link MeatMeal} class.
 * </p>
 */
public sealed interface Meat permits MeatMeal {

    /**
     * Checks if the meat contains spices.
     *
     * @return true if the meat contains spices, false otherwise
     */
    boolean hasSpices();

    /**
     * Provides a recommended side dish to pair with the meat.
     *
     * @return the name of the recommended side dish
     */
    String recommendedSideDish();
}
