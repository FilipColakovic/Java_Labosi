package hr.java.restaurant.model;

import java.util.Objects;

/**
 * Represents a waiter in the restaurant system.
 * This class extends {@link Person} and contains information about the waiter's contract and bonus.
 */
public class Waiter extends Person {

    private Contract contract;
    private final Bonus bonus;

    /**
     * Constructs a new {@code Waiter} with the specified details.
     *
     * @param firstName the first name of the waiter
     * @param lastName the last name of the waiter
     * @param contract the contract of the waiter
     * @param bonus the bonus associated with the waiter
     */
    public Waiter(String firstName, String lastName, Contract contract, Bonus bonus) {
        super(firstName, lastName);
        this.contract = contract;
        this.bonus = bonus;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
    /**
     * A builder class for creating {@code Waiter} instances.
     */
    public static class WaiterBuilder{
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;

        public WaiterBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public WaiterBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public WaiterBuilder setContract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public WaiterBuilder setBonus(Bonus bonus) {
            this.bonus = bonus;
            return this;
        }

        public Waiter build(){
            return new Waiter(firstName, lastName, contract, bonus);

        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            WaiterBuilder that = (WaiterBuilder) o;
            return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(contract, that.contract) && Objects.equals(bonus, that.bonus);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, contract, bonus);
        }
    }
}
