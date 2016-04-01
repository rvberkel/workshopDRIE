package springCommandObjects;

import javax.validation.Valid;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import POJO.Adres;
import POJO.AdresType;

@Component("adresWithAdrestype")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdresWithAdrestype {
	@Valid
	Adres adres;
	@Valid
	AdresType adresType;
	
	public AdresWithAdrestype() {
	}
	
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	public AdresType getAdresType() {
		return adresType;
	}
	public void setAdresType(AdresType adresType) {
		this.adresType = adresType;
	}	
}
