package commands.employees;
import java.lang.*;
import org.springframework.security.acls.model.NotFoundException;


public class ActiveEmployeeExistsQuery {

    public ActiveEmployeeExistsQuery() {
        /*if (!EmployeeRepository.existsByIsActive()) {
            throw new NotFoundException("No active employee exists.");
        }*/
    }
}