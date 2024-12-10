package hr.java.restaurant.repository;

import hr.java.restaurant.model.Deliverer;
import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Order;
import hr.java.restaurant.model.Restaurant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrdersRepository <T extends Order>{
    public static final String ORDERS_FILE_PATH = "dat/orders.txt";

    public static final Integer NUMBER_OF_ROWS_PER_ORDER = 5;

    public T findById(Long id){
        return findAll().stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public List<T> findAll() {
        List<T> orders = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(ORDERS_FILE_PATH))) {
            List<String> fileRows = stream.collect(Collectors.toList());

            for (int i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_ORDER); i++) {
                int baseIndex = i * NUMBER_OF_ROWS_PER_ORDER;

                Long id = Long.parseLong(fileRows.get(baseIndex)); // ID narudžbe

                // Dohvati restoran prema ID-u
                Long restaurantId = Long.parseLong(fileRows.get(baseIndex + 1)); // ID restorana
                Restaurant restaurant = new RestaurantsRepository<Restaurant>().findById(restaurantId);

                // Dohvati jela prema ID-evima
                String[] mealIds = fileRows.get(baseIndex + 2).split(","); // ID-jevi jela
                List<Meal> meals = new ArrayList<>();
                for (String mealId : mealIds) {
                    Meal meal = new MealsRepository<Meal>().findById(Long.parseLong(mealId.trim()));
                    meals.add(meal);
                }

                // Dohvati dostavljača prema ID-u
                Long delivererId = Long.parseLong(fileRows.get(baseIndex + 3)); // ID dostavljača
                Deliverer deliverer = new DeliverersRepository<Deliverer>().findById(delivererId);

                // Dohvati datum i vrijeme dostave
                LocalDateTime deliveryDateAndTime = LocalDateTime.parse(fileRows.get(baseIndex + 4), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

                // Kreiraj objekt Order
                Order tempOrder = new Order(id, restaurant, meals, deliverer, deliveryDateAndTime);
                orders.add((T) tempOrder);
            }
        } catch (IOException e) {
            throw new RuntimeException("Greška prilikom čitanja datoteke narudžbi!", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Greška u formatu podataka u datoteci narudžbi!", e);
        }

        return orders;
    }

}
