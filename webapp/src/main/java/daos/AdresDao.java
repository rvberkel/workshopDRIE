/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.Adres;
import genericDao.GenericDaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/*
@Repository("AdresDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
*/
public class AdresDao extends GenericDaoImpl<Adres, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(AdresDao.class);
    
        //@Transactional
	public List<Adres> readByExample(Adres adres) {
    	//Criteria criteria = createEntityCriteria(getCurrentSession());
		Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding adres by example");
		@SuppressWarnings("unchecked")
		List<Adres> results = (List<Adres>)criteria.add(create(adres)).list();
    	return results;
    }

    @Override
    public List<Adres> readAll() {
    	//Criteria criteria = createEntityCriteria(getCurrentSession());
    	Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<Adres> results = (List<Adres>)criteria.add(create(Adres.class)).list();
        return results;
    }
}
