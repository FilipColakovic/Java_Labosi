package hr.java.restaurant.model;

import java.math.BigDecimal;

/**
 * Represents an ingredient in a meal with its name, category, calorie content, and preparation method.
 */
public class Ingredient extends Entitiy {

    private String name;
    private Category category;
    private BigDecimal kcal;
    private String preparationMethod;

    /**
     * Constructs an Ingredient object with the specified attributes.
     *
     * @param id the unique identifier of the ingredient
     * @param name the name of the ingredient
     * @param category the category the ingredient belongs to
     * @param kcal the calorie content of the ingredient
     * @param preparationMethod the method of preparation for the ingredient
     */
    public Ingredient(long id, String name, Category category, BigDecimal kcal, String preparationMethod) {
        super(id);
        this.name = name;
        this.category = category;
        this.kcal = kcal;
        this.preparationMethod = preparationMethod;
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

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }
}
