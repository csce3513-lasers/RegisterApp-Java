package edu.uark.registerapp.controllers;

import edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery;
import edu.uark.registerapp.commands.employees.EmployeeSignInCommand;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.EmployeeSignIn;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")

public class SignInRouteController {
    //controller implented here   
    @RequestMapping(method = RequestMethod.GET)  
    public ModelAndView signInGetRoute(@RequestParam Map<String, String> map) {
        ModelAndView modelAndView;
        try {
            //if employee exists, serve this page
            ActiveEmployeeExistsQuery activeEmployeeExistsQuery = new ActiveEmployeeExistsQuery();
            modelAndView = new ModelAndView("signInView");
        }
        catch(NotFoundException e) {
            modelAndView = new ModelAndView("redirect:/employeeDetail");
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView signInPostRoute(EmployeeSignIn employeeSignIn, HttpServletRequest req) {
        ModelAndView modelAndView;
        employeeSignIn.setEmployeeId(req.getParameter("empID"));
        employeeSignIn.setPassword(req.getParameter("passWord"));
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        try {
            EmployeeSignInCommand employeeSignInCommand = new EmployeeSignInCommand(employeeSignIn, sessionId);
            modelAndView = new ModelAndView("redirect:/mainMenu"); 
        }
        catch(UnprocessableEntityException e){ //could not catch another NotFoundException
            modelAndView = new ModelAndView("signInView");
            modelAndView.addObject("errorMessage", "Sign-in unsuccessful. Please try again.");
        }
        return modelAndView;
    }
}
