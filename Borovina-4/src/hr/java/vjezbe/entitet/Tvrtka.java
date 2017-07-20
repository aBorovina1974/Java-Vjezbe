package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet tvrtke koji je definiran nazivom tvrtke i OIB-om
 * tvrtke, te klijentima, zaposlenicima, komunikacijama, uslugama i alarmima.
 * 
 * @author Ante
 */
public class Tvrtka {
	
	public static final int BROJ_KLIJENATA = 2;
	public static final int BROJ_ZAPOSLENIKA = 2;
    
	private String nazivTvrtke;
	private String oibTvrtke;
	private List<Klijent> klijenti;
	private List<Zaposlenik> zaposlenici;
	private List<Komunikacija> komunikacije;
	private List<? extends Usluga> usluge;
	private List<Alarm> alarmi;
	
/**
 * Inicijalizira podatak o nazivu tvrtke i OIB-u tvrtke.
 * 	
 * @param nazivTvrtke
 * @param oibTvrtke
 */
	public Tvrtka(String nazivTvrtke, String oibTvrtke) {
		this.nazivTvrtke = nazivTvrtke;
		this.oibTvrtke = oibTvrtke;
	}


	/**
	 * Dohvaæa i vraæa podatak o nazivu tvrtke.
	 * Vraæa String koji predstavlja naziv tvrtke.
	 * @return naziv tvrtke
	 */
	public String getNazivTvrtke() {
		return nazivTvrtke;
	}

	/**
	 * Postavlja podatak o nazivu tvrtke.
	 * Kao argument prima String koji predstavlja naziv tvrtke
	 * @param nazivTvrtke naziv tvrtke
	 */
	public void setNazivTvrtke(String nazivTvrtke) {
		this.nazivTvrtke = nazivTvrtke;
	}

	/**
	 * Dohvaæa i vraæa podatak o OIB-u tvrtke.
	 * Vraæa String koji predstavlja podatak o OIB-u tvrtke.
	 * @return OIB tvrtke
	 */
	public String getOibTvrtke() {
		return oibTvrtke;
	}

	/**
	 * Postavlja podatak o OIB-u tvrtke.
	 * Kao argument prima String koji predstavlja OIB tvrtke
	 * @param oibTvrtke OIB tvrtke
	 */
	public void setOibTvrtke(String oibTvrtke) {
		this.oibTvrtke = oibTvrtke;
	}

	/**
	 * Dohvaæa i vraæa podatke o klijentima tvrtke.
	 * Vraæa referencu na listu klijenata koja predstavlja
	 * klijente tvrtke.
	 * @return referenca na listu klijenata
	 */
	public List<Klijent> getKlijenti() {
		return klijenti;
	}

	/**
	 * Postavlja podatke o klijentima tvrtke.
	 * Kao argument prima referencu na listu klijenata koja 
	 * predstavlja klijente tvrtke
	 * @param klijenti referenca na listu klijenata
	 */
	public void setKlijenti(List<Klijent> klijenti) {
		this.klijenti = klijenti;
	}

	/**
	 * Dohvaæa podatke i vraæa o zaposlenicima tvrtke.
	 * Vraæa referencu na listu zaposlenika koja predstavlja 
	 * zaposlenike tvrtke
	 * @return referenca na listu zaposlenika
	 */
	public List<Zaposlenik> getZaposlenici() {
		return zaposlenici;
	}

	/**
	 * Postavlja podatke o zaposlenicima tvrtke.
	 * Kao argument prima referencu na listu zaposlenika koja
	 * predstavlja zaposlenike tvrtke.
	 * @param zaposlenici referenca na listu zaposlenika
	 */
	public void setZaposlenici(List<Zaposlenik> zaposlenici) {
		this.zaposlenici = zaposlenici;
	}

	/**
	 * Dohvaæa i vraæa podatke o komunikacijama.
	 * Vraæa referencu na listu komunikacija koja predstavlja
	 * komunikacije.
	 * @return referenca na listu komunikacija
	 */
	public List<Komunikacija> getKomunikacije() {
		return komunikacije;
	}

	/**
	 * Postavlja poatke o komunikacijama.
	 * Kao argument prima referencu na listu komunikacija koja 
	 * predstavlja komunikacije.
	 * @param komunikacije referenca na listu komunikacija
	 */
	public void setKomunikacije(List<Komunikacija> komunikacije) {
		this.komunikacije = komunikacije;
	}

	/**
	 * Dohvaæa i vraæa podatke o uslugama tvrtke.
	 * Vraæa referencu na listu usluga koja predstavlja usluge tvrtke.
	 * @return referenca na listu usluga
	 */
	public List<? extends Usluga> getUsluge() {
		return usluge;
	}

	/**
	 * Postavlja podatke o uslugama tvrtke.
	 * Kao argument prima referencu na listu usluga koja
	 * predstavlja usluge tvrtke.
	 * @param usluge referenca na listu usluga
	 */
	public void setUsluge(List<? extends Usluga> usluge) {
		this.usluge = usluge;
	}

	/**
	 * Dohvaæa i vraæa podatke o alarmima.
	 * Vraæa referencu na listu alarma koja predstavlja alarme.
	 * @return referenca na listu alarma
	 */
	public List<Alarm> getAlarmi() {
		return alarmi;
	}

	/**
	 * Postavlja podatke o alarmima.
	 * Kao argument prima referencu na listu alarma koja 
	 * predsatvlja alarme. 
	 * @param alarmi referenca na listu alarma
	 */
	public void setAlarmi(List<Alarm> alarmi) {
		this.alarmi = alarmi;
	}

}
