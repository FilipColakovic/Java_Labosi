package hr.java.restaurant.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an order placed at a restaurant, containing meals, a deliverer,
 * and the delivery date and time. This class allows calculation of the total price of the order.
 */
public class Order extends Entitiy implements Serializable {

    private Restaurant restaurant;
    private List<Meal> meals;
    private Deliverer deliverer;
    private LocalDateTime deliveryDateAndTime;

    /**
     * Constructs a new {@code Order} with the given parameters.
     *
     * @param id the unique identifier for the order
     * @param restaurant the restaurant where the order is placed
     * @param meals the list of meals in the order
     * @param deliverer the deliverer assigned to the order
     * @param deliveryDateAndTime the date and time when the order is delivered
     */
    public Order(long id, Restaurant restaurant, List<Meal> meals, Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
        super(id);
        this.restaurant = restaurant;
        this.meals = meals;
        this.deliverer = deliverer;
        this.deliveryDateAndTime = deliveryDateAndTime;
    }
    /**
     * Calculates the total price of the order by summing the prices of all meals.
     *
     * @return the total price of the order
     */
    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (Meal meal : meals) {
            total = total.add(meal.getPrice());
        }
        return total;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    public LocalDateTime getDeliveryDateAndTime() {
        return deliveryDateAndTime;
    }

    public void setDeliveryDateAndTime(LocalDateTime deliveryDateAndTime) {
        this.deliveryDateAndTime = deliveryDateAndTime;
    }
}
