package POJO;
// Generated Feb 25, 2016 4:02:48 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "artikel")
public class Artikel implements java.io.Serializable {
    private Integer idArtikel;
    @Size(min =2, max=30, message = "geef een naam in tussen de 2 en 30 tekens")
    private String artikelnaam;
    @Min(0)@Max(10000)
    private double artikelprijs;
    @NotEmpty(message = "geef een artikelnummer in")
    private String artikelnummer;
    private String artikelomschrijving;

    public Artikel() {
    }

    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdArtikel() {
        return this.idArtikel;
    }

    public void setIdArtikel(Integer idArtikel) {
        this.idArtikel = idArtikel;
    }
    @Column
    public String getArtikelnaam() {
        return this.artikelnaam;
    }

    public void setArtikelnaam(String artikelnaam) {
        this.artikelnaam = artikelnaam;
    }
    @Column
    public double getArtikelprijs() {
        return this.artikelprijs;
    }

    public void setArtikelprijs(double artikelprijs) {
        this.artikelprijs = artikelprijs;
    }
    @Column
    public String getArtikelnummer() {
        return this.artikelnummer;
    }

    public void setArtikelnummer(String artikelnummer) {
        this.artikelnummer = artikelnummer;
    }
    @Column
    public String getArtikelomschrijving() {
        return this.artikelomschrijving;
    }

    public void setArtikelomschrijving(String artikelomschrijving) {
        this.artikelomschrijving = artikelomschrijving;
    }
}
