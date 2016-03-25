/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import POJO.Artikel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Service.BestellingService;

@Controller
public class ArtikelController {

    @Autowired
    BestellingService dao;
    @Autowired
    Artikel artikel;
    

    @RequestMapping(value = "/listArtikelen", method = RequestMethod.GET)
    public String showLijstArtikelen(Model model) {
        model.addAttribute("artikelen",dao.readAllArtikel());
        return "listArtikelen";
    }

    @RequestMapping(value = "/deleteArtikel", method = RequestMethod.GET)
    public String deleteArtikel(@RequestParam("getId") String idArtikel, Model model) {
        int artikelId = Integer.parseInt(idArtikel);
        dao.deleteArtikelUitDatabase(artikelId);
        model.addAttribute("artikelen", dao.readAllArtikel());
        return "listArtikelen";
    }

    @RequestMapping(value = "/updateArtikel", method = RequestMethod.GET)
    public String showUpdateArtikelForm(@RequestParam("idArtikel") String idArtikel, Model model) {
        int artikelId = Integer.parseInt(idArtikel);
        Artikel artikel = dao.readArtikelOpId(artikelId);
        model.addAttribute("artikel", artikel);
        return "artikel";
    }

    @RequestMapping(value = "/createArtikel", method = RequestMethod.GET)
    public String showArtikelForm() {
        return "artikel";
    }

    @RequestMapping(value = "/createArtikel", method = RequestMethod.POST)
    public String createArtikel(@RequestParam("artikelnaam") String artikelnaam, @RequestParam("artikelprijs") String artikelprijs,
            @RequestParam("artikelnummer") String artikelnummer, @RequestParam("artikelomschrijving") String artikelomschrijving,
            @RequestParam("idArtikel") String idArtikel, Model model) {
        Artikel artikel = new Artikel();
        artikel.setArtikelnaam(artikelnaam);
        artikel.setArtikelnummer(artikelnummer);
        artikel.setArtikelprijs((Double.parseDouble(artikelprijs)));
        artikel.setArtikelomschrijving(artikelomschrijving);

        if (idArtikel == null || idArtikel.isEmpty() || Integer.parseInt(idArtikel) == 0) {
            dao.createArtikel(artikel);
        } else {
            artikel.setIdArtikel(Integer.parseInt(idArtikel));
            dao.updateArtikel(artikel);
        }
        model.addAttribute("artikelen", dao.readAllArtikel());
        return "listArtikelen";
    }
}
