/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.Betaling;
import genericDao.GenericDaoImpl;
import java.util.List;

import org.hibernate.Criteria;
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
@Repository("BetalingDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
*/
public class BetalingDao extends GenericDaoImpl<Betaling, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(BetalingDao.class);
    public List<Betaling> readByExample(Betaling betaling) {
    	//Criteria criteria = createEntityCriteria(getCurrentSession());
    	Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding betaling by example");
    	@SuppressWarnings("unchecked")
		List<Betaling> results = (List<Betaling>)criteria.add(create(betaling)).list();
    	return results;
    }

    @Override
    public List<Betaling> readAll() {
        //Criteria criteria = createEntityCriteria(getCurrentSession());
        Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<Betaling> results = (List<Betaling>)criteria.add(create(Betaling.class)).list();
        return results;
    }
}
