package controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import POJO.Artikel;
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
		model.addAttribute("aantal", "");
		model.addAttribute("klant_id","");
		
    	return "bestelling";
    }
	
	@RequestMapping(value="/createBestelling", method=RequestMethod.POST)
	public String processBestellingForm(@ModelAttribute("aantal") String aantal, 
										@ModelAttribute("artikelnaam") String artikelnaam,
										@ModelAttribute("idArtikel") String artikelId,
										@ModelAttribute("artikelnummer") String artikelnummer,
										@ModelAttribute("artikelprijs") String artikelprijs,
										//@ModelAttribute("artikel") Artikel artikel, 
										Model model) {
		
		klant = new Klant();
		klant.setIdKlant(1);
		
		bestelling = new Bestelling();
		bestelling.setKlant(klant);
		
//		Artikel a = new Artikel();
//		a.setArtikelnummer("Nieuw Aerikel");
//		a.setArtikelnummer(artikelnummer);
//		a.setArtikelprijs(Double.parseDouble(artikelprijs));
		
//		bestelService.createArtikel(a);
		bestelService.createBestelling(bestelling);
		
	
		Artikel artikelObj = bestelService.readArtikelOpId(Integer.parseInt("1")); //dit werkt, maar is niet dynamisch
		//Artikel artikelObj = (Artikel) bestelService.readArtikel(artikel).get(0);
		//Artikel artikelObj = artikel;
		bha = new BestellingHasArtikel();
		
		bha.setArtikel(artikelObj);
		//bha.setArtikel(artikel);
		bha.setBestelling(bestelling);
		bha.setAantal(Integer.parseInt(aantal));
		
		//bestelling.addToBestellingHasArtikelen(bha);
		bestelService.createBestellingHasArtikel(bha);
		
		
		model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
        return "listBestelling";
	}
	
	@RequestMapping(value="/showUpdateBestelling", method=RequestMethod.GET)
    public String showUpdateBestellingForm(@RequestParam("idBestelling") String idBestelling, Model model) {
    	int id = Integer.parseInt(idBestelling);
    	
    	
    	model.addAttribute("bestelling", bestelService.readBestellingOpId(id));
    	model.addAttribute("artikelen", bestelService.readAllArtikel());
//		model.addAttribute("aantal", "");
//		model.addAttribute("klant_id","");
    	return "bestelling";
    }
	
	//Delete bestelling werkt alleen als de bestelling geen artikelen heeft
	@RequestMapping (value = "/deleteBestelling", method = RequestMethod.GET)
	  public String deleteBestelling(@RequestParam("idBestelling") String idBestelling, Model model) {
        int id = Integer.parseInt(idBestelling);
        bestelService.removeBestelling(id);
        
//        List<Artikel> artikelen = bestelService.IETS_ZINNIGS_HIER
//        for(Artikel a: artikelen) {
//        	bestelService.deleteArtikelUitBestelling(id, a.getIdArtikel());
//        }
        
        model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
        return "listBestelling";
    }

}
