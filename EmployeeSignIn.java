package models.API;

import javax.validation.constraints.NotNull;

public class EmployeeSignIn {
    
    @NotNull
    private String employeeID;
    
    @NotNull
    private String password;
    
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmployeeID() {
        return employeeID;
    }
    
    public String getPassword() {
        return password;
    }
    
}
