package daos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import Helpers.DatabaseClearer;
import POJO.Adres;
import POJO.AdresType;
import POJO.Artikel;
import POJO.Klant;
import Service.BestellingService;
import Service.KlantenService;
import genericDao.GenericDao;
import Helpers.SpringHibernateUtil;
import Helpers.WebAppInitializer;
import Helpers.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
@WebAppConfiguration
public class AdresDaoTest {
	@Autowired
    //@Qualifier("AdresDao")
	AdresDao adresDao;
	@Autowired
	//@Qualifier("AdresTypeDao")
	AdresTypeDao adresTypeDao;
	@Autowired
	Klant klant;
	@Autowired
	Klant klant2;
	@Autowired
	private AdresType adresType;
	@Autowired
	private AdresType adresType2;
	@Autowired
	private AdresType adresType3;
	@Autowired
	private Adres adres;
	@Autowired
	private Adres adres2;
	@Autowired
	private Adres adres3;
	@Autowired
	Artikel artikelCreated;
	@Autowired 
	Artikel artikel1;
	@Autowired
	Artikel artikel2;
	@Autowired
	Artikel artikel3;
	@Autowired
    KlantenService klantenService;
	@Autowired
	BestellingService bestellingService;
	@Autowired
	private DatabaseClearer databaseClearer;
	
	@Before
	public void clearDatabase() {
		databaseClearer.clearDatabase();
		klant.setVoornaam("Henk");
		klant.setTussenvoegsel("van");
		klant.setAchternaam("Dijk");
		klant.setEmail("henk@vandijk.nl");
		klant2.setVoornaam("Sjonneh");
		klant2.setTussenvoegsel("de");
		klant2.setAchternaam("Bonneh");
		klant2.setEmail("sjappie@sjonnie.com");
		adres.setStraatnaam("Teststraat");
        adres.setHuisnummer("101");
        adres.setPostcode("1234AB");
        adres.setWoonplaats("Testopolis");
        adres2.setStraatnaam("Testlaan");
        adres2.setHuisnummer("2");
        adres2.setPostcode("4321BA");
        adres2.setWoonplaats("Testipilis");
        adres3.setStraatnaam("Fluitjeshuppel");
        adres3.setHuisnummer("123");
        adres3.setPostcode("8001GA");
        adres3.setWoonplaats("Orgelhummer");
        adresType3.setAdres_type(2);
        adresType2.setAdres_type(1);
        adresType.setAdres_type(0);
        adresDao.createEntity(adres);
        adresDao.createEntity(adres2);
        adresDao.createEntity(adres3);
        adresTypeDao.createEntity(adresType);
        adresTypeDao.createEntity(adresType2);
        adresTypeDao.createEntity(adresType3);
        klant.addToAdressen(adres, adresType);
        klant.addToAdressen(adres2, adresType2);
        klant2.addToAdressen(adres, adresType2);
        klant2.addToAdressen(adres2, adresType);
        klant2.addToAdressen(adres3, adresType3);
        klantenService.createKlant(klant);
        klantenService.createKlant(klant2);
        
        artikelCreated.setArtikelnaam("artikelCreated");
        artikelCreated.setArtikelnummer("0001");
        artikelCreated.setArtikelprijs(2);
        artikelCreated.setArtikelomschrijving("Om te testen");
        bestellingService.createArtikel(artikelCreated);
        artikel1.setArtikelnaam("Badpak");
        artikel1.setArtikelnummer("0001");
        artikel1.setArtikelprijs(19.95);
        artikel1.setArtikelomschrijving("Om te creëeren");
        bestellingService.createArtikel(artikel1);
        artikel2.setArtikelnaam("Blaat");
        artikel2.setArtikelnummer("0004");
        artikel2.setArtikelprijs(56);
        artikel2.setArtikelomschrijving("Burp");
        bestellingService.createArtikel(artikel2);
        artikel3.setArtikelnaam("Fluffel");
        artikel3.setArtikelnummer("3204");
        artikel3.setArtikelprijs(4);
        artikel3.setArtikelomschrijving("Nurg");
        bestellingService.createArtikel(artikel3);
	}
	
	/*
	@Test
	public void adresDaoShouldAddAdres() {
		adres.setStraatnaam("pluhweg");
		adres.setHuisnummer("1234a");
		adres.setPostcode("1234ab");
		adres.setWoonplaats("dorpdorp");
		assertEquals("pluhweg", adres.getStraatnaam());
		instance.createEntity(adres);
		assertEquals(1, (int)adres.getIdAdres());
	}
	*/
	/*
	@Test
	public void testReadAdresOpPostcodeEnHuisnummer() {
		Adres adres4 = adresDao.readByPostcodeAndHuisnummer(adres.getPostcode(), adres.getHuisnummer());
		assertEquals("101", adres4.getHuisnummer());
	}
	*/
	/*
	@Test
	public void testReadAdresMetAdresTypeOpAdresId() {
	}
	*/
	@Test
	public void testReadAdresTypeOpKlantId() {
		adresDao.coupleAdresWithKlant(klant2.getIdKlant(), adres.getIdAdres(), adresType.getIdAdres_type());
		List<AdresType> adrestypen = adresTypeDao.readByKlantId(2);
		List<Adres> adressen = adresDao.readByKlantId(2);
		assertEquals(4, adrestypen.size());
		assertEquals(4, adressen.size());
	}
}
