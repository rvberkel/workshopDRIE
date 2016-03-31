package POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adresType")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "adres_type")
public class AdresType implements Serializable{
    private Integer idAdres_type;
    private String adres_type;
    private static String[] types = {"Bezorgadres", "Factuuradres", "Bezoekadres"};
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdAdres_type(){
        return this.idAdres_type;
    }
    
    public void setIdAdres_type(Integer id){
        this.idAdres_type = id;
    }
    
    @Column
    public String getAdres_type(){
        return this.adres_type;
    }
    
    public void setAdres_type(String adres_type){
        this.adres_type = adres_type;
    }
    
    public void setAdres_type(int adres_type){
        this.adres_type = types[adres_type];
    }
    @Transient
    public String[] getAllTypes(){
        return types;
    }
    
    private void setAllTypes(String[] allTypes){
        types = allTypes;
    }
}
