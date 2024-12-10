package hr.java.restaurant.generics;

import hr.java.restaurant.model.Restaurant;

import java.util.List;
/**
 * Represents a labor exchange office for restaurants, storing a list of restaurants and providing access to it.
 *
 * @param <T> The type of elements, constrained to {@link Restaurant}.
 */
public class RestaurantLabourExchangeOffice<T extends Restaurant> {
    private final List<T> restaurants;
    /**
     * Constructs a new {@code RestaurantLabourExchangeOffice} with a list of restaurants.
     *
     * @param restaurants The list of restaurants to manage.
     */
    public RestaurantLabourExchangeOffice(List<T> restaurants) {
        this.restaurants = restaurants;
    }

    public List<T> getRestaurant() {
        return restaurants;
    }

    public T findRestaurantWithMostEmployees() {
        return restaurants.stream()
                .max((r1, r2) -> Integer.compare(
                        r1.getAllEmployees().size(),
                        r2.getAllEmployees().size()
                ))
                .orElse(null);
    }
}
