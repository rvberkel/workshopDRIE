/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.BestellingHasArtikel;
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

@Repository("BestellingHasArtikelDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
public class BestellingHasArtikelDao extends GenericDaoImpl<BestellingHasArtikel, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(BestellingHasArtikelDao.class);

    public List<BestellingHasArtikel> readByExample(BestellingHasArtikel bestellingHasArtikel) {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
    	//Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding bestellingHasArtikel by example");
    	@SuppressWarnings("unchecked")
		List<BestellingHasArtikel> results = (List<BestellingHasArtikel>)criteria.add(create(bestellingHasArtikel)).list();
    	return results;
    }

    @Override
    public List<BestellingHasArtikel> readAll() {
        Criteria criteria = createEntityCriteria(getCurrentSession());
        //Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<BestellingHasArtikel> results = (List<BestellingHasArtikel>)criteria.add(create(BestellingHasArtikel.class)).list();
        return results;
    }
}
