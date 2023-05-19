package com.brm.pojo;

import java.util.List;

/**
 * This class represents the information for a company.
 */
public class Company {

    private String companyName;
    private List<Employee> employees;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
