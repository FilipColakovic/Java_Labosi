package hr.java.restaurant.generics;

import hr.java.restaurant.model.Ingredient;
import hr.java.restaurant.model.Meal;

public class MealIngredientPair<T extends Meal, S extends Ingredient> {
    private final T meal;
    private final S ingredient;

    public MealIngredientPair(T meal, S ingredient) {
        this.meal = meal;
        this.ingredient = ingredient;
    }

    public T getMeal() {
        return meal;
    }

    public S getIngredient() {
        return ingredient;
    }
}
