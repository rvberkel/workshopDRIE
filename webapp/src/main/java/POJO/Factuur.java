package POJO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/*
@Component("factuur")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
*/
@Entity
@Table(name="factuur")
public class Factuur implements java.io.Serializable {
    private Integer idFactuur;
    private Bestelling bestelling;
    private Date factuurDatum;
    //@Resource(name = "betalingen")
    private Set<Betaling> betalingen = new HashSet<>();

    public Factuur() {
    }
 
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdFactuur() {
        return this.idFactuur;
    }
    
    public void setIdFactuur(Integer idFactuur) {
        this.idFactuur = idFactuur;
    }
    
    @ManyToOne
    @JoinColumn(name = "bestelling_idBestelling", nullable = false)
    public Bestelling getBestelling() {
        return this.bestelling;
    }
    
    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
    
    @Column (name = "factuur_datum")
    public Date getFactuurDatum() {
        return this.factuurDatum;
    }
    
    public void setFactuurDatum(Date factuurDatum) {
        this.factuurDatum = factuurDatum;
    }
    
    @OneToMany(mappedBy = "factuur", targetEntity = Betaling.class, fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public Set<Betaling> getBetalingen() {
        return this.betalingen;
    }
    
    public void setBetalingen(Set<Betaling> betalingen) {
        this.betalingen = betalingen;
    }
    
    public void addToBetalingen(Betaling betaling){
        betaling.setFactuur(this);
        if(betaling != null){
            this.betalingen.add(betaling);
        }
    }
    
    public void removeFromBetalingen(Betaling betaling){
        betaling.setFactuur(null);
        this.betalingen.remove(betaling);
    }
}