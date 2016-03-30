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
		
		Bestelling b = new Bestelling();
		b.setIdBestelling(Integer.parseInt(idBestelling));
		
		factuurObject = new Factuur();
		factuurObject.setFactuurDatum(factuurDatum); //een soort van Date-parser?
		
		factuurObject.setBestelling(b);  //zou kunnen valideren of bestelling bestaat
		
    	
    	if (idFactuur == null || idFactuur.isEmpty())
    		bestelService.createFactuur(factuurObject);
    	else {
    		factuurObject.setIdFactuur(Integer.parseInt(idFactuur));
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
