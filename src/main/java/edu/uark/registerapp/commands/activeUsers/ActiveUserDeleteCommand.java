package edu.uark.registerapp.commands.activeUsers;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActiveUserDeleteCommand implements VoidCommandInterface {
	
    String sessionKey;
	
    @Transactional
    @Override
    public void execute() {
	final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findBySessionKey(sessionKey);
        if (activeUserEntity.isPresent()) {
            if(!activeUserEntity.get().getName().isBlank()) { //Validate the incoming Employee request object, first name should not be blank, last name should not be blank?
                this.activeUserRepository.delete(activeUserEntity.get());
            }
            else {
                throw new NotFoundException("Active user");
            }
	}
        else {
            throw new NotFoundException("Active user");
        }
    }

    public ActiveUserDeleteCommand setSessionKey(String sessionKey) {
	this.sessionKey = sessionKey;
	return this;
    }
	
    @Autowired
    ActiveUserRepository activeUserRepository;
}
