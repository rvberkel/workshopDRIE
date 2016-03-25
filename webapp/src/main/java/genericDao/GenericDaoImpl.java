package genericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Helpers.SpringHibernateUtil;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;

@Transactional
@Scope("prototype")
public abstract class GenericDaoImpl <T, PK extends Serializable> implements GenericDao <T, PK> {
	private static final Logger LOG = LoggerFactory.getLogger(GenericDaoImpl.class);
	private Class<T> type;
	SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
    public GenericDaoImpl(){
        this.type =(Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	public GenericDaoImpl(Class<T> type){
        this.type = type;
    }
	
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        //Session session = sessionFactory.openSession();
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
	@SuppressWarnings("unchecked")
	public PK createEntity(T o) {
		LOG.debug("creating " + type.getSimpleName() + " instance");
		PK id = (PK)getCurrentSession().save(o);
		//Session session = getSession();
		//PK id = (PK)session.save(o);
		//closeSession(session);
		return id;
	}
        
	public T readEntity(PK id) {
		LOG.info("get " + type.getSimpleName() + " by id");
		T instance = (T)getCurrentSession().get(type, id);
		//Session session = getSession();
		//T instance = (T)session.get(type, id);
		//closeSession(session);
		return instance;
	}
	public void updateEntity(T o) {
		LOG.info("updating " + type.getSimpleName() + " instance");
		getCurrentSession().update(o);
		//Session session = getSession();
		//session.update(o);
		///closeSession(session);
	}
	public void deleteEntity(T o) {
		LOG.info("deleting " + type.getSimpleName() + " instance");
		getCurrentSession().delete(o);
		//Session session = getSession();
		//session.delete(o);
		//closeSession(session);
	}
	public void deleteEntityById(PK id) {
		LOG.info("deleting by id " + type.getSimpleName() + " instance");
		T instance = readEntity(id);
		deleteEntity(instance);
		//T instance = readEntity(id);
		//deleteEntity(instance);
	}
	public Criteria createEntityCriteria(Session session) {
		LOG.info("creating Criteria for " + type.getSimpleName());
		Criteria criteria = session.createCriteria(type);
		return criteria;
	}
}
