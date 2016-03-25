package interfaces;

import java.util.List;

import POJO.AdresType;

public interface AdresTypeDaoInterface {
	public List<AdresType> readByKlantId(int idKlant);
}
