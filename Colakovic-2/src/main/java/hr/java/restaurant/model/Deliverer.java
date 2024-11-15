package hr.java.restaurant.model;

public class Deliverer extends Person{
    private Contract contract;
    private int deliveryCount = 0;
    private Bonus bonus;

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

    public void incrementDeliveryCount(){
        deliveryCount++;
    }

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
