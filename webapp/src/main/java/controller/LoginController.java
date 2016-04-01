/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.KlantenService;
import daos.UserPasswordDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("welcomeMessage")
public class LoginController {
	@Autowired
    UserPasswordDao validator;
	@Autowired
    private KlantenService dao;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLogin() {
		return "inloggen";
	}
	
	@RequestMapping(value="/welkom", method=RequestMethod.POST)
	public String login(@ModelAttribute("inlognaam") String inlognaam, @ModelAttribute("inlogwachtwoord") String inlogwachtwoord, 
			Model model) {
		//Map<String, Object> modelMap = model.asMap();
        //String inlognaam = (String)modelMap.get("inlognaam");
        //String inlogwachtwoord = (String)modelMap.get("inlogwachtwoord");
        String welcomeMessage = "Welcome" + " " + inlognaam + "!";
        if(validator.checkUser(inlognaam, inlogwachtwoord))
        {
//        	model.addAttribute("klanten", dao.readAlleKlanten());
    		model.addAttribute("welcomeMessage", welcomeMessage);
    		return "welkom";
        }
        else
        {
           return "inloggen";
        }
	}
	
	/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String inlognaam = request.getParameter("inlognaam");
        String inlogwachtwoord = request.getParameter("inlogwachtwoord");
        String welcomeMessage = "Welcome" + " " + inlognaam + "!";
        if(validate.checkUser(inlognaam, inlogwachtwoord))
        {
            out.println("Logging in has been succesfull!");
            request.setAttribute("klanten", dao.readAlleKlanten());
            request.setAttribute("welcomeMessage", welcomeMessage);
            RequestDispatcher rs = request.getRequestDispatcher("listKlant.jsp");
            rs.forward(request, response);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("inloggen.jsp");
           rs.include(request, response);
        }
    }
    */
}
   
