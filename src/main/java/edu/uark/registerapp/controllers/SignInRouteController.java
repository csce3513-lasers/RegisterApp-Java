package edu.uark.registerapp.controllers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery;
import edu.uark.registerapp.commands.employees.EmployeeSignInCommand;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.controllers.BaseRouteController;

@Controller
@RequestMapping(value = "/")
public class SignInRouteController extends BaseRouteController {
	// TODO: Route for initial page load

    @RequestMapping(method = RequestMethod.GET)  
    public ModelAndView showSignIn(@RequestParam Map<String, String> map) {
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
	public ModelAndView performSignIn(EmployeeSignIn employeeSignIn, HttpServletRequest request) {

        employeeSignIn.setEmployeeId(req.getParameter("empID"));
        employeeSignIn.setPassword(req.getParameter("passWord"));
		
        try {
            EmployeeSignInCommand employeeSignInCommand = new EmployeeSignInCommand(employeeSignIn, request.getSession().getId());
            return new ModelAndView(REDIRECT_PREPEND.concat(ViewNames.MAIN_MENU.getRoute()));
        }
        catch(UnprocessableEntityException e){ //could not catch another NotFoundException
            ModelAndView modelAndView = new ModelAndView("signInView");
            modelAndView.addObject("errorMessage", "Sign-in unsuccessful. Please try again.");
			return modelAndView;
        }
		
	}
}
