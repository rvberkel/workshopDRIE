package POJO;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
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
@Component("bestelling")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
*/
@Entity
@Table(name="bestelling")
public class Bestelling implements java.io.Serializable {
    private Integer idBestelling;
    private Klant klant;
    //@Resource(name="bestellingHasArtikelen")
    private Set<BestellingHasArtikel> bestellingHasArtikelen = new HashSet<>();
    //@Resource(name="facturen")
    private Set<Factuur> facturen = new HashSet<>();

    public Bestelling() {
    }
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdBestelling() {
        return this.idBestelling;
    }
    
    public void setIdBestelling(Integer idBestelling) {
        this.idBestelling = idBestelling;
    }
    
    @ManyToOne
    @JoinColumn(name = "klant_idKlant", nullable = false)
    public Klant getKlant() {
        return this.klant;
    }
    
    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    
    @OneToMany(mappedBy = "bestelling",targetEntity = BestellingHasArtikel.class, orphanRemoval=true, 
    		fetch = FetchType.EAGER) 
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public Set<BestellingHasArtikel> getBestellingHasArtikelen() {
        return this.bestellingHasArtikelen;
    }
    
    public void setBestellingHasArtikelen(Set<BestellingHasArtikel> bestellingHasArtikelen) {
        this.bestellingHasArtikelen = bestellingHasArtikelen;
    }
    
    @OneToMany(mappedBy = "bestelling", targetEntity = Factuur.class, orphanRemoval=true, 
    		fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public Set<Factuur> getFacturen() {
        return this.facturen;
    }
    
    public void setFacturen(Set<Factuur> facturen) {
        this.facturen = facturen;
    }
    
    public void addToBestellingHasArtikelen(BestellingHasArtikel bestellingHasArtikel){
        bestellingHasArtikel.setBestelling(this);
        if (bestellingHasArtikel != null){
            this.bestellingHasArtikelen.add(bestellingHasArtikel);
        }
    }
    
    public void addToFacturen(Factuur factuur){
        factuur.setBestelling(this);
        if(factuur != null){
            this.facturen.add(factuur);
        }
    }
    
    public void removeFromBestellingHasArtikelen(BestellingHasArtikel bestellingHasArtikel){
        bestellingHasArtikel.setBestelling(null);
        this.bestellingHasArtikelen.remove(bestellingHasArtikel);
    }
    public void removeFromFacturen(Factuur factuur){
        factuur.setBestelling(null);
        this.facturen.remove(factuur);
    }
}
