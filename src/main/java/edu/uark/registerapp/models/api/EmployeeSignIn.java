package edu.uark.registerapp.models.api;

import javax.validation.constraints.NotNull;

public class EmployeeSignIn {

    @NotNull
    private String employeeId;

    @NotNull
    private String password;

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPassword() {
        return password;
    }

}
