package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery;
import edu.uark.registerapp.controllers.enums.QueryParameterMessages;
import edu.uark.registerapp.controllers.enums.QueryParameterNames;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.ActiveUserEntity;

@Controller
@RequestMapping(value = "/employeeDetail")
public class EmployeeDetailRouteController extends BaseRouteController {

	private static final Optional<ActiveUserEntity> CurrentUser = null;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start(
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) 
	{
		final Optional <ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);
			
		// TODO: Logic to determine if the user associated with the current session


		if(isElevatedUser(activeUserEntity))

		{
			return new ModelAndView(
			REDIRECT_PREPEND.concat(
				ViewNames.EmployeeDetail.getRoute().concat(
					this.buildInitialQueryParameter(
						QueryParameterNames.ERROR_CODE.getValue(),
						QueryParameterMessages.SESSION_NOT_ACTIVE.getKeyAsString()))));
		}
		else if(activeUserEntity==null)
		{
			return buildInvalidSessionResponse();
		}
		else
		{
			return buildNoPermissionsResponse();
		}
		
		//  is able to create an employee
		
		

		
	}

	@RequestMapping(value = "/employeeDetail/{employeeId}", method = RequestMethod.GET)
	public ModelAndView startWithEmployee(
		@PathVariable final UUID employeeId,
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {

		final Optional<ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);

		if (!activeUserEntity.isPresent()) {
			return this.buildInvalidSessionResponse();
		} else if (!this.isElevatedUser(activeUserEntity)) {
			return this.buildNoPermissionsResponse();
		}

		// TODO: Query the employee details using the request route parameter
		else
		{/*
			UUID employee=queryParameters[employeeId];
			EmployeeQuery query=this.setEmployeeRecordId(employee).execute();
		}*/
		// TODO: Serve up the page
		return new ModelAndView(ViewModelNames.EMPLOYEE_TYPES.getValue());
	}

	// Helper methods
	/*private boolean activeUserExists() {
		// TODO: Helper method to determine if any active users Exist

		

		

		{
			return true;
		}
		
		{
			return false;
		}
	}*/
}
}