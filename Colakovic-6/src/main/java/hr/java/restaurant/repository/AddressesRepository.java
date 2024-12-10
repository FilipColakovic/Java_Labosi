package hr.java.restaurant.repository;

import hr.java.restaurant.model.Address;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressesRepository <T extends Address>{

    public static final String ADDRESSES_FILE_PATH = "dat/addresses.txt";

    public static final Integer NUMBER_OF_ROWS_PER_ADDRESS = 5;

    public T findById(Long id){
        return findAll().stream().filter(temp -> id.equals(temp.getId())).findFirst().orElse(null);
    }

    public List<T> findAll() {
        List<T> addresses = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(ADDRESSES_FILE_PATH))) {
            List<String> fileRows = stream.toList();

            for (int i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_ADDRESS); i++) {
                int baseIndex = i * NUMBER_OF_ROWS_PER_ADDRESS;

                Long id = Long.parseLong(fileRows.get(baseIndex)); // ID
                String street = fileRows.get(baseIndex + 1); // Ulica
                String houseNumber = fileRows.get(baseIndex + 2); // Broj kuće
                String city = fileRows.get(baseIndex + 3); // Grad
                String postalCode = fileRows.get(baseIndex + 4); // Poštanski broj

                // Kreiraj objekt Address
                Address tempAddress = new Address(id, street, houseNumber, city, postalCode);
                addresses.add((T) tempAddress);
            }
        } catch (IOException e) {
            throw new RuntimeException("Greška prilikom čitanja datoteke adresa!", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Greška u formatu podataka u datoteci adresa!", e);
        }

        return addresses;
    }


    public void save(List<T> entities) {
        try(PrintWriter writer = new PrintWriter(ADDRESSES_FILE_PATH)) {
            for (T entity : entities) {
                writer.println(entity.getId());
                writer.println(entity.getStreet());
                writer.println(entity.getHouseNumber());
                writer.println(entity.getCity());
                writer.println(entity.getPostalCode());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
