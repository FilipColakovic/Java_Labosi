package hr.java.restaurant.model;

/**
 * Interface representing a vegan meal with methods to determine if it is low-calorie
 * and provide a serving suggestion. Only {@code VeganMeal} is permitted to implement this interface.
 */
public sealed interface Vegan permits VeganMeal {

    /**
     * Checks if the vegan meal is low-calorie.
     *
     * @return {@code true} if the meal is low-calorie, {@code false} otherwise
     */
    boolean isLowCalorie();

    /**
     * Provides a serving suggestion for the vegan meal.
     *
     * @return a string with the serving suggestion for the meal
     */
    String servingSuggestion();
}
