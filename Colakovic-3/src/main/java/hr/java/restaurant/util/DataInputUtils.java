package hr.java.restaurant.util;

import hr.java.restaurant.exception.DuplicateEntryException;
import hr.java.restaurant.exception.InvalidValueException;
import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * A utility class for handling user input and creating various entities in the restaurant system.
 * This class contains methods for inputting data for categories, ingredients, meals, employees, addresses,
 * restaurants, and orders, with appropriate validation and error handling.
 */
public class DataInputUtils {

    public static final int NUMBER_OF_CATEGORIES = 3;
    public static final int NUMBER_OF_INGREDIENTS = 5;
    public static final int NUMBER_OF_MEALS = 9;
    public static final int NUMBER_OF_DELIVERERS = 3;
    public static final int NUMBER_OF_RESTAURANTS = 3;
    public static final int NUMBER_OF_ORDERS = 3;
    public static final int NUMBER_OF_EMPLOYEES = 9;
    public static final BigDecimal MINIMUM_WAGE = new BigDecimal("600");
    /**
     * Prompts the user for input to create a new Category and returns a newly created Category object.
     *
     * @param scanner The Scanner object used for reading user input.
     * @param categories The array of existing categories to determine the next available ID.
     * @return A new Category object created from the user's input.
     */
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
        String tempDescription = scanner.nextLine();

        return new Category(id, tempName, tempDescription);
    }
    /**
     * Prompts the user for input to create a new Ingredient and returns a newly created Ingredient object.
     *
     * @param scanner The Scanner object used for reading user input.
     * @param categories The array of categories to choose from for the ingredient's category.
     * @param ingredients The array of existing ingredients to determine the next available ID.
     * @return A new Ingredient object created from the user's input.
     */
    public static Ingredient nextIngredient(Scanner scanner, Category[] categories, Ingredient[] ingredients){
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
        for (int i=0; i < categories.length; i++){
            System.out.println((i+1) + "." + categories[i].getName() + ", " + categories[i].getDescription());
        }

        int index = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odabetite kojoj kategoriji pripada: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
        tempCategory = categories[index];

        BigDecimal tempKcal = InputValidatorUtils.validatePositiveBigDecimal(scanner, "kalorije: ", Messages.INVALID_BIGDECIMAL_INPUT_AND_NEGATIVE_ERROR);

        System.out.print("način pripreme: ");
        String tempPreparationMethod = scanner.nextLine();

        return new Ingredient(id,tempName, tempCategory, tempKcal, tempPreparationMethod);
    }
    /**
     * Prompts the user for input to create a new Meal and returns a newly created Meal object.
     *
     * @param scanner The Scanner object used for reading user input.
     * @param categories The array of categories to choose from for the meal's category.
     * @param ingredients The array of ingredients to choose from for the meal.
     * @param meals The array of existing meals to determine the next available ID.
     * @return A new Meal object created from the user's input.
     */
    public static Meal nextMeal(Scanner scanner, Category[] categories, Ingredient[] ingredients, Meal[] meals){
        boolean valid = false;
        int selectedMealType;
        do {
            System.out.printf("Odaberite vrstu jela(broj):%n 1. Vegansko%n 2. Vegetariansko%n 3. Mesno");
            selectedMealType = InputValidatorUtils.validatePositiveInt(scanner,"Odaberite vrstu jela(broj):%n 1. Vegansko%n 2. Vegetariansko%n 3. Mesno", Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
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
        String tempName;
        while (true) {
            try {
                System.out.print("naziv: ");
                tempName = scanner.nextLine();

                InputValidatorUtils.checkForDuplicateMealName(tempName, meals );
                break;

            } catch (DuplicateEntryException e) {
                System.out.println("Pogreška: " + e.getMessage() + " Pokušajte ponovo.");
            }
        }

        Category tempCategory;
        for (int i=0; i < categories.length; i++){
            System.out.println((i+1) + "." + categories[i].getName() + ", " + categories[i].getDescription());
        }
        int indexCategory = InputValidatorUtils.validatePositiveInt(scanner,
                "Odabetite kojoj kategoriji pripada: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
        tempCategory = categories[indexCategory];

        int numberOfIngrediants = InputValidatorUtils.validatePositiveInt(scanner,
                "Koliko sastojaka sadrži jelo?", Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
        Ingredient[] tempIngrediants = new Ingredient[numberOfIngrediants];

        for (int i=0; i < ingredients.length; i++){
            System.out.println((i+1) + "." + ingredients[i].getName());
        }
        for (int i = 0; i < numberOfIngrediants; i++) {
            int indexIngrediant = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odabetite sastojke: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
            tempIngrediants[i] = ingredients[indexIngrediant];
        }

        BigDecimal tempPrice;
        while (true) {
            try {
                tempPrice = InputValidatorUtils.validatePositiveBigDecimal(scanner,
                        "Unesite cijenu: ", Messages.INVALID_BIGDECIMAL_INPUT_AND_NEGATIVE_ERROR);

                // Provjera cijene i bacanje iznimke ako nije validna
                if (tempPrice.compareTo(BigDecimal.ZERO) < 0 || tempPrice.compareTo(new BigDecimal("500")) > 0) {
                    throw new InvalidValueException("Nevažeća cijena: " + tempPrice + ". Cijena mora biti između 0 i 500.");
                }
                break;
            } catch (InvalidValueException e) {
                System.out.println("Pogreška: " + e.getMessage() + " Pokušajte ponovo.");
            }
        }


        return switch (selectedMealType) {
            case 1 -> {int tempKcal = InputValidatorUtils.validatePositiveInt(scanner,
                    "kalorije: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
                        yield new VeganMeal(id, tempName, tempCategory, tempIngrediants, tempPrice, tempKcal);
                        }
            case 2 -> {System.out.println("Da li sadrži orašaste plodove(DA/NE): ");
                        String tempStringVegetarian = scanner.nextLine();
                        boolean tempContainsNuts = tempStringVegetarian.contains("DA");

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
    /**
     * Prompts the user for input to create a new Employee and returns a newly created Employee object.
     *
     * @param scanner The Scanner object used for reading user input.
     * @param employees The array of existing employees to determine the next available ID.
     * @return A new Employee object created from the user's input.
     */
    public static Person nextEmployee(Scanner scanner, Person[] employees){
        boolean valid = false;
        int selectedEmployeeType;
        do {
            selectedEmployeeType = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odaberite vrstu zaposlenika(broj):%n 1. Chef%n 2. Waiter%n 3. Deliverer",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
        }while (selectedEmployeeType < 1 || selectedEmployeeType > 3);

        String tempFirstName;
        String tempLastName;


        while (true) {
            try {
                System.out.print("Ime: ");
                tempFirstName = scanner.nextLine();

                System.out.print("Prezime: ");
                tempLastName = scanner.nextLine();

                InputValidatorUtils.checkForDuplicateEmployee(tempFirstName, tempLastName, employees);
                break;

            } catch (DuplicateEntryException e) {
                System.out.println("Pogreška: " + e.getMessage() + " Pokušajte ponovo.");
            }
        }

        BigDecimal salary;
        while (true) {
            try {
                salary = InputValidatorUtils.validatePositiveBigDecimal(scanner,
                        "Unesite plaću: ", Messages.INVALID_BIGDECIMAL_INPUT_AND_NEGATIVE_ERROR);
                if (salary.compareTo(MINIMUM_WAGE) < 0) {
                    throw new InvalidValueException("Plaća ne može biti manja od minimalne dozvoljene plaće (" + MINIMUM_WAGE + ").");
                }
                break;
            } catch (InvalidValueException e) {
                System.out.println("Pogreška: " + e.getMessage() + " Molimo unesite ponovo.");
            }
        }
        LocalDate startDate = null;
        while (startDate == null) {
            System.out.println("Unesite datum početka ugovora (dd-MM-yyyy):");
            try {
                startDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Neispravan format datuma. Molimo unesite ponovno.");
            }
        }

        LocalDate endDate = null;
        while (endDate == null) {
            System.out.println("Unesite datum završetka ugovora (dd-MM-yyyy):");
            try {
                endDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Neispravan format datuma. Molimo unesite ponovno.");
            }
        }
        int tempContarctIndex = InputValidatorUtils.validatePositiveInt(scanner,
                "Unesite vrstu ugovora:\n 1. puno radno vrijeme\n 2. nepuno radno vrijeme):",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);

        Contract.ContractType tempContarctType = switch (tempContarctIndex){
            case 1 -> tempContarctType = Contract.ContractType.FULL_TIME;
            case 2 -> tempContarctType = Contract.ContractType.PART_TIME;
            default -> throw new  IllegalArgumentException("Nepoznata vrsta ugovora");
        };

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
                yield nextEmployee(scanner, employees);
            }
        };
    }
    /**
     * Prompts the user for input to create a new Address and returns a newly created Address object.
     *
     * @param scanner The Scanner object used for reading user input.
     * @param addresses The array of existing addresses to determine the next available ID.
     * @return A new Address object created from the user's input.
     */
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
    /**
     * Prompts the user for input to create a new Restaurant and returns a newly created Restaurant object.
     *
     * @param scanner The Scanner object used for reading user input.
     * @param addresses The array of addresses to choose from for the restaurant's location.
     * @param restaurants The array of existing restaurants to determine the next available ID.
     * @return A new Restaurant object created from the user's input.
     */
    public static Restaurant nextRestaurant(Scanner scanner, Meal[] meals, Person[] employees,Address[] addresses, Restaurant[] restaurants){
        long id = 0;
        for (Restaurant restaurant : restaurants) {
            if (restaurant != null && restaurant.getId() > id) {
                id = restaurant.getId();
            }
        }
        id++;

        String tempName;
        while (true) {
            try {
                System.out.print("naziv: ");
                tempName = scanner.nextLine();

                InputValidatorUtils.checkForDuplicateRestaurantName(tempName, restaurants );
                break;

            } catch (DuplicateEntryException e) {
                System.out.println("Pogreška: " + e.getMessage() + " Pokušajte ponovo.");
            }
        }

        System.out.println("Upišite adresu restorana: " );
        Address tempAddress = nextAddress(scanner, addresses);


        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) {
                addresses[i] = tempAddress;
                break;
            }
        }

        int numberOfMeals = InputValidatorUtils.validatePositiveInt(scanner,
                "Koliko jela sadrži restoran?",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
        Meal[] tempMeal = new Meal[numberOfMeals];
        for (int i=0; i < meals.length; i++){
            System.out.println((i+1) + "." + meals[i].getName());
        }
        for (int i = 0; i < numberOfMeals; i++) {
            int indexMeal = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odabetite jela: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
            tempMeal[i] = meals[indexMeal];
        }

        int numberOfChefs = InputValidatorUtils.validatePositiveInt(scanner,
                "Koliko kuhara rade u restoranu?", Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
        Chef[] tempChef = new Chef[numberOfChefs];

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof Chef chef) {
                System.out.println((i + 1) + ". " + chef.getFirstName() + " " + chef.getLastName());
            }
        }

        for (int i = 0; i < numberOfChefs; i++) {
            int indexChef = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odabetite kuhara: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
            tempChef[i] = (Chef) employees[indexChef];
        }

        int numberOfWaiters = InputValidatorUtils.validatePositiveInt(scanner,
                "Koliko konobara rade u restoranu?",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
        Waiter[] tempWaiter = new Waiter[numberOfWaiters];

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof Waiter waiter) {
                System.out.println((i + 1) + ". " + waiter.getFirstName() + " " + waiter.getLastName());
            }
        }
        for (int i = 0; i < numberOfWaiters; i++) {
            int indexWaiter = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odabetite konobara: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
            tempWaiter[i] = (Waiter) employees[indexWaiter];
        }

        int numberOfDeliverers = InputValidatorUtils.validatePositiveInt(scanner,
                "Koliko dostavljača rade u restoranu?",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
        Deliverer[] tempDeliverer = new Deliverer[numberOfDeliverers];

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof Deliverer deliverer) {
                System.out.println((i + 1) + ". " + deliverer.getFirstName() + " " + deliverer.getLastName());
            }
        }
        for (int i = 0; i < numberOfDeliverers; i++) {
            int indexDeliverer = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odabetite dostavljača: ", Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
            tempDeliverer[i] = (Deliverer) employees[indexDeliverer];
        }

        return new Restaurant(id, tempName, tempAddress, tempMeal, tempChef, tempWaiter, tempDeliverer);
    }
    /**
     * Prompts the user to enter details for creating a new order, including selecting a restaurant,
     * choosing ordered meals, selecting a deliverer, and entering the order date and time.
     *
     * @param scanner The Scanner object used to read user input.
     * @param restaurants The array of available restaurants to choose from.
     * @param meals The array of available meals to choose from.
     * @param employees The array of employees, used to select a deliverer.
     * @param orders The array of existing orders, used to generate a unique ID for the new order.
     * @return A new Order object containing the details entered by the user.
     * @throws IllegalArgumentException If an unknown selection is made during the user input process.
     */
    public static Order nextOrder(Scanner scanner, Restaurant[] restaurants, Meal[] meals, Person[] employees, Order[] orders){
        long id = 0;
        for (Order order : orders) {
            if (order != null && order.getId() > id) {
                id = order.getId();
            }
        }
        id++;

        Restaurant tempRestaurant;
       for (int i=0; i < restaurants.length; i++){
            System.out.println((i+1) + "." + restaurants[i].getName());
        }
        int indexRestaurant = InputValidatorUtils.validatePositiveInt(scanner,
                "Odabetite iz kojeg restorana je nadudžba: ", Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
        tempRestaurant = restaurants[indexRestaurant];

        int numberOfMeals = InputValidatorUtils.validatePositiveInt(scanner,
                "Koliko jela je naručeno?", Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR);
        Meal[] tempMeal = new Meal[numberOfMeals];
        for (int i=0; i < tempRestaurant.getMeals().length; i++){
            System.out.println((i+1) + "." + tempRestaurant.getMeals()[i].getName());
        }
        for (int i = 0; i < numberOfMeals; i++) {
            int indexMeal = InputValidatorUtils.validatePositiveInt(scanner,
                    "Odabetite jela: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
            tempMeal[i] = meals[indexMeal];
        }

        Deliverer tempDeliverer;
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
        int indexDeliverer = InputValidatorUtils.validatePositiveInt(scanner,
                "Odabetite iz koji dostavljač dostavlja nadudžbu: ", Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
        tempDeliverer = deliverers[indexDeliverer];

        System.out.print("datum i vrijeme(dd.MM.yyyy.HH.mm): ");
        LocalDateTime tempDateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm"));

        return new Order(id, tempRestaurant, tempMeal, tempDeliverer, tempDateTime);
    }

}
