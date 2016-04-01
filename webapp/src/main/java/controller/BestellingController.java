package controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import POJO.Bestelling;
import POJO.BestellingHasArtikel;
import POJO.Klant;
import Service.BestellingService;

@Controller
public class BestellingController {
	@Autowired
	BestellingService bestelService;

	@Autowired
	Klant klant;
	@Autowired
	Bestelling bestelling;
	@Autowired
	BestellingHasArtikel bha;

	@RequestMapping(value="/listBestellingen", method=GET)
	public String listBestellingen(Model model) {
		model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
    	return "listBestelling";
	}
	@RequestMapping(value="/createBestelling", method=RequestMethod.GET)
    public String showBestellingForm(Model model) {
		model.addAttribute("artikelen", bestelService.readAllArtikel());
		
    	return "bestelling";
    }
	@RequestMapping(value="/showUpdateBestelling", method=RequestMethod.GET)
    public String showUpdateBestellingForm(@RequestParam("idBestelling") String idBestelling, Model model) {
    	int id = Integer.parseInt(idBestelling);
    	bestelling = bestelService.readBestellingOpId(id);
    	model.addAttribute("bestelling", bestelling);
    	return "bestelling";
    }
	@RequestMapping (value = "/deleteBestelling", method = RequestMethod.GET)
	  public String deleteBestelling(@RequestParam("getId") String idBestelling, Model model) {
        int id = Integer.parseInt(idBestelling);
        bestelService.removeBestelling(id);
        model.addAttribute("bestelling", bestelService.readAlleBestellingen());
        return "listBestelling";
    }

}
