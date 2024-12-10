package hr.java.restaurant.sort;

import hr.java.restaurant.model.Chef;
import hr.java.restaurant.model.Deliverer;
import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Waiter;

import java.math.BigDecimal;
import java.util.Comparator;

public class SalaryComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        BigDecimal salary1 = getSalary(p1);
        BigDecimal salary2 = getSalary(p2);

        return salary2.compareTo(salary1);
    }

    public BigDecimal getSalary(Person person) {
        BigDecimal salary = BigDecimal.ZERO;

        if (person instanceof Chef) {
            salary = ((Chef) person).getContract().getSalary();
        } else if (person instanceof Waiter) {
            salary = ((Waiter) person).getContract().getSalary();
        } else if (person instanceof Deliverer) {
            salary = ((Deliverer) person).getContract().getSalary();
        }

        return salary;
    }
}