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
	public String processBestellingForm( 
			//@RequestParam("artikelnaam") String artikelnaam,
			@RequestParam("idArtikel") String artikelId,
			//@RequestParam("artikelnummer") String artikelnummer,
			//@RequestParam("artikelprijs") String artikelprijs,
			@RequestParam("aantal") String aantal,
			@RequestParam("klantId") String klantId,
			@RequestParam("idBestelling") String bestellingId,
			Model model) {
		
		klant = new Klant();
		klant.setIdKlant(Integer.parseInt(klantId));
		
		bestelling = new Bestelling();
		bestelling.setKlant(klant);
		
		String[] artikelIds = artikelId.split(",");
		String[] aantallen = aantal.split(",");  
		
		// === CREATE ===
		//if (bestellingId == null || bestellingId.isEmpty() || Integer.parseInt(bestellingId) == 0) {
			
			bestelService.createBestelling(bestelling);
			
			for(int i = 0; i < artikelIds.length; i++) {
				int id = Integer.parseInt(artikelIds[i]);
				Artikel artikelObj = bestelService.readArtikelOpId(id);
				bha = new BestellingHasArtikel();
				bha.setArtikel(artikelObj);
				bha.setBestelling(bestelling);
				bha.setAantal(Integer.parseInt("0" + aantallen[i]));  //beetje valsspelen dit
				//bestelling.addToBestellingHasArtikelen(bha);
				bestelService.createBestellingHasArtikel(bha);
			}
			
		// === UPDATE ===
//		} else {
//			bestelling.setIdBestelling(Integer.parseInt(bestellingId));
//			bestelService.updateBestelling(bestelling);
//			
//			for(int i = 0; i < artikelIds.length; i++) {
//				int id = Integer.parseInt(artikelIds[i]);
//				Artikel artikelObj = bestelService.readArtikelOpId(id);
//				bha = new BestellingHasArtikel();
//				bha.setArtikel(artikelObj);
//				bha.setBestelling(bestelling);
//				bha.setAantal(Integer.parseInt("0" + aantallen[i]));  //beetje valsspelen dit
//				//bestelling.addToBestellingHasArtikelen(bha);
//				bestelService.updateAantalArtikelenInBestelling(bha);
//				
//			}
//		}
	
		model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
        return "listBestelling";
	}
	
	@RequestMapping(value="/showUpdateBestelling", method=RequestMethod.GET)
    public String showUpdateBestellingForm(@RequestParam("idBestelling") String idBestelling, Model model) {
    	
		int id = Integer.parseInt(idBestelling);
		
    	
    	model.addAttribute("bestellingHasArtikelen", bestelService.readBestellingOpId(id).getBestellingHasArtikelen());
    	model.addAttribute("bestelling", bestelService.readBestellingOpId(id));
		model.addAttribute("klantId", bestelService.readBestellingOpId(id).getKlant().getIdKlant());
		
		// === ingekorte betere versie
//		bestelling = bestelService.readBestellingOpId(Integer.parseInt(idBestelling));
//		
//		model.addAttribute("bestellingHasArtikel", bestelling.getBestellingHasArtikelen());
//		model.addAttribute("bestelling", bestelling);
		
		
    	return "bestellingUpdate";
    }
	
	@RequestMapping(value="/updateBestelling", method=RequestMethod.POST)
	public String processUpdateBestellingForm(
		//@RequestParam("artikelnaam") String artikelnaam,
		@RequestParam("idArtikel") String artikelId,
		//@RequestParam("artikelnummer") String artikelnummer,
		//@RequestParam("artikelprijs") String artikelprijs,
		@RequestParam("aantal") String aantal,
		@RequestParam("klantId") String klantId,
		@RequestParam("idBestelling") String bestellingId,
		@RequestParam("idBHA") String bhaId,
		Model model) {
	
		klant = new Klant();
		klant.setIdKlant(Integer.parseInt(klantId));
		
		bestelling = new Bestelling();
		bestelling.setKlant(klant);
		
		String[] artikelIds = artikelId.split(",");
		String[] aantallen = aantal.split(",");
		String[] bhaIds = bhaId.split(",");
		
		
		bestelling.setIdBestelling(Integer.parseInt(bestellingId));
		bestelService.updateBestelling(bestelling);
		
		for(int i = 0; i < bhaIds.length; i++) {
			int id = Integer.parseInt(bhaIds[i]);
			bha = bestelService.readBestellingHasArtikelOpId(id);
			//bha.setArtikel(artikelObj);
			//bha.setBestelling(bestelling);
			bha.setAantal(Integer.parseInt("0" + aantallen[i]));  //beetje valsspelen dit
			//bestelling.addToBestellingHasArtikelen(bha);
			bestelService.updateAantalArtikelenInBestelling(bha);
			
		}
		
//		for(int i = 0; i < artikelIds.length; i++) {
//			int id = Integer.parseInt(artikelIds[i]);
//			Artikel artikelObj = bestelService.readArtikelOpId(id);
//			bha = new BestellingHasArtikel();
//			bha.setArtikel(artikelObj);
//			bha.setBestelling(bestelling);
//			bha.setAantal(Integer.parseInt("0" + aantallen[i]));  //beetje valsspelen dit
//			//bestelling.addToBestellingHasArtikelen(bha);
//			bestelService.updateAantalArtikelenInBestelling(bha);
//			
//		}
	
		model.addAttribute("bestellingen", bestelService.readAlleBestellingen());
	    return "listBestelling";

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
