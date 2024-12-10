package hr.java.restaurant.model;

import java.io.Serializable;

/**
 * Klasa koja predstavlja kategoriju jela u restoranu.
 * Svaka kategorija ima naziv i opis.
 */
public class Category extends Entitiy implements Serializable {

    private String name;
    private String description;

    /**
     * Konstruktor koji inicijalizira kategoriju s nazivom i opisom.
     *
     * @param id jedinstveni identifikator kategorije
     * @param name naziv kategorije
     * @param description opis kategorije
     */
    public Category(long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
