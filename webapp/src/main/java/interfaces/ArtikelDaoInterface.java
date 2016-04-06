package interfaces;

import java.util.List;

import POJO.Artikel;

public interface ArtikelDaoInterface {
	public List<Artikel> readByBestellingId(int bestellingId);
}
