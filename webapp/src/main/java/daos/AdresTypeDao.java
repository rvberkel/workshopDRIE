/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.Adres;
import POJO.AdresType;
import POJO.Klant;
import genericDao.GenericDaoImpl;
import interfaces.AdresTypeDaoInterface;

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

@Repository("AdresTypeDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
public class AdresTypeDao extends GenericDaoImpl<AdresType, Integer> implements AdresTypeDaoInterface {
    private static final Logger LOG = LoggerFactory.getLogger(AdresTypeDao.class);

	public List<AdresType> readByExample(AdresType adresType) {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
    	//Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding adrestype by example");
		@SuppressWarnings("unchecked")
		List<AdresType> results = (List<AdresType>)criteria.add(create(adresType)).list();
    	return results;
    }

    @Override
    public List<AdresType> readAll() {
    	/*
        Criteria criteria = createEntityCriteria(getCurrentSession());
        //Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<AdresType> results = (List<AdresType>)criteria.add(create(AdresType.class)).list();
        return results;
        */
    	String query = "select * from adres_type";
    	Session session = getCurrentSession();
    	SQLQuery q = session.createSQLQuery(query);
    	q.addEntity(AdresType.class);
    	List<AdresType> results = (List<AdresType>)q.list();
    	return results;
    }
    
    @Override
    public List<AdresType> readByKlantId(int idKlant) {
    	//String query = "select * from adres_type where idAdres_type IN (select adres_type_idAdres_type "
    	//		+ "from klant_has_adres where klant_idKlant = " + idKlant + ")";
    	String query = "select * from adres_type inner join klant_has_adres as kha on kha.adres_type_idAdres_type "
    			+ "IN (select kha.adres_type_idAdres_type from klant_has_adres as kha where kha.adres_idAdres "
    			+ "IN (select kha.adres_idAdres from klant_has_adres as kha where kha.klant_idKlant = " + idKlant + "))";
    	Session session = getCurrentSession();
    	SQLQuery q = session.createSQLQuery(query);
    	q.addEntity(AdresType.class);
    	List<AdresType> results = (List<AdresType>)q.list();
    	return results;
    }
}
