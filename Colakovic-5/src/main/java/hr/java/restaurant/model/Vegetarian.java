package hr.java.restaurant.model;

/**
 * A sealed interface representing vegetarian meals in the restaurant system.
 * This interface provides methods for handling vegetarian meal-specific features,
 * including allergy information and drink recommendations.
 */
public sealed interface Vegetarian permits VegetarianMeal {

    /**
     * Checks if the vegetarian meal contains ingredients that may trigger a nut allergy.
     *
     * @return {@code true} if the meal contains ingredients that may trigger a nut allergy, {@code false} otherwise
     */
    boolean nutAllergy();

    /**
     * Provides a recommendation for a drink that pairs well with the vegetarian meal.
     *
     * @return a string recommending a drink for the meal
     */
    String recommendedDrink();
}
