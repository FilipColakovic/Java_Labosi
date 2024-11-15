package hr.java.production.main;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static final int NUMBER_OF_CATEGORIES = 3;
    public static final int NUMBER_OF_INGREDIANTS = 5;
    public static final int NUMBER_OF_MEALS = 3;
    public static final int NUMBER_OF_CHEFS = 3;
    public static final int NUMBER_OF_WAITERS = 3;
    public static final int NUMBER_OF_DELIVERERS = 3;
    public static final int NUMBER_OF_RESTAURANTS = 3;
    public static final int NUMBER_OF_ORDERS = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Category[] categories = new Category[NUMBER_OF_CATEGORIES];
        Ingredient[] ingredients = new Ingredient[NUMBER_OF_INGREDIANTS];
        Meal[] meals = new Meal[NUMBER_OF_MEALS];
        Chef[] chefs = new Chef[NUMBER_OF_CHEFS];
        Waiter[] waiters = new Waiter[NUMBER_OF_WAITERS];
        Deliverer[] deliverers = new Deliverer[NUMBER_OF_DELIVERERS];
        Restaurant[] restaurants = new Restaurant[NUMBER_OF_RESTAURANTS];
        Order[] orders = new Order[NUMBER_OF_ORDERS];


        for(int i=0; i< NUMBER_OF_CATEGORIES; i++){
            System.out.printf("Unesite podatke za "+ (i+1) +". kategoriju artikala: \n");
            Category tempCategory = nextCategory(sc);

            categories[i] = tempCategory;

        }

        for(int i=0; i< NUMBER_OF_INGREDIANTS; i++){
            System.out.printf("Unesite podatke za "+ (i+1) +". sastojak: \n");
            Ingredient tempIngrediant = nextIngrediant(sc, categories);

            ingredients[i] = tempIngrediant;

        }

        for (int i = 0; i < NUMBER_OF_MEALS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + " jelo: ");
            Meal tempMeal = nextMeal(sc, categories, ingredients);

            meals[i] = tempMeal;
        }

        for (int i = 0; i < NUMBER_OF_CHEFS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + " kuhara: ");
            Chef tempChef = nextChef(sc);

            chefs[i] = tempChef;
        }

        for (int i = 0; i < NUMBER_OF_WAITERS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + " konobara: ");
            Waiter tempWaiter = nextWaiter(sc);

            waiters[i] = tempWaiter;
        }

        for (int i = 0; i < NUMBER_OF_DELIVERERS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + " dostavljača: ");
            Deliverer tempDeliverer = nextDeliverer(sc);

            deliverers[i] = tempDeliverer;
        }

        for (int i = 0; i < NUMBER_OF_RESTAURANTS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + ". restoran: ");
            Restaurant tempRestaurant = nextRestaurant(sc, meals, chefs, waiters, deliverers);

            restaurants[i] = tempRestaurant;

        }

        for (int i = 0; i < NUMBER_OF_ORDERS; i++) {
            System.out.println("Unesite podatke za "+ (i+1) + ". narudžbu: ");
            Order tempOrder = nextOrder(sc,restaurants, meals, deliverers);

            orders[i] = tempOrder;

            processOrder(orders[i]);

        }

        findMostExpensiveOrder(orders);

        findTopDeliverer(deliverers);

    }

    static Category nextCategory(Scanner scanner){
        System.out.print("naziv: ");
        String tempName = scanner.nextLine();

        System.out.print("opis: ");
        String tempDiscription = scanner.nextLine();

        return new Category(tempName, tempDiscription);

    }

    static Ingredient nextIngrediant(Scanner scanner, Category[] categories){
        System.out.print("naziv: ");
        String tempName = scanner.nextLine();

        Category tempCategory;
        System.out.println("Odabetite kojoj kategoriji pripada: ");
        for (int i=0; i < categories.length; i++){
            System.out.println((i+1) + "." + categories[i].getName() + ", " + categories[i].getDescription());
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        tempCategory = categories[index];

        System.out.print("kalorije: ");
        BigDecimal tempKcal = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("način pripreme: ");
        String tempPreperationMethod = scanner.nextLine();

        return new Ingredient(tempName, tempCategory, tempKcal, tempPreperationMethod);

    }

    static Meal nextMeal(Scanner scanner, Category[] categories, Ingredient[] ingredients){
        System.out.print("naziv: ");
        String tempName = scanner.nextLine();

        Category tempCategory;
        System.out.println("Odabetite kojoj kategoriji pripada: ");
        for (int i=0; i < categories.length; i++){
            System.out.println((i+1) + "." + categories[i].getName() + ", " + categories[i].getDescription());
        }
        int indexCategory = scanner.nextInt() - 1;
        scanner.nextLine();
        tempCategory = categories[indexCategory];


        System.out.println("Koliko sastojaka sadrži jelo?");
        int numberOfIngrediants = scanner.nextInt();
        scanner.nextLine();
        Ingredient[] tempIngrediant = new Ingredient[numberOfIngrediants];
        System.out.println("Odabetite sastojke: ");
        for (int i=0; i < ingredients.length; i++){
            System.out.println((i+1) + "." + ingredients[i].getName());
        }
        for (int i = 0; i < numberOfIngrediants; i++) {
            int indexIngrediant = scanner.nextInt() - 1;
            scanner.nextLine();
            tempIngrediant[i] = ingredients[indexIngrediant];
        }

        System.out.print("cijenu: ");
        BigDecimal tempPrice = scanner.nextBigDecimal();
        scanner.nextLine();

        return new Meal(tempName, tempCategory, tempIngrediant, tempPrice);
    }

    static Chef nextChef(Scanner scanner){
        System.out.print("Ime: ");
        String tempFirstName = scanner.nextLine();

        System.out.print("Prezime: ");
        String tempLastName = scanner.nextLine();

        System.out.print("Plaća: ");
        BigDecimal tempSalary = scanner.nextBigDecimal();
        scanner.nextLine();

        return new Chef(tempFirstName, tempLastName, tempSalary);

    }

    static Waiter nextWaiter(Scanner scanner){
        System.out.print("Ime: ");
        String tempFirstName = scanner.nextLine();

        System.out.print("Prezime: ");
        String tempLastName = scanner.nextLine();

        System.out.print("Plaća: ");
        BigDecimal tempSalary = scanner.nextBigDecimal();
        scanner.nextLine();

        return new Waiter(tempFirstName, tempLastName, tempSalary);

    }

    static Deliverer nextDeliverer(Scanner scanner){
        System.out.print("Ime: ");
        String tempFirstName = scanner.nextLine();

        System.out.print("Prezime: ");
        String tempLastName = scanner.nextLine();

        System.out.print("Plaća: ");
        BigDecimal tempSalary = scanner.nextBigDecimal();
        scanner.nextLine();

        return new Deliverer(tempFirstName, tempLastName, tempSalary);

    }

    static Address nextAddress(Scanner scanner){
        System.out.print("ulica: ");
        String tempStreet = scanner.nextLine();

        System.out.print("broj adrese: ");
        String tempHouseNumber = scanner.nextLine();

        System.out.print("grad: ");
        String tempCity = scanner.nextLine();

        System.out.print("poštanski broj: ");
        String tempPostalCode = scanner.nextLine();

        return new Address(tempStreet, tempHouseNumber, tempCity, tempPostalCode);
    }

    static Restaurant nextRestaurant(Scanner scanner, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers){
        System.out.print("naziv: ");
        String tempName = scanner.nextLine();

        System.out.println("Upišite adresu restorana: " );
        Address tempAddress = nextAddress(scanner);

        System.out.println("Koliko jela sadrži restoran?");
        int numberOfMeals = scanner.nextInt();
        scanner.nextLine();
        Meal[] tempMeal = new Meal[numberOfMeals];
        System.out.println("Odabetite jela: ");
        for (int i=0; i < meals.length; i++){
            System.out.println((i+1) + "." + meals[i].getName());
        }
        for (int i = 0; i < numberOfMeals; i++) {
            int indexMeal = scanner.nextInt() - 1;
            scanner.nextLine();
            tempMeal[i] = meals[indexMeal];
        }

        System.out.println("Koliko kuhara rade u restoranu?");
        int numberOfChefs = scanner.nextInt();
        scanner.nextLine();
        Chef[] tempChef = new Chef[numberOfChefs];
        System.out.println("Odabetite kuhara: ");
        for (int i=0; i < chefs.length; i++){
            System.out.println((i+1) + "." + chefs[i].getFirstName() +" "+ chefs[i].getLastName());
        }
        for (int i = 0; i < numberOfChefs; i++) {
            int indexChef = scanner.nextInt() - 1;
            scanner.nextLine();
            tempChef[i] = chefs[indexChef];
        }

        System.out.println("Koliko konobara rade u restoranu?");
        int numberOfWaiters = scanner.nextInt();
        scanner.nextLine();
        Waiter[] tempWaiter = new Waiter[numberOfWaiters];
        System.out.println("Odabetite konobara: ");
        for (int i=0; i < waiters.length; i++){
            System.out.println((i+1) + "." + waiters[i].getFirstName() +" "+ waiters[i].getLastName());
        }
        for (int i = 0; i < numberOfWaiters; i++) {
            int indexWaiter = scanner.nextInt() - 1;
            scanner.nextLine();
            tempWaiter[i] = waiters[indexWaiter];
        }

        System.out.println("Koliko dostavljača rade u restoranu?");
        int numberOfDeliverers = scanner.nextInt();
        scanner.nextLine();
        Deliverer[] tempDeliverer = new Deliverer[numberOfDeliverers];
        System.out.println("Odabetite dostavljača: ");
        for (int i=0; i < waiters.length; i++){
            System.out.println((i+1) + "." + deliverers[i].getFirstName() +" "+ deliverers[i].getLastName());
        }
        for (int i = 0; i < numberOfDeliverers; i++) {
            int indexDeliverer = scanner.nextInt() - 1;
            scanner.nextLine();
            tempDeliverer[i] = deliverers[indexDeliverer];
        }

        return new Restaurant(tempName, tempAddress, tempMeal, tempChef, tempWaiter, tempDeliverer);
    }

    static Order nextOrder(Scanner scanner,Restaurant[] restaurants, Meal[] meals, Deliverer[] deliverers){

        Restaurant tempRestaurant;
        System.out.println("Odabetite iz kojeg restorana je nadudžba: ");
        for (int i=0; i < restaurants.length; i++){
            System.out.println((i+1) + "." + restaurants[i].getName());
        }
        int indexRestaurant = scanner.nextInt() - 1;
        scanner.nextLine();
        tempRestaurant = restaurants[indexRestaurant];

        System.out.println("Koliko jela je naručeno?");
        int numberOfMeals = scanner.nextInt();
        scanner.nextLine();
        Meal[] tempMeal = new Meal[numberOfMeals];
        System.out.println("Odabetite jela: ");
        for (int i=0; i < tempRestaurant.getMeals().length; i++){
            System.out.println((i+1) + "." + tempRestaurant.getMeals()[i].getName());
        }
        for (int i = 0; i < numberOfMeals; i++) {
            int indexMeal = scanner.nextInt() - 1;
            scanner.nextLine();
            tempMeal[i] = meals[indexMeal];
        }

        Deliverer tempDeliverer;
        System.out.println("Odabetite iz koji dostavljač dostavlja nadudžbu: ");
        for (int i=0; i < deliverers.length; i++){
            System.out.println((i+1) + "." + deliverers[i].getFirstName() +" "+ deliverers[i].getLastName());
        }
        int indexDeliverer = scanner.nextInt() - 1;
        scanner.nextLine();
        tempDeliverer = deliverers[indexDeliverer];



        System.out.print("datum i vrijrme(dd.MM.yyyy.HH.mm): ");
        LocalDateTime tempDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm"));


        return new Order(tempRestaurant, tempMeal, tempDeliverer, tempDateTime);
    }

    public static void findMostExpensiveOrder(Order[] orders) {

        BigDecimal maxPrice = BigDecimal.ZERO;
        Restaurant[] mostExpensiveRestaurants = new Restaurant[orders.length];
        int count = 0;

        for (int i=0; i < orders.length;i++) {
            BigDecimal totalPrice = orders[i].getTotalPrice();
            if (totalPrice.compareTo(maxPrice) > 0) {
                maxPrice = totalPrice;
                count = 0;
                mostExpensiveRestaurants[count++] = orders[i].getRestaurant();
            } else if (totalPrice.compareTo(maxPrice) == 0) {
                mostExpensiveRestaurants[count++] = orders[i].getRestaurant();
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

    private static void findTopDeliverer(Deliverer[] deliverers) {
        int maxDeliveries = 0;
        Deliverer[] topDeliverers = new Deliverer[deliverers.length];
        int count = 0;


        for (Deliverer deliverer : deliverers) {
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

}
