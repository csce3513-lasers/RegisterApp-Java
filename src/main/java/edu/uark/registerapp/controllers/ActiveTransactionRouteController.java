package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.activeTransactions.ActiveTransactionQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.ActiveTransaction;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.enums.EmployeeClassification;

@Controller
@RequestMapping(value = "/activeTransaction")
public class ActiveTransactionRouteController extends BaseRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start(
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {
		final Optional<ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);
		if (!activeUserEntity.isPresent()) {
			return this.buildInvalidSessionResponse();
		} else if (!this.isElevatedUser(activeUserEntity.get())) {
			return this.buildNoPermissionsResponse(
				ViewNames.ACTIVE_TRANSACTION.getRoute());
		}

		final ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.ACTIVE_TRANSACTION.getViewName()),
				queryParameters);

		modelAndView.addObject(
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			true);
		modelAndView.addObject(
			ViewModelNames.ACTIVE_TRANSACTION.getValue(),
			(new ActiveTransaction()).setLookupCode(StringUtils.EMPTY).setCount(0));

		return modelAndView;
	}

	@RequestMapping(value = "/{activeTransactionId}", method = RequestMethod.GET)
	public ModelAndView startWithActiveTransaction(
		@PathVariable final UUID activeTransactionId,
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {

		final Optional<ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);
		if (!activeUserEntity.isPresent()) {
			return this.buildInvalidSessionResponse();
		} else if (!this.isElevatedUser(activeUserEntity.get())) {
			return this.buildNoPermissionsResponse(
				ViewNames.ACTIVE_TRANSACTION.getRoute());
		}

		final ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.ACTIVE_TRANSACTION.getViewName()),
				queryParameters);

		modelAndView.addObject(
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			EmployeeClassification.isElevatedUser(
				activeUserEntity.get().getClassification()));

		try {
			modelAndView.addObject(
				ViewModelNames.ACTIVE_TRANSACTION.getValue(),
				this.activeTransactionQuery.setProductId(activeTransactionId).execute());
		} catch (final Exception e) {
			modelAndView.addObject(
				ViewModelNames.ERROR_MESSAGE.getValue(),
				e.getMessage());
			modelAndView.addObject(
				ViewModelNames.ACTIVE_TRANSACTION.getValue(),
				(new ActiveTransaction())
					.setCount(0)
					.setLookupCode(StringUtils.EMPTY));
		}

		return modelAndView;
	}

	// Properties
	@Autowired
	private ActiveTransactionQuery activeTransactionQuery;
}
