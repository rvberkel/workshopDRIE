package Helpers;

import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseClearer {
	@Autowired
    private SessionFactory factory;
    private Session session;
    private final String[] tableNames = {"account", "klant_has_adres", "bestelling_has_artikel", "betaling", "factuur",
        "bestelling", "artikel", "adres", "klant", "adres_type", "betaalwijze"};
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseClearer.class);

    public void clearDatabase() {
        session = factory.openSession();
        for (String s : tableNames){
            deleteRowsFromTable(s);
            resetAutoIncrement(s);
        }
        session.close();
    }
    
    public void clearTable(Object o){
        session = factory.openSession();
        if (o.getClass().isAnnotationPresent(Table.class)){
            String tableName = o.getClass().getAnnotation(Table.class).name();
            deleteRowsFromTable(tableName);
            resetAutoIncrement(tableName);
        }
        else {
            System.err.println(o.getClass().getSimpleName() + " doesn't have the required Table annotation");
            LOG.error("Table annotation wasn't found for " + o.getClass().getSimpleName() + ", could not clear table." );
        }
        session.close();
    }
    
    public void clearTable(String tableName){
        session = factory.openSession();
        try {
            deleteRowsFromTable(tableName);
            resetAutoIncrement(tableName);
        }
        catch(Exception e){
            System.err.println("Couldn't clear " + tableName + ": " + e.getMessage());
            LOG.error(e.getMessage());
            for (StackTraceElement ste : e.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + " on line " + ste.getLineNumber());
            }
        }
        session.close();
    }
    
    private void deleteRowsFromTable(String tableName){
        String queryDelete = "delete from " + tableName + " where 1";
        session.beginTransaction();
        session.createSQLQuery(queryDelete).executeUpdate();
        session.getTransaction().commit();
    }
    
    private void resetAutoIncrement(String tableName){
        String queryReset = "alter table `deel2`.`" + tableName + "` auto_increment = 1";
        session.beginTransaction();
        session.createSQLQuery(queryReset).executeUpdate();
        session.getTransaction().commit();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
