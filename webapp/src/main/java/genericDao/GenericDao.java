package genericDao;

import POJO.Account;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface GenericDao <T, PK extends Serializable> { 
    
    PK createEntity(T newInstance);
    List<T> readByExample(T t);
    T readEntity(PK id);
    Criteria createEntityCriteria(Session session);
    void updateEntity(T transientObject);
    void deleteEntity(T persistentObject);
    void deleteEntityById(PK id);
    List<T> readAll();
}