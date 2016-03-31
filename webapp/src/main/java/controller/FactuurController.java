package controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Autowired
	Betaalwijze betaalwijzeObject;
	
	/* ====    methodes    ==== */
	
	@RequestMapping(value="/listFacturen", method=GET) 
	public String listFacuurtjes(Model model) {
		model.addAttribute("facturen", bestelService.readAlleFacturen());
    	return "listFactuur";
	}
	
//	//hier is nog geen jsp van
//	@RequestMapping(value="/factuur/{factuurId}", method=GET)
//	public String showBetaalwijze(@PathVariable("factuurId") String factuurId, Model model){
//		factuurObject = bestelService.readFactuurOpId(Integer.parseInt(factuurId));
//		
//		model.addAttribute("factuur", factuurObject);
//		return "showFactuur";
//	}
	
	@RequestMapping(value="/createfactuur", method=RequestMethod.GET)
    public String showCreateFactuurForm() {
		//public String showCreateFactuurForm(Model model) {
		//	model.addAttribute(new Factuur());
    	return "factuur";
    }
	
	@RequestMapping(value="/createfactuur", method=RequestMethod.POST)
	public String processFactuurForm(@RequestParam("idFactuur") String idFactuur,
									 @RequestParam("factuurDatum") Date factuurDatum, 
									 @RequestParam("idBestelling") String idBestelling, Model model) {
		
		factuurObject = new Factuur();
		factuurObject.setFactuurDatum(factuurDatum); 
		
		int bestellingId = Integer.parseInt("0" + idBestelling);
		int factuurId = Integer.parseInt(idFactuur);
		
		//checken of de ingegeven bestelling Id wel bestaat
		if (bestelService.readBestellingOpId(bestellingId) == null)  {
			factuurObject.setIdFactuur(factuurId);
			model.addAttribute("factuur", factuurObject);
			//eigenlijk dus nog iets meegeven van een foutmelding
			return "factuur";
		} else {
			Bestelling b = new Bestelling();
			b.setIdBestelling(bestellingId);
			factuurObject.setBestelling(b); 
		}
		
		//checken voor creeeren of updaten
		if (factuurId == 0)
    		bestelService.createFactuur(factuurObject);
    	else {
    		factuurObject.setIdFactuur(factuurId);
    		bestelService.updateFactuur(factuurObject);
    	}
		
    	model.addAttribute("facturen", bestelService.readAlleFacturen());
		
		return "listFactuur";
	}
	
	@RequestMapping(value="/showUpdateFactuur", method=RequestMethod.GET)
    public String showUpdateFactuurForm(@RequestParam("idFactuur") String idFactuur, Model model) {
    	int id = Integer.parseInt(idFactuur);
    	factuurObject = bestelService.readFactuurOpId(id);
    	model.addAttribute("factuur", factuurObject);
    	return "factuur";
    }
}
