package daos;

import POJO.Adres;
import POJO.Klant;
import genericDao.GenericDaoImpl;
import interfaces.AdresDaoInterface;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("AdresDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
public class AdresDao extends GenericDaoImpl<Adres, Integer> implements AdresDaoInterface {
    private static final Logger LOG = LoggerFactory.getLogger(AdresDao.class);
    
        //@Transactional
	public List<Adres> readByExample(Adres adres) {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
		//Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding adres by example");
		@SuppressWarnings("unchecked")
		List<Adres> results = (List<Adres>)criteria.add(create(adres)).list();
    	return results;
    }

    @Override
    public List<Adres> readAll() {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
    	//Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<Adres> results = (List<Adres>)criteria.add(create(Adres.class)).list();
        return results;
    }
    
    @Override
    public List<Adres> readByKlantId(int idKlant) {
    	LOG.info("Started finding adressen based on klantId");
    	String query = "select * from adres where idAdres IN (select adres_idAdres from klant_has_adres where klant_idKlant = " 
    			+ idKlant + ")";
    	Session session = getCurrentSession();
    	SQLQuery q = session.createSQLQuery(query);
    	q.addEntity(Adres.class);
    	List<Adres> results = (List<Adres>)q.list();
    	return results;
    }
    
    @Override
    public Adres readByPostcodeAndHuisnummer(String postcode, String huisnummer) {
    	String query = "select * from adres where postcode = " + postcode + " and huisnummer = " + huisnummer;
    	Session session = getCurrentSession();
    	SQLQuery q = session.createSQLQuery(query);
    	q.addEntity(Klant.class);
    	return new Adres();
    }
    
    @Override
    public void decoupleAdresFromKlant(int klantId, int adresId) {
    	String query = "delete from klant_has_adres where klant_idKlant = " + klantId + " and adres_idAdres = " + adresId;
    	Session session = getCurrentSession();
    	SQLQuery q = session.createSQLQuery(query);
    	q.executeUpdate();
    }
}
