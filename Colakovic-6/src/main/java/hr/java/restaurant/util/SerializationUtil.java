package hr.java.restaurant.util;

import hr.java.restaurant.model.Contract;
import hr.java.restaurant.model.Order;
import hr.java.restaurant.model.Restaurant;

import java.io.*;
import java.util.List;

public class SerializationUtil {

    public static void serializeRestaurants(List<Restaurant> restaurants, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(restaurants);
            System.out.println("Restorani su uspješno serijalizirani.");
        } catch (IOException e) {
            System.err.println("Greška pri serijalizaciji restorana: " + e.getMessage());
        }
    }

    public static void serializeOrders(List<Order> orders, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(orders);
            System.out.println("Narudžbe su uspješno serijalizirane.");
        } catch (IOException e) {
            System.err.println("Greška pri serijalizaciji narudžbi: " + e.getMessage());
        }
    }

    public static void serializeContracts(List<Contract> contracts, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(contracts);
            System.out.println("Ugovori su uspješno serijalizirani.");
        } catch (IOException e) {
            System.err.println("Greška pri serijalizaciji ugovora: " + e.getMessage());
        }
    }

    public static List<Restaurant> deserializeRestaurants(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Restaurant>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Greška pri deserijalizaciji restorana: " + e.getMessage());
            return null;
        }
    }

    public static List<Order> deserializeOrders(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Order>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Greška pri deserijalizaciji narudžbi: " + e.getMessage());
            return null;
        }
    }

    public static List<Contract> deserializeContracts(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Contract>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Greška pri deserijalizaciji ugovora: " + e.getMessage());
            return null;
        }
    }
}
