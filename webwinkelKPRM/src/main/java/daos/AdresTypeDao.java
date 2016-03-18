/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.AdresType;
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
@Repository("AdresTypeDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
*/
public class AdresTypeDao extends GenericDaoImpl<AdresType, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(AdresTypeDao.class);

	public List<AdresType> readByExample(AdresType adresType) {
    	//Criteria criteria = createEntityCriteria(getCurrentSession());
    	Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding adrestype by example");
		@SuppressWarnings("unchecked")
		List<AdresType> results = (List<AdresType>)criteria.add(create(adresType)).list();
    	return results;
    }

    @Override
    public List<AdresType> readAll() {
        //Criteria criteria = createEntityCriteria(getCurrentSession());
        Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<AdresType> results = (List<AdresType>)criteria.add(create(AdresType.class)).list();
        return results;
    }
}
