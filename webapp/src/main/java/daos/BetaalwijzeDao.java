/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.Betaalwijze;
import genericDao.GenericDaoImpl;

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

@Repository("BetaalwijzeDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
public class BetaalwijzeDao extends GenericDaoImpl<Betaalwijze, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(BetaalwijzeDao.class);

    public List<Betaalwijze> readByExample(Betaalwijze betaalwijze) {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
    	//Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding betaalwijze by example");
    	@SuppressWarnings("unchecked")
		List<Betaalwijze> results = (List<Betaalwijze>)criteria.add(create(betaalwijze)).list();
    	return results;
    }

    @Override
    public List<Betaalwijze> readAll() {
    	/*
        Criteria criteria = createEntityCriteria(getCurrentSession());
        //Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all betaalwijzen");
        @SuppressWarnings("unchecked")
        List<Betaalwijze> results = (List<Betaalwijze>)criteria.add(create(Betaalwijze.class)).list();
        return results;
        */
    	String query = "select * from betaalwijze";
        Session session = getCurrentSession();
        SQLQuery q = session.createSQLQuery(query);
        q.addEntity(Betaalwijze.class);
        List<Betaalwijze> results = (List<Betaalwijze>)q.list();
        return results;
    }
}
