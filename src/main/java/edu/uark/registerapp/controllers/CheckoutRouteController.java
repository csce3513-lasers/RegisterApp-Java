package edu.uark.registerapp.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutRouteController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ModelAndView showPage(@RequestParam final Map<String, String> queryParameters) {
        
        //CHECK IF SIGNED IN
        
        return new ModelAndView("ActiveTransactionView"); //CHANGE TO ENUM
    }
}