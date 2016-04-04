 package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import POJO.Adres;
import POJO.AdresType;
import POJO.Klant;
import Service.KlantenService;

@Controller
@SessionAttributes({"idKlant", "detailsVanKlant", "oudIdAdresType", "adressenVanKlant"})
public class KlantDetailsController {
	@Autowired
	private KlantenService klantenService;
	
	@RequestMapping(value="/checkKlantDetails", method=RequestMethod.GET)
	public String checkKlantDetails(@RequestParam("idKlant") String idKlant, @RequestParam("voornaam") String voornaam, 
			@RequestParam("tussenvoegsel") String tussenvoegsel, @RequestParam("achternaam") String achternaam, Model model) {
		String detailsVanKlant;
		if (tussenvoegsel == null || tussenvoegsel.isEmpty())
			detailsVanKlant = "details van " + voornaam + " " + achternaam;
		else
			detailsVanKlant = "details van " + voornaam + " " + tussenvoegsel + " " + achternaam;
		model.addAttribute("detailsVanKlant", detailsVanKlant);
		model.addAttribute("idKlant", idKlant);
		model.addAttribute("oudIdAdresType", 0);
		return "listKlantDetails";
	}
	
	@RequestMapping(value="/showKlantDetails", method=RequestMethod.GET)
	public String showKlantDetails(Model model) {
		model.addAttribute("detailsVanKlant");
		model.addAttribute("oudIdAdresType", 0);
		return "listKlantDetails";
	}
	
	@RequestMapping(value="/checkAdressen", method=RequestMethod.GET)
	public String showAdressen(Model model, @ModelAttribute("idKlant") String idKlant) {
		int klantId = Integer.parseInt(idKlant);
		Klant klant = klantenService.readKlantOpId(klantId);
		model.addAttribute("adressen", klantenService.readAdresOpKlantId(klantId));
		model.addAttribute("adrestypen", klantenService.readAdresTypeOpKlantId(klantId));
		String adressenVanKlant;
		if (klant.getTussenvoegsel() == null || klant.getTussenvoegsel().isEmpty())
			adressenVanKlant = "adressen van " + klant.getVoornaam() + " " + klant.getAchternaam();
		else
			adressenVanKlant = "adressen van " + klant.getVoornaam() + " " + klant.getTussenvoegsel() + " " + klant.getAchternaam();
		model.addAttribute("adressenVanKlant", adressenVanKlant);
		return "listAdres";
	}
	
	@RequestMapping(value="/checkAccounts", method=RequestMethod.GET)
	public String showAccount() {
		return "listAccount";
	}
	
	@RequestMapping(value="/checkBestellingen", method=RequestMethod.GET)
	public String showBestellingen() {
		return "listBestelling";
	}
	
	@RequestMapping(value="/checkBetalingen", method=RequestMethod.GET)
	public String showBetalingen() {
		return "listBetaling";
	}
}
