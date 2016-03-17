package Service;

import POJO.Account;
import POJO.Adres;
import POJO.AdresType;
import POJO.Klant;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import Helpers.DatabaseClearer;
import Helpers.HibernateUtil;
import Helpers.SpringHibernateUtil;
import POJO.Account;
import POJO.Adres;
import POJO.AdresType;
import POJO.Bestelling;
import POJO.Betaalwijze;
import POJO.Betaling;
import POJO.Factuur;
import POJO.Klant;
import daos.AccountDaoTest;
import daos.BetalingDao;
import daos.FactuurDao;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
public class KlantenServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(AccountDaoTest.class);
    @Autowired
    private DatabaseClearer databaseClearer;
    
    private Date date = new Date();
    @Autowired
    private Factuur f1;
    @Autowired
    private Bestelling b1;
    @Autowired
    private Klant klant;
    @Autowired
    private Adres adres;
    @Autowired
    private AdresType adresType;
    @Autowired
    private Betaalwijze bw;
    @Autowired
    private Account account;
    
    @Autowired
    KlantenService instance;
    
    public KlantenServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        databaseClearer.clearDatabase();
        
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        
        adres.setStraatnaam("Teststraat");
        adres.setHuisnummer("101");
        adres.setPostcode("1234AB");
        adres.setWoonplaats("Testopolis");
        
        adresType.setAdres_type(0);
        
        account.setAccountNaam("testingunlimited85");
        account.setCreatieDatum(new Timestamp(date.getTime()));   
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateKlant() {
        boolean expResult = true;
        boolean result = instance.createKlant(klant);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateAdres() {
        boolean expResult = true;
        boolean result = instance.createAdres(adres);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateKlantMetAdres() {
        boolean expResult = true;
        boolean result = instance.createKlantMetAdres(klant, adres, adresType);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateAccount(){
        boolean expResult = true;
        boolean result = instance.createAccount(account, klant);
        assertEquals(expResult, result);
    }

    @Test
    public void testReadKlant() {
        instance.createKlant(klant);
        assertEquals("moet Theo zijn", "Theo", instance.readKlant(klant).getVoornaam());
    }

    @Test
    public void testReadKlantOpId() {
        int id = 1;
        instance.createKlant(klant);
        assertEquals("moet Theo zijn", "Theo", instance.readKlantOpId(id).getVoornaam());
    }

    @Test
    public void testReadAdres() {
        instance.createAdres(adres);
        assertEquals("moet Teststraat zijn", "Teststraat", instance.readAdres(adres).getStraatnaam());
    }

    @Test
    public void testReadAdresOpId() {
        int adresId = 1;
        instance.createAdres(adres);
        assertEquals("moet Teststraat zijn", "Teststraat", instance.readAdresOpId(adresId).getStraatnaam());
    }

    @Test
    public void testReadAccountOpId() {
        int accountId = 1;
        instance.createAccount(account, klant);
        assertEquals("moet testingunlimited85 zijn", "testingunlimited85", instance.readAccountOpId(accountId).getAccountNaam());
    }

    @Test
    public void testReadAccountOpKlantId() {
        int klantId = 1;
        instance.createAccount(account, klant);
        assertEquals(" moet testingunlimited85 zijn", "testingunlimited85", instance.readAccountOpKlantId(klantId).get(0).getAccountNaam());
    }

    @Test
    public void testUpdateKlant() {
        instance.createKlant(klant);
        klant.setVoornaam("Tamarinde");
        instance.updateKlant(klant);
        assertEquals("moet Tamarinde zijn", "Tamarinde", instance.readKlant(klant).getVoornaam());
    }

    @Test
    public void testUpdateAdres() {
        instance.createAdres(adres);
        adres.setStraatnaam("ProbeerPlein");
        instance.updateAdres(adres);
        assertEquals("moet ProbeerPlein zijn", "ProbeerPlein", instance.readAdres(adres).getStraatnaam());
    }

    @Test
    public void testUpdateAccount() {
        instance.createAccount(account, klant);
        account.setAccountNaam("DaTesterBoy96");
        instance.updateAccount(account);
        assertEquals("moet DaTesterBoy96 zijn", "DaTesterBoy96", instance.readAccountOpId(1).getAccountNaam());
    }

    @Test
    public void testDeleteKlant() {
        instance.createKlant(klant);
        instance.deleteKlant(klant);
        assertNull(instance.readKlantOpId(1));
    }

    @Test
    public void testDeleteKlantById() {
        int klantId = 1;
        instance.createKlant(klant);
        instance.deleteKlantById(klantId);
        assertNull(instance.readKlantOpId(1));
    }

    @Test
    public void testDeleteAdres() {
        instance.createAdres(adres);
        instance.deleteAdres(adres);
        assertNull(instance.readAdresOpId(1));
    }

    @Test
    public void testDeleteAdresById() {
        int adresId = 1;
        instance.createAdres(adres);
        instance.deleteAdresById(adresId);
        assertNull(instance.readAdresOpId(1));
    }

    @Test
    public void testDeleteAccount() {
        int accountId = 1;
        instance.createAccount(account, klant);
        instance.deleteAccount(account);
        assertNull(instance.readAccountOpId(accountId));
    }    
}
