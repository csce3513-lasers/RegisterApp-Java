package edu.uark.registerapp.commands.employees;

import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.commands.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;


public class ActiveEmployeeExistsQuery {
    public ActiveEmployeeExistsQuery() {
        if (!employeeRepository.existsByIsActive(true)) {
            throw new NotFoundException("No active employee exists.");
        }
    }
    
    @Autowired
    EmployeeRepository employeeRepository;
}
