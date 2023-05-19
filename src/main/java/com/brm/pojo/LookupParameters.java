package com.brm.pojo;

/**
 * This class represents the lookup parameters passed from the Lambda to be utilized to find the employee.
 */
public class LookupParameters {

    private String company;
    private int id;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
