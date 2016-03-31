package POJO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component("klant")
@Scope("prototype")
@Entity(name="klant")
@Table(name= "klant")
public class Klant implements java.io.Serializable {
    private Integer idKlant;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    
    @Resource(name="betalingen")
    private Set<Betaling> betalingen;
    @Resource(name="bestellingen")
    private Set<Bestelling> bestellingen;
    @Resource(name="accounts")
    private Set<Account> accounts;
    @Resource(name="adressen")
    private Map<Adres, AdresType> adressen;

    public Klant() {
    }
 
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdKlant() {
        return this.idKlant;
    }
    
    public void setIdKlant(Integer idKlant) {
        this.idKlant = idKlant;
    }
    
    @Column
    public String getVoornaam() {
        return this.voornaam;
    }
    
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }
    
    @Column
    public String getTussenvoegsel() {
        return this.tussenvoegsel;
    }
    
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }
    
    @Column
    public String getAchternaam() {
        return this.achternaam;
    }
    
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    
    @Column
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @OneToMany(mappedBy = "klant", targetEntity = Betaling.class, orphanRemoval=true, fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE})
    public Set<Betaling> getBetalingen() {
        return this.betalingen;
    }
    
    public void setBetalingen(Set<Betaling> betalingen) {
        this.betalingen = betalingen;
    }
    
    @OneToMany(mappedBy = "klant", targetEntity = Bestelling.class, orphanRemoval=true, fetch = FetchType.EAGER) 
    @Cascade({CascadeType.SAVE_UPDATE})
    public Set<Bestelling> getBestellingen() {
        return this.bestellingen;
    }
    
    public void setBestellingen(Set<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
    
    @OneToMany(mappedBy = "klant", targetEntity = Account.class, orphanRemoval=true, fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE})
    public Set<Account> getAccounts() {
        return this.accounts;
    }
    
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="klant_has_adres",
    joinColumns=@JoinColumn(name="klant_idKlant"),
    inverseJoinColumns=@JoinColumn(name="adres_type_idAdres_type"))
    @MapKeyJoinColumn(name = "adres_idAdres", table = "klant_has_adres")
    public Map<Adres, AdresType> getAdressen() {
        return this.adressen;
    }
    
    public void setAdressen(Map<Adres,AdresType> adressen) {
        this.adressen = adressen;
    }
    
    public void addToAccount(Account account){
        account.setKlant(this);
        this.accounts.add(account);
    }
    
    public void addToBestellingen(Bestelling bestelling){
        bestelling.setKlant(this);
        if(bestelling != null){
            this.bestellingen.add(bestelling);
        }
    }
    public void addToBetalingen(Betaling betaling){
        betaling.setKlant(this);
        if(betaling != null){
            this.betalingen.add(betaling);
        }
    }
    
    public void removeFromBestellingen(Bestelling bestelling){
        bestelling.setKlant(null);
        this.bestellingen.remove(bestelling);
    }
    
    public void addToAdressen(Adres adres, AdresType adresType){
        if(adres != null && adresType != null){
            this.adressen.put(adres, adresType);
        }
    }
    public void removeFromAdressen(Adres adres, AdresType adresType){
        this.adressen.remove(adres, adresType);
    }
    
    public void removeFromAdressen(Adres adres) {
    	this.adressen.remove(adres);
    }
}