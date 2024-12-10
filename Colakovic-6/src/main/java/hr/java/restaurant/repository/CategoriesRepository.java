package hr.java.restaurant.repository;

import hr.java.restaurant.model.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoriesRepository<T extends Category> {

    public static final String CATEGORIES_FILE_PATH ="dat/categories.txt";
    public static final Integer NUMBER_OF_ROWS_PER_CATEGORY = 3;

    public T findById(Long id){
        return findAll().stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public List<T> findAll(){
        List<T> categories = new ArrayList<T>();

        try(Stream<String> stream = Files.lines(Paths.get(CATEGORIES_FILE_PATH))) {
            List<String> fileRows = stream.collect(Collectors.toList());

            for(Integer i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_CATEGORY); i++) {
                Long id = Long.parseLong(fileRows.get(i * NUMBER_OF_ROWS_PER_CATEGORY));
                String name = fileRows.get(i * NUMBER_OF_ROWS_PER_CATEGORY + 1);
                String description = fileRows.get(i * NUMBER_OF_ROWS_PER_CATEGORY + 2);
                Category tempCategory = new Category(id, name, description);
                categories.add((T) tempCategory);
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return categories;
    }
}
