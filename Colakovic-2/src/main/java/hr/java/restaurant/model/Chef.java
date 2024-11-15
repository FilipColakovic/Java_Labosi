package hr.java.restaurant.model;

public class Chef extends Person{
    private Contract contract;
    private Bonus bonus;

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

        public Chef build(){
            return new Chef(firstName, lastName, contract, bonus);
        }
    }
}
