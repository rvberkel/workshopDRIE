package Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import POJO.Artikel;
import POJO.Bestelling;
import POJO.BestellingHasArtikel;
import POJO.Betaalwijze;
import POJO.Betaling;
import POJO.Factuur;
import POJO.Klant;
import daos.ArtikelDao;
import daos.BestellingDao;
import daos.BestellingHasArtikelDao;
import daos.BetaalwijzeDao;
import daos.BetalingDao;
import daos.FactuurDao;
import daos.KlantDao;
import genericDao.GenericDao;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bestellingService")
@Scope("prototype")
public class BestellingService {
    private static final Logger LOG = LoggerFactory.getLogger(BestellingService.class);
    @Autowired
    @Qualifier("BestellingDao")
    GenericDao bestellingDao;
    @Autowired
    @Qualifier("BestellingHasArtikelDao")
    GenericDao bhaDao;
    @Autowired
    //@Qualifier("ArtikelDao")        
    ArtikelDao artikelDao;
    @Autowired
    @Qualifier("FactuurDao")
    GenericDao factuurDao;
    @Autowired
    @Qualifier("BetalingDao")
    GenericDao betalingDao;
    @Autowired
    @Qualifier("BetaalwijzeDao")
    GenericDao betaalwijzeDao;
    @Autowired
    @Qualifier("KlantDao")
    GenericDao klantDao;
    
    // ================ create ====================
    
    public boolean createArtikel(Artikel artikel){
        LOG.info("createArtikel gestart");
        artikelDao.createEntity(artikel);
        return artikel.getIdArtikel() > 0;
    }
    
    public boolean createFactuur(Factuur factuur){
        LOG.info("createFactuur gestart");
        factuurDao.createEntity(factuur);
        return factuur.getIdFactuur() > 0;
    }
    
    public boolean createBestelling(Bestelling bestelling){
        LOG.info("createBestelling gestart");
        bestellingDao.createEntity(bestelling);
        return bestelling.getIdBestelling() > 0;
    }
    
    public boolean createBestellingHasArtikel(BestellingHasArtikel bestellingHasArtikel){
        LOG.info("createBestellingHasArtikel gestart");
        bhaDao.createEntity(bestellingHasArtikel);
        return bestellingHasArtikel.getIdBestelArtikel() > 0;
    }
    
    public boolean createBetaling(Betaling betaling){
        LOG.info("createBetaling gestart");
        betalingDao.createEntity(betaling);
        return betaling.getIdBetaling() > 0;
    }
    
    public boolean createBetaalwijze(Betaalwijze betaalwijze){
        LOG.info("createBetaalwijze gestart");
        betaalwijzeDao.createEntity(betaalwijze);
        return betaalwijze.getIdBetaalwijze() > 0;  
    }
    
    //============================= read =====================
    
    public List<Artikel> readArtikel(Artikel artikel){
        LOG.info("readArtikel gestart");
        return artikelDao.readByExample(artikel);
    }
    public Artikel readArtikelOpId(int artikelId){
        LOG.info("readArtikelOpId gestart");
        return (Artikel) artikelDao.readEntity(artikelId);
    }
    
    public List <Artikel> readAllArtikel(){
        LOG.info("read alle artikelen gestart");
        return (List<Artikel>)artikelDao.readAll();
    }
    
    public List<Artikel> readArtikelOpBestellingId(int bestellingId) {
    	LOG.info("read artikelen op bestellingId gestart");
        return artikelDao.readByBestellingId(bestellingId);
    }
    
    public List<Factuur> readFactuur(Factuur factuur){
        LOG.info("readFactuur gestart");
        return factuurDao.readByExample(factuur);
    }
    public Factuur readFactuurOpId(int factuurId){
        LOG.info("readFactuurOpId");
        return (Factuur) factuurDao.readEntity(factuurId);
    }
    public List<Factuur> readFactuurOpBestellingId(int bestelId) {
    	LOG.info("readFactuurOpBestellingId gestart");
    	return (List<Factuur>)((FactuurDao) factuurDao).readOpBestelId(bestelId);
    }
    
    public List<Factuur> readAlleFacturen() {
    	LOG.info("readAlleFacturen gestart");
    	return(List<Factuur>)factuurDao.readAll();
    }
    public List<Bestelling> readBestelling(Bestelling bestelling){
        LOG.info("readBestelling gestart");
        return bestellingDao.readByExample(bestelling);
    }
    public Bestelling readBestellingOpId(int bestellingId){
        LOG.info("readBestellingOpId gestart");
        return (Bestelling) bestellingDao.readEntity(bestellingId);
    }
    public List<Bestelling> readAlleBestellingen(){
    	LOG.info("read alle bestellingen gestart");
    	return (List<Bestelling>)bestellingDao.readAll();
    }
    
    public List<BestellingHasArtikel> readBestellingHasArtikel(BestellingHasArtikel bestellingHasArtikel){
        LOG.info("readBestellingHasArtikel gestart");
        return bhaDao.readByExample(bestellingHasArtikel);
    }
    
    public BestellingHasArtikel readBestellingHasArtikelOpId(int bestellingHasArtikelId){
        LOG.info("readBestellingHasArtikelOpId gestart");
        return (BestellingHasArtikel) bhaDao.readEntity(bestellingHasArtikelId);
    }
    
    public List<Betaling> readBetaling(Betaling betaling){
        LOG.info("readBetaling gestart");
        return betalingDao.readByExample(betaling);
    }
    public Betaling readBetalingOpId(int betalingId){
        LOG.info("readBetalingOpId gestart");
        return (Betaling) betalingDao.readEntity(betalingId);
    }
    
    public List<Betaalwijze> readBetaalwijze(Betaalwijze betaalwijze){
        LOG.info("readBetaalwijze gestart");
        return betaalwijzeDao.readByExample(betaalwijze);
        
    }
    public Betaalwijze readBetaalWijzeOpId(int betaalwijzeId){
        LOG.info("readBetaalWijzeOpId gestart");
        return (Betaalwijze) betaalwijzeDao.readEntity(betaalwijzeId);
    }
    
    public List<Betaalwijze> readAlleBetaalwijzen(){
        LOG.info("readAlleBetaalwijzen gestart");
        return(List<Betaalwijze>)betaalwijzeDao.readAll();
    }
    
    // ===================== update =========================
    
    public boolean updateArtikel(Artikel artikel){
        try {
            artikelDao.updateEntity(artikel);
        }
        catch(HibernateException he){
            LOG.error("Couldn't update artikel, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Artikel succesfully updated");
        return true;
    }
    
    public boolean updateBestelling(Bestelling bestelling){
        try {
            bestellingDao.updateEntity(bestelling);
        }
        catch(HibernateException he){
            LOG.error("Couldn't update bestelling, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Bestelling succesfully updated");
        return true;
    }
    
    public boolean updateAantalArtikelenInBestelling(BestellingHasArtikel bestHasArt){
        try {
            bhaDao.updateEntity(bestHasArt);
        }
        catch(HibernateException he){
            LOG.error("Couldn't update aantal artikelen in bestelling, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Aantal artikelen succesfully updated");
        return true;
    }
    
    public boolean updateAantalArtikelenInBestelling(int bestellingId, int artikelId, int artikelAantal){
        try {
            Bestelling bestelling = (Bestelling) bestellingDao.readEntity(bestellingId);
            BestellingHasArtikel bestHasArt = null;
            for (BestellingHasArtikel bha : bestelling.getBestellingHasArtikelen()){
                if (bha.getArtikel().getIdArtikel() == artikelId){
                    bestHasArt = bha;
                }
            }
            if (bestHasArt != null){
                bestHasArt.setAantal(artikelAantal);
                bhaDao.updateEntity(bestHasArt);
            }
            else {
                return false;
            }
        }
        catch(HibernateException he){
            LOG.error("Couldn't update aantal artikelen in bestelling, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Aantal artikelen succesfully updated");
        return true;
    }
    
    public boolean updateBetaalwijze(Betaalwijze betaalwijze){
        try {
            betaalwijzeDao.updateEntity(betaalwijze);
        }
        catch(HibernateException he){
            LOG.error("Couldn't update betaalwijze, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Betaalwijze succesfully updated");
        return true;
    }
    
    public boolean updateBetaalwijze(int id, int betaalWijze){
        try {
            Betaalwijze betaalwijze = (Betaalwijze) betaalwijzeDao.readEntity(id);
            betaalwijze.setBetaalwijzeKeuze(betaalWijze);
            betaalwijzeDao.updateEntity(betaalwijze);
        }
        catch(HibernateException he){
            LOG.error("Couldn't update betaalwijze, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Betaalwijze succesfully updated");
        return true;
    }
    
    public boolean updateBetaling(Betaling betaling){
        try {
            betalingDao.updateEntity(betaling);
        }
        catch(HibernateException he){
            LOG.error("Couldn't update betaling, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Betaling succesfully updated");
        return true;
    }
    
    public boolean updateFactuur(Factuur factuur){
        try {
            factuurDao.updateEntity(factuur);
        }
        catch(HibernateException he){
            LOG.error("Couldn't update factuur, " + he.getMessage());
            for (StackTraceElement ste : he.getStackTrace()){
                LOG.error("at " + ste.getClassName() + "." + ste.getMethodName() + ", line " + ste.getLineNumber());
            }
            return false;
        }
        LOG.debug("Factuur succesfully updated");
        return true;
    }
    
    // ========================= delete =====================
    
     public boolean removeBestelling(int bestelling_id) {
        LOG.info("removeBestelling op bestelling ID in Service gestart");
        Bestelling tempBestelling = new Bestelling();
        tempBestelling.setIdBestelling(bestelling_id);
        return removeBestelling(tempBestelling);        
    }
    
    public boolean removeBestelling(Bestelling bestelling) {
        LOG.info("removeBestelling op bestelling Object in Service gestart");
        
        try {
            bestellingDao.deleteEntity(bestelling);
            return true;
        } catch (HibernateException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }
    
    // NOG GOED CONTROLEREN
    public int removeAllBestellingenOpKlantId(int klant_id) {
        Klant tempKlant = (Klant) klantDao.readEntity(klant_id);
        Bestelling tempBestelling = new Bestelling();
        tempBestelling.setKlant(tempKlant);
        
        List<Bestelling> bestellingen = bestellingDao.readByExample(tempBestelling);
        for (Bestelling b: bestellingen) {
            removeBestelling(b);
    }
        
        return bestellingen.size();
    }  
    
    public boolean deleteArtikelUitDatabase(int artikel_id){
        LOG.info("deleteArtikel op ArtikelID in Service gestart");
        Artikel tempArtikel = new Artikel();
        tempArtikel.setIdArtikel(artikel_id);
        
        try {
            artikelDao.deleteEntity(tempArtikel);
            return true;
        } catch (HibernateException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }
    
    public boolean deleteArtikelUitBestelling(int bestelling_id, int artikel_id){
        
        Bestelling bestelling = (Bestelling) bestellingDao.readEntity(bestelling_id);
        BestellingHasArtikel bestHasArt = null;
        for (BestellingHasArtikel bha : bestelling.getBestellingHasArtikelen()){
            if (bha.getArtikel().getIdArtikel() == artikel_id){
                bestHasArt = bha;
            }
        }

        List<BestellingHasArtikel> tempBhaList = bhaDao.readByExample(bestHasArt);
        if (tempBhaList.size() == 1) {
            try {
                bhaDao.deleteEntity(tempBhaList.get(0));
                return true;
            } catch (HibernateException ex) {
                LOG.error(ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean deleteArtikelUitBestellingOpBHAId(int bha_id) {
    	LOG.info("deleteArtikelUitBestellingOpBHAId op BHA_id in Service gestart");
    	try {
    		BestellingHasArtikel bhaObj = (BestellingHasArtikel)bhaDao.readEntity(bha_id);
    		bhaDao.deleteEntity(bhaObj);
    		
    		return true;
    	} catch (HibernateException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }
    
    //bij deletenFactuur ook de bijbehorende betalingen deleten? (of gebeurt dat vanzelf)
    public boolean deleteFactuur(Factuur factuur) {
        LOG.info("deleteFactuur op FactuurID in Service gestart");
        
//        HashSet<Betaling> betalingen = (HashSet)factuur.getBetalingen();
//        for (Betaling b: betalingen) {
//            deleteBetaling(b);
//        }
       
        try {
            factuurDao.deleteEntity(factuur);
            return true;
        } catch (HibernateException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }
    
    public boolean deleteBetaling(Betaling betaling) {
        LOG.info("deleteBetaling op BetalingID in Service gestart");
       
        try {
            betalingDao.deleteEntity(betaling);
            return true;
        } catch (HibernateException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }
    
    public boolean deleteBetaalwijze(Betaalwijze betaalwijze) {
        LOG.info("deleteBetaalwijze op BetaalwijzeID in Service gestart");
       
        try {
            betaalwijzeDao.deleteEntity(betaalwijze);
            return true;
        } catch (HibernateException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

	
}
