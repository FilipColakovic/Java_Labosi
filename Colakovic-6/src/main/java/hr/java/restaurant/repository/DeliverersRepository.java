package hr.java.restaurant.repository;

import hr.java.restaurant.model.Bonus;
import hr.java.restaurant.model.Contract;
import hr.java.restaurant.model.Deliverer;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliverersRepository <T extends Deliverer>{
    public static final String DELIVERERS_FILE_PATH = "dat/deliverers.txt";
    public static final Integer NUMBER_OF_ROWS_PER_DELIVERER = 5;

    public T findById(Long id){
        return findAll().stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public List<T> findAll() {
        List<T> deliverers = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(DELIVERERS_FILE_PATH))) {
            List<String> fileRows = stream.collect(Collectors.toList());

            for (int i = 0; i < (fileRows.size() / NUMBER_OF_ROWS_PER_DELIVERER); i++) {
                int baseIndex = i * NUMBER_OF_ROWS_PER_DELIVERER;

                Long id = Long.parseLong(fileRows.get(baseIndex)); // ID
                String firstName = fileRows.get(baseIndex + 1); // Ime
                String lastName = fileRows.get(baseIndex + 2); // Prezime

                // Dohvati ugovor prema ID-u
                Long contractId = Long.parseLong(fileRows.get(baseIndex + 3)); // ID ugovora
                Contract contract = new ContractsRepository<Contract>().findById(contractId);

                // Bonus dostavljača
                BigDecimal bonusAmount = new BigDecimal(fileRows.get(baseIndex + 4));

                // Kreiraj objekt Deliverer
                Bonus bonus = new Bonus(bonusAmount);
                Deliverer tempDeliverer = new Deliverer(firstName, lastName, contract, bonus);
                deliverers.add((T) tempDeliverer);
            }
        } catch (IOException e) {
            throw new RuntimeException("Greška prilikom čitanja datoteke dostavljača!", e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Greška u formatu podataka u datoteci dostavljača!", e);
        }

        return deliverers;
    }

}
