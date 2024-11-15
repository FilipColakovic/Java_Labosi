package hr.java.restaurant.model;

public class Address  extends Entitiy{
    private String street, houseNumber, city, postalCode;

    public Address(long id, String street, String houseNumber, String city, String postalCode) {
        super(id);
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public static class AddressBuilder{
        private long id;
        private String street, houseNumber, city, postalCode;

        public AddressBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public AddressBuilder setStreet(String street) {
            this.street = street;
            return this;
        }
        public AddressBuilder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }
        public AddressBuilder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Address build(){
            return new Address(id, street,houseNumber,city,postalCode);
        }
    }
}
