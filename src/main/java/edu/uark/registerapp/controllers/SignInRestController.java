package edu.uark.registerapp.controllers;

import edu.uark.registerapp.commands.activeUsers.ActiveUserDeleteCommand;
import edu.uark.registerapp.models.api.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInRestController {
    @RequestMapping(value = "/api/signOut", method = RequestMethod.DELETE) 
    public @ResponseBody ApiResponse deleteProduct(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String sessionId = session.getId();
	this.activeUserDeleteCommand.setSessionKey(sessionId).execute();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setRedirectUrl("/");
	return apiResponse;
    }
    
    // Properties
    @Autowired
    private ActiveUserDeleteCommand activeUserDeleteCommand;

}
