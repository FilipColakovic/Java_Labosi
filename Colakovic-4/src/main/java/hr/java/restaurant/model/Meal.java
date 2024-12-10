package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a meal with its name, category, ingredients, and price.
 */
public class Meal extends Entitiy {
    private String name;
    private Category category;
    private Set<Ingredient> ingredients;
    private BigDecimal price;

    /**
     * Constructs a Meal object with the specified attributes.
     *
     * @param id the unique identifier of the meal
     * @param name the name of the meal
     * @param category the category the meal belongs to
     * @param ingredients the list of ingredients that make up the meal
     * @param price the price of the meal
     */
    public Meal(long id, String name, Category category, Set<Ingredient> ingredients, BigDecimal price) {
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(name, meal.name) && Objects.equals(category, meal.category) && Objects.equals(ingredients, meal.ingredients) && Objects.equals(price, meal.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, ingredients, price);
    }
}
