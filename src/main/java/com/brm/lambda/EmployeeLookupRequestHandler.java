package com.brm.lambda;


import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.brm.dao.EmployeeLookup;
import com.brm.exception.EmployeeLookupException;
import com.brm.pojo.Employee;
import com.brm.pojo.LookupParameters;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * This class is the entry point for calls to the Lambda function and handles the incoming requests.
 */
public class EmployeeLookupRequestHandler implements RequestHandler<LookupParameters, String> {

    /**
     * This method handles the incoming Lambda request and attempts to find the employee name using the specified
     * input data.
     *
     * @param params the input parameters from the Lambda function request; these should be in JSON format and include
     *               the company and identifier of the employee to be located
     * @param context the Lambda execution environment context object
     * @return the string that indicates the name of the employee
     */
    public String handleRequest(LookupParameters params, Context context) {
        try {
            EmployeeLookup employeeLookup = EmployeeLookup.instance();
            Employee employeeData = employeeLookup.findEmployeeByCompanyAndId(params.getCompany(), params.getId());
            if (employeeData != null) {
                return "The name of the employee is " + employeeData.getName() + ".";
            }
            else {
                return "The employee could not be located.";
            }
        }
        catch (EmployeeLookupException ele) {
            return "The employee could not be located due to an error. " + ele.getMessage();
        }
    }
}
