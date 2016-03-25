
package daos;

import POJO.Artikel;
import genericDao.GenericDao;
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

@Repository("ArtikelDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
public class ArtikelDao extends GenericDaoImpl<Artikel, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(ArtikelDao.class);

  
    public List<Artikel> readByExample(Artikel artikel) {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
    	//Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding artikel by example");
		@SuppressWarnings("unchecked")
		List<Artikel> results = (List<Artikel>)criteria.add(create(artikel)).list();
    	return results;
    }

    @Override
    public List<Artikel> readAll() {
    	String query = "select * from artikel";
        Session session = getCurrentSession();
        SQLQuery q = session.createSQLQuery(query);
        q.addEntity(Artikel.class);
        List<Artikel> results = (List<Artikel>)q.list();
        return results;
    }
}
