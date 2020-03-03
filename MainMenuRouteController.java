package edu.uark.registerapp.controllers;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.employees.EmployeeQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Employee;

@Controller
@RequestMapping(value = "/mainMenu")
public class MainMenuRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start() {
		return (new ModelAndView(ViewNames.MAIN_MENU.getViewName()))
			.addObject(
				ViewModelNames.Employee.getValue(),
				(new Employee()).setLookupCode(StringUtils.EMPTY).setCount(0));
	}

	

	// Properties
	@Autowired
	private EmployeeQuery employeeQuery;
}
