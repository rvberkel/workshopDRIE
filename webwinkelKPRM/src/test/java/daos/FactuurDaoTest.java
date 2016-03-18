
package daos;

import Helpers.DatabaseClearer;
import Helpers.HibernateUtil;
import Helpers.SpringHibernateUtil;
import POJO.*;
import genericDao.GenericDao;

import java.util.Date;
import java.sql.Timestamp;  
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
public class FactuurDaoTest {
	@Autowired
    private DatabaseClearer databaseClearer;
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
    Betaling bet1;
    @Autowired
    Betaling bet2;
    @Autowired
    Betaalwijze bw;
    
    private Set<Betaling> betalingen;
    
    @Before
    public void setUp() {
        databaseClearer.clearDatabase();
        
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
        
        session.getTransaction().commit();
        session.close();
        
        f1.setFactuurDatum(new Timestamp(date.getTime()));
        f1.setBestelling(b1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateFactuur() {
        //arrange
            //zie boven
        
        //act
        facDao.createEntity(f1);
        
        //assert
        System.out.println("Factuur ID = " + f1.getIdFactuur());
        assertTrue(f1.getIdFactuur().intValue() > 0);
    }
    
    @Test
    public void testFindFactuurByExample() {
        facDao.createEntity(f1);
        assertNotNull(facDao.readByExample(f1));
    }
    
    @Test
    public void testFindFactuurById() {
        //arrange
        facDao.createEntity(f1);
               
        //act & assert
        assertNotNull(facDao.readEntity(1));
    }
    
    @Test
    public void testDeleteFactuurByExample() {
        facDao.createEntity(f1);
        facDao.deleteEntity(f1);
        assertTrue(facDao.readByExample(f1).isEmpty());
    }
    
    @Test
    public void testDeleteFactuurById() {
        facDao.createEntity(f1);
        int id = f1.getIdFactuur();
        facDao.deleteEntityById(id);
        assertNull(facDao.readEntity(id));
    }
    
    @Test
    public void testUpdateFactuur() {
        facDao.createEntity(f1);
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        bw.setBetaalwijze(0);
        session.save(bw);
        
        bet1.setBetaalDatum(new Timestamp(date.getTime()));
        bet1.setFactuur(f1);
        bet1.setKlant(klant);
        bet1.setBetaalwijze(bw);
        bet2.setBetaalDatum(new Timestamp(date.getTime()));
        bet2.setFactuur(f1);
        bet2.setKlant(klant);
        bet2.setBetaalwijze(bw);
        session.save(bet1);
        session.save(bet2);
       
        betalingen = new HashSet<Betaling>();
        betalingen.add(bet1);
        betalingen.add(bet2);
        f1.setBetalingen(betalingen);
        
        session.getTransaction().commit();
        session.close();
        
        //act
        facDao.updateEntity(f1);
        //assert
        assertNotNull(((Factuur)facDao.readEntity(f1.getIdFactuur())).getBetalingen());
    }    
}
