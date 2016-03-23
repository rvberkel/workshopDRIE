package POJO;

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

@Component("bestellingHasArtikel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name="bestelling_has_artikel")
public class BestellingHasArtikel implements java.io.Serializable {
    private Integer idBestelArtikel;
    private Artikel artikel;
    private Bestelling bestelling;
    private int aantal;

    public BestellingHasArtikel() {
    }
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdBestelArtikel() {
        return this.idBestelArtikel;
    }
    
    public void setIdBestelArtikel(Integer idBestelArtikel) {
        this.idBestelArtikel = idBestelArtikel;
    }
    @ManyToOne
    @JoinColumn(name = "artikel_idArtikel", nullable = false)
    public Artikel getArtikel() {
        return this.artikel;
    }
    
    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }
    @ManyToOne
    @JoinColumn(name = "bestelling_idBestelling", nullable = false)
    public Bestelling getBestelling() {
        return this.bestelling;
    }
    
    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
    @Column
    public int getAantal() {
        return this.aantal;
    }
    
    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}
