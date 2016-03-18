/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.Klant;
import genericDao.GenericDaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
/*
@Repository("KlantDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
*/
public class KlantDao extends GenericDaoImpl<Klant, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(KlantDao.class);
    public List<Klant> readByExample(Klant klant) {
    	//Criteria criteria = createEntityCriteria(getCurrentSession());
    	Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding klant by example");
    	@SuppressWarnings("unchecked")
		List<Klant> results = (List<Klant>)criteria.add(create(klant)).list();
    	return results;
    }
    
    public List<Klant> readAll(){
        //Criteria criteria = createEntityCriteria(getCurrentSession());
        /*
    	Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<Klant> results = (List<Klant>)criteria.add(create(Klant.class)).list();
        return results;
        */
    	String query = "select * from klant";
    	Session session = getSession();
    	SQLQuery q = session.createSQLQuery(query);
    	q.addEntity(Klant.class);
    	List<Klant> results = (List<Klant>)q.list();
    	return results;
    }
}
