package controllers;

import models.api.EmployeeSignIn;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        //if employee exists, serve this page
        ModelAndView modelAndView;
        Boolean employeeExists = true; // placeholder
        if(employeeExists) {
            modelAndView = new ModelAndView("signInView");
        }
        else {
            modelAndView = new ModelAndView("employeeDetailView");
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView signInPostRoute(EmployeeSignIn employeeSignIn, HttpServletRequest req) {
        ModelAndView modelAndView;
        employeeSignIn.setEmployeeId(req.getParameter("empID"));
        employeeSignIn.setPassword(req.getParameter("passWord"));
        //check if valid
        if(employeeSignIn.getEmployeeId().equals("123") && employeeSignIn.getPassword().equals("password")) {
            HttpSession session = req.getSession(); // creates a new session if session doesn't exist
            String sessionId = session.getId();
            modelAndView = new ModelAndView("mainMenuView");
        }
        else {
            modelAndView = new ModelAndView("signInView");
            modelAndView.addObject("errorMessage", "Sign-in unsuccessful. Please try again.");
        }
        return modelAndView;
    }
}