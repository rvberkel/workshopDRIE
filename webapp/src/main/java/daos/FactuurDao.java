
package daos;

import POJO.Factuur;
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

@Repository("FactuurDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
public class FactuurDao extends GenericDaoImpl<Factuur, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(FactuurDao.class);
    
    public List<Factuur> readByExample(Factuur factuur) {
    	Criteria criteria = createEntityCriteria(getCurrentSession());
    	//Criteria criteria = createEntityCriteria(getSession());
    	LOG.info("Started finding factuur by example");
    	@SuppressWarnings("unchecked")
		List<Factuur> results = (List<Factuur>)criteria.add(create(factuur)).list();
    	return results;
    }

    @Override
    public List<Factuur> readAll() {
        Criteria criteria = createEntityCriteria(getCurrentSession());
        //Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<Factuur> results = (List<Factuur>)criteria.add(create(Factuur.class)).list();
        return results;
    }
}
