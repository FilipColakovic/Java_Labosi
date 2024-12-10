package hr.java.restaurant.sort;
import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MealRestaurantComparator implements Comparator<Meal> {

    private final Map<Meal, List<Restaurant>> mealRestaurantMap;

    public MealRestaurantComparator(Map<Meal, List<Restaurant>> mealRestaurantMap) {
        this.mealRestaurantMap = mealRestaurantMap;
    }

    @Override
    public int compare(Meal meal1, Meal meal2) {
        List<Restaurant> restaurants1 = mealRestaurantMap.getOrDefault(meal1, Collections.emptyList());
        List<Restaurant> restaurants2 = mealRestaurantMap.getOrDefault(meal2, Collections.emptyList());

        int count1 = restaurants1.size();
        int count2 = restaurants2.size();

        return Integer.compare(count2, count1);
    }



}
