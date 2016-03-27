package controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import POJO.Bestelling;
import POJO.Betaalwijze;
import POJO.Factuur;
import Service.BestellingService;

@Controller
public class FactuurController {
	@Autowired
	BestellingService bestelService;
	
	@Autowired
	Factuur factuurObject;
	
	
	//hier is nog geen jsp van
	@RequestMapping(value="/factuur/{factuurId}", method=GET)
	public String showBetaalwijze(@PathVariable("factuurId") String factuurId, Model model){
		factuurObject = bestelService.readFactuurOpId(Integer.parseInt(factuurId));
		
		model.addAttribute("factuur", factuurObject);
		return "showFactuur";
	}
	
	@RequestMapping(value="/createfactuur", method=RequestMethod.GET)
    public String showCreateFactuurForm() {
    	return "factuur";
    }
	
//	@RequestMapping(value="/createfactuur", method=RequestMethod.POST)
//	public String processFactuurForm() {
//		
//		Bestelling b = new Bestelling();
//		b.setIdBestelling(idBestelling);
//		
//		factuurObject = new Factuur();
//		factuurObject.setFactuurDatum(factuurDatum);
//		factuurObject.setBestelling(bestelling);
//		
//		betaalwijzeObject = new Betaalwijze();
//    	betaalwijzeObject.setBetaalwijze(Integer.parseInt(betaalwijze)); 
//    	
//    	if (idBetaalwijze == null || idBetaalwijze.isEmpty())
//    		bestelService.createBetaalwijze(betaalwijzeObject);
//    	else {
//    		betaalwijzeObject.setIdBetaalwijze(Integer.parseInt(idBetaalwijze));
//    		bestelService.updateBetaalwijze(betaalwijzeObject);
//    	}
//    	model.addAttribute("betaalwijzen", bestelService.readAlleBetaalwijzen());
//		
//		return "listFactuur";
//	}
}
