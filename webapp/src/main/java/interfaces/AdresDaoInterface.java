package interfaces;

import java.util.List;

import POJO.Adres;

public interface AdresDaoInterface {
	public List<Adres> readByKlantId(int idKlant);
	public void decoupleAdresFromKlant(int klantId, int adresId);
	public Adres readByPostcodeAndHuisnummer(String postcode, String huisnummer);
}
