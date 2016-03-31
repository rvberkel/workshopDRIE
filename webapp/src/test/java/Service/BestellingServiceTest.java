
package Service;

import POJO.*;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import Helpers.DatabaseClearer;
import Helpers.SpringHibernateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
@WebAppConfiguration
public class BestellingServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(BestellingServiceTest.class);
    @Autowired
    private DatabaseClearer dbClearer;
    @Autowired
    BestellingService bestellingService;
    @Autowired
    KlantenService klantenService;
    private Date date = new Date();
    @Autowired
    private Factuur factuur;
    
    @Autowired
    private Bestelling bestelling;
    @Autowired
    private Klant klant;
    @Autowired
    private Artikel artikel;
    @Autowired
    private BestellingHasArtikel bestellingHasArtikel;
    @Autowired
    private Adres adres;
    @Autowired
    private AdresType adresType;
    @Autowired
    private Betaalwijze betaalwijze;
    @Autowired
    private Account account;
    @Autowired
    private Betaling betaling;
    
    @Before
    public void setUp() {
        dbClearer.clearDatabase();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateBestellingMetAllesErOpEnEraan_ShouldPass_OfMisschienNiet() {
         
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        
        artikel.setArtikelnaam("artikelCreated");
        artikel.setArtikelnummer("0001");
        artikel.setArtikelprijs(2);
        artikel.setArtikelomschrijving("Om te testen");
        bestellingService.createArtikel(artikel);
        
        adres.setStraatnaam("Teststraat");
        adres.setHuisnummer("101");
        adres.setPostcode("1234AB");
        adres.setWoonplaats("Testopolis");
        
        adresType.setAdres_type(0);
        klantenService.createKlantMetAdres(klant, adres, adresType);
        
        account.setAccountNaam("testingunlimited85");
        account.setCreatieDatum(new Timestamp(date.getTime()));
        klantenService.createAccount(account, klant);
        
        factuur.setFactuurDatum(new Timestamp(date.getTime()));
                
        betaling.setBetaalDatum(new Timestamp(date.getTime()));
        betaling.setBetaalwijze(betaalwijze);
        betaling.setBetalingsGegevens("ik betaal niet");
        
        betaalwijze.setBetaalwijzeKeuze(0);
        bestellingService.createBetaalwijze(betaalwijze);
        
        betaling.setKlant(klant);
        factuur.addToBetalingen(betaling);
        
        bestellingHasArtikel.setAantal(1);
        bestellingHasArtikel.setArtikel(artikel);
        
        bestelling.setKlant(klant);
        bestelling.addToBestellingHasArtikelen(bestellingHasArtikel);
        bestelling.addToFacturen(factuur);
        bestellingService.createBestelling(bestelling);
        assertTrue(bestelling.getIdBestelling()==1);
        assertTrue(bestellingService.readFactuurOpId(1).getIdFactuur()==1); 
    }
}
