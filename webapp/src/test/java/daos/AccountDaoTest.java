package daos;

import Helpers.DatabaseClearer;
import Helpers.HibernateUtil;
import Helpers.SpringHibernateUtil;
import Helpers.WebAppInitializer;
import Helpers.WebConfig;
import POJO.Account;
import POJO.Klant;
import genericDao.GenericDao;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import java.sql.Timestamp;
import javax.annotation.Resource;
import static junit.framework.Assert.assertEquals;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)//deze klasse wordt gebruikt om de test te runnen in plaats van JUnits eigen klasse
@ContextConfiguration(classes=SpringHibernateUtil.class)
@WebAppConfiguration
public class AccountDaoTest {
    private static final Logger LOG = LoggerFactory.getLogger(AccountDaoTest.class);
    
    @Autowired
    private DatabaseClearer dbClearer;
    
    @Autowired
    Klant klant;
    @Autowired
    Account testAccount;
    @Autowired
    @Qualifier("AccountDao")
    GenericDao instance;
    
    public AccountDaoTest() {
    }
    
    @Before
    public void setUp() {
        dbClearer.clearDatabase();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateAccount() {
        LOG.info("createAccountTest");
        Date date= new Date();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        session.beginTransaction();
        klant.setIdKlant((Integer) session.save(klant));
        session.getTransaction().commit();
        session.close();
        
        testAccount.setAccountNaam("testingunlimited85");
        testAccount.setCreatieDatum(new Timestamp(date.getTime()));
        testAccount.setKlant(klant);
        instance.createEntity(testAccount);
        Account testAccount = (Account) instance.readEntity(1);
        assertEquals("Account naam moet testingunlimited85 zijn", "testingunlimited85", testAccount.getAccountNaam());
        
    }
    
    @Test
    public void testUpdateAccount() {
        
        LOG.info("updateAccountTest");
        Date date= new Date();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        session.beginTransaction();
        klant.setIdKlant((Integer) session.save(klant));
        session.getTransaction().commit();
        session.close();
        
        testAccount.setAccountNaam("testingunlimited85");
        testAccount.setCreatieDatum(new Timestamp(date.getTime()));
        testAccount.setKlant(klant);
        instance.createEntity(testAccount);
        
        testAccount.setAccountNaam("DaTesterBoy96");
        instance.updateEntity(testAccount);
        Account testAccount = (Account) instance.readEntity(1);
        assertEquals("Account naam moet DaTesterBoy96 zijn", "DaTesterBoy96", testAccount.getAccountNaam());
    }
    
    @Test
    public void testDeleteAccount() throws Exception {     
        LOG.info("deleteAccountTest");
        Date date= new Date();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        session.beginTransaction();
        klant.setIdKlant((Integer) session.save(klant));
        session.getTransaction().commit();
        session.close();
        
        testAccount.setAccountNaam("testingunlimited85");
        testAccount.setCreatieDatum(new Timestamp(date.getTime()));
        testAccount.setKlant(klant);
        instance.createEntity(testAccount);

        instance.deleteEntity(testAccount);
        
        assertNull(instance.readEntity(1));
    }
    
    @Test
    public void testFindAccount() {
        LOG.info("deleteAccountTest");
        Date date= new Date();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        session.beginTransaction();
        klant.setIdKlant((Integer) session.save(klant));
        session.getTransaction().commit();
        session.close();
        
        testAccount.setAccountNaam("testingunlimited85");
        testAccount.setCreatieDatum(new Timestamp(date.getTime()));
        testAccount.setKlant(klant);
        instance.createEntity(testAccount);
        Account testAccount = (Account) instance.readEntity(1);
        assertTrue(testAccount.getIdAccount() > 0);
    }
    
}
