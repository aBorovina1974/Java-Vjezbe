package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Predstavlja entitet usluge prodaja artikla definiran artiklom
 * @author Ante
 *
 */
public class ProdajaArtikla extends Usluga  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5244888532392853937L;
	private Artikl artikl;
/**
 * Inicijalizira podatke o klijentu, vrsti usluge, opisu usluge, datumu usluge,
 * cijeni usluge, obavljanju usluge, naplati usluge i artiklu.	
 * @param klijent podatak o klijentu
 * @param vrstaUsluge podatak o vrsti usluge
 * @param opisUsluge podatak o opisu usluge
 * @param datumUsluge podatak o datumu usluge
 * @param cijenaUsluge podatak o cijeni usluge
 * @param uslugaObavljena podatak o obavljanju usluge
 * @param uslugaNaplacena podatak o naplati usluge
 * @param artikl podatak o artiklu
 */
	public ProdajaArtikla(Klijent klijent, VrstaUsluge vrstaUsluge, String opisUsluge, 
			             LocalDate datumUsluge,BigDecimal cijenaUsluge, Boolean uslugaObavljena,
			             Boolean uslugaNaplacena, Artikl artikl)
	{
	super(klijent, vrstaUsluge, opisUsluge, datumUsluge,
		  cijenaUsluge,uslugaObavljena, uslugaNaplacena);
	this.artikl = artikl;
	}

	/**
	 * Dohvaæa i vraæa podatak o artiklu.
	 * Vraæa referencu na objekt klase Artikl koji predstavlja artikal.
	 * @return referenca na objekt klase Artikl
	 */
	public Artikl getArtikl() {
		return artikl;
	}

	/**
	 * Postavlja podatak o artiklu.
	 * Kao argument prima referencu na objekt klase Artikl koji predstavlja artikal.
	 * @param artikl referenca na objekt klase Artikl
	 */
	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}
/**
 * Raèuna i vraæa ukupnu cijenu usluge na osnovu broja prodanih artikala.
 * @param broj prodanih artikala
 * @return ukupna cijena usluge
 */
//	public BigDecimal prodaja(int brojArtikala)
//	{
//		this.setUslugaObavljena(true);
//		this.setUslugaNaplacena(true);
//		return (this.getCijenaUsluge().multiply(new BigDecimal(brojArtikala)));
//	}
	
	public BigDecimal prodajaArtikala(int brArtikala, Robna robna)
	{
		return robna.prodaja(brArtikala);
    }
}
