package interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import POJO.Adres;

@Repository
public interface AdresRepository extends JpaRepository<Adres, Integer> {
	@Transactional
	public Adres readByPostcodeAndHuisnummer(String postcode, String huisnummer);
}
