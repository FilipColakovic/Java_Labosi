package hr.java.restaurant.model;

public sealed interface Meat permits MeatMeal{
    boolean hasSpices();

    String recommendedSideDish();
}
