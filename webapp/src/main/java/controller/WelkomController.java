package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("welcomeMessage")
public class WelkomController {
	@RequestMapping(value="/welkom", method=RequestMethod.GET)
	public String showWelkomPage(){
		return "welkom";
	}


}
