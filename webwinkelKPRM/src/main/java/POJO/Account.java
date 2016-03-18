package POJO;
// Generated Feb 25, 2016 4:02:48 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/*
@Component("account")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
*/
@Entity
@Table(name="account")
public class Account implements java.io.Serializable {
	private Integer idAccount;
    private Klant klant;
    private String accountNaam;
    private Date creatieDatum;

    public Account() {
    }
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdAccount() {
        return this.idAccount;
    }
    
    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }
    
    @ManyToOne
    @JoinColumn(name = "klant_idKlant", nullable = false)
    public Klant getKlant() {
        return this.klant;
    }
    
    public void setKlant(Klant klant) {
        this.klant = klant;
    }
    
    @Column (name = "account_naam")
    public String getAccountNaam() {
        return this.accountNaam;
    }
    
    public void setAccountNaam(String accountNaam) {
        this.accountNaam = accountNaam;
    }
    
    @Column (name = "creatie_datum")
    public Date getCreatieDatum() {
        return this.creatieDatum;
    }
    
    public void setCreatieDatum(Date creatieDatum) {
        this.creatieDatum = creatieDatum;
    }
}


