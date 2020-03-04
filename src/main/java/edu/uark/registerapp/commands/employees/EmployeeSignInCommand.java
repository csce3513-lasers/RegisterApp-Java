package edu.uark.registerapp.commands.employees;

import java.util.Arrays;
import java.util.Optional;

import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeSignInCommand {
    EmployeeSignIn employeeSignIn;
    String sessionKey;

    public EmployeeSignInCommand(EmployeeSignIn employeeSignIn, String sessionKey) {
        this.employeeSignIn = employeeSignIn;
        this.sessionKey = sessionKey;
	this.validateProperties();
	final Optional<EmployeeEntity> validEmployeeEntity = this.validateEmployee();
	this.createActiveUser(validEmployeeEntity);
    }

    // Helper methods
    private void validateProperties() {
        //Validate the incoming Employee request object, employee id should not be blank and should be a number, password should not be blank?
	if (!StringUtils.isNumeric(employeeSignIn.getEmployeeId())) { //checks numeric and blank
            throw new UnprocessableEntityException("employeeId");
        }
        if(employeeSignIn.getPassword().isBlank()) {
            throw new UnprocessableEntityException("password");
        }
    }
	
    private Optional<EmployeeEntity> validateEmployee() {
        //final Optional<EmployeeEntity> employeeEntity = employeeRepository.queryByEmployeeId(employeeSignIn.getEmployeeId()); 
        final Optional<EmployeeEntity> employeeEntity = employeeRepository.findByEmployeeId(Integer.valueOf(employeeSignIn.getEmployeeId())); //no queryByEmployeeId method, only findByEmployeeId(), takes in int, not string
        
        //Validate the incoming Employee request object, employee id should not be blank and should be a number, password should not be blank?
        if (employeeEntity.isPresent()) { //if is present employee id is not empty since it was found by id
            if(employeeEntity.get().getPassword().length == 0) { //password is byte array so check on length
                if(Arrays.equals(employeeEntity.get().getPassword(), employeeSignIn.getPassword().getBytes())) {
                    return employeeEntity;
                }
                else {
                    throw new UnprocessableEntityException("No such employee exists"); //could not catch another NotFoundException in SignInRouteController
                }
            }
            else {
                throw new UnprocessableEntityException("No such employee exists"); //could not catch another NotFoundException in SignInRouteController
            }
        }
        else {
            throw new UnprocessableEntityException("No such employee exists"); //could not catch another NotFoundException in SignInRouteController
        }
    }

    @Transactional
    private void createActiveUser(Optional<EmployeeEntity> employeeEntity) {
        final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findByEmployeeId(employeeEntity.get().getId());
        if(activeUserEntity.isPresent()) {
            activeUserEntity.get().setSessionKey(sessionKey);
            activeUserRepository.save(activeUserEntity.get());
        }
        else {
            ActiveUserEntity newActiveUserEntity = new ActiveUserEntity();
            newActiveUserEntity.setSessionKey(sessionKey);
            newActiveUserEntity.setClassification(employeeEntity.get().getClassification());
            newActiveUserEntity.setEmployeeId(employeeEntity.get().getId());
            newActiveUserEntity.setName(employeeEntity.get().getFirstName() + " " + employeeEntity.get().getLastName());
            activeUserRepository.save(newActiveUserEntity);
        }
    }
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    ActiveUserRepository activeUserRepository;
}
