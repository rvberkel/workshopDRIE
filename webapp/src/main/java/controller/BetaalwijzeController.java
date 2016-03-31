package controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;

import POJO.Betaalwijze;
import Service.BestellingService;

@Controller
public class BetaalwijzeController {
	@Autowired
	BestellingService bestelService;
	
	@Autowired
	Betaalwijze betaalwijzeObject;
	
	
	@RequestMapping(value="/betaalwijze/{betaalwijzeId}", method=GET)
	public String showBetaalwijze(@PathVariable("betaalwijzeId") String betaalwijzeId, Model model){
		betaalwijzeObject = bestelService.readBetaalWijzeOpId(Integer.parseInt(betaalwijzeId));
		
		model.addAttribute("betaalwijze", betaalwijzeObject);
		return "showBetaalwijze";
	}
	
	@RequestMapping(value="/findBetaalwijze", method=POST)
	public String findBetaalwijze(@RequestParam("idBetaalwijze") String betaalwijzeId, Model model){
		betaalwijzeObject = bestelService.readBetaalWijzeOpId(Integer.parseInt(betaalwijzeId));
		if (betaalwijzeObject != null) {
			model.addAttribute("betaalwijze", betaalwijzeObject);
			return "showBetaalwijze";
		} else {
			return "showBetaalwijzeNotFound";
		}
		
	}
	
	@RequestMapping(value="/listbetaalwijze", method=GET) 
	public String listBetaalwijze(Model model) {
		List<Betaalwijze> lijst = new ArrayList<>();
		lijst = bestelService.readAlleBetaalwijzen();
	
		model.addAttribute("betaalwijzen", lijst);
    	return "listBetaalwijze";
	}
	
	@RequestMapping(value="/createbetaalwijze", method=RequestMethod.GET)
    public String showCreateBetaalwijzeForm() {		
    	return "betaalwijze";
    }
	
	@RequestMapping(value="/createbetaalwijze", method=RequestMethod.POST)
	public String processBetaalwijze(@Valid Betaalwijze betaalwijzeObj, Errors errors,
									@RequestParam("betaalwijze") String betaalwijze,
						    		@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
//		Dit werkt helaas nog niet goed
		if (errors.hasErrors()) {
			return "betaalwijze";
		}
		
//		if (Integer.parseInt(betaalwijze) > 6 || Integer.parseInt(betaalwijze) < 0 ) {
//			return "betaalwijze";  //lomp, maar t werkt
//			//als je update hebt gedaan, en een ongeldig getal invult, dan is het id-veld leeg bij de re-try...
//		}
		
    	betaalwijzeObject = new Betaalwijze();
    	betaalwijzeObject.setBetaalwijzeKeuze(Integer.parseInt(betaalwijze)); 
    	
    	if (idBetaalwijze == null || idBetaalwijze.isEmpty() || Integer.parseInt(idBetaalwijze) == 0)
    		bestelService.createBetaalwijze(betaalwijzeObject);
    	else {
    		betaalwijzeObject.setIdBetaalwijze(Integer.parseInt(idBetaalwijze));
    		bestelService.updateBetaalwijze(betaalwijzeObject);
    	}
    	model.addAttribute("betaalwijzen", bestelService.readAlleBetaalwijzen());
    	return "listBetaalwijze";
    }
	
    @RequestMapping(value="/deleteBetaalwijze", method=RequestMethod.GET)
    public String deleteBetaaaaaaaaaaaaaaaaaaaaaaalwijze(@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
    	int id = Integer.parseInt(idBetaalwijze);
    	Betaalwijze bw = new Betaalwijze();
    	bw.setIdBetaalwijze(id);
    	bestelService.deleteBetaalwijze(bw);
    	
    	model.addAttribute("betaalwijzen", bestelService.readAlleBetaalwijzen());
    	return "listBetaalwijze";
    }
    
    @RequestMapping(value="/updateBetaalwijze", method=RequestMethod.GET)
    public String showUpdateBetaalForm(@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
    	int idBetaawijze = Integer.parseInt(idBetaalwijze);
    	betaalwijzeObject = bestelService.readBetaalWijzeOpId(idBetaawijze);
    	model.addAttribute("betaalwijze", betaalwijzeObject);
    	return "betaalwijze";
    }
}
