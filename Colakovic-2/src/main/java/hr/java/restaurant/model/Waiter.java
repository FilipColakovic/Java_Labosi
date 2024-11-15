package hr.java.restaurant.model;

public class Waiter extends Person{

    private Contract contract;
    private Bonus bonus;

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
    }
}
