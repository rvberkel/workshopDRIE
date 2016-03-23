/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.KlantenService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import POJO.Klant;

import javax.servlet.RequestDispatcher;

@Controller
//@WebServlet("/KlantController")
public class KlantController extends HttpServlet {
  private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/klant.jsp";
    private static String LIST_KLANT = "/listKlant.jsp";
    @Autowired
    private KlantenService dao;
    @Autowired
    Klant klant;
    
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String deleteKlant(@RequestParam("getId") String idKlant, Model model) {
    	int klantId = Integer.parseInt(idKlant);
    	dao.deleteKlantById(klantId);
    	model.addAttribute("klanten", dao.readAlleKlanten());
    	return "listKlant";
    }
    
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String showCreateKlantForm() {
    	return "klant";
    }
    
    @RequestMapping(value="/update", method=RequestMethod.GET)
    public String showUpdateKlantForm(@RequestParam("idKlant") String idKlant, Model model) {
    	int klantId = Integer.parseInt(idKlant);
    	klant = dao.readKlantOpId(klantId);
    	model.addAttribute("klant", klant);
    	return "klant";
    }
    
    @RequestMapping(value="/createKlant", method=RequestMethod.POST)
    /*
    public String createKlant(Model model) {
    	klant.setVoornaam("Henk");
    	klant.setAchternaam("Moeder");
    	klant.setTussenvoegsel("de");
    	klant.setEmail("henk@moeder.com");
    	dao.createKlant(klant);
    	model.addAttribute("klanten", dao.readAlleKlanten());
    	return "listKlant";
    }
    */
    
    public String createKlant(@ModelAttribute("voornaam") String voornaam, @ModelAttribute("tussenvoegsel") String tussenvoegsel, 
    		@ModelAttribute("achternaam") String achternaam, @ModelAttribute("email") String email, 
    		@RequestParam("idKlant") String idKlant, Model model) {
    	Klant klant = new Klant();
    	klant.setVoornaam(voornaam);
    	klant.setTussenvoegsel(tussenvoegsel);
    	klant.setAchternaam(achternaam);
    	klant.setEmail(email);
    	if (idKlant == null || idKlant.isEmpty())
    		dao.createKlant(klant);
    	else {
    		klant.setIdKlant(Integer.parseInt(idKlant));
    		dao.updateKlant(klant);
    	}
    	model.addAttribute("klanten", dao.readAlleKlanten());
    	return "listKlant";
    }
    
    
    /*
    @RequestMapping(value="listKlant", method=RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int idKlant = Integer.parseInt(request.getParameter("idKlant"));
            dao.deleteKlantById(idKlant);
            forward = LIST_KLANT;
            request.setAttribute("klanten", dao.readAlleKlanten());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int idKlant = Integer.parseInt(request.getParameter("idKlant"));
            Klant klant = dao.readKlantOpId(idKlant);
            request.setAttribute("klant", klant);
        } else if (action.equalsIgnoreCase("listKlant")){
            forward = LIST_KLANT;
            request.setAttribute("klanten", dao.readAlleKlanten());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
    @RequestMapping(value="/klant.jsp", method=RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	Klant klant = new Klant();
        klant.setVoornaam(request.getParameter("voornaam"));
        klant.setTussenvoegsel(request.getParameter("tussenvoegsel"));
        klant.setAchternaam(request.getParameter("achternaam"));
        klant.setEmail(request.getParameter("email"));
        String idKlant = request.getParameter("idKlant");
        if(idKlant == null || idKlant.isEmpty())
        {
            dao.createKlant(klant);
        }
        else
        {
            klant.setIdKlant(Integer.parseInt(idKlant));
            dao.updateKlant(klant);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_KLANT);
        request.setAttribute("klanten", dao.readAlleKlanten());
        view.forward(request, response);
    }
    */
}