package commands.employees;

import models.api.EmployeeSignIn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeSignInCommand {
/*    EmployeeSignIn employeeSignIn;
    String sessionKey;

    public EmployeeSignInCommand(EmployeeSignIn employeeSignIn, String sessionKey) {
        this.employeeSignIn = employeeSignIn;
        this.sessionKey = sessionKey;
        if(employeeValid() && employeeExists()) {
            createActiveUser();
            
        }
    }
    
    private boolean employeeValid() {
        boolean valid = false;
        if (StringUtils.isNumeric(employeeSignIn.getEmployeeId())) { // only true if contains numeric values
            if(!employeeSignIn.getPassword().isBlank()) {
                valid = true;
            }
        }
        return valid;
    }
    
    private boolean employeeExists() {
        boolean exists = false;
        final Optional<EmployeeEntity> employeeEntity = EmployeeRepository.queryByEmployeeId(employeeSignIn.getEmployeeId());
        if (employeeEntity.isPresent()) {
            if(employeeEntity.get("password").equals(employeeSignIn.getPassword())) {
                exists = true;
            }
        }
        return exists;
    }
    
    @Transactional
    private void createActiveUser() {
        final Optional<UserEntity> userEntity = ActiveUserRespository.findById(employeeSignIn.getEmployeeId());
        if(userEntity.isPresent()) {
            userEntity.sessionKey = sessionKey; 
        }
        else {
            //create new ative user
            //set stuff
        }
        ActiveUserRespository.save(userEntity.get());
    }*/
}