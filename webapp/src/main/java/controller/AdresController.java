package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import POJO.Adres;
import POJO.AdresType;
import POJO.Klant;
import Service.KlantenService;

@Controller
@SessionAttributes({"idKlant", "oudIdAdresType"})
public class AdresController {
    @Autowired
    private KlantenService klantenService;
    int kak;
    
    @RequestMapping(value="/showCreateAdresForm", method=RequestMethod.GET)
    public String showCreateAdresForm(Model model) {
    	model.addAttribute("adrestypen", klantenService.readAlleAdresTypen());
    	return "adres";
    }
    
    @RequestMapping(value="/showUpdateAdresForm", method=RequestMethod.GET)
    public String showUpdateAdresForm(@RequestParam("idAdres") String idAdres, @RequestParam("oudIdAdresType") String idAdresType, 
    		Model model) {
    	int adresId = Integer.parseInt(idAdres);
    	int adresTypeId = Integer.parseInt(idAdresType);
    	kak = Integer.parseInt(idAdresType);
    	model.addAttribute("adres", klantenService.readAdresOpId(adresId));
    	model.addAttribute("adrestypen", klantenService.readAlleAdresTypen());
    	AdresType oudAdresType = klantenService.readAdresTypeOpId(adresTypeId);
    	model.addAttribute("adrestype", oudAdresType);
    	return "adres";
    }
    
    @RequestMapping(value="/deleteAdres", method=RequestMethod.GET)
    public String deleteAdres(@RequestParam("idAdres") String idAdres, @RequestParam("idAdresType") String idAdresType, 
    		@ModelAttribute("idKlant") String idKlant, Model model) {
    	int adresId = Integer.parseInt(idAdres);
    	int klantId = Integer.parseInt(idKlant);
    	int adresTypeId = Integer.parseInt(idAdresType);
    	klantenService.deleteAdresFromKlant(klantId, adresId, adresTypeId);
    	if (klantenService.checkIfAdresIsOwned(adresId).size() == 0)
    		klantenService.deleteAdresById(adresId);
    	Klant klant = klantenService.readKlantOpId(klantId);
    	/*
		Map<Adres, AdresType> adressen = klant.getAdressen();
		System.out.println("AANTAL ADRESEEN VIA DELETE = " + adressen.size());
		Set<Map.Entry<Adres, AdresType>> adresjes = adressen.entrySet();
		ArrayList<Adres> ad = new ArrayList<>();
		ArrayList<AdresType> adt = new ArrayList<>();
		for(Map.Entry<Adres, AdresType> adresje: adresjes){
			ad.add(adresje.getKey());
			adt.add(adresje.getValue());
		}
		model.addAttribute("adressen", ad);
		model.addAttribute("adrestypen", adt);
		*/
    	model.addAttribute("adressen", klantenService.readAdresOpKlantId(klantId));
		model.addAttribute("adrestypen", klantenService.readAdresTypeOpKlantId(klantId));
		return "listAdres";
    }
    
    @RequestMapping(value="/createOrUpdateAdres", method=RequestMethod.POST)
    public String createOrUpdateAdres(@ModelAttribute("straatnaam") String straatnaam, 
    		@ModelAttribute("huisnummer") String huisnummer, @ModelAttribute("postcode") String postcode, 
    		@ModelAttribute("woonplaats") String woonplaats, @ModelAttribute ("idAdrestype") String idAdresType, 
    		@ModelAttribute("oudIdAdrestype") String oudIdAdresType, @RequestParam("idAdres") String idAdres, 
    		@ModelAttribute("idKlant") String idKlant, Model model) {
    	System.out.println(oudIdAdresType + " OF NIETS!!!");
    	System.out.println("MAAR WEL " + idKlant + "!!!");
    	int klantId = Integer.parseInt(idKlant);
    	int adresTypeId = Integer.parseInt(idAdresType);
    	//int oudAdresTypeId = Integer.parseInt(oudIdAdresType);
    	Klant klant = klantenService.readKlantOpId(klantId);
    	Adres adres = new Adres();
    	AdresType oudAdresType = klantenService.readAdresTypeOpId(kak);
    	AdresType adresType = klantenService.readAdresTypeOpId(adresTypeId);
    	adres.setStraatnaam(straatnaam);
    	adres.setHuisnummer(huisnummer);
    	adres.setPostcode(postcode);
    	adres.setWoonplaats(woonplaats);
    	Adres checkAdres = klantenService.readAdresOpPostcodeEnHuisnummer(postcode, huisnummer);
    	if (checkAdres == null && (idAdres != null || !idAdres.isEmpty())) {
    		klantenService.createAdres(adres);
			klant.addToAdressen(adres, adresType);
			klant.removeFromAdressen(klantenService.readAdresOpId(Integer.parseInt(idAdres)), oudAdresType);
			klantenService.updateKlant(klant);
    	}
    	else if (checkAdres == null && (idAdres == null || idAdres.isEmpty())) {
    		klantenService.createAdres(adres);
			klant.addToAdressen(adres, adresType);
			klantenService.updateKlant(klant);
    	}
    	else {
    		if (idAdres == null || idAdres.isEmpty()) {
    			klant.addToAdressen(checkAdres, adresType);
    			klantenService.updateKlant(klant);
    		}
    		else {
    			klant.addToAdressen(checkAdres, adresType);
    			klant.removeFromAdressen(klantenService.readAdresOpId(Integer.parseInt(idAdres)), adresType);
    			klantenService.updateKlant(klant);
    		}
    	}
    	/*
		Map<Adres, AdresType> adressen = klant.getAdressen();
		System.out.println("AANTAL ADRESSEN VIA CREATEORUPDATE = " + adressen.size());
		Set<Map.Entry<Adres, AdresType>> adresjes = adressen.entrySet();
		ArrayList<Adres> ad = new ArrayList<>();
		ArrayList<AdresType> adt = new ArrayList<>();
		for(Map.Entry<Adres, AdresType> adresje: adresjes){
			ad.add(adresje.getKey());
			adt.add(adresje.getValue());
		}
		model.addAttribute("adressen", ad);
		model.addAttribute("adrestypen", adt);
		*/
    	model.addAttribute("adressen", klantenService.readAdresOpKlantId(klantId));
		model.addAttribute("adrestypen", klantenService.readAdresTypeOpKlantId(klantId));
    	return "listAdres";
    }
}