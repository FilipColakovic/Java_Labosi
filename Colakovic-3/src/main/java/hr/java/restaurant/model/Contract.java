package hr.java.restaurant.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a contract for an employee with details such as salary, start and end dates, and contract type.
 */
public class Contract {

    private BigDecimal salary;
    private LocalDate startDate, endDate;
    private ContractType contractType;

    /**
     * Enum representing the two possible types of contracts: Full-time and Part-time.
     */
    public enum ContractType {
        FULL_TIME,
        PART_TIME
    }

    /**
     * Constructs a contract with the specified salary, start date, end date, and contract type.
     *
     * @param salary       the salary associated with the contract
     * @param startDate    the start date of the contract
     * @param endDate      the end date of the contract
     * @param contractType the type of contract (full-time or part-time)
     */
    public Contract(BigDecimal salary, LocalDate startDate, LocalDate endDate, ContractType contractType) {
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractType = contractType;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "salary=" + salary +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", contractType=" + contractType +
                '}';
    }
}
