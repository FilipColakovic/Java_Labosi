package hr.java.production.main;

import hr.java.restaurant.model.*;
import hr.java.restaurant.util.DataInputUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * The main class that simulates a restaurant management system.
 * This class handles the initialization of restaurant-related data, including categories, ingredients,
 * meals, employees, restaurants, and orders. It also processes orders and identifies top performers
 * based on specific criteria, such as highest salary, longest contract, and most expensive order.
 */
public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    /**
     * The entry point of the restaurant management program.
     * It initializes the restaurant system, collects input from the user, and processes data for categories, ingredients,
     * meals, employees, restaurants, and orders. The program also identifies and displays top performers, such as the
     * highest-paid employee, the employee with the longest contract, and the most expensive order.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        logger.info("Program je pokrenut");

        Category[] categories = new Category[DataInputUtils.NUMBER_OF_CATEGORIES];
        Ingredient[] ingredients = new Ingredient[DataInputUtils.NUMBER_OF_INGREDIENTS];
        Meal[] meals = new Meal[DataInputUtils.NUMBER_OF_MEALS];
        Address[] addresses = new Address[DataInputUtils.NUMBER_OF_RESTAURANTS];
        Restaurant[] restaurants = new Restaurant[DataInputUtils.NUMBER_OF_RESTAURANTS];
        Order[] orders = new Order[DataInputUtils.NUMBER_OF_ORDERS];
        Person[] employees = new Person[DataInputUtils.NUMBER_OF_EMPLOYEES];

        for(int i=0; i< DataInputUtils.NUMBER_OF_CATEGORIES; i++){
            System.out.printf("Unesite podatke za "+ (i+1) +". kategoriju artikala: \n");
            Category tempCategory = DataInputUtils.nextCategory(sc, categories);
            categories[i] = tempCategory;
            logger.info("Stvorena je kategorija "+tempCategory.getName());
        }

        for(int i = 0; i< DataInputUtils.NUMBER_OF_INGREDIENTS; i++){
            System.out.printf("Unesite podatke za "+ (i+1) +". sastojak: \n");
            Ingredient tempIngrediant = DataInputUtils.nextIngredient(sc, categories, ingredients);
            ingredients[i] = tempIngrediant;
            logger.info("Stvoren je sastojak "+tempIngrediant.getName());
        }

        for (int i = 0; i < DataInputUtils.NUMBER_OF_MEALS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + " jelo: ");
            Meal tempMeal = DataInputUtils.nextMeal(sc, categories, ingredients, meals);
            meals[i] = tempMeal;
            logger.info("Stvoreno je jelo "+tempMeal.getName());
        }

        for (int i = 0; i < DataInputUtils.NUMBER_OF_EMPLOYEES; i++) {
            Person tempPerson = DataInputUtils.nextEmployee(sc, employees);
            employees[i] = tempPerson;
            logger.info("Stvoren je zaposlenik "+tempPerson.getFirstName() + " " + tempPerson.getLastName());
        }

        for (int i = 0; i < DataInputUtils.NUMBER_OF_RESTAURANTS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + ". restoran: ");
            Restaurant tempRestaurant =DataInputUtils.nextRestaurant(sc, meals, employees, addresses, restaurants);
            restaurants[i] = tempRestaurant;
        }

        for (int i = 0; i < DataInputUtils.NUMBER_OF_ORDERS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + ". narudžbu: ");
            Order tempOrder = DataInputUtils.nextOrder(sc,restaurants, meals, employees, orders);

            orders[i] = tempOrder;
            processOrder(orders[i]);
        }

        Person highestPayedEmployee = findHighestPaidEmployee(employees);

        Person longestContractEmployee = findLongestContractEmployee(employees);

        findMostExpensiveOrder(orders);

        findTopDeliverer(employees);
    }

    public static void findMostExpensiveOrder(Order[] orders) {

        BigDecimal maxPrice = BigDecimal.ZERO;
        Restaurant[] mostExpensiveRestaurants = new Restaurant[orders.length];
        int count = 0;

        for (Order order : orders) {
            BigDecimal totalPrice = order.getTotalPrice();
            if (totalPrice.compareTo(maxPrice) > 0) {
                maxPrice = totalPrice;
                count = 0;
                mostExpensiveRestaurants[count++] = order.getRestaurant();
            } else if (totalPrice.compareTo(maxPrice) == 0) {
                mostExpensiveRestaurants[count++] = order.getRestaurant();
            }
        }


        System.out.printf("Restoran(i) sa najskupljom narudžbom (cijena: " + maxPrice + "): ");
        for (int i = 0; i < count; i++) {
            Restaurant restaurant = mostExpensiveRestaurants[i];
            if (restaurant != null) {
                System.out.println(" " + restaurant.getName());
            }
        }

    }

    private static void findTopDeliverer(Person[] employees) {

        Deliverer[] tempDeliverer = new Deliverer[DataInputUtils.NUMBER_OF_DELIVERERS];
        int index = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof Deliverer) {
                tempDeliverer[index++] = (Deliverer) employees[i];
            }
        }
        int maxDeliveries = 0;
        Deliverer[] topDeliverers = new Deliverer[tempDeliverer.length];
        int count = 0;

        for (Deliverer deliverer : tempDeliverer) {
            int deliveryCount = deliverer.getDeliveryCount();
            if (deliveryCount > maxDeliveries) {
                maxDeliveries = deliveryCount;
                count = 0;
                topDeliverers[count++] = deliverer;
            } else if (deliveryCount == maxDeliveries) {
                topDeliverers[count++] = deliverer;
            }
        }

        System.out.println("Dostavljač(i) s najviše dostava (broj dostava: " + maxDeliveries + "):");
        for (int i = 0; i < count; i++) {
            Deliverer deliverer = topDeliverers[i];
            if (deliverer != null) {
                System.out.println("Ime: " + deliverer.getFirstName());
                System.out.println("Prezime: " + deliverer.getLastName());
                // Include additional details as needed
            }
        }
    }

    private static void processOrder(Order order) {

        order.getDeliverer().incrementDeliveryCount();
    }

    static Person findHighestPaidEmployee(Person[] employees) {
        if (employees == null || employees.length == 0) return null;

        Person highestPaidEmployee = null; // Use a descriptive name
        BigDecimal highestSalary = BigDecimal.ZERO; // Initialize to zero

        for (Person employee : employees) {
            BigDecimal currentSalary = BigDecimal.ZERO;
            Contract currentContract = null;

            if (employee instanceof Chef chef) {
                currentContract = chef.getContract();
            } else if (employee instanceof Waiter waiter) {
                currentContract = waiter.getContract();
            } else if (employee instanceof Deliverer deliverer) {
                currentContract = deliverer.getContract();
            }

            if (currentContract != null) {
                currentSalary = currentContract.getSalary();
                if (highestPaidEmployee == null || currentSalary.compareTo(highestSalary) > 0) {
                    highestPaidEmployee = employee;
                    highestSalary = currentSalary;
                }
            }
        }

        return highestPaidEmployee;
    }

    static Person findLongestContractEmployee(Person[] employees) {
        if (employees == null || employees.length == 0) return null;

        Person longestContractEmployee = null; // Use a descriptive name
        LocalDate earliestStartDate = null; // To keep track of the earliest start date

        for (Person employee : employees) {
            Contract currentContract = null;

            if (employee instanceof Chef chef) {
                currentContract = chef.getContract();
            } else if (employee instanceof Waiter waiter) {
                currentContract = waiter.getContract();
            } else if (employee instanceof Deliverer deliverer) {
                currentContract = deliverer.getContract();
            }

            if (currentContract != null) {
                if (longestContractEmployee == null ||
                        (earliestStartDate != null && currentContract.getStartDate().isBefore(earliestStartDate))) {

                    longestContractEmployee = employee;
                    earliestStartDate = currentContract.getStartDate();
                }
            }
        }
        return longestContractEmployee;
    }
}
