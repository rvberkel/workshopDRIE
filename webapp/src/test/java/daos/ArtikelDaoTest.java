package daos;

import Helpers.*;
import POJO.*;
import genericDao.GenericDao;

import java.util.List;
import org.hibernate.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Helpers.SpringHibernateUtil.class)
@WebAppConfiguration
public class ArtikelDaoTest {
    
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    DatabaseClearer dbClearer;
    @Autowired
    Artikel artikelCreated;
    @Autowired 
    Artikel artikel1;
    @Autowired
    Artikel artikel2;
    @Autowired
    Artikel artikel3;
    
    @Autowired
    @Qualifier("ArtikelDao")
    GenericDao instance;
    
    public ArtikelDaoTest() {
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
        artikelCreated.setArtikelnaam("artikelCreated");
        artikelCreated.setArtikelnummer("0001");
        artikelCreated.setArtikelprijs(2);
        artikelCreated.setArtikelomschrijving("Om te testen");
        artikelCreated.setIdArtikel((Integer)session.save(artikelCreated));
        session.getTransaction().commit();
        session.beginTransaction();
        artikel2.setArtikelnaam("Blaat");
        artikel2.setArtikelnummer("0004");
        artikel2.setArtikelprijs(56);
        artikel2.setArtikelomschrijving("Burp");
        artikel2.setIdArtikel((Integer)session.save(artikel2));
        session.getTransaction().commit();
        session.beginTransaction();
        artikel3.setArtikelnaam("Fluffel");
        artikel3.setArtikelnummer("3204");
        artikel3.setArtikelprijs(4);
        artikel3.setArtikelomschrijving("Nurg");
        artikel3.setIdArtikel((Integer)session.save(artikel3));
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateArtikel_ShouldPass() {
        //Arrange
        System.out.println("createArtikel_ShouldPass");
        
        artikel1.setArtikelnaam("Badpak");
        artikel1.setArtikelnummer("0001");
        artikel1.setArtikelprijs(19.95);
        artikel1.setArtikelomschrijving("Om te creëeren");
        //Act
        instance.createEntity(artikel1);
        //Assert
        
        Artikel loadArtikel;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loadArtikel = (Artikel) session.get("POJO.Artikel", artikel1.getIdArtikel());
            session.getTransaction().commit();
        }
        System.out.println("\u001B[31m" + loadArtikel.getArtikelnaam());
        assertEquals(artikel1.getArtikelnaam(), loadArtikel.getArtikelnaam());
    }
    /*
    @Test
    public void testDeleteArtikel_ShouldPass() {
        System.out.println("deleteArtikel_ShouldPass");        
        //Arrange in setup
        Artikel loadArtikel;
        //Act
        instance.deleteEntity(artikelCreated);
        //Assert
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loadArtikel = (Artikel) session.get("POJO.Artikel", artikelCreated.getIdArtikel());
            session.getTransaction().commit();
        }
        assertNull(loadArtikel);
    }

    @Test
    public void testDeleteArtikelById_ShouldPass() {
        System.out.println("deleteArtikelById_ShouldPass");
        //Arrange in setup
        Artikel loadArtikel;
        //Act
        instance.deleteEntityById(artikelCreated.getIdArtikel());
        //Assert
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loadArtikel = (Artikel) session.get("POJO.Artikel", artikelCreated.getIdArtikel());
            session.getTransaction().commit();
        }
        assertNull(loadArtikel);

    }

    @Test
    public void testUpdateArtikel_ShouldPass() {
        System.out.println("updateArtikel_ShouldPass");
        //Arrange in setup
        Artikel loadArtikel;
        artikelCreated.setArtikelomschrijving("is dit updated");
        //act
        instance.updateEntity(artikelCreated);
        //Assert
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loadArtikel = (Artikel) session.get("POJO.Artikel", artikelCreated.getIdArtikel());
            session.getTransaction().commit();
        }
        assertEquals(artikelCreated.getArtikelomschrijving(), loadArtikel.getArtikelomschrijving());
    }

    @Test
    public void testFindArtikelById_ShouldPass() {
        System.out.println("findArtikelById _ShouldPass");
        //Arrange in setup
        Artikel loadArtikel;
        //Act
        loadArtikel = (Artikel)instance.readEntity(artikelCreated.getIdArtikel());
        //Assert
        assertEquals(artikelCreated.getArtikelomschrijving(), loadArtikel.getArtikelomschrijving());
    }

    @Test
    public void testFindArtikelByExample_ShouldPass() {
        System.out.println("findArtikelByExample_ShouldPass");
        //Arrange in setup
        Artikel loadArtikel;
        //Act
        List<Artikel> artikelen = instance.readByExample(artikelCreated);
        loadArtikel = artikelen.get(0);
        //Assert
        assertEquals(artikelCreated.getArtikelomschrijving(), loadArtikel.getArtikelomschrijving());
    }
    */
}
