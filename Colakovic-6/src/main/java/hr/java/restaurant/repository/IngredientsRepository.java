package hr.java.restaurant.repository;

import hr.java.restaurant.model.Category;
import hr.java.restaurant.model.Ingredient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IngredientsRepository <T extends Ingredient>{

    public static final String INGREDIANTS_FILE_PATH ="dat/ingrediants.txt";
    public static final Integer NUMBER_OF_ROWS_PER_INGREDIANT = 5;

    public T findById(Long id){
        return findAll().stream().filter(temp -> id.equals(temp.getId())).findFirst().orElse(null);
    }

    public List<T> findAll(){
        List<T> ingrediants = new ArrayList<T>();

        try(Stream<String> stream = Files.lines(Path.of(INGREDIANTS_FILE_PATH))) {
            List<String> fileRows = stream.collect(Collectors.toList());

            for(Integer i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_INGREDIANT); i++) {
                Long id = Long.parseLong(fileRows.get(i * NUMBER_OF_ROWS_PER_INGREDIANT));
                String name = fileRows.get(i * NUMBER_OF_ROWS_PER_INGREDIANT + 1);
                Category category = new CategoriesRepository<Category>()
                        .findById(Long.parseLong(fileRows.get(i*NUMBER_OF_ROWS_PER_INGREDIANT + 2)));
                BigDecimal kcal = new  BigDecimal(fileRows.get(i * NUMBER_OF_ROWS_PER_INGREDIANT + 3));
                String preparationMethod = fileRows.get(i * NUMBER_OF_ROWS_PER_INGREDIANT + 4);

                Ingredient tempIngrediant = new Ingredient(id, name, category, kcal, preparationMethod);
                ingrediants.add((T) tempIngrediant);
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return ingrediants;
    }
}
