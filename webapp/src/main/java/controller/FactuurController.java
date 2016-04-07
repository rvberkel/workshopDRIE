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
    public String showCreateFactuurForm(@RequestParam("idBestelling") String idBestelling, 
									Model model) {
		
		Bestelling bestelObject = new Bestelling();
		bestelObject.setIdBestelling(Integer.parseInt(idBestelling)); 
		
		factuurObject = new Factuur();
		factuurObject.setBestelling(bestelObject);
		
		model.addAttribute("factuur", factuurObject);
		
    	return "factuur";
    }
	
	@RequestMapping(value="/createfactuur", method=RequestMethod.POST)
	public String processFactuurForm(@RequestParam("factuurDatum") Date factuurDatum, 
									 @RequestParam("idBestelling") String idBestelling, 
									 Model model) {
		
		int bestelId = Integer.parseInt(idBestelling);
		Factuur factuurObj = null;
		
		try {
			factuurObj = bestelService.readFactuurOpBestellingId(bestelId).get(0);
		} catch (Exception ex) {}
		
		//UPDATE
		if (factuurObj != null) {
			factuurObj.setFactuurDatum(factuurDatum);
			bestelService.updateFactuur(factuurObj);
		} 
		//CREATE
		else {
			Bestelling bestelObj = new Bestelling();
			bestelObj.setIdBestelling(bestelId);
			
			factuurObj = new Factuur();
			factuurObj.setFactuurDatum(factuurDatum);
			factuurObj.setBestelling(bestelObj);
			
			bestelService.createFactuur(factuurObj);
		}
		
    	model.addAttribute("facturen", bestelService.readAlleFacturen());
		
		return "listFactuur";
	}
	
	@RequestMapping(value="/showUpdateFactuur", method=RequestMethod.GET)
    public String showUpdateFactuurForm(@RequestParam("idFactuur") String idFactuur, 
    									Model model) {
    	
    	factuurObject = bestelService.readFactuurOpId(Integer.parseInt(idFactuur));
    	
    	model.addAttribute("factuur", factuurObject);
    	
    	return "factuur";
    }
	
	@RequestMapping(value="/deleteFactuur", method=RequestMethod.GET)
	public String deleteFactuur(@RequestParam("idFactuur") String idFactuur, Model model) {
    	Factuur f = new Factuur();
    	f.setIdFactuur(Integer.parseInt(idFactuur));
    	bestelService.deleteFactuur(f);
    	
    	model.addAttribute("facturen", bestelService.readAlleFacturen());
    	return "listFactuur";
	}
}
