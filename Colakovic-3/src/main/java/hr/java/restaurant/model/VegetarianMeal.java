package hr.java.restaurant.model;

import java.math.BigDecimal;

/**
 * Represents a vegetarian meal in the restaurant system.
 * This class extends {@link Meal} and implements the {@link Vegetarian} interface,
 * providing information about whether the meal contains nuts and drink recommendations.
 */
public final class VegetarianMeal extends Meal implements Vegetarian {

    private boolean containsNuts;

    /**
     * Constructs a new {@code VegetarianMeal} with the specified details.
     *
     * @param id          the unique identifier for the meal
     * @param name        the name of the meal
     * @param category    the category to which the meal belongs
     * @param ingredients the ingredients used in the meal
     * @param price       the price of the meal
     * @param containsNuts {@code true} if the meal contains nuts, {@code false} otherwise
     */
    public VegetarianMeal(long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, boolean containsNuts) {
        super(id, name, category, ingredients, price);
        this.containsNuts = containsNuts;
    }

    public boolean isContainsNuts() {
        return containsNuts;
    }

    public void setContainsNuts(boolean containsNuts) {
        this.containsNuts = containsNuts;
    }

    /**
     * Checks if the vegetarian meal is safe for individuals with nut allergies.
     *
     * @return {@code true} if the meal does not contain nuts, {@code false} otherwise
     */
    @Override
    public boolean nutAllergy() {
        return !containsNuts;
    }

    /**
     * Provides a drink recommendation for the vegetarian meal.
     *
     * @return a string recommending a drink for the meal (e.g., white wine)
     */
    @Override
    public String recommendedDrink() {
        return "Preporučujemo belo vino uz ovo jelo.";
    }
}
