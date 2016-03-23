package POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adres")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "adres")
public class Adres implements java.io.Serializable {
    private Integer idAdres;
    private String straatnaam;
    private String postcode;
    private String huisnummer;
    private String woonplaats;

    public Adres() {
    }
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdAdres() {
        return this.idAdres;
    }
    
    public void setIdAdres(Integer idAdres) {
        this.idAdres = idAdres;
    }
    
    @Column
    public String getStraatnaam() {
        return this.straatnaam;
    }
    
    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }
    
    @Column
    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    @Column
    public String getHuisnummer() {
        return this.huisnummer;
    }
    
    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }
    
    @Column
    public String getWoonplaats() {
        return this.woonplaats;
    }
    
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
}