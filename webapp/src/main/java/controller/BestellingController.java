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
	
	@RequestMapping(value="/showUpdateBestellingForm", method=RequestMethod.GET)
    public String showUpdateBestellingForm(@RequestParam("idBestelling") String idBestelling, Model model) {
    	
		int bestellingId = Integer.parseInt(idBestelling);
		Bestelling bestelObj = bestelService.readBestellingOpId(bestellingId);
		Klant klantObj = bestelObj.getKlant();
		Set<BestellingHasArtikel> bhas = bestelObj.getBestellingHasArtikelen();
		
    	model.addAttribute("bestellingHasArtikelen", bhas);
    	model.addAttribute("bestelling", bestelObj);
		model.addAttribute("klantId", klantObj.getIdKlant());
		model.addAttribute("totaalprijs", getTotaalprijs(bestellingId));
		
    	return "bestellingUpdate";
    }
	
	@RequestMapping (value = "/showArtikelKeuzeLijst", method=RequestMethod.GET)
	public String showArtikelList(@RequestParam("klantId") String klantId, @RequestParam("idBestelling") String idBestelling,
									Model model) {
		int bestellingId = Integer.parseInt(idBestelling);
		List<Artikel> artikelenInBestelling = bestelService.readArtikelOpBestellingId(bestellingId);
		System.out.println(artikelenInBestelling.size());
		List<Artikel> alleArtikelen = bestelService.readAllArtikel();
		System.out.println(alleArtikelen.size());
		List<Artikel> bijnaAlleArtikelen = new ArrayList<>();
		for (Artikel artikel : alleArtikelen) {
			for (Artikel bestelArtikel : artikelenInBestelling) {
				if (!artikel.getIdArtikel().equals(bestelArtikel.getIdArtikel()))
					bijnaAlleArtikelen.add(artikel);
			}
		}
		System.out.println(bijnaAlleArtikelen.size());
		model.addAttribute("artikelKeuzeLijst", bijnaAlleArtikelen);
		model.addAttribute("idBestelling", idBestelling);
		model.addAttribute("klantId", Integer.parseInt(klantId));
		return "artikelKeuzeLijst";
	}
	
	@RequestMapping(value="/createBestelling", method=RequestMethod.POST)
	public String processBestellingForm(@RequestParam("idArtikel") String artikelId,
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
		bestelService.createBestelling(bestelling);
			
		for(int i = 0; i < artikelIds.length; i++) {	
			int id = Integer.parseInt(artikelIds[i]);
			Artikel artikelObj = bestelService.readArtikelOpId(id);
			BestellingHasArtikel bha = new BestellingHasArtikel();
			bha.setArtikel(artikelObj);  
			bha.setBestelling(bestelling);
			bha.setAantal(Integer.parseInt(cleanedAantallen.get(i))); 
			bestelService.createBestellingHasArtikel(bha);
		}
		
		int bestellingId = bestelling.getIdBestelling();
		bestelling = bestelService.readBestellingOpId(bestellingId);
		
        //DOOR naar updateBestelling vanwege TOTAALPRIJS
        model.addAttribute("bestellingHasArtikelen", bestelling.getBestellingHasArtikelen());  
    	model.addAttribute("bestelling", bestelling); 				
		model.addAttribute("klantId", Integer.parseInt(klantId)); 
		model.addAttribute("totaalprijs", getTotaalprijs(bestellingId));  
		return "bestellingUpdate";
	}
	
	@RequestMapping(value="/updateBestelling", method=RequestMethod.POST)
	public String processUpdateBestellingForm(
			@RequestParam("idArtikel") String artikelId,
			@RequestParam("aantal") String aantal,
			@RequestParam("klantId") String klantId,
			@RequestParam("idBestelling") String bestellingId,
			@RequestParam("idBHA") String bhaId,
			Model model) {
	
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
		
		int id_bestelling = Integer.parseInt(bestellingId);
		Bestelling bestelling = bestelService.readBestellingOpId(id_bestelling);
	    
        model.addAttribute("bestellingHasArtikelen", bestelling.getBestellingHasArtikelen());  
    	model.addAttribute("bestelling", bestelling); 				
		model.addAttribute("klantId", Integer.parseInt(klantId)); 
		model.addAttribute("totaalprijs", getTotaalprijs(id_bestelling));  
		return "bestellingUpdate";
	}
	
	
	//Delete bestelling 
	@RequestMapping (value = "/deleteBestelling", method = RequestMethod.GET)
	  public String deleteBestelling(@RequestParam("idBestelling") String idBestelling, Model model) {
        bestelService.removeBestelling(Integer.parseInt(idBestelling));
        
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
		model.addAttribute("totaalprijs", getTotaalprijs(bestellingId));
		return "bestellingUpdate";
	}
	
	@RequestMapping(value = "/addArtikelToBestelling", method=RequestMethod.POST)
	public String addArtToBest(@RequestParam("idBestelling") String idBestelling, @RequestParam("aantal") String aantal,
			@RequestParam("idArtikel") String idArtikel, Model model) {
		int bestellingId = Integer.parseInt(idBestelling);
		String[] artikelIds = idArtikel.split(",");
		String[] aantallen = aantal.split(",");
		List<String> cleanedAantallen = new ArrayList<>();
		for(int i = 0; i < aantallen.length; i++) {
			if(!aantallen[i].isEmpty()) {
				cleanedAantallen.add(aantallen[i]);
			}
		}
		for(int i = 0; i < artikelIds.length; i++) {
			int artikelId = Integer.parseInt(artikelIds[i]);
			BestellingHasArtikel bha = new BestellingHasArtikel();
			bha.setArtikel(bestelService.readArtikelOpId(artikelId));
			bha.setBestelling(bestelService.readBestellingOpId(bestellingId));
			bha.setAantal(Integer.parseInt(cleanedAantallen.get(i))); 
			//bestelling.addToBestellingHasArtikelen(bha);
			bestelService.createBestellingHasArtikel(bha);
		}
		Bestelling bestelling = bestelService.readBestellingOpId(bestellingId);
		Set<BestellingHasArtikel> bhas = bestelling.getBestellingHasArtikelen(); 
	
    	model.addAttribute("bestellingHasArtikelen", bhas);
    	model.addAttribute("bestelling", bestelling);
		model.addAttribute("klantId");
		model.addAttribute("totaalprijs", getTotaalprijs(bestellingId));
		return "bestellingUpdate";
	}
	
	private double getTotaalprijs(int idBestelling) {
		Bestelling bestelObj = bestelService.readBestellingOpId(idBestelling);
		Set<BestellingHasArtikel> bhas = bestelObj.getBestellingHasArtikelen();
		
		double totaalprijs = 0;
		for (BestellingHasArtikel bha: bhas) {
			double prijs = bha.getArtikel().getArtikelprijs() * bha.getAantal();
			totaalprijs = totaalprijs + prijs;
		}
		
		return (totaalprijs * 100)/100;
	}
}
