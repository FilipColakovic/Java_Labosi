package hr.java.restaurant.model;

import java.math.BigDecimal;

/**
 * Klasa koja predstavlja bonus koji je dodijeljen zaposleniku.
 * Bonus je izražen kao iznos u formatu {@link BigDecimal}.
 */
public record Bonus(BigDecimal amount) {

    /**
     * Konstruktor za inicijalizaciju bonusa s iznosom.
     *
     * @param amount iznos bonusa u obliku {@link BigDecimal}
     */
}
