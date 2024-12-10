package hr.java.restaurant.sort;

import hr.java.restaurant.model.Chef;
import hr.java.restaurant.model.Deliverer;
import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Waiter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class ContractDurationComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        long duration1 = getContractDuration(p1);
        long duration2 = getContractDuration(p2);

        return Long.compare(duration1, duration2); // Sortira od najkraćeg do najdužeg
    }

    private long getContractDuration(Person person) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (person instanceof Chef) {
            startDate = ((Chef) person).getContract().getStartDate();
            endDate = ((Chef) person).getContract().getEndDate();
        } else if (person instanceof Waiter) {
            startDate = ((Waiter) person).getContract().getStartDate();
            endDate = ((Waiter) person).getContract().getEndDate();
        } else if (person instanceof Deliverer) {
            startDate = ((Deliverer) person).getContract().getStartDate();
            endDate = ((Deliverer) person).getContract().getEndDate();
        }
        if (startDate != null && endDate != null) {
            return ChronoUnit.DAYS.between(startDate, endDate);
        }
        return 0;
    }
}
