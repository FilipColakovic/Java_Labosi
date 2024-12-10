package hr.java.restaurant.util;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.util.*;

public class Selection {

    public static BigDecimal getSalary(Person person) {
        if (person instanceof Chef chef) {
            return chef.getContract().getSalary();
        } else if (person instanceof Waiter waiter) {
            return waiter.getContract().getSalary();
        } else if (person instanceof Deliverer deliverer) {
            return deliverer.getContract().getSalary();
        }
        return BigDecimal.ZERO;
    }


    public static void selectMeal(Map<Meal, List<Restaurant>> mealToRestaurants, Scanner sc) {
        System.out.println("Odaberite jelo:");
        int index = 1;
        List<Meal> mealList = new ArrayList<>(mealToRestaurants.keySet());
        for (Meal meal : mealList) {
            System.out.println(index++ + ". " + meal.getName());
        }

        int choice = -1;
        while (choice < 1 || choice > mealList.size()) {
            try {
                System.out.print("Odaberi broj jela: ");
                choice = Integer.parseInt(sc.nextLine());

                if (choice < 1 || choice > mealList.size()) {
                    System.out.println("Pogrešan unos. Molimo odaberite broj od 1 do " + mealList.size() + ".");
                }

            } catch (NumberFormatException e) {
                System.out.println("Pogrešan unos. Molimo unesite broj.");
            }
        }

        Meal selectedMeal = mealList.get(choice - 1);
        List<Restaurant> availableRestaurants = mealToRestaurants.get(selectedMeal);

        if (availableRestaurants != null && !availableRestaurants.isEmpty()) {
            System.out.println("Restorani s odabranim jelom:");
            for (Restaurant restaurant : availableRestaurants) {
                System.out.println("- " + restaurant.getName());
            }
        } else {
            System.out.println("Odabrano jelo nije dostupno u nijednom restoranu.");
        }

    }

    public static Category selectCategory(Scanner scanner, Set<Category> categories) {
        List<Category> categoryList = new ArrayList<>(categories);
        Category tempCategory;
        int indexCategory =1;
        for (Category category: categories) {
            System.out.println(indexCategory+ "." + category.getName() + ", " + category.getDescription());
            indexCategory++;
        }

        int index = InputValidatorUtils.validatePositiveInt(scanner,
                "Odabetite kojoj kategoriji pripada: ",Messages.INVALID_INT_INPUT_AND_NEGATIVE_ERROR) - 1;
        tempCategory = categoryList.get(index);
        return tempCategory;
    }


}
