package edu.uark.registerapp.controllers;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;

@Controller
@RequestMapping(value = "/employeeDetail")
public class EmployeeDetailRouteController extends HttpServlet {
	@RequestMapping(method = RequestMethod.GET)
	
	public ModelAndView EmployeeView(@RequestParam Map<String,String> Arguments, HttpServletRequest request )
	{
		
		/*/ if (!employeeExists || !elavated)
		{
			ModelAndView m = new ModelAndView("EmployeeDetail.html");
        	m.setViewName("employee detail")
        	return m;
        	
        	
		}
		else if (!requst.isRequestedSessionIdValid)
        {
        	ModelAndView m = new ModelAndView("SignInVeiw.html");
        	m.setViewName("sign in")
        	m.setErrorMessage("Invalid session id");
        	return m;
        }
        else
        {
        	return new ModelAndView("MainMenueVeiw.html");
        }*/
		
		
		@RequestMapping(value = "/employeeDetail/{employeeId}", method = RequestMethod.GET)
	public ModelAndView existingEmployee(@PathVariable Map<String, String> pathVarsMap, HttpServletRequest request )
	{
		
	}
	
	
	
	
	