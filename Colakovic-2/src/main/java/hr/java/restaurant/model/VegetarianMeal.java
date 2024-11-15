package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class VegetarianMeal extends Meal implements Vegetarian{
    private boolean containsNuts;

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

    @Override
    public boolean nutAllergy() {
        return !containsNuts;
    }

    @Override
    public String recommendedDrink() {
        return "Preporučujemo belo vino uz ovo jelo.";
    }
}
