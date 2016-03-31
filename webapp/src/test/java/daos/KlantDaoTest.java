/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Helpers.DatabaseClearer;
import Helpers.SpringHibernateUtil;
import POJO.Adres;
import POJO.AdresType;
import POJO.Klant;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
@WebAppConfiguration
public class KlantDaoTest {
	@Autowired
	DatabaseClearer dbClearer;
	@Autowired
	Adres adres;
	@Autowired
	AdresType adresType;
	@Autowired
	Klant klant;
	@Autowired
	SessionFactory sessionFactory;
    
    @Before
    public void setUp() {
    	dbClearer.clearDatabase();
    }
    
    @After
    public void tearDown() {
        dbClearer.clearDatabase();
    }

    @Test
    public void testCreateKlant() {
        Session session = sessionFactory.openSession();
        adres.setStraatnaam("Teststraat");
        adres.setHuisnummer("101");
        adres.setPostcode("1234AB");
        adres.setWoonplaats("Testopolis");
        
        session.beginTransaction();
        adres.setIdAdres((Integer) session.save(adres));
        session.getTransaction().commit();
        
        adresType.setAdres_type(0);
        session.beginTransaction();
        adresType.setIdAdres_type((Integer) session.save(adresType));
        session.getTransaction().commit();
        
        klant.setVoornaam("Theo");
        klant.setAchternaam("Tester");
        klant.setTussenvoegsel("de");
        klant.setEmail("ttest@nep.com");
        Map<Adres,AdresType> adressen = new HashMap<>();
        adressen.put(adres, adresType);
        klant.setAdressen(adressen);
        
        session.beginTransaction();
        klant.setIdKlant((Integer) session.save(klant));
        session.getTransaction().commit();
        session.close();
        assertTrue(klant.getIdKlant() > 0);
    }
    
}
