/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;
import Helpers.HibernateUtil;
import com.lambdaworks.crypto.SCryptUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("UserPasswordDao")
@Configurable(autowire=Autowire.BY_TYPE)
@Scope("prototype")
@Transactional
public class UserPasswordDao{
    SessionFactory sessionFactory;
    private static final Logger LOG = LoggerFactory.getLogger(UserPasswordDao.class);
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        sessionFactory.openSession();
    }
    
    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    /*
    protected Session getSession(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }
	
    protected void closeSession(Session session){
        session.getTransaction().commit();
        session.close();
    }
    */
    public boolean createUser(String inlognaam, String inlogwachtwoord){
        Session session = getCurrentSession();
        Query query = session.createSQLQuery("INSERT INTO login (inlognaam, inlogwachtwoord) VALUES ( '" +inlognaam+ "','"+ inlogwachtwoord+"')");
        //Query query = session.createSQLQuery("INSERT INTO login (inlognaam, inlogwachtwoord) VALUES (:naam, :wachtwoord)");
        //query.setParameter("naam", inlognaam);
        //query.setParameter("wachtwoord", inlogwachtwoord);
        int rowsAffected = query.executeUpdate();
        //closeSession(session);
        return rowsAffected >= 1;
    }
    
    public boolean checkUser(String inlognaam, String inlogwachtwoord){
        String returnedWachtwoord = null;
        Session session = getCurrentSession();
        String query = "select l.inlogwachtwoord from login l where l.inlognaam = '" + inlognaam + "'";
        SQLQuery q = session.createSQLQuery(query);
        List<String> result = (List<String>)q.list();
        //closeSession(session);
        if(!result.isEmpty() && result != null){
            returnedWachtwoord = result.get(0);
        }
        if (SCryptUtil.check(inlogwachtwoord, returnedWachtwoord) == true){
        	return true;
        }
        else
        	return false;          
  }   
}