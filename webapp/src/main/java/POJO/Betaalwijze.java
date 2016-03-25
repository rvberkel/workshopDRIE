package POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("betaalwijze")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name="Betaalwijze")
public class Betaalwijze implements Serializable {
    private int idBetaalwijze;
    private String betaalwijze;
    private static String[] betaalWijzes = {"Contant","Pinbetaling","iDeal","Creditcard","PayPal","AfterPay","Natura"};
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public int getIdBetaalwijze(){
        return this.idBetaalwijze;
    }
    
    public void setIdBetaalwijze(int id){
        this.idBetaalwijze = id;
    }
    
    @Column
    public String getBetaalwijze(){
        return this.betaalwijze;
    }
    
    protected void setBetaalwijze(String betaalwijze){
        this.betaalwijze = betaalwijze;
    }
    
    public void setBetaalwijze(int betaalwijze){
        this.betaalwijze = betaalWijzes[betaalwijze];
    }
    @Transient
    public String[] getAllBetaalWijzes(){
        return betaalWijzes;
    }
    
    private void setAllBetaalWijzes(String[] betaalWijzen){
        betaalWijzes = betaalWijzen;
    }
}
