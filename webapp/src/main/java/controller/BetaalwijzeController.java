package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import POJO.Betaalwijze;
import Service.BestellingService;
import daos.BetaalwijzeDao;

@Controller
public class BetaalwijzeController {
	@Autowired
	BestellingService bestelService;
	
	
	BetaalwijzeDao betaalwijzeDao = new BetaalwijzeDao(); //dit moet natuurlijk eigen via service en generiekeDAO lopen
	
	@Autowired
	Betaalwijze betaalwijzeObject;
	
	@RequestMapping(value="/createbetaalwijze", method=RequestMethod.GET)
    public String showCreateBetaalwijzeForm() {
    	return "betaalwijze";
    }
	
	@RequestMapping(value="/listbetaalwijze", method=GET) 
	public String listBetaalwijze(Model model) {
		List<Betaalwijze> lijst = new ArrayList<>();
	//	Betaalwijze b = new Betaalwijze();
//		b.setBetaalwijze(2); //de derde in de array

//	lijst = betaalwijzeDao.readAll();
		lijst = bestelService.readAlleBetaalwijzen();
	//	b = bestelService.readBetaalWijzeOpId(1);
	//	lijst.add(b);
		
		model.addAttribute("betaalwijzen", lijst);
    	return "listBetaalwijze";
	}
	
	@RequestMapping(value="/createBetaalwijze", method=RequestMethod.POST)
	public String createBetaalwijze(@RequestParam("betaalwijze") String betaalwijze,
    		@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
		
		if (Integer.parseInt(betaalwijze) > 6 || Integer.parseInt(betaalwijze) < 0 ) {
			return "betaalwijze";  //lomp, maar t werkt
		}
		
    	betaalwijzeObject = new Betaalwijze();
    	betaalwijzeObject.setBetaalwijze(Integer.parseInt(betaalwijze)); 
    	
    	if (idBetaalwijze == null || idBetaalwijze.isEmpty())
    		bestelService.createBetaalwijze(betaalwijzeObject);
    	else {
    		betaalwijzeObject.setIdBetaalwijze(Integer.parseInt(idBetaalwijze));
    		bestelService.updateBetaalwijze(betaalwijzeObject);
    	}
    	model.addAttribute("betaalwijzen", bestelService.readAlleBetaalwijzen());
    	return "listBetaalwijze";
    }
	
    @RequestMapping(value="/deleteBetaalwijze", method=RequestMethod.GET)
    public String deleteBetaalwijze(@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
    	int id = Integer.parseInt(idBetaalwijze);
    	Betaalwijze bw = new Betaalwijze();
    	bw.setIdBetaalwijze(id);
    	bestelService.deleteBetaalwijze(bw);
    	
    	model.addAttribute("betaalwijzen", bestelService.readAlleBetaalwijzen());
    	
    	return "listBetaalwijze";
    }
    
    
    @RequestMapping(value="/updateBetaalwijze", method=RequestMethod.GET)
    public String showUpdateBetaalForm(@RequestParam("idBetaalwijze") String idBetaalwijze, Model model) {
    	int idBetaawijze = Integer.parseInt(idBetaalwijze);
    	betaalwijzeObject = bestelService.readBetaalWijzeOpId(idBetaawijze);
    	model.addAttribute("betaalwijze", betaalwijzeObject);
    	return "betaalwijze";
    }
	
}
