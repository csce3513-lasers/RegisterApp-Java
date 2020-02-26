
package commands.employees;

import models.API.EmployeeSignIn;

public class EmployeeSignInCommand {
    EmployeeSignIn employeeSignIn;
    
    public EmployeeSignInCommand(EmployeeSignIn employeeSignIn) {
        this.employeeSignIn = employeeSignIn; 
    }
}
