package hr.java.restaurant.util;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DataInputUtils {

    public static final int NUMBER_OF_CATEGORIES = 3;
    public static final int NUMBER_OF_INGREDIANTS = 5;
    public static final int NUMBER_OF_MEALS = 9;
    public static final int NUMBER_OF_CHEFS = 3;
    public static final int NUMBER_OF_WAITERS = 3;
    public static final int NUMBER_OF_DELIVERERS = 3;
    public static final int NUMBER_OF_RESTAURANTS = 3;
    public static final int NUMBER_OF_ORDERS = 3;
    public static final int NUMBER_OF_EMPLOYEES = 9;

    public static Category nextCategory(Scanner scanner, Category[] categories){
        long id = 0;
        for (Category category : categories) {
            if (category != null && category.getId() > id) {
                id = category.getId();
            }
        }
        id++;

        System.out.print("naziv: ");
        String tempName = scanner.nextLine();

        System.out.print("opis: ");
        String tempDiscription = scanner.nextLine();

        return new Category(id, tempName, tempDiscription);
    }

    public static Ingredient nextIngrediant(Scanner scanner, Category[] categories, Ingredient[] ingredients){
        long id = 0;
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null && ingredient.getId() > id) {
                id = ingredient.getId();
            }
        }
        id++;

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

        return new Ingredient(id,tempName, tempCategory, tempKcal, tempPreperationMethod);
    }

    public static Meal nextMeal(Scanner scanner, Category[] categories, Ingredient[] ingredients, Meal[] meals){
        boolean valid = false;
        int selectedMealType;
        do {
            System.out.printf("Odaberite vrstu jela(broj):%n 1. Vegansko%n 2. Vegetariansko%n 3. Mesno");
            selectedMealType = scanner.nextInt();
            scanner.nextLine();
            if (selectedMealType < 1 || selectedMealType > 3) {
                valid = true;
            }
        }while (valid);

        long id = 0;
        for (Meal meal : meals) {
            if (meal != null && meal.getId() > id) {
                id = meal.getId();
            }
        }
        id++;

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
        Ingredient[] tempIngrediants = new Ingredient[numberOfIngrediants];
        System.out.println("Odabetite sastojke: ");
        for (int i=0; i < ingredients.length; i++){
            System.out.println((i+1) + "." + ingredients[i].getName());
        }
        for (int i = 0; i < numberOfIngrediants; i++) {
            int indexIngrediant = scanner.nextInt() - 1;
            scanner.nextLine();
            tempIngrediants[i] = ingredients[indexIngrediant];
        }

        System.out.print("cijenu: ");
        BigDecimal tempPrice = scanner.nextBigDecimal();
        scanner.nextLine();

        return switch (selectedMealType) {
            case 1 -> {System.out.println("kalorije: ");
                        int tempKcal = scanner.nextInt();
                        scanner.nextLine();
                        yield new VeganMeal(id, tempName, tempCategory, tempIngrediants, tempPrice, tempKcal);
                        }
            case 2 -> {System.out.println("Da li sadrži orašaste plodove(DA/NE): ");
                        String tempStringVegetarian = scanner.nextLine();
                        boolean tempContainsNuts = false;
                        switch (tempStringVegetarian){
                            case "DA" -> tempContainsNuts = true;
                            case "NE" -> tempContainsNuts = false;
                        }
                    yield new VegetarianMeal(id, tempName, tempCategory,tempIngrediants, tempPrice,tempContainsNuts);}
            case 3 -> {System.out.println("Kakvo meso sadrži: ");
                    String tempStringMeat = scanner.nextLine();
                   yield  new MeatMeal(id, tempName, tempCategory,tempIngrediants, tempPrice,tempStringMeat);}
            default -> {
                System.out.println("Nepoznat izbor pokušajte ponovo.");
                yield nextMeal(scanner,categories,ingredients,meals);
            }
        };

    }

    public static Person nextEmployee(Scanner scanner){
        boolean valid = false;
        int selectedEmployeeType;
        do {
            System.out.printf("Odaberite vrstu zaposlenika(broj):%n 1. Chef%n 2. Waiter%n 3. Deliverer");
            selectedEmployeeType = scanner.nextInt();
            scanner.nextLine();
            if (selectedEmployeeType < 1 || selectedEmployeeType > 3) {
                valid = true;
            }
        }while (valid);

        System.out.print("Ime: ");
        String tempFirstName = scanner.nextLine();

        System.out.print("Prezime: ");
        String tempLastName = scanner.nextLine();

        BigDecimal salary = InputValidatorUtils.validatePositiveBigDecimal(scanner,
                "Unesite plaću: ", Messages.INVALID_BIGDECIMAL_INPUT_AND_NEGATIVE_ERROR);

        System.out.println("Unesite datum početka ugovora (dd-MM-yyyy):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Unesite datum završetka ugovora (dd-MM-yyyy):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Unesite vrstu ugovora:\n 1. puno radno vrijeme\n 2. PART_TIME):");
        int tempContarctIndex = scanner.nextInt();
        scanner.nextLine();
        Contract.ContractType tempContarctType = null;
        switch (tempContarctIndex){
            case 1 -> tempContarctType = Contract.ContractType.FULL_TIME;
            case 2 -> tempContarctType = Contract.ContractType.PART_TIME;
        }

        Contract contract = new Contract(salary, startDate, endDate, tempContarctType);

        Bonus bonus = new Bonus(InputValidatorUtils.validatePositiveBigDecimal(scanner,
                "Unesite bonus: ", Messages.INVALID_BIGDECIMAL_INPUT_AND_NEGATIVE_ERROR));

        return switch (selectedEmployeeType) {
            case 1 -> new Chef.ChefBuilder()
                    .setFirstName(tempFirstName)
                    .setLastName(tempLastName)
                    .setContract(contract)
                    .setBonus(bonus)
                    .build();
            case 2 -> new Waiter.WaiterBuilder()
                    .setFirstName(tempFirstName)
                    .setLastName(tempLastName)
                    .setContract(contract)
                    .setBonus(bonus)
                    .build();
            case 3 -> new Deliverer.DelivererBuilder()
                    .setFirstName(tempFirstName)
                    .setLastName(tempLastName)
                    .setContract(contract)
                    .setBonus(bonus)
                    .build();
            default -> {
                System.out.println("Nepoznat izbor pokušajte ponovo.");
                yield nextEmployee(scanner);
            }
        };
    }

    public static Address nextAddress(Scanner scanner, Address[]  addresses){
        long id = 0;
        for (Address address : addresses) {
            if (address != null && address.getId() > id) {
                id = address.getId();
            }
        }
        id++;
        System.out.print("ulica: ");
        String tempStreet = scanner.nextLine();

        System.out.print("broj adrese: ");
        String tempHouseNumber = scanner.nextLine();

        System.out.print("grad: ");
        String tempCity = scanner.nextLine();

        System.out.print("poštanski broj: ");
        String tempPostalCode = scanner.nextLine();

        return new Address(id, tempStreet, tempHouseNumber, tempCity, tempPostalCode);
    }

    public static Restaurant nextRestaurant(Scanner scanner, Meal[] meals, Person[] employees,Address[] addresses, Restaurant[] restaurants){
        long id = 0;
        for (Restaurant restaurant : restaurants) {
            if (restaurant != null && restaurant.getId() > id) {
                id = restaurant.getId();
            }
        }
        id++;

        System.out.print("naziv: ");
        String tempName = scanner.nextLine();

        System.out.println("Upišite adresu restorana: " );
        Address tempAddress = nextAddress(scanner, addresses);


        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) {
                addresses[i] = tempAddress;
                break;
            }
        }

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

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof Chef) {
                Chef chef = (Chef) employees[i];
                System.out.println((i + 1) + ". " + chef.getFirstName() + " " + chef.getLastName());
            }
        }

        for (int i = 0; i < numberOfChefs; i++) {
            int indexChef = scanner.nextInt() - 1;
            scanner.nextLine();
            tempChef[i] = (Chef) employees[indexChef];
        }

        System.out.println("Koliko konobara rade u restoranu?");
        int numberOfWaiters = scanner.nextInt();
        scanner.nextLine();
        Waiter[] tempWaiter = new Waiter[numberOfWaiters];
        System.out.println("Odabetite konobara: ");

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof Waiter) {
                Waiter waiter = (Waiter) employees[i];
                System.out.println((i + 1) + ". " + waiter.getFirstName() + " " + waiter.getLastName());
            }
        }
        for (int i = 0; i < numberOfWaiters; i++) {
            int indexWaiter = scanner.nextInt() - 1;
            scanner.nextLine();
            tempWaiter[i] = (Waiter) employees[indexWaiter];
        }

        System.out.println("Koliko dostavljača rade u restoranu?");
        int numberOfDeliverers = scanner.nextInt();
        scanner.nextLine();
        Deliverer[] tempDeliverer = new Deliverer[numberOfDeliverers];
        System.out.println("Odabetite dostavljača: ");

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof Deliverer) {
                Deliverer deliverer = (Deliverer) employees[i];
                System.out.println((i + 1) + ". " + deliverer.getFirstName() + " " + deliverer.getLastName());
            }
        }
        for (int i = 0; i < numberOfDeliverers; i++) {
            int indexDeliverer = scanner.nextInt() - 1;
            scanner.nextLine();
            tempDeliverer[i] = (Deliverer) employees[indexDeliverer];
        }

        return new Restaurant(id, tempName, tempAddress, tempMeal, tempChef, tempWaiter, tempDeliverer);
    }

    public static Order nextOrder(Scanner scanner, Restaurant[] restaurants, Meal[] meals, Person[] employees, Order[] orders){
        long id = 0;
        for (Order order : orders) {
            if (order != null && order.getId() > id) {
                id = order.getId();
            }
        }
        id++;

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
        Deliverer[] deliverers = new Deliverer[NUMBER_OF_DELIVERERS];
        for (int i = 0; i < NUMBER_OF_EMPLOYEES; i++) {
            int j = 0;
            if (employees[i] instanceof Deliverer) {
                deliverers[j++] = (Deliverer) employees[i];
            }
        }
        for (int i=0; i < deliverers.length; i++){
            System.out.println((i+1) + "." + deliverers[i].getFirstName() +" "+ deliverers[i].getLastName());
        }
        int indexDeliverer = scanner.nextInt() - 1;
        scanner.nextLine();
        tempDeliverer = deliverers[indexDeliverer];

        System.out.print("datum i vrijrme(dd.MM.yyyy.HH.mm): ");
        LocalDateTime tempDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm"));

        return new Order(id, tempRestaurant, tempMeal, tempDeliverer, tempDateTime);
    }

}
