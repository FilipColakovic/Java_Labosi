package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Meal extends Entitiy{
    private String name;
    private Category category;
    private Ingredient[] ingredients;
    private BigDecimal price;

    public Meal(long id, String name, Category category, Ingredient[] ingredients, BigDecimal price) {
        super(id);
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
