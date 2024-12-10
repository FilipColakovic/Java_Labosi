package hr.java.restaurant.model;

import java.util.Objects;

/**
 * Represents a Chef, a specific type of employee in the restaurant.
 */
public class Chef extends Person {
    private Contract contract;
    private Bonus bonus;

    /**
     * Constructs a Chef with the specified first name, last name, contract, and bonus.
     *
     * @param firstName the first name of the chef
     * @param lastName  the last name of the chef
     * @param contract  the contract details of the chef
     * @param bonus     the bonus details of the chef
     */
    public Chef(String firstName, String lastName, Contract contract, Bonus bonus) {
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

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
    /**
     * Builder class to create instances of {@link Chef}.
     */
    public static class ChefBuilder{
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;

        public ChefBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ChefBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ChefBuilder setContract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public ChefBuilder setBonus(Bonus bonus) {
            this.bonus = bonus;
            return this;
        }
        /**
         * Builds and returns a Chef instance.
         *
         * @return the created Chef object
         */
        public Chef build(){
            return new Chef(firstName, lastName, contract, bonus);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            ChefBuilder that = (ChefBuilder) o;
            return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(contract, that.contract) && Objects.equals(bonus, that.bonus);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName, contract, bonus);
        }
    }
}
