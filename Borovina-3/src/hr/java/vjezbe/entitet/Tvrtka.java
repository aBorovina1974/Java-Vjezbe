package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet tvrtke koji je definiran nazivom tvrtke i OIB-om
 * tvrtke, te klijentima, zaposlenicima, komunikacijama, uslugama i alarmima.
 * 
 * @author Ante
 */
public class Tvrtka {
	
	public static final int BROJ_KLIJENATA = 2;
	public static final int BROJ_ZAPOSLENIKA = 2;
	public static final int BROJ_KOMUNIKACIJA = 2;
	public static final int BROJ_USLUGA = 3;
	public static final int BROJ_ALARMA = 3;
	
	private String nazivTvrtke;
	private String oibTvrtke;
	private Klijent[] klijenti = new Klijent[BROJ_KLIJENATA];
	private Zaposlenik[] zaposlenici = new Zaposlenik[BROJ_ZAPOSLENIKA];
	private Komunikacija[] komunikacije = new Komunikacija[BROJ_KOMUNIKACIJA];
	private Usluga[] usluge = new Usluga[BROJ_USLUGA];
	private Alarm[] alarmi = new Alarm[BROJ_ALARMA];
	
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
	 * Dohva�a i vra�a podatak o nazivu tvrtke.
	 * Vra�a String koji predstavlja naziv tvrtke.
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
	 * Dohva�a i vra�a podatak o OIB-u tvrtke.
	 * Vra�a String koji predstavlja podatak o OIB-u tvrtke.
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
	 * Dohva�a i vra�a podatke o klijentima tvrtke.
	 * Vra�a referencu na polje klijenata koje predstavlja
	 * klijente tvrtke.
	 * @return referenca na polje klijenata
	 */
	public Klijent[] getKlijenti() {
		return klijenti;
	}

	/**
	 * Postavlja podatke o klijentima tvrtke.
	 * Kao argument prima referencu na polje klijenata koje 
	 * predstavlja klijente tvrtke
	 * @param klijenti referenca na polje klijenata
	 */
	public void setKlijenti(Klijent[] klijenti) {
		this.klijenti = klijenti;
	}

	/**
	 * Dohva�a podatke i vra�a o zaposlenicima tvrtke.
	 * Vra�a referencu na polje zaposlenika koje predstavlja 
	 * zaposlenike tvrtke
	 * @return referenca na polje zaposlenika
	 */
	public Zaposlenik[] getZaposlenici() {
		return zaposlenici;
	}

	/**
	 * Postavlja podatke o zaposlenicima tvrtke.
	 * Kao argument prima referencu na polje zaposlenika koje
	 * predstavlja zaposlenike tvrtke.
	 * @param zaposlenici referenca na polje zaposlenika
	 */
	public void setZaposlenici(Zaposlenik[] zaposlenici) {
		this.zaposlenici = zaposlenici;
	}

	/**
	 * Dohva�a i vra�a podatke o komunikacijama.
	 * Vra�a referencu na polje komunikacija koje predstavlja
	 * komunikacije.
	 * @return referenca na polje komunikacija
	 */
	public Komunikacija[] getKomunikacije() {
		return komunikacije;
	}

	/**
	 * Postavlja poatke o komunikacijama.
	 * Kao argument prima referencu na polje komunikacija koje 
	 * predstavlja komunikacije.
	 * @param komunikacije referenca na polje komunikacija
	 */
	public void setKomunikacije(Komunikacija[] komunikacije) {
		this.komunikacije = komunikacije;
	}

	/**
	 * Dohva�a i vra�a podatke o uslugama tvrtke.
	 * Vra�a referencu na polje usluga koje predstavlja usluge tvrtke.
	 * @return referenca na polje usluga
	 */
	public Usluga[] getUsluge() {
		return usluge;
	}

	/**
	 * Postavlja podatke o uslugama tvrtke.
	 * Kao argument prima referencu na polje usluga koje 
	 * predstavlja usluge tvrtke.
	 * @param usluge referenca na polje usluga
	 */
	public void setUsluge(Usluga[] usluge) {
		this.usluge = usluge;
	}

	/**
	 * Dohva�a i vra�a podatke o alarmima.
	 * Vra�a referencu na polje alarma koje predstavlja alarme.
	 * @return referenca na polje alarma
	 */
	public Alarm[] getAlarmi() {
		return alarmi;
	}

	/**
	 * Postavlja podatke o alarmima.
	 * Kao argument prima referencu na polje alarma koje 
	 * predsatvlja alarme. 
	 * @param alarmi referenca na polje alarma
	 */
	public void setAlarmi(Alarm[] alarmi) {
		this.alarmi = alarmi;
	}

}
