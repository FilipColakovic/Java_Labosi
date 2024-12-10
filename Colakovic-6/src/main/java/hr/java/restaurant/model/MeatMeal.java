package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Represents a meat-based meal with a specific type of meat.
 * This class extends {@link Meal} and implements {@link Meat} to provide details
 * about the meat type and methods specific to meat-based meals.
 */
public final class MeatMeal extends Meal implements Meat {

    private String meatType;

    /**
     * Constructs a new {@code MeatMeal} with the given parameters.
     *
     * @param id the unique identifier for the meal
     * @param name the name of the meal
     * @param category the category to which the meal belongs
     * @param ingredients the list of ingredients in the meal
     * @param price the price of the meal
     * @param meatType the type of meat used in the meal
     */
    public MeatMeal(long id, String name, Category category, Set<Ingredient> ingredients, BigDecimal price, String meatType) {
        super(id, name, category, ingredients, price);
        this.meatType = meatType;
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    /**
     * Determines whether the meat contains spices.
     * If the meat is "piletina" (chicken), it contains spices.
     *
     * @return {@code true} if the meat contains spices, otherwise {@code false}
     */
    @Override
    public boolean hasSpices() {
        return meatType.equalsIgnoreCase("piletina");
    }

    /**
     * Recommends a side dish to pair with the meat.
     *
     * @return the recommended side dish for the meat meal, which is "pire krompir" (mashed potatoes)
     */
    @Override
    public String recommendedSideDish() {
        return "Preporučujemo pire krompir kao prilog.";
    }
}
