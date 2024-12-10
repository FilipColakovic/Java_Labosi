package hr.java.production.main;

import hr.java.restaurant.model.*;
import hr.java.restaurant.sort.ContractDurationComparator;
import hr.java.restaurant.sort.IngredientAlphabeticalComparator;
import hr.java.restaurant.sort.MealRestaurantComparator;
import hr.java.restaurant.sort.SalaryComparator;
import hr.java.restaurant.util.DataInputUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


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

        Set<Category> categories = new HashSet<>();
        Set<Ingredient> ingredients = new HashSet<>();
        List<Meal> meals = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        List<Restaurant> restaurants = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Person> employees = new ArrayList<>();
        Map<Meal, List<Restaurant>> mealRestaurantMap = new HashMap<>();

        for(int i=0; i< DataInputUtils.NUMBER_OF_CATEGORIES; i++){
            System.out.printf("Unesite podatke za "+ (i+1) +". kategoriju artikala: \n");
            categories.add(DataInputUtils.nextCategory(sc, categories)) ;
            logger.info("Stvorena je nova kategorija ");
        }

        for(int i = 0; i< DataInputUtils.NUMBER_OF_INGREDIENTS; i++){
            System.out.printf("Unesite podatke za "+ (i+1) +". sastojak: \n");
            ingredients.add(DataInputUtils.nextIngredient(sc, categories, ingredients)) ;
            logger.info("Stvoren je novi sastojak ");
        }

        for (int i = 0; i < DataInputUtils.NUMBER_OF_MEALS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + " jelo: ");
            meals.add(DataInputUtils.nextMeal(sc, categories, ingredients, meals));
            logger.info("Stvoreno je novo jelo ");
        }

        for (int i = 0; i < DataInputUtils.NUMBER_OF_EMPLOYEES; i++) {
            employees.add(DataInputUtils.nextEmployee(sc, employees));
            logger.info("Stvoren je novi zaposlenik ");
        }

        for (int i = 0; i < DataInputUtils.NUMBER_OF_RESTAURANTS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + ". restoran: ");
            restaurants.add(DataInputUtils.nextRestaurant(sc, meals, employees, addresses, restaurants));
        }

        for (Restaurant restaurant : restaurants) {
            for (Meal meal : restaurant.getMeals()) {
                List<Restaurant> restaurantList = mealRestaurantMap.getOrDefault(meal, new ArrayList<>());
                restaurantList.add(restaurant);
                mealRestaurantMap.put(meal, restaurantList);
            }
        }



        for (int i = 0; i < DataInputUtils.NUMBER_OF_ORDERS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + ". narudžbu: ");
            orders.add(DataInputUtils.nextOrder(sc,restaurants, meals, employees, orders));
            processOrder(orders.getLast());
        }

        Person highestPayedEmployee = findHighestPaidEmployee(employees);

        Person longestContractEmployee = findLongestContractEmployee(employees);

        findMostExpensiveOrder(orders);

        findTopDeliverer(employees);

        employees.sort(new SalaryComparator());

        employees.sort(new ContractDurationComparator());

        meals.sort(new MealRestaurantComparator(mealRestaurantMap));

        SortedSet<Ingredient> ingredientSortedSet = new TreeSet<>(new IngredientAlphabeticalComparator());
    }

    public static void findMostExpensiveOrder(List<Order> orders) {

        BigDecimal maxPrice = orders.stream()
                .map(Order::getTotalPrice)
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        List<Restaurant> mostExpensiveRestaurants = orders.stream()
                .filter(order -> order.getTotalPrice().compareTo(maxPrice) == 0)
                .map(Order::getRestaurant)
                .distinct()
                .toList();

        System.out.printf("Restoran(i) sa najskupljom narudžbom (cijena: %s):%n", maxPrice);
        mostExpensiveRestaurants.forEach(restaurant ->
                System.out.println(" " + restaurant.getName()));

    }

    private static void findTopDeliverer(List<Person> employees) {

        List<Deliverer> deliverers = employees.stream()
                .filter(person -> person instanceof Deliverer)
                .map(person -> (Deliverer) person)
                .toList();

        int maxDeliveries  = deliverers.stream()
                .mapToInt(Deliverer::getDeliveryCount)
                .max()
                .orElse(0);

        List<Deliverer> topDeliverers = deliverers.stream()
                .filter(deliverer -> deliverer.getDeliveryCount() == maxDeliveries )
                .toList();

        System.out.println("Dostavljač(i) s najviše dostava (broj dostava: " + maxDeliveries + "):");
        topDeliverers.forEach(deliverer -> {
            System.out.println("Ime: " + deliverer.getFirstName());
            System.out.println("Prezime: " + deliverer.getLastName());
        });
    }

    private static void processOrder(Order order) {
        order.getDeliverer().incrementDeliveryCount();
    }

    static Person findHighestPaidEmployee(List<Person> employees) {
        if (employees == null || employees.size() == 0) return null;

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

    static Person findLongestContractEmployee(List<Person> employees) {
        if (employees == null || employees.size() == 0) return null;

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
