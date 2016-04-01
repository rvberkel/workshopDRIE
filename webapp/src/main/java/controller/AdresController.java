package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import POJO.Adres;
import POJO.AdresType;
import POJO.Artikel;
import POJO.Klant;
import Service.KlantenService;
import springCommandObjects.AdresWithAdrestype;

@Controller
@SessionAttributes({"idKlant", "oudIdAdresType"})
public class AdresController {
    @Autowired
    private KlantenService klantenService;
    
    @RequestMapping(value="/showCreateAdresForm", method=RequestMethod.GET)
    public String showCreateAdresForm(Model model, @ModelAttribute("oudIdAdresType") String idAdresType) {
    	model.addAttribute("adrestypen", klantenService.readAlleAdresTypen());
    	model.addAttribute("oudIdAdresType", idAdresType);
    	return "adres";
    }
    
    @RequestMapping(value="/showUpdateAdresForm", method=RequestMethod.GET)
    public String showUpdateAdresForm(@RequestParam("idAdres") String idAdres, @RequestParam("oudIdAdresType") String idAdresType, 
    		Model model) {
    	int adresId = Integer.parseInt(idAdres);
    	int adresTypeId = Integer.parseInt(idAdresType);
    	model.addAttribute("adres", klantenService.readAdresOpId(adresId));
    	model.addAttribute("adrestypen", klantenService.readAlleAdresTypen());
    	AdresType oudAdresType = klantenService.readAdresTypeOpId(adresTypeId);
    	model.addAttribute("adrestype", oudAdresType);
    	model.addAttribute("oudIdAdresType", idAdresType);
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
    	model.addAttribute("adressen", klantenService.readAdresOpKlantId(klantId));
		model.addAttribute("adrestypen", klantenService.readAdresTypeOpKlantId(klantId));
		return "listAdres";
    }
    
    @RequestMapping(value="/createOrUpdateAdres", method=RequestMethod.POST)
    public String updateAdres(@Valid Adres adres, Errors errors, @ModelAttribute("straatnaam") String straatnaam, 
    		@ModelAttribute("huisnummer") String huisnummer, @ModelAttribute("postcode") String postcode, 
    		@ModelAttribute("woonplaats") String woonplaats, @ModelAttribute("idAdrestype") String idAdresType, 
    		@ModelAttribute("oudIdAdresType") String oudIdAdresType, @RequestParam("idAdres") String idAdres, 
    		@ModelAttribute("idKlant") String idKlant, Model model) {
    	if(errors.hasErrors()){
    		int oudAdresTypeId = Integer.parseInt(oudIdAdresType);
    		AdresType oudAdresType = klantenService.readAdresTypeOpId(oudAdresTypeId);
    		model.addAttribute("adrestypen", klantenService.readAlleAdresTypen());
        	model.addAttribute("adrestype", oudAdresType);
        	model.addAttribute("oudIdAdresType", oudAdresTypeId);
    		return "adres";
    	}
    	
    	int klantId = Integer.parseInt(idKlant);
    	int adresTypeId = Integer.parseInt(idAdresType);
    	Adres checkAdres = klantenService.readAdresOpPostcodeEnHuisnummer(postcode, huisnummer);
    	
    	adres.setStraatnaam(straatnaam);
    	adres.setHuisnummer(huisnummer);
    	adres.setPostcode(postcode);
    	adres.setWoonplaats(woonplaats);
    	
    	if (checkAdres == null && !idAdres.isEmpty()) {
    		int adresId = Integer.parseInt(idAdres);
    		int oudAdresTypeId = Integer.parseInt(oudIdAdresType);
    		klantenService.createAdres(adres);
			klantenService.addAdresToKlant(klantId, adres.getIdAdres(), adresTypeId);
			klantenService.deleteAdresFromKlant(klantId, adresId, oudAdresTypeId);
    	}
    	else if (checkAdres == null && (idAdres == null || idAdres.isEmpty())) {
    		klantenService.createAdres(adres);
			klantenService.addAdresToKlant(klantId, adres.getIdAdres(), adresTypeId);
    	}
    	else if (checkAdres != null) {
    		if (idAdres == null || idAdres.isEmpty()) {
    			klantenService.addAdresToKlant(klantId, checkAdres.getIdAdres(), adresTypeId);
    		}
    		else {
    			int adresId = Integer.parseInt(idAdres);
    			int oudAdresTypeId = Integer.parseInt(oudIdAdresType);
    			klantenService.addAdresToKlant(klantId, checkAdres.getIdAdres(), adresTypeId);
    			klantenService.deleteAdresFromKlant(klantId, adresId, oudAdresTypeId);
    		}
    	}
    	model.addAttribute("adressen", klantenService.readAdresOpKlantId(klantId));
		model.addAttribute("adrestypen", klantenService.readAdresTypeOpKlantId(klantId));
    	return "listAdres";
    }
}