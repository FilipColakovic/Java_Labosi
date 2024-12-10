package hr.java.restaurant.model;

/**
 * Represents a person with a first name and a last name.
 * This is an abstract class that serves as a base for different types of employees.
 */
public abstract class Person {

    protected Long id;
    protected String firstName;
    protected String lastName;

    /**
     * Constructs a new {@code Person} with the specified first name and last name.
     *
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }
}
