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
@SessionAttributes("idKlant")
public class AdresController {
    @Autowired
    private KlantenService dao;
    @Autowired
    Klant klant;
    
    @RequestMapping(value="/createAdres", method=RequestMethod.GET)
    public String showCreateAdresForm() {
    	return "adres";
    }
    
    @RequestMapping(value="/deleteAdres", method=RequestMethod.GET)
    public String deleteAdres(@RequestParam("idAdres") String idAdres, @ModelAttribute("idKlant") String idKlant, Model model) {
    	int adresId = Integer.parseInt(idAdres);
    	int klantId = Integer.parseInt(idKlant);
    	dao.deleteAdresFromKlant(klantId, adresId);
    	Klant klant = dao.readKlantOpId(klantId);
		Map<Adres, AdresType> adressen = klant.getAdressen();
		Set<Map.Entry<Adres, AdresType>> adresjes = adressen.entrySet();
		ArrayList<Adres> ad = new ArrayList<>();
		ArrayList<AdresType> adt = new ArrayList<>();
		for(Map.Entry<Adres, AdresType> adresje: adresjes){
			ad.add(adresje.getKey());
			adt.add(adresje.getValue());
		}
		model.addAttribute("adressen", ad);
		model.addAttribute("adrestypen", adt);
		return "listAdres";
    }
    
    @RequestMapping(value="/createOrUpdateAdres", method=RequestMethod.POST)
    public String createOrUpdateAdres(@ModelAttribute("straatnaam") String straatnaam, @ModelAttribute("huisnummer") String huisnummer, 
    		@ModelAttribute("postcode") String postcode, @ModelAttribute("woonplaats") String woonplaats, 
    		@RequestParam("idAdres") String idAdres, @ModelAttribute("idKlant") String idKlant, Model model) {
    	Klant klant = dao.readKlantOpId(Integer.parseInt(idKlant));
    	Adres adres = new Adres();
    	adres.setStraatnaam(straatnaam);
    	adres.setHuisnummer(huisnummer);
    	adres.setPostcode(postcode);
    	adres.setWoonplaats(woonplaats);
    	if (idAdres == null || idAdres.isEmpty()) {
    		List<Adres> adressen = dao.readAlleAdressen();
    	}	
    	else {
    		adres.setIdAdres(Integer.parseInt(idAdres));
    		dao.updateAdres(adres);
    	}
		Map<Adres, AdresType> adressen = klant.getAdressen();
		Set<Map.Entry<Adres, AdresType>> adresjes = adressen.entrySet();
		ArrayList<Adres> ad = new ArrayList<>();
		ArrayList<AdresType> adt = new ArrayList<>();
		for(Map.Entry<Adres, AdresType> adresje: adresjes){
			ad.add(adresje.getKey());
			adt.add(adresje.getValue());
		}
		model.addAttribute("adressen", ad);
		model.addAttribute("adrestypen", adt);
    	return "listKlant";
    }
}