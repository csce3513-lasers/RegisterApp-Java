package controllers;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")

public class SignInRouteController {
    
    @RequestMapping(method = RequestMethod.GET)
    //controller implented here  
    public ModelAndView start(@RequestParam Map<String, String> map) {
        
        return new ModelAndView("signin.html");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ApiResponse createProduct( {
        
    }

}
