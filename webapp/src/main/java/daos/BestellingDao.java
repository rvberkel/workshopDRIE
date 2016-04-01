package daos;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import POJO.Bestelling;
import POJO.Factuur;
import genericDao.GenericDaoImpl;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;

@Repository("BestellingDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
public class BestellingDao extends GenericDaoImpl<Bestelling, Integer> {
	private static final Logger LOG = LoggerFactory.getLogger(BestellingDao.class);
	
	public List<Bestelling> readByExample(Bestelling bestelling) {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
    	//Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding bestelling by example");
    	@SuppressWarnings("unchecked")
		List<Bestelling> results = (List<Bestelling>)criteria.add(create(bestelling)).list();
    	return results;
    }

    @Override
    public List<Bestelling> readAll() {
        String query = "select * from bestelling";
        Session session = getCurrentSession();
        SQLQuery q = session.createSQLQuery(query);
        q.addEntity(Bestelling.class);
        List<Bestelling> results = (List<Bestelling>)q.list();
        return results;
    }
}
