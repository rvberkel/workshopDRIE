package interfaces;

import java.util.List;

import POJO.Adres;

public interface AdresDaoInterface {
	public List<Adres> readByKlantId(int idKlant);
	public Adres readByPostcodeAndHuisnummer(String postcode, String huisnummer);
	public List<Integer> checkIfAdresIsOwned(int adresId);
	public void decoupleAdresFromKlant(int klantId, int adresId, int adresTypeId);
	void coupleAdresWithKlant(int klantId, int adresId, int adresTypeId);
}
