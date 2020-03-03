package commands.activeUsers;

import models.api.EmployeeSignIn;
import org.apache.commons.lang3.StringUtils;

public class ActiveUserDeleteCommand {
    String sessionKey;
    
    public ActiveUserDeleteCommand(EmployeeSignIn employeeSignIn) {
        /*if (StringUtils.isNumeric(employeeSignIn.getEmployeeId())) { // only true if contains numeric values
            if(!employeeSignIn.getPassword().isBlank()) {
                final Optional<UserEntity> userEntity = ActiveUserRespository.findBySessionKey(sessionKey);
                if(userEntity.isPresent()) {
                    ActiveUserRespository.delete(userEntity.get());
                }
                
            }
        }*/
    }
}
