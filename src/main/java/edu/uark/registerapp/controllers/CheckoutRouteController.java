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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.transactions.TransactionEntryQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.EmployeeClassification;
import edu.uark.registerapp.models.api.Transaction;
import edu.uark.registerapp.models.entities.ActiveUserEntity;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutRouteController extends BaseRouteController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ModelAndView showPage(@RequestParam final Map<String, String> queryParameters, final HttpServletRequest request) {
        
        //CHECK IF SIGNED IN
        final Optional<ActiveUserEntity> activeUserEntity = this.getCurrentUser(request);
        if (!activeUserEntity.isPresent()) {
            return this.buildInvalidSessionResponse();
        }
        else {
            return new ModelAndView("ActiveTransactionView"); //CHANGE TO ENUM
        }
        
    }
	@RequestMapping(value = "/{transactionID}", method = RequestMethod.GET)
	public ModelAndView startWithTransaction(
		@PathVariable final UUID transactionId,
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {

		final Optional<ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);
		if (!activeUserEntity.isPresent()) {
			return this.buildInvalidSessionResponse();
		}

		final ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.TRANSACTION_VIEW.getViewName()),
				queryParameters);

		modelAndView.addObject(
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			EmployeeClassification.isElevatedUser(
				activeUserEntity.get().getClassification()));

		try {
			modelAndView.addObject(
				ViewModelNames.PRODUCT.getValue(),
				this.transactionEntryQuery.setTransactionId(transactionId).execute());
		} catch (final Exception e) {
			modelAndView.addObject(
				ViewModelNames.ERROR_MESSAGE.getValue(),
				e.getMessage());
			modelAndView.addObject(
				ViewModelNames.TRANSACTION_VIEW.getValue(),
				(new Transaction())
					.setCount(0));
		}

		return modelAndView;
    }
    	// Properties
	@Autowired
    private TransactionEntryQuery transactionEntryQuery;
}
