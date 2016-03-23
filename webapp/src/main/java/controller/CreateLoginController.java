/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.lambdaworks.crypto.SCryptUtil;
import daos.UserPasswordDao;
import java.io.IOException;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@WebServlet("/CreateLoginController")
public class CreateLoginController {
	@Autowired
    UserPasswordDao validate;
	
	@RequestMapping(value="/createLogin", method=RequestMethod.GET)
	public String showCreateLogin() {
		return "createLogin";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createUser(@ModelAttribute("inlognaam") String inlognaam, @ModelAttribute("inlogwachtwoord") 
			String inlogwachtwoord) throws ServletException, IOException {
		String encryptedPassword = hashPassword(inlogwachtwoord);
		Boolean gelukt = validate.createUser(inlognaam, encryptedPassword);
        if(gelukt){
            return "inloggen";
        }
        else{
            return "createLogin";
        }
	}
	/*
    @Override
    @RequestMapping(value="/createLogin", method=RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String inlognaam = request.getParameter("inlognaam");
        String inlogwachtwoord = request.getParameter("inlogwachtwoord");
        String encryptedPassword = hashPassword(inlogwachtwoord);
        
        Boolean gelukt = validate.createUser(inlognaam, encryptedPassword);
        if(gelukt){
            RequestDispatcher view = request.getRequestDispatcher("inloggen.jsp");
            view.forward(request, response);
        }
        else{
            out.println("Inloggen niet gelukt");
            RequestDispatcher view = request.getRequestDispatcher("createLogin.jsp");
            view.forward(request, response);
        }
    }
    */  
    
    //pas bij creeren van nieuw inlognaam & wachtwoord
    private String hashPassword(String wachtwoord) throws IOException{
        String generatedSecuredPasswordHash = SCryptUtil.scrypt(wachtwoord, 16, 16, 16);
        return generatedSecuredPasswordHash;
    }
}