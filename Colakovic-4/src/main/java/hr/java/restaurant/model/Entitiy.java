package hr.java.restaurant.model;

/**
 * Represents an abstract entity with a unique identifier.
 */
public abstract class Entitiy {

    protected long id;

    /**
     * Constructs an Entity object with the specified ID.
     *
     * @param id the unique identifier for the entity
     */
    public Entitiy(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
