package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}