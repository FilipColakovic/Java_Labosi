package hr.java.restaurant.util;

import hr.java.restaurant.exception.DuplicateEntryException;
import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Restaurant;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Utility class that provides methods for validating user input and checking for duplicate entries in arrays.
 * Contains methods for validating positive integers and BigDecimals, as well as methods to check for duplicate
 * employees, meals, and restaurants by name.
 */
public class InputValidatorUtils {
    /**
     * Validates that the user inputs a positive BigDecimal number.
     *
     * @param sc The Scanner object used to read input from the user.
     * @param message The prompt message to be displayed to the user.
     * @param errorMessage The error message to be displayed if the input is invalid.
     * @return A valid positive BigDecimal entered by the user.
     */
    public static BigDecimal validatePositiveBigDecimal(Scanner sc, String message, String errorMessage) {
        BigDecimal number = new BigDecimal(0);
        Boolean isValid;

        do {
            isValid = true;
            try {
                System.out.println(message);
                number = sc.nextBigDecimal();

                if(number.compareTo(BigDecimal.ZERO) < 0) {
                    isValid = false;
                    System.out.println(errorMessage);
                }

            } catch (InputMismatchException e) {
                isValid = false;
                System.out.println(errorMessage);
                sc.nextLine();
            }
        } while(!isValid);
        sc.nextLine();
        return number;
    }
    /**
     * Validates that the user inputs a positive integer.
     *
     * @param sc The Scanner object used to read input from the user.
     * @param message The prompt message to be displayed to the user.
     * @param errorMessage The error message to be displayed if the input is invalid.
     * @return A valid positive integer entered by the user.
     */
    public static int validatePositiveInt(Scanner sc, String message, String errorMessage) {
        int number = 0;
        Boolean isValid;
        do {
            isValid = true;
            try {
                System.out.println(message);
                number = sc.nextInt();

                if(number <= 0) {
                    isValid = false;
                    System.out.println(errorMessage);
                }
            } catch (InputMismatchException e) {
                isValid = false;
                System.out.println(errorMessage);
                sc.nextLine();
            }
        } while(!isValid);
        sc.nextLine();
        return number;
    }
    /**
     * Checks if an employee with the given first and last name already exists in the employee array.
     * If a duplicate is found, a DuplicateEntryException is thrown.
     *
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     * @param employees The array of employees to check for duplicates.
     * @throws DuplicateEntryException if an employee with the same name already exists.
     */
    public static void checkForDuplicateEmployee(String firstName, String lastName, Person[] employees)
            throws DuplicateEntryException {

        for (Person employee : employees) {
            if (employee != null &&
                    employee.getFirstName().equalsIgnoreCase(firstName) &&
                    employee.getLastName().equalsIgnoreCase(lastName)) {

                throw new DuplicateEntryException("Zaposlenik s imenom '" + firstName +
                        "' i prezimenom '" + lastName + "' već postoji.");

            }
        }
    }

    /**
     * Checks if a meal with the given name already exists in the meals array.
     * If a duplicate is found, a DuplicateEntryException is thrown.
     *
     * @param name The name of the meal.
     * @param meals The array of meals to check for duplicates.
     * @throws DuplicateEntryException if a meal with the same name already exists.
     */
    public static void checkForDuplicateMealName(String name, Meal[] meals)
            throws DuplicateEntryException {

        for (Meal meal : meals) {
            if (meal != null && meal.getName().equalsIgnoreCase(name)) {
                throw new DuplicateEntryException("Jelo s nazivom '" + name + "' već postoji.");
            }
        }
    }

    /**
     * Checks if a restaurant with the given name already exists in the restaurants array.
     * If a duplicate is found, a DuplicateEntryException is thrown.
     *
     * @param name The name of the restaurant.
     * @param restaurants The array of restaurants to check for duplicates.
     * @throws DuplicateEntryException if a restaurant with the same name already exists.
     */
    public static void checkForDuplicateRestaurantName(String name, Restaurant[] restaurants)
            throws DuplicateEntryException {

        for (Restaurant restaurant : restaurants) {
            if (restaurant != null && restaurant.getName().equalsIgnoreCase(name)) {
                throw new DuplicateEntryException("Restoran s nazivom '" + name + "' već postoji.");
            }
        }
    }







}
