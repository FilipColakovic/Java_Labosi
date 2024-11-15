package hr.java.restaurant.model;

import java.math.BigDecimal;

public final class MeatMeal extends Meal implements Meat {

    private String meatType;

    public MeatMeal(long id, String name, Category category, Ingredient[] ingredients, BigDecimal price, String meatType) {
        super(id, name, category, ingredients, price);
        this.meatType = meatType;
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    @Override
    public boolean hasSpices() {
        return meatType.equalsIgnoreCase("piletina");
    }

    @Override
    public String recommendedSideDish() {
        return "Preporučujemo pire krompir kao prilog.";
    }
}
