package edu.uark.registerapp.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.commands.products.ProductCreateCommand;
import edu.uark.registerapp.commands.products.ProductDeleteCommand;
import edu.uark.registerapp.commands.products.ProductUpdateCommand;
import edu.uark.registerapp.models.api.ApiResponse;
import edu.uark.registerapp.models.api.Product;

@RestController
@RequestMapping(value = "/api/Employee")
public class EmployeeRestController {
	@RequestMapping(value = "/api/Employee", method = RequestMethod.POST)
	public @ResponseBody ApiResponse createEmployee(@RequestBody final Employee emplyoee, HttpServletRequest request, HttpServletResponse response ) 
	{
		//if(employeeExist && !elevated)
		/*	{
			response.sendRedirect("MainMenueVeiw.html"):
				response.setStatus(HttpStatus.FOUND);
		}
		elif(!emplyoee.getIsActive)
		{
			response.setStatus(HttpStatus.FOUND);
			response.sendRedirect("SignInView.html")
		}
				*/
		}