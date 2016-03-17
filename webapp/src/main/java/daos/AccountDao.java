/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import POJO.Account;
import genericDao.GenericDaoImpl;

import static org.hibernate.criterion.Example.create;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/*
@Repository("AccountDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
*/
public class AccountDao extends GenericDaoImpl<Account, Integer> {
	private static final Logger LOG = LoggerFactory.getLogger(AccountDao.class);
    
    public List<Account> readByExample(Account account) {
    	//Session session = getCurrentSession();
    	Session session = getSession();
    	Criteria criteria = createEntityCriteria(session);
    	LOG.info("Started finding account by example");
    	@SuppressWarnings("unchecked")
		List<Account> results = (List<Account>)criteria.add(create(account)).list();
    	return results;
    }

    @Override
    public List<Account> readAll() {
        //Criteria criteria = createEntityCriteria(getCurrentSession());
    	Criteria criteria = createEntityCriteria(getSession());
        LOG.info("Started finding all klanten");
        @SuppressWarnings("unchecked")
        List<Account> results = (List<Account>)criteria.add(create(Account.class)).list();
        return results;
    }
}
