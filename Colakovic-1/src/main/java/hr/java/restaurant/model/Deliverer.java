package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Deliverer {
    private String firstName, lastName;
    private BigDecimal salary;
    private int deliveryCount;

    public Deliverer(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.deliveryCount = 0;
    }

    public int getDeliveryCount() {
        return deliveryCount;
    }

    public void incrementDeliveryCount() {
        this.deliveryCount++;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
