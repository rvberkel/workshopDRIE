package controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@RequestMapping(value="/listBestellingen", method=GET)
	public String listBestellingen(Model model) {
		model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
    	return "listBestelling";
	}
	
	@RequestMapping(value="/showCreateBestellingForm", method=RequestMethod.GET)
    public String showBestellingForm(Model model) {
		model.addAttribute("artikelen", bestelService.readAllArtikel());
		model.addAttribute("aantal", "");
		model.addAttribute("klant_id","");
		
    	return "bestelling";
    }
	
	@RequestMapping(value="/createBestelling", method=RequestMethod.POST)
	public String processBestellingForm( 
			//@RequestParam("artikelnaam") String artikelnaam,
			@RequestParam("idArtikel") String artikelId,
			//@RequestParam("artikelnummer") String artikelnummer,
			//@RequestParam("artikelprijs") String artikelprijs,
			@RequestParam("aantal") String aantal,
			@RequestParam("klantId") String klantId,
			Model model) {
		
		Klant klant = new Klant();
		klant.setIdKlant(Integer.parseInt(klantId));
		Bestelling bestelling = new Bestelling();
		bestelling.setKlant(klant);
		
		String[] artikelIds = artikelId.split(",");
		String[] aantallen = aantal.split(",");
		List<String> cleanedAantallen = new ArrayList<>();
		for(int i = 0; i < aantallen.length; i++) {
			if(!aantallen[i].isEmpty()) {
				cleanedAantallen.add(aantallen[i]);
			}
		}
		
//		System.out.println("Array: "+ aantallen);
//		System.out.println("List: " + cleanedAantallen.toString());

		
		// === CREATE ===
		//if (bestellingId == null || bestellingId.isEmpty() || Integer.parseInt(bestellingId) == 0) {
			
		bestelService.createBestelling(bestelling);
			
		for(int i = 0; i < artikelIds.length; i++) {	
			int id = Integer.parseInt(artikelIds[i]);
			Artikel artikelObj = bestelService.readArtikelOpId(id);
			BestellingHasArtikel bha = new BestellingHasArtikel();
			bha.setArtikel(artikelObj);  
			bha.setBestelling(bestelling);
			bha.setAantal(Integer.parseInt(cleanedAantallen.get(i))); 
			//bestelling.addToBestellingHasArtikelen(bha);
			bestelService.createBestellingHasArtikel(bha);
		}
		model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
        return "listBestelling";
	}
	
	@RequestMapping(value="/showUpdateBestellingForm", method=RequestMethod.GET)
    public String showUpdateBestellingForm(@RequestParam("idBestelling") String idBestelling, Model model) {
    	
		int id = Integer.parseInt(idBestelling);
    	model.addAttribute("bestellingHasArtikelen", bestelService.readBestellingOpId(id).getBestellingHasArtikelen());
    	model.addAttribute("bestelling", bestelService.readBestellingOpId(id));
		model.addAttribute("klantId", bestelService.readBestellingOpId(id).getKlant().getIdKlant());
		
		// === ingekorte betere versie
//		bestelling = bestelService.readBestellingOpId(Integer.parseInt(idBestelling));
//		model.addAttribute("bestellingHasArtikel", bestelling.getBestellingHasArtikelen());
//		model.addAttribute("bestelling", bestelling);
		
    	return "bestellingUpdate";
    }
	
	@RequestMapping(value="/updateBestelling", method=RequestMethod.POST)
	public String processUpdateBestellingForm(
			@RequestParam("idArtikel") String artikelId,
			@RequestParam("aantal") String aantal,
//			@RequestParam("klantId") String klantId,
//			@RequestParam("idBestelling") String bestellingId,
			@RequestParam("idBHA") String bhaId,
			Model model) {
		
//		nou, als ie alleen de gegevens van de aangevinkte artikelen meeneemt, en je schrapt al die nullen uit de aantallenlijst, 
//		dan houd je als het goed is twee arrays over die even groot zijn en op de juist volgorde staan.
	
		String[] aantallen = aantal.split(",");
		List<String> cleanedAantallen = new ArrayList<>();
		for(int i = 0; i < aantallen.length; i++) {
			if(!aantallen[i].isEmpty()) {
				cleanedAantallen.add(aantallen[i]);
			}
		}
		
		String[] bhaIds = bhaId.split(","); //deze wordt 'beschermd' door de checkboxes
				
		for(int i = 0; i < bhaIds.length; i++) {
			int id = Integer.parseInt(bhaIds[i]);
			BestellingHasArtikel bha = bestelService.readBestellingHasArtikelOpId(id);
			bha.setAantal(Integer.parseInt(cleanedAantallen.get(i))); 
			//bestelling.addToBestellingHasArtikelen(bha);
			bestelService.updateAantalArtikelenInBestelling(bha);
		}
		
		model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
	    return "listBestelling";
	}
	
	
	//Delete bestelling 
	@RequestMapping (value = "/deleteBestelling", method = RequestMethod.GET)
	  public String deleteBestelling(@RequestParam("idBestelling") String idBestelling, Model model) {
        int id = Integer.parseInt(idBestelling);
        bestelService.removeBestelling(id);
        
        model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
        return "listBestelling";
	}
	
	//Delete artikel from bestelling
	@RequestMapping (value = "/deleteArtikelFromBestelling", method=RequestMethod.GET)
	public String deleteArtFromBest(@RequestParam("idBestelling") String idBestelling,
									@RequestParam("klantId") String klantId,
									@RequestParam("idBHA") String idBHA,
									Model model) {
		int bestellingId = Integer.parseInt(idBestelling);
		bestelService.deleteArtikelUitBestellingOpBHAId(Integer.parseInt(idBHA));
		
		Bestelling bestelling = bestelService.readBestellingOpId(bestellingId);
		Set<BestellingHasArtikel> bhas = bestelling.getBestellingHasArtikelen(); 
	
    	model.addAttribute("bestellingHasArtikelen", bhas);
    	model.addAttribute("bestelling", bestelling);
		model.addAttribute("klantId", Integer.parseInt(klantId));
		
		return "bestellingUpdate";
	}
	
}