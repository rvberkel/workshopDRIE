package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import POJO.Betaalwijze;
import Service.BestellingService;
import daos.BetaalwijzeDao;

@Controller
public class BetaalwijzeController {
	@Autowired
	BestellingService bestelService;
	
	
	BetaalwijzeDao betaalwijzeDao = new BetaalwijzeDao(); //dit moet natuurlijk eigen via service en generiekeDAO lopen
	
	@Autowired
	Betaalwijze betaalwijze;
	
	@RequestMapping(value="/createbetaalwijze", method=RequestMethod.GET)
    public String showCreateBetaalwijzeForm() {
    	return "betaalwijze";
    }
	
	
	public String createBetaalwijze(@ModelAttribute("omschrijving") String omschrijving,
    		@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
		
    	betaalwijze = new Betaalwijze();
    	betaalwijze.setBetaalwijze(omschrijving); //tijdelijk, eigenlijk moet dit via een keuze-int
    	
    	if (idBetaalwijze == null || idBetaalwijze.isEmpty())
    		bestelService.createBetaalwijze(betaalwijze);
    	else {
    		betaalwijze.setIdBetaalwijze(Integer.parseInt(idBetaalwijze));
    		bestelService.updateBetaalwijze(betaalwijze);
    	}
    	model.addAttribute("betaalwijzen", betaalwijzeDao.readAll());
    	return "listBetaalwijze";
    }
	
    @RequestMapping(value="/deleteBetaalwijze", method=RequestMethod.GET)
    public String deleteBetaalwijze(@RequestParam("idBetaawijze") String idBetaalwijze, Model model) {
    	int id = Integer.parseInt(idBetaalwijze);
    	Betaalwijze bw = new Betaalwijze();
    	bw.setIdBetaalwijze(id);
    	bestelService.deleteBetaalwijze(bw);
    	
    	model.addAttribute("klanten", betaalwijzeDao.readAll());
    	
    	return "listBetaalwijze";
    }
    
    
    @RequestMapping(value="/updateBetaalwijze", method=RequestMethod.GET)
    public String showUpdateBetaalForm(@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
    	int idBetaawijze = Integer.parseInt(idBetaalwijze);
    	betaalwijze = bestelService.readBetaalWijzeOpId(idBetaawijze);
    	model.addAttribute("betaalwijze", betaalwijze);
    	return "betaalwijze";
    }
	
}
