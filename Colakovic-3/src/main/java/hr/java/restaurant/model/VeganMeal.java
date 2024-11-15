package hr.java.restaurant.model;

import java.math.BigDecimal;

/**
 * Represents a vegan meal in the restaurant system, extending from {@link Meal} and implementing the {@link Vegan} interface.
 * This class contains information about the meal's calories and provides specific methods for vegan meal handling.
 */
public final class VeganMeal extends Meal implements Vegan {

    private int calories;

    /**
     * Constructs a new {@code VeganMeal}.
     *
     * @param id          the unique identifier for the meal
     * @param name        the name of the meal
     * @param category    the category of the meal
     * @param ingredients the ingredients used in the meal
     * @param price       the price of the meal
     * @param calories    the number of calories in the meal
     */
    public VeganMeal(long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, int calories) {
        super(id, name, category, ingredients, price);
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Checks if the vegan meal is considered low-calorie (less than 500 calories).
     *
     * @return {@code true} if the meal is low-calorie, {@code false} otherwise
     */
    @Override
    public boolean isLowCalorie() {
        return calories < 500;
    }

    /**
     * Provides a serving suggestion for the vegan meal.
     *
     * @return a string suggesting that the meal should be served cold
     */
    @Override
    public String servingSuggestion() {
        return "Servirajte hladno";
    }
}
