package daos;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Helpers.DatabaseClearer;
import POJO.Adres;
import genericDao.GenericDao;
import Helpers.SpringHibernateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
public class AdresDaoTest {
	/*
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	*/
	@Autowired
    @Qualifier("AdresDao")
    GenericDao instance;
	
	@Autowired
	private Adres adres;
	
	@Autowired
	private DatabaseClearer databaseClearer;
	
	@Before
	public void clearDatabase() {
		databaseClearer.clearDatabase();
	}
	/*
	@Test
	public void dataSourceShouldNotBeNull() {
		assertNotNull(dataSource);
	}
	
	@Test
	public void localSessionFactoryBeanShouldNotBeNull() {
		assertNotNull(sessionFactory);
	}
	
	@Test
	public void AdresDaoShouldNotBeNull() {
		assertNotNull(adresDao);
	}
	
	@Test
	public void AdresShouldNotBeNull() {
		assertNotNull(adres);
	}
	*/
	
	@Test
	public void AdresDaoShouldAddAdres() {
		adres.setStraatnaam("pluhweg");
		adres.setHuisnummer("1234a");
		adres.setPostcode("1234ab");
		adres.setWoonplaats("dorpdorp");
		assertEquals("pluhweg", adres.getStraatnaam());
		instance.createEntity(adres);
		assertEquals(1, (int)adres.getIdAdres());
	}
}
