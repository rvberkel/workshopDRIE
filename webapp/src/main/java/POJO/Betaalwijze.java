package POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("betaalwijze")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name="Betaalwijze")
public class Betaalwijze implements Serializable {
    private Integer idBetaalwijze;
    private String betaalwijze;
    private static String[] betaalWijzes = {"Contant","Pinbetaling","iDeal","Creditcard","PayPal","AfterPay","Natura"};
    
    @Min(0)
    @Max(6)
    private int keuze;
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    public Integer getIdBetaalwijze(){
        return this.idBetaalwijze;
    }
    
    public void setIdBetaalwijze(Integer id){
        this.idBetaalwijze = id;
    }
    
    @Column
    public String getBetaalwijze(){
        return this.betaalwijze;
    }
 
    protected void setBetaalwijze(String betaalwijze){
        this.betaalwijze = betaalwijze;
    }
        
    public void setBetaalwijzeKeuze(int betaalwijze){
    	keuze = betaalwijze;
        this.betaalwijze = betaalWijzes[keuze];
    }
    @Transient
    public String[] getAllBetaalWijzes(){
        return betaalWijzes;
    }
    
    private void setAllBetaalWijzes(String[] betaalWijzen){
        betaalWijzes = betaalWijzen;
    }
}
