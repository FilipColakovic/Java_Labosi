package hr.java.restaurant.repository;

import hr.java.restaurant.model.Category;
import hr.java.restaurant.model.Ingredient;
import hr.java.restaurant.model.Meal;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MealsRepository <T extends Meal> {

    public static final String MEALS_FILE_PATH = "dat/meals.txt";
    public static final Integer NUMBER_OF_ROWS_PER_MEAL = 5;

    public T findById(Long id){
        return findAll().stream().filter(temp -> id.equals(temp.getId())).findFirst().orElse(null);
    }

    public List<T> findAll() {
        List<T> meals = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(MEALS_FILE_PATH))) {
            List<String> fileRows = stream.collect(Collectors.toList());

            for (int i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_MEAL); i++) {
                int baseIndex = i * NUMBER_OF_ROWS_PER_MEAL;

                Long id = Long.parseLong(fileRows.get(baseIndex)); // ID
                String name = fileRows.get(baseIndex + 1); // Ime jela

                // Dohvati kategoriju prema ID-u
                Long categoryId = Long.parseLong(fileRows.get(baseIndex + 2)); // ID kategorije
                Category category = new CategoriesRepository<Category>().findById(categoryId);

                // Sastojci - pretpostavljamo da je lista ID-eva sastojaka odvojena zarezom
                String[] ingredientIds = fileRows.get(baseIndex + 3).split(",");
                Set<Ingredient> ingredients = new HashSet<>();
                for (String ingredientId : ingredientIds) {
                    Long ingredientIdLong = Long.parseLong(ingredientId);
                    Ingredient ingredient = new IngredientsRepository<Ingredient>().findById(ingredientIdLong);
                    ingredients.add(ingredient);
                }

                // Cijena jela
                BigDecimal price = new BigDecimal(fileRows.get(baseIndex + 4));

                // Kreiraj objekt Meal
                Meal tempMeal = new Meal(id, name, category, ingredients, price);
                meals.add((T) tempMeal);
            }
        } catch (IOException e) {
            throw new RuntimeException("Greška prilikom čitanja datoteke jela!", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Greška u formatu podataka u datoteci jela!", e);
        }

        return meals;
    }
}
