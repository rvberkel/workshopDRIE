/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import Helpers.DatabaseClearer;
import Helpers.SpringHibernateUtil;
import POJO.Betaalwijze;
import daos.BetaalwijzeDao;
import genericDao.GenericDao;

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
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringHibernateUtil.class)
@WebAppConfiguration
public class BetaalwijzeDaoTest {
	@Autowired 
	private DatabaseClearer dbClearer;
	@Autowired
    private Betaalwijze contantBetalen;
	@Autowired
    @Qualifier("BetaalwijzeDao")
    GenericDao betaalwijzeDao;
    
    @Before
    public void setUp() {
    	dbClearer.clearDatabase();
    	contantBetalen.setBetaalwijzeKeuze(0);
    }
    
    @After
    public void tearDown() {
        dbClearer.clearDatabase();
    }

    @Test
    public void testCreateBetaalwijze() {
        betaalwijzeDao.createEntity(contantBetalen);
        assertTrue(contantBetalen.getIdBetaalwijze() > 0);
    }

    @Test
    public void testDeleteBetaalwijze() {
        betaalwijzeDao.createEntity(contantBetalen);
        betaalwijzeDao.deleteEntity(contantBetalen);
        assertTrue(betaalwijzeDao.readByExample(contantBetalen).isEmpty());
    }

    @Test
    public void testDeleteBetaalwijzeById() {
        betaalwijzeDao.createEntity(contantBetalen);
        int id = contantBetalen.getIdBetaalwijze();
        betaalwijzeDao.deleteEntityById(id);
        assertNull(betaalwijzeDao.readEntity(id));
    }

    @Test
    public void testUpdateBetaalwijze() {
    	int betaalwijze = 1;
        betaalwijzeDao.createEntity(contantBetalen);
        contantBetalen.setBetaalwijzeKeuze(betaalwijze);
        betaalwijzeDao.updateEntity(contantBetalen);
        Betaalwijze updatedBetaalwijze = (Betaalwijze)betaalwijzeDao.readEntity(contantBetalen.getIdBetaalwijze());
        assertEquals(updatedBetaalwijze.getBetaalwijze(), contantBetalen.getBetaalwijze());
    }

    @Test
    public void testFindBetaalwijzeById() {
        betaalwijzeDao.createEntity(contantBetalen);
        assertNotNull(betaalwijzeDao.readEntity(contantBetalen.getIdBetaalwijze()));
    }

    @Test
    public void testFindBetaalwijzeByExample() {
        betaalwijzeDao.createEntity(contantBetalen);
        assertFalse(betaalwijzeDao.readByExample(contantBetalen).isEmpty());
    }
}
