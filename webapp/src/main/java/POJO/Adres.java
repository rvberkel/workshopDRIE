package POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adres")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "adres")
public class Adres implements java.io.Serializable {
    private Integer idAdres;
    @NotNull
    @Size(min=2, max=45)
    private String straatnaam;
    @NotNull
    //@Size(min=6, max=7)
    @Pattern(regexp="^[1-9][0-9]{3}(?!SA|SD|SS)[A-Z]{2}$", message="Dit is geen geldige postcode. Gebruik geen spaties en geen "
    		+ "kleine letters. Voorbeeld: 1234AB")
    private String postcode;
    @NotNull
    @Size(min=1, max=11)
    private String huisnummer;
    @NotNull
    @Size(min=3, max=45)
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