package daos;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import Helpers.DatabaseClearer;
import Helpers.HibernateUtil;
import Helpers.SpringHibernateUtil;
import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingHasArtikel;
import POJO.Factuur;
import POJO.Klant;
import genericDao.GenericDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
@WebAppConfiguration
public class BestellingDaoTest {
	@Autowired
	private DatabaseClearer databaseClearer;
	@Autowired
    @Qualifier("BestellingDao")
    GenericDao bestellingDao;
	@Autowired
    @Qualifier("FactuurDao")
    GenericDao factuurDao;
	@Autowired
	private Bestelling bestelling;
	@Autowired
	private Bestelling bestelling2;
	@Autowired
	private Klant klant;
	@Autowired
	private Klant klant2;
	@Autowired
	private Artikel artikel;
	@Autowired
	private Artikel artikel2;
	@Autowired
	private BestellingHasArtikel bestelArtikel;
	@Autowired
	private BestellingHasArtikel bestelArtikel2;
	@Autowired
	private Factuur factuur;
	@Autowired
	private Factuur factuur2;
	
	@Before
	public void setUp() {
		databaseClearer.clearDatabase();
		
		klant.setEmail("tina@test.nl");
		klant.setTussenvoegsel("de");
		klant.setVoornaam("Tina");
		klant.setAchternaam("Tester");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(klant);
		session.getTransaction().commit();
		
		klant2.setEmail("tinus@test.nl");
		klant2.setTussenvoegsel("de");
		klant2.setVoornaam("Tinus");
		klant2.setAchternaam("Tester");
		session.beginTransaction();
		session.save(klant2);
		session.getTransaction().commit();
		
		artikel.setArtikelnaam("Spul");
		artikel.setArtikelnummer("123154hgnb");
		artikel.setArtikelomschrijving("Dit is spul");
		artikel.setArtikelprijs(10.65);
		session.beginTransaction();
		session.save(artikel);
		session.getTransaction().commit();
		
		artikel2.setArtikelnaam("Rommel");
		artikel2.setArtikelnummer("wedf9823");
		artikel2.setArtikelomschrijving("Dit is rommel");
		artikel2.setArtikelprijs(6.36);
		session.beginTransaction();
		session.save(artikel2);
		session.getTransaction().commit();
		
		bestelling2.setKlant(klant);
		bestelArtikel.setArtikel(artikel);
		bestelArtikel.setBestelling(bestelling2);
		bestelArtikel.setAantal(2);
		bestelling2.addToBestellingHasArtikelen(bestelArtikel);
		factuur.setFactuurDatum(new Timestamp(new Date().getTime()));
		bestelling2.addToFacturen(factuur);
		session.beginTransaction();
		session.save(bestelling2);
		session.getTransaction().commit();
		session.close();
	}
	
	@After
	public void tearDown() {
		//databaseClearer.clearDatabase();
	}

	@Test
	public void testCreateBestelling() {
		bestelling.setKlant(klant);
		bestelArtikel2.setAantal(1000);
		bestelArtikel2.setBestelling(bestelling);
		bestelArtikel2.setArtikel(artikel2);
		bestelling.addToBestellingHasArtikelen(bestelArtikel2);
		factuur2.setBestelling(bestelling);
		factuur2.setFactuurDatum(new Timestamp(new Date().getTime()));
		bestelling.addToFacturen(factuur2);
		bestellingDao.createEntity(bestelling);
		assertEquals(2, (int)bestelling.getIdBestelling());
	}
	
	@Test
	public void testDeleteBestelling() {
		bestellingDao.deleteEntity(bestelling2);
		assertEquals(0, bestellingDao.readByExample(bestelling2).size());
	}
	
	@Test
	public void testDeleteBestellingById() {
		bestellingDao.deleteEntityById(1);
		assertNull(bestellingDao.readEntity(1));
	}
	
	@Test
	public void testUpdateBestellingToevoegenNieuwArtikel() {
		bestelArtikel2.setAantal(14);
		bestelArtikel2.setArtikel(artikel2);
		bestelArtikel2.setBestelling(bestelling2);
		bestelling2.addToBestellingHasArtikelen(bestelArtikel2);
		bestellingDao.updateEntity(bestelling2);
		assertEquals(1, (int)bestelling2.getKlant().getIdKlant());
		assertEquals(2, (int)((Factuur)factuurDao.readEntity(1)).getBestelling().getBestellingHasArtikelen().size());
	}
	
	@Test
	public void testUpdateBestellingAanpassingAantalVanAanwezigArtikel() {
		bestelArtikel.setAantal(65);
		bestelArtikel.setArtikel(artikel);
		bestelArtikel.setBestelling(bestelling2);
		bestelling2.addToBestellingHasArtikelen(bestelArtikel);
		bestellingDao.updateEntity(bestelling2);
		assertEquals(1, bestelling2.getFacturen().size());
		assertEquals(1, (int)((Factuur)factuurDao.readEntity(1)).getBestelling().getBestellingHasArtikelen().size());
	}
	
	@Test
	public void testFindBestellingById() {
		assertEquals(1, (int)((Bestelling)bestellingDao.readEntity(1)).getIdBestelling());
	}
	
	@Test
	public void testFindBestellingByExample() {
		assertEquals(1, (int)((Bestelling)bestellingDao.readByExample(bestelling2).get(0)).getIdBestelling());
	}
}
