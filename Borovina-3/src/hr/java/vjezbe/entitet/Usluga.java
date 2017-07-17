package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Predstavlja entitet usluge definiran klijentom, vrstom usluge
 * opisom usluge, datumom usluge, cijenom usluge, te podacima o 
 * obavljanju i naplati usluge.
 */
import java.time.LocalDate;

public class Usluga {
	
	private Klijent klijent;
	private String vrstaUsluge;
	private String opisUsluge;
	private LocalDate datumUsluge;
	private BigDecimal cijenaUsluge;
	private boolean uslugaObavljena = false;
	private boolean uslugaNaplacena = false;
	
/**
 * Inicijalizira podatke o klijentu, vrsti usluge, opisu usluge, datumu usluge
 * cijeni usluge, te podatke o obavljanju i naplati usluge.
 * @param klijent podatak o klijentu
 * @param vrstaUsluge podatak o vrsti usluge
 * @param opisUsluge podatak o opisu usluge
 * @param datumUsluge podatak o datumu usluge
 * @param cijenaUsluge podatak o cijeni usluge
 * @param uslugaObavljena podatak o obavljanju usluge
 * @param uslugaNaplacena podatak onaplati usluge
 */
	public Usluga(Klijent klijent, String vrstaUsluge, String opisUsluge, LocalDate datumUsluge,
			      BigDecimal cijenaUsluge, Boolean uslugaObavljena, Boolean uslugaNaplacena) {

		this.klijent = klijent;
		this.vrstaUsluge = vrstaUsluge;
		this.opisUsluge = opisUsluge;
		this.datumUsluge = datumUsluge;
		this.cijenaUsluge = cijenaUsluge;
		this.uslugaObavljena = uslugaObavljena;
		this.uslugaNaplacena = uslugaNaplacena;
	}

	/**
	 * Dohva�a i vra�a podatak o klijentu.
	 * Vra�a referencu na objekt klase Klijent koji predstavlja klijenta.
	 * @return referenca na objekt klase Klijent
	 */
	public Klijent getKlijent() {
		return klijent;
	}

	/**
	 * Postavlja podatak o klijentu.
	 * Kao argument prima referencu na objekt klase Klijent 
	 * koji predstavlja klijenta. 
	 * @param klijent referenca na objekt klase Klijent
	 */
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	/**
	 * Dohva�a i vra�a podatak o vrsti usluge.
	 * Vra�a String koji predstavlja vrstu usluge.
	 * @return vrsta usluge
	 */
	public String getVrstaUsluge() {
		return vrstaUsluge;
	}

	/**
	 * Postavlja podatak o vrsti usluge.
	 * Kao argument prima String koji prestavlja vrstu usluge.
	 * @param vrstaUsluge vrsta usluge
	 */
	public void setVrstaUsluge(String vrstaUsluge) {
		this.vrstaUsluge = vrstaUsluge;
	}

	/**
	 * Dohva�a i vra�a podatak o opisu usluge.
	 * Vra�a String koji prestavlja opis usluge.
	 * @return opis usluge
	 */
	public String getOpisUsluge() {
		return opisUsluge;
	}

	/**
	 * Postavlja podatak o opisu usluge.
	 * Kao argument prima String koji predstavlja opis usluge.
	 * @param opisUsluge opis usluge
	 */
	public void setOpisUsluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}

	/**
	 * Dohva�a i vra�a podatak o datumu usluge.
	 * Vra�a referencu na objekt klase LocalDate koji
	 * predstavlja datum usluge.
	 * @return referenca na objekt klase LocalDate
	 */
	public LocalDate getDatumUsluge() {
		return datumUsluge;
	}

	/**
	 * Postavlja podatak o datumu usluge.
	 * Kao argument prima referencu na objekt LocalDate
	 * koji predstavlja datum usluge.
	 * @param datumUsluge referenca na objekt LocalDate
	 */
	public void setDatumUsluge(LocalDate datumUsluge) {
		this.datumUsluge = datumUsluge;
	}

	/**
	 * Dohva�a i vra�a podatak ocijeni usluge.
	 * Vra�a referencu na objekt BigDecimal koji
	 * prestavlja cijenu usluge.
	 * @return referenca na objekt BigDecimal
	 */
	public BigDecimal getCijenaUsluge() {
		return cijenaUsluge;
	}

	/**
	 * Postavlja podatak o cijeni usluge.
	 * Kao argument prima referencu na objekt BigDecimal
	 * koji predstavlja cijenu usluge.
	 * @param cijenaUsluge referenca na objekt BigDecimal
	 */
	public void setCijenaUsluge(BigDecimal cijenaUsluge) {
		this.cijenaUsluge = cijenaUsluge;
	}

	/**
	 * Dohva�a i vra�a podatak da li je usluga obavljena.
	 * Vra�a boolean vrijednost koja predstavlja da li je usluga obavljena.
	 * @return podatak o obavljenoj usluzi
	 */
	public boolean getUslugaObavljena() {
		return uslugaObavljena;
	}
	
	/**
	 * Postavlja podatak da li je usluga obavljena.
	 * Kao argument prima boolean vrijednost koja predstavlja da li je
	 * usluga obavljena.
	 * @param uslugaObavljena podatak o obavljenoj usluzi
	 */
	public void setUslugaObavljena(boolean uslugaObavljena) {
		this.uslugaObavljena = uslugaObavljena;
	}

	/**
	 * Dohva�a i vra�a podatak da li je usluga napla�ena.
	 * Vra�a boolean vrijednost koja predstavlja da li je usluga napla�ena.
	 * @return podatak o napla�enoj usluzi
	 */
	public boolean getUslugaNaplacena() {
		return uslugaNaplacena;
	}

	/**
	 * Postavlja podatak da li je usluga napla�ena.
	 * Kao argument prima boolean vrijednost koja predstavlja da li je
	 * usluga napla�ena.
	 * @param uslugaObavljena podatak o napla�enoj usluzi
	 */
	public void setUslugaNaplacena(boolean uslugaNapla�ena) {
		this.uslugaNaplacena = uslugaNapla�ena;
	}
	
}
