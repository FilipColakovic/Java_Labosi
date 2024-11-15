package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class VeganMeal extends Meal implements Vegan{
    private int calories;

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

    @Override
    public boolean isLowCalorie(){
        return calories < 500;
    }

    @Override
    public String servingSuggestion(){
        return "Servirajte hladno";
    }
}
