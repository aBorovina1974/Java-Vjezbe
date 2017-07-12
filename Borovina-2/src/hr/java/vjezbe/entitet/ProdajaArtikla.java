package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdajaArtikla extends Usluga implements Robna {

	private Artikl artikl;
	
	public ProdajaArtikla(Klijent klijent, String vrstaUsluge, String opisUsluge, 
			             LocalDate datumUsluge,BigDecimal cijenaUsluge, Boolean uslugaObavljena,
			             Boolean uslugaNaplacena, Artikl artikl)
	{
	super(klijent, vrstaUsluge, opisUsluge, datumUsluge,
		  cijenaUsluge,uslugaObavljena, uslugaNaplacena);
	this.artikl = artikl;
	}

	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}
	
	public BigDecimal prodaja(int brojArtikala)
	{
		this.setUslugaObavljena(true);
		this.setUslugaNaplacena(true);
		return (this.getCijenaUsluge().multiply(new BigDecimal(brojArtikala)));
	}
}
