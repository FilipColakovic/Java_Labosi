package hr.java.restaurant.model;

import java.util.Set;

/**
 * Represents a restaurant with various employees (chefs, waiters, deliverers),
 * meals offered, and an address.
 */
public class Restaurant extends Entitiy {

    private String name;
    private Address address;
    private Set<Meal> meals;
    private Set<Chef> chefs;
    private Set<Waiter> waiters;
    private Set<Deliverer> deliverers;

    /**
     * Constructs a new {@code Restaurant} with the specified name, address, meals,
     * chefs, waiters, and deliverers.
     *
     * @param id the unique identifier of the restaurant
     * @param name the name of the restaurant
     * @param address the address of the restaurant
     * @param meals an array of meals offered by the restaurant
     * @param chefs an array of chefs employed by the restaurant
     * @param waiters an array of waiters employed by the restaurant
     * @param deliverers an array of deliverers employed by the restaurant
     */
    public Restaurant(long id, String name, Address address, Set<Meal> meals, Set<Chef> chefs, Set<Waiter> waiters, Set<Deliverer> deliverers) {
        super(id);
        this.name = name;
        this.address = address;
        this.meals = meals;
        this.chefs = chefs;
        this.waiters = waiters;
        this.deliverers = deliverers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Set<Chef> getChefs() {
        return chefs;
    }

    public void setChefs(Set<Chef> chefs) {
        this.chefs = chefs;
    }

    public Set<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(Set<Waiter> waiters) {
        this.waiters = waiters;
    }

    public Set<Deliverer> getDeliverers() {
        return deliverers;
    }

    public void setDeliverers(Set<Deliverer> deliverers) {
        this.deliverers = deliverers;
    }
}
