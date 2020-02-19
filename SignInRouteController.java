package controllers;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")

public class SignInRouteController {
    @RequestMapping(method = RequestMethod.GET)
    
    //controller implented here   
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start() {
        //if employee exists, serve this page
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ApiResponse createProduct(
    }
}
