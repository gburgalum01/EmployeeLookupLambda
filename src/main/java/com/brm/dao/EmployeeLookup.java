package com.brm.dao;

import com.brm.exception.EmployeeLookupException;
import com.brm.pojo.Company;
import com.brm.pojo.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

/**
 * This class provides employee lookup functionality to assist in fulfilling the incoming request.
 */
public class EmployeeLookup {

    private List<Company> companyData;

    /**
     * This is the private constructor utilized to build an instance of this class.
     *
     * @throws EmployeeLookupException an exception that occurred while building an instance of this class
     */
    private EmployeeLookup() throws EmployeeLookupException {
        loadCompanyData();
    }

    /**
     * This is the method that allows a caller to build an instance of this class.
     *
     * @return an instance of this class
     * @throws EmployeeLookupException an exception that occurred while building an instance of this class
     */
    public static EmployeeLookup instance() throws EmployeeLookupException {
        return new EmployeeLookup();
    }

    /**
     * This method attempts to find the employee using the identifier of the employee and the company to which the
     * individual is associated.
     *
     * @param company the company associated with the employee to be found
     * @param id the identifier of the employee to be found
     * @return the employee with the given identifier and associated with the given company; null if not found
     * @throws EmployeeLookupException an exception that occurred while attempting to find the employee
     */
    public Employee findEmployeeByCompanyAndId(String company, int id) throws EmployeeLookupException {

        try {
            if (companyData != null) {
                Optional<Employee> employeeDataOptional = companyData.stream()
                        .filter(c -> c.getCompanyName().equalsIgnoreCase(company))
                        .flatMap(c -> c.getEmployees().stream())
                        .filter(e -> e.getId() == id)
                        .findFirst();
                return employeeDataOptional.orElse(null);
            }
            return null;
        }
        catch (Exception e) {
            throw new EmployeeLookupException("An error occurred while attempting to look up the employee.");
        }
    }

    /**
     * This method attempts to load the company data from the specified JSON file on the classpath.
     *
     * @throws EmployeeLookupException an exception that occurred while loading the company data
     */
    private void loadCompanyData() throws EmployeeLookupException {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            this.companyData = objectMapper.readValue(EmployeeLookup.class.getClassLoader().getResourceAsStream("companies.json"), new TypeReference<List<Company>>(){});
        }
        catch (Exception e) {
            throw new EmployeeLookupException("The companies information could not be loaded.");
        }
    }
}
