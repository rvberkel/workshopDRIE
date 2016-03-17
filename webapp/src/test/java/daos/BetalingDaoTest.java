/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Helpers.DatabaseClearer;
import Helpers.HibernateUtil;
import Helpers.SpringHibernateUtil;
import POJO.Account;
import POJO.Bestelling;
import POJO.Betaalwijze;
import POJO.Betaling;
import POJO.Factuur;
import POJO.Klant;
import genericDao.GenericDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import static junit.framework.Assert.assertEquals;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
public class BetalingDaoTest {
    private static final Logger LOG = LoggerFactory.getLogger(AccountDaoTest.class);
    @Autowired
    private DatabaseClearer dbClearer;
    @Autowired
    @Qualifier("FactuurDao")
    GenericDao facDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    private Date date = new Date();
    @Autowired
    private Factuur f1;
    @Autowired
    private Bestelling b1;
    @Autowired
    private Klant klant;
    @Autowired
    private Betaalwijze bw;
    @Autowired
    private Set<Betaling> betalingen;
    @Autowired
    @Qualifier("BetalingDao")
    GenericDao instance;
    @Autowired
    private Betaling testBetaling;
    
    @Before
    public void setUp() {
        dbClearer.clearDatabase();
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        
        session.save(klant);
        klant.setIdKlant(1);
        
        b1.setKlant(klant);
        session.save(b1);
        
        f1.setFactuurDatum(new Timestamp(date.getTime()));
        f1.setBestelling(b1);
        session.save(f1);
        f1.setIdFactuur(1);
        
        bw.setBetaalwijze(0);
        session.save(bw);
        bw.setIdBetaalwijze(1);
                
        session.getTransaction().commit();
        session.close();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateBetaling() {
        LOG.info("createBetalingTest");
        
        testBetaling.setBetaalDatum(new Timestamp(date.getTime()));
        testBetaling.setBetaalwijze(bw);
        testBetaling.setKlant(klant);
        testBetaling.setFactuur(f1);
        instance.createEntity(testBetaling);
        assertTrue(((Betaling)instance.readEntity(1)).getIdBetaling()> 0);
    }
    
    @Test
    public void testDeleteBetaling() throws Exception {
        LOG.info("deleteBetalingTest");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        testBetaling.setBetaalDatum(new Timestamp(date.getTime()));
        testBetaling.setBetaalwijze(bw);
        testBetaling.setKlant(klant);
        testBetaling.setFactuur(f1);
        session.getTransaction().commit();
        session.close();
        instance.deleteEntity(testBetaling);
        assertNull(instance.readEntity(1));
    }
    
    @Test
    public void testUpdateBetaling() throws Exception {
        LOG.info("updateBetalingTest");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        testBetaling.setBetaalDatum(new Timestamp(date.getTime()));
        testBetaling.setBetaalwijze(bw);
        testBetaling.setKlant(klant);
        testBetaling.setFactuur(f1);
        session.save(testBetaling);
        session.getTransaction().commit();
        session.close();
        
        Betaling testBetaling2 = (Betaling)instance.readEntity(1);
        testBetaling2.setBetalingsGegevens("ik betaal niet");
        instance.updateEntity(testBetaling2);
        assertEquals("er schijnt nergens voor betaald te worden", 
                "ik betaal niet", ((Betaling)instance.readEntity(1)).getBetalingsGegevens() );
    }
    
    @Test
    public void testFindBetaling() {
        LOG.info("findBetalingTest");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        testBetaling.setBetaalDatum(new Timestamp(date.getTime()));
        testBetaling.setBetaalwijze(bw);
        testBetaling.setKlant(klant);
        testBetaling.setFactuur(f1);
        session.save(testBetaling);
        session.getTransaction().commit();
        session.close();
        instance.readEntity(1);
        assertEquals("id moet gelijk zijn", new Integer(1), ((Betaling)instance.readEntity(1)).getIdBetaling());
    }
    
    @Test
    public void testFindBetalingByExample(){
        LOG.info("findBetalingBy ExampleTest");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        testBetaling.setBetaalDatum(new Timestamp(date.getTime()));
        testBetaling.setBetaalwijze(bw);
        testBetaling.setKlant(klant);
        testBetaling.setFactuur(f1);
        session.save(testBetaling);
        session.getTransaction().commit();
        session.close();
        instance.readByExample(testBetaling);
        assertEquals("id moet gelijk zijn", new Integer(1), ((Betaling)instance.readEntity(1)).getIdBetaling());      
    }    
}
