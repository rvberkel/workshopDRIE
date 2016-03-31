package daos;

import Helpers.*;
import POJO.*;
import genericDao.GenericDao;

import java.util.List;
import org.hibernate.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
@WebAppConfiguration
public class BestellingHasArtikelDaoTest {
	@Autowired
    SessionFactory sessionFactory;
	@Autowired
    DatabaseClearer dbClearer;
	@Autowired
    Klant klant;
	@Autowired
    Bestelling bestelling;
	@Autowired
    Artikel artikel;
	@Autowired
    Artikel artikel2;
	@Autowired
    BestellingHasArtikel bhaCreated;
	@Autowired
    @Qualifier("BestellingHasArtikelDao")
    GenericDao instance;
     
    public BestellingHasArtikelDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() { 
    }
    
    @Before
    public void setUp() {
        dbClearer.clearDatabase();
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        klant.setVoornaam("voornaam");
        klant.setAchternaam("Achternaam");
        klant.setEmail("email");
        klant.setIdKlant((Integer) session.save(klant));
        
        bestelling.setKlant(klant);
        bestelling.setIdBestelling((Integer)session.save(bestelling));
      
        artikel.setArtikelnaam("Test");
        artikel.setArtikelnummer("1111");
        artikel.setArtikelprijs(10);
        artikel.setIdArtikel((Integer) session.save(artikel));
        
        artikel2.setArtikelnaam("Test2");
        artikel2.setArtikelnummer("2222");
        artikel2.setArtikelprijs(20);
        artikel2.setIdArtikel((Integer) session.save(artikel2));
        
        bhaCreated.setBestelling(bestelling);
        bhaCreated.setArtikel(artikel);
        bhaCreated.setAantal(5);
        bhaCreated.setIdBestelArtikel((Integer) session.save(bhaCreated));
        
        session.getTransaction().commit();
        session.close();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateBestellingHasArtikel_ShouldPass() {
        System.out.println("createBestellingHasArtikel_ShouldPass");
        //arrange
        BestellingHasArtikel bha = new BestellingHasArtikel();
        bha.setArtikel(artikel2);
        bha.setBestelling(bestelling);
        bha.setAantal(5);
        //act
        instance.createEntity(bha);
        BestellingHasArtikel bhaTest;
        //Assert
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bhaTest = (BestellingHasArtikel) session.get("POJO.BestellingHasArtikel", 2);
            session.getTransaction().commit();
        }
        assertEquals(bha.getAantal(), bhaTest.getAantal());
        
    }

    @Test
    public void testDeleteBestellingHasArtikel_ShouldPass() {
        System.out.println("deleteBestellingHasArtikel_ShouldPass");
        //arrange in setup
        BestellingHasArtikel bhaTest;
        //act
        instance.deleteEntity(bhaCreated);
        //assert
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bhaTest = (BestellingHasArtikel) session.get("POJO.BestellingHasArtikel", bhaCreated.getIdBestelArtikel());
            session.getTransaction().commit();
        }
        assertNull(bhaTest);
    }

   @Test
    public void testDeleteBestellingHasArtikelById_ShouldPass() {
        System.out.println("deleteBestellingHasArtikelById_ShouldPass");
        //arrange in setup
        BestellingHasArtikel bhaTest;
        //act
        instance.deleteEntityById(bhaCreated.getIdBestelArtikel());
        //assert
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bhaTest = (BestellingHasArtikel) session.get("POJO.BestellingHasArtikel", bhaCreated.getIdBestelArtikel());
            session.getTransaction().commit();
        }
        assertNull(bhaTest);
    }

    @Test
    public void testUpdateBestellingHasArtikel_ShouldPass() {
        System.out.println("updateBestellingHasArtikel_ShouldPass");
        //arrange in setup
        BestellingHasArtikel bhaTest;
        //act
        bhaCreated.setAantal(9);
        instance.updateEntity(bhaCreated);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bhaTest = (BestellingHasArtikel) session.get("POJO.BestellingHasArtikel", bhaCreated.getIdBestelArtikel());
            session.getTransaction().commit();
        }
        assertEquals(bhaCreated.getAantal(), bhaTest.getAantal());
    }

    @Test
    public void testFindBestellingHasArtikelById_ShouldPass() {
        System.out.println("findBestellingHasArtikelById_ShouldPass");
        //arrange
        BestellingHasArtikel expResult = bhaCreated;
        //act
        BestellingHasArtikel result = (BestellingHasArtikel)instance.readEntity(bhaCreated.getIdBestelArtikel());
        //assert
        assertEquals(expResult.getAantal(), result.getAantal());
    }

    @Test
    public void testFindBestellingHasArtikelByExample_ShouldPass() {
        System.out.println("findBestellingHasArtikelByExample_ShouldPass");
        //arrange in setup
        //act
        List<BestellingHasArtikel> result = instance.readByExample(bhaCreated);
        //assert
        assertEquals(bhaCreated.getAantal(), result.get(0).getAantal());
    }
}
