package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends Entitiy{
    private Restaurant restaurant;
    private Meal meals[];
    private Deliverer deliverer;
    private LocalDateTime deliveryDateAndTime;

    public Order(long id, Restaurant restaurant, Meal[] meals, Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
        super(id);
        this.restaurant = restaurant;
        this.meals = meals;
        this.deliverer = deliverer;
        this.deliveryDateAndTime = deliveryDateAndTime;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < meals.length; i++) {
            total = total.add(meals[i].getPrice());
        }
        return total;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public void setMeals(Meal[] meals) {
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
