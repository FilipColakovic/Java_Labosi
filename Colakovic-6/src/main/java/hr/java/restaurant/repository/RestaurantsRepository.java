package hr.java.restaurant.repository;

import hr.java.restaurant.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestaurantsRepository<T extends Restaurant>{

    public static final String RESTAURANTS_FILE_PATH = "dat/restaurants.txt";

    public static final Integer NUMBER_OF_ROWS_PER_RESTAURANT = 7;

    public T findById(Long id){
        return findAll().stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public List<T> findAll() {
        List<T> restaurants = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(RESTAURANTS_FILE_PATH))) {
            List<String> fileRows = stream.collect(Collectors.toList());

            for (int i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_RESTAURANT); i++) {
                int baseIndex = i * NUMBER_OF_ROWS_PER_RESTAURANT;

                Long id = Long.parseLong(fileRows.get(baseIndex)); // ID
                String name = fileRows.get(baseIndex + 1); // Ime restorana

                // Dohvati adresu prema ID-u
                Long addressId = Long.parseLong(fileRows.get(baseIndex + 2)); // ID adrese
                Address address = new AddressesRepository<Address>().findById(addressId);

                // Jela - pretpostavljamo da je lista ID-eva jela odvojena zarezima
                String[] mealIds = fileRows.get(baseIndex + 3).split(",");
                Set<Meal> meals = new HashSet<>();
                for (String mealId : mealIds) {
                    Long mealIdLong = Long.parseLong(mealId);
                    Meal meal = new MealsRepository<Meal>().findById(mealIdLong);
                    meals.add(meal);
                }

                // Kuhari - pretpostavljamo da je lista ID-eva kuhara odvojena zarezima
                String[] chefIds = fileRows.get(baseIndex + 4).split(",");
                Set<Chef> chefs = new HashSet<>();
                for (String chefId : chefIds) {
                    Long chefIdLong = Long.parseLong(chefId);
                    Chef chef = new ChefsRepository<Chef>().findById(chefIdLong);
                    chefs.add(chef);
                }

                // Konobari - pretpostavljamo da je lista ID-eva konobara odvojena zarezima
                String[] waiterIds = fileRows.get(baseIndex + 5).split(",");
                Set<Waiter> waiters = new HashSet<>();
                for (String waiterId : waiterIds) {
                    Long waiterIdLong = Long.parseLong(waiterId);
                    Waiter waiter = new WaitersRepository<Waiter>().findById(waiterIdLong);
                    waiters.add(waiter);
                }

                // Dostavljači - pretpostavljamo da je lista ID-eva dostavljača odvojena zarezima
                String[] delivererIds = fileRows.get(baseIndex + 6).split(",");
                Set<Deliverer> deliverers = new HashSet<>();
                for (String delivererId : delivererIds) {
                    Long delivererIdLong = Long.parseLong(delivererId);
                    Deliverer deliverer = new DeliverersRepository<Deliverer>().findById(delivererIdLong);
                    deliverers.add(deliverer);
                }

                // Kreiraj objekt Restaurant
                Restaurant tempRestaurant = new Restaurant(id, name, address, meals, chefs, waiters, deliverers);
                restaurants.add((T) tempRestaurant);
            }
        } catch (IOException e) {
            throw new RuntimeException("Greška prilikom čitanja datoteke restorana!", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Greška u formatu podataka u datoteci restorana!", e);
        }

        return restaurants;
    }

}
