package hr.java.restaurant.model;

/**
 * Represents a Deliverer (employee) with a contract, delivery count, and a bonus.
 */
public class Deliverer extends Person {
    private Contract contract;
    private int deliveryCount = 0;
    private Bonus bonus;

    /**
     * Constructs a Deliverer object with the specified first name, last name, contract, and bonus.
     *
     * @param firstName the first name of the deliverer
     * @param lastName  the last name of the deliverer
     * @param contract  the contract associated with the deliverer
     * @param bonus     the bonus associated with the deliverer
     */
    public Deliverer(String firstName, String lastName, Contract contract, Bonus bonus) {
        super(firstName, lastName);
        this.contract = contract;
        this.bonus = bonus;
        this.deliveryCount = 0;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(int deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
    /**
     * Increments the number of deliveries made by the deliverer.
     */
    public void incrementDeliveryCount(){
        deliveryCount++;
    }
    /**
     * Builder pattern for constructing a Deliverer object.
     */
    public static class DelivererBuilder{
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;
        private int deliveryCount = 0;

        public DelivererBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public DelivererBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public DelivererBuilder setContract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public DelivererBuilder setBonus(Bonus bonus){
            this.bonus = bonus;
            return  this;
        }

        public Deliverer build(){
            return new Deliverer(firstName, lastName, contract, bonus);
        }
    }
}
