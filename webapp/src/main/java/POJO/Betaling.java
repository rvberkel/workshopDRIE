package POJO;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("betaling")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name="betaling")
public class Betaling implements java.io.Serializable {
    private Integer idBetaling;
    private Betaalwijze betaalwijze;
    private Factuur factuur;
    private Klant klant;
    private Date betaalDatum;
    private String betalingsGegevens;

    public Betaling() {
    }
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdBetaling() {
        return this.idBetaling;
    }
    
    public void setIdBetaling(Integer idBetaling) {
        this.idBetaling = idBetaling;
    }
    @ManyToOne
    @JoinColumn(name = "betaalwijze_idBetaalwijze")
    public Betaalwijze getBetaalwijze() {
        return this.betaalwijze;
    }
    
    public void setBetaalwijze(Betaalwijze betaalwijze) {
        this.betaalwijze = betaalwijze;
    }
    @ManyToOne
    @JoinColumn(name = "factuur_idFactuur", nullable = false)
    public Factuur getFactuur() {
        return this.factuur;
    }
    
    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }
    @ManyToOne
    @JoinColumn(name = "klant_idKlant", nullable = false)
    public Klant getKlant() {
        return this.klant;
    }
    
    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    @Column (name = "betaal_datum")
    public Date getBetaalDatum() {
        return this.betaalDatum;
    }
    
    public void setBetaalDatum(Date betaalDatum) {
        this.betaalDatum = betaalDatum;
    }
    @Column
    public String getBetalingsGegevens() {
        return this.betalingsGegevens;
    }
    
    public void setBetalingsGegevens(String betalingsGegevens) {
        this.betalingsGegevens = betalingsGegevens;
    }
}