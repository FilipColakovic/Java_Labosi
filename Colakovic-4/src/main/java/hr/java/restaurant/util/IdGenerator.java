package hr.java.restaurant.util;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class IdGenerator {

    public static <T> long generateNextId(Collection<T> items, Function<T, Long> idExtractor) {
        return items.stream()
                .filter(Objects::nonNull) // Izbjegavanje null vrijednosti
                .map(idExtractor)        // Ekstrakcija ID-a pomoću predanog `Function`
                .max(Long::compare)      // Pronalaženje maksimalnog ID-a
                .orElse(0L) + 1;         // Započni od 1 ako nema elemenata
    }
}
