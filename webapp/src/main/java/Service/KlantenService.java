/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import POJO.Account;
import POJO.Adres;
import POJO.AdresType;
import POJO.Klant;
import daos.AccountDao;
import daos.AdresDao;
import daos.AdresTypeDao;
import daos.KlantDao;
import genericDao.GenericDao;
import interfaces.AdresRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("klantenService")
@Scope("prototype")
public class KlantenService {
    private static final Logger LOG = LoggerFactory.getLogger(KlantenService.class);
    
    @Autowired
    @Qualifier("KlantDao")
    GenericDao klantDao;
    @Autowired
    @Qualifier("AccountDao")
    GenericDao accountDao;
    @Autowired
    //@Qualifier("AdresDao")
    AdresDao adresDao;
    @Autowired
    //@Qualifier("AdresTypeDao")
    AdresTypeDao adresTypeDao;
    @Autowired
    AdresRepository adresRepo;
   // ===== Create ======
    
    public boolean createKlant(Klant klant){
        LOG.info("createKlant");
        klantDao.createEntity(klant);
        return klant.getIdKlant() > 0; 
    }
    
    public boolean createAdres(Adres adres){
        LOG.info("createAdres");
        adresDao.createEntity(adres);
        return adres.getIdAdres() > 0;   
    }
    
    public boolean createKlantMetAdres(Klant klant, Adres adres, AdresType adresType){
        LOG.info("createKlantMetAdres");
        adresDao.createEntity(adres); 
        adresTypeDao.createEntity(adresType);
        klant.addToAdressen(adres, adresType);
        klantDao.createEntity(klant);
        return klant.getIdKlant() > 0;
    }
    
    public boolean createAccount(Account account, Klant klant){
        LOG.info("createAccount");
        if (klantDao.readByExample(klant).isEmpty()){
            klantDao.createEntity(klant);
        }
        account.setKlant(klant);
        accountDao.createEntity(account);
        klant.addToAccount(account);
        return account.getIdAccount() > 0;
    }
    
    // ========= READ ============
    
    public Klant readKlant(Klant klant) {
        LOG.info("readKlant gestart");        
        return (Klant)klantDao.readByExample(klant).get(0);
    }
    public List<Klant> readAlleKlanten(){
        LOG.info("readAlleKlanten gestart");
        return(List<Klant>)klantDao.readAll();
    } 
    
    public Klant readKlantOpId(int id) {
        LOG.info("readKlantOpId gestart");        
        return (Klant)klantDao.readEntity(id);
    }
    
    public Adres readAdres(Adres adres){
        LOG.info("readAdres gestart");
        return (Adres)adresDao.readByExample(adres).get(0);
    }
    
    public Adres readAdresOpId(int adresId){
        LOG.info("readAdres gestart");
        return (Adres)adresDao.readEntity(adresId);
    }
    
    public List<Adres> readAdresOpKlantId(int klantId){
    	LOG.info("readAdresOpKlantId gestart");
    	return (List<Adres>)adresDao.readByKlantId(klantId);
    }
    
    public List<Adres> readAlleAdressen(){
        LOG.info("readAlleAdressen gestart");
        return(List<Adres>)adresDao.readAll();
    }
    
    public List<Integer> checkIfAdresIsOwned(int adresId) {
    	LOG.info("checking if adres is owned");
    	return (List<Integer>)adresDao.checkIfAdresIsOwned(adresId);
    }
    
    public List<AdresType> readAlleAdresTypen() {
    	LOG.info("readAlleAdresTypen gestart");
    	return (List<AdresType>)adresTypeDao.readAll();
    }
    
    public Adres readAdresOpPostcodeEnHuisnummer(String postcode, String huisnummer) {
    	LOG.info("readAdresOpPostcodeEnHuisnummer gestart");
    	try {
    		return adresRepo.readByPostcodeAndHuisnummer(postcode, huisnummer);
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    }
    
    public AdresType readAdresTypeOpId(int adresTypeId) {
    	LOG.info("readAdresTypeOpId gestart");
    	return (AdresType)adresTypeDao.readEntity(adresTypeId);
    }
    
    public List<AdresType> readAdresTypeOpKlantId(int klantId) {
    	LOG.info("readAdresTypeOpKlantId gestart");
    	return adresTypeDao.readByKlantId(klantId);
    }
    
    public Account readAccountOpId(int accountId){
        LOG.info("readAccount op account id gestart");
        return (Account)accountDao.readEntity(accountId);
    }
    public List<Account> readAccountOpKlantId(int klantId){
        LOG.info("read account op klant Id gestart");
        Klant newKlant = (Klant)klantDao.readEntity(klantId);
        Set<Account> accountSet = newKlant.getAccounts();
        List<Account> accountLijst = new ArrayList<>();
        for(Account a : accountSet){
            accountLijst.add(a);
        }
        return accountLijst;
    }
    // ============= Update ============
    
    public boolean updateKlant(Klant klant){
        LOG.info("update klant gestart");
        try {
        klantDao.updateEntity(klant);
        return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }
    public boolean updateAdres(Adres adres){
        LOG.info("update adres gestart");
        try{
            adresDao.updateEntity(adres);
            return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }   
    public boolean updateAccount(Account account){
        LOG.info("update account gestart");
        try{
        accountDao.updateEntity(account);
        return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }
    
    //============== delete =================
    public boolean deleteKlant(Klant klant){
        LOG.info("delete klant gestart");
        try {
        klantDao.deleteEntity(klant);
        return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }
    public boolean deleteKlantById(int klantId){
        LOG.info("delete klant by id gestart");
        try {
        klantDao.deleteEntityById(klantId);
        return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }
    public boolean deleteAdres(Adres adres){
        LOG.info("delete adres gestart");
        try {
        adresDao.deleteEntity(adres);
        return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }
    public boolean deleteAdresById(int adresId){
        LOG.info("delete adres by id gestart");
        try {
        adresDao.deleteEntityById(adresId);
        return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }
    
    public boolean deleteAdresFromKlant(int klantId, int adresId, int adresTypeId) {
    	LOG.info("verwijder adres uit klant gestart");
    	try {
            adresDao.decoupleAdresFromKlant(klantId, adresId, adresTypeId);
            return true;
            } catch (Exception ex){
                LOG.error(ex.getMessage());
                return false;
            }
    }
    
    public boolean addAdresToKlant(int klantId, int adresId, int adresTypeId) {
    	LOG.info("toevoegen adres aan klant gestart");
    	try {
            adresDao.coupleAdresWithKlant(klantId, adresId, adresTypeId);
            return true;
            } catch (Exception ex){
                LOG.error(ex.getMessage());
                return false;
            }
    }
    
    public boolean deleteAccount(Account account){
        LOG.info("delete account gestart");
        try{
            accountDao.deleteEntity(account);
            return true;
        } catch (HibernateException ex){
            LOG.error(ex.getMessage());
            return false;
        }
    }
}
