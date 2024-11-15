package hr.java.restaurant.model;

public abstract class Entitiy {

    protected long id;

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
