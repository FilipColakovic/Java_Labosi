package hr.java.restaurant.repository;

import hr.java.restaurant.model.Bonus;
import hr.java.restaurant.model.Contract;
import hr.java.restaurant.model.Waiter;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaitersRepository <T extends Waiter> {
    public static final String WAITERS_FILE_PATH = "dat/waiters.txt";
    public static final Integer NUMBER_OF_ROWS_PER_WAITER = 5;

    public T findById(Long id){
        return findAll().stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public List<T> findAll() {
        List<T> waiters = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(WAITERS_FILE_PATH))) {
            List<String> fileRows = stream.collect(Collectors.toList());

            for (int i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_WAITER); i++) {
                int baseIndex = i * NUMBER_OF_ROWS_PER_WAITER;

                Long id = Long.parseLong(fileRows.get(baseIndex)); // ID
                String firstName = fileRows.get(baseIndex + 1); // Ime
                String lastName = fileRows.get(baseIndex + 2); // Prezime

                // Dohvati ugovor prema ID-u
                Long contractId = Long.parseLong(fileRows.get(baseIndex + 3)); // ID ugovora
                Contract contract = new ContractsRepository<Contract>().findById(contractId);

                // Bonus konobara
                BigDecimal bonusAmount = new BigDecimal(fileRows.get(baseIndex + 4));

                // Kreiraj objekt Waiter
                Bonus bonus = new Bonus(bonusAmount);
                Waiter tempWaiter = new Waiter(firstName, lastName, contract, bonus);
                waiters.add((T) tempWaiter);
            }
        } catch (IOException e) {
            throw new RuntimeException("Greška prilikom čitanja datoteke konobara!", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Greška u formatu podataka u datoteci konobara!", e);
        }

        return waiters;
    }

}
