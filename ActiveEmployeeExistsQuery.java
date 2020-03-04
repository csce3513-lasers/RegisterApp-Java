package edu.uark.registerapp.commands.employees;

import edu.uark.registerapp.models.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;


public class ActiveEmployeeExistsQuery {
    public ActiveEmployeeExistsQuery() {
        if (!employeeRepository.existsByIsActive(true)) {
            throw new NotFoundException("No active employee exists.");
        }
    }
    
    @Autowired
    EmployeeRepository employeeRepository;
}
