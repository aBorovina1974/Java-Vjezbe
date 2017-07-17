package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Predstavlja entitet komunikacije koji je definiran klijentom,
 * zaposlenikom, vrstom komunikacije, sadržajem komunikacije i
 * vremenom komunikacije.
 * 
 * @author Ante
 *
 */

public class Komunikacija {
	
	private Klijent klijent;
	private Zaposlenik zaposlenik;
	private String vrstaKomunikacije;
	private String sadrzajKomunikacije;
	private LocalDateTime vrijemeKomunikacije;
	
	/**
	 * Inicijalizira podatke o klijentu, zaposleniku, vrsti komunikacije,
	 * sadržaju komunikacije i vremenu komunikacije.
	 * @param klijent podatak o klijentu
	 * @param zaposlenik podatak o zaposleniku
	 * @param vrstaKomunikacije podatak o vrsti komunikacije
	 * @param sadrzajKomunikacije podatak o sadržaju komunikacije
	 * @param vrijemeKomunikacije podatak o vremenu komunikacije
	 */
	
	public Komunikacija(Klijent klijent, Zaposlenik zaposlenik, String vrstaKomunikacije, 
			            String sadrzajKomunikacije, LocalDateTime vrijemeKomunikacije) {
		
		this.klijent = klijent;
		this.zaposlenik = zaposlenik;
		this.vrstaKomunikacije = vrstaKomunikacije;
		this.sadrzajKomunikacije = sadrzajKomunikacije;
		this.vrijemeKomunikacije = vrijemeKomunikacije;
	}

	/**
	 * Dohvaæa i vraæa podatak o klijentu.
	 * Vraæa referencu na objekt klase Klijent koji predstavlja klijenta
	 * @return referenca na objekt klase Klijent
	 */
	public Klijent getKlijent() {
		return klijent;
	}

	/**
	 * Postavlja podatak o klijentu.
	 * Kao argument prima referencu na objekt klase Klijent
	 * koji predstavlja klijenta.
	 * @param klijent referenca na objekt klase klijent.
	 */
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	/**
	 * Dohvaæa i vraæa podatak o zaposleniku.
	 * Vraæa referencu na objekt klase Zaposlenik koji predstavlja zaposlenika.
	 * @return referenca na objekt klase Zaposlenik
	 */
	public Zaposlenik getZaposlenik() {
		return zaposlenik;
	}

	/**
	 * Postavlja podatak o zaposleniku.
	 * Kao argument vraæa referencu na objekt klase Zaposlenik 
	 * koji predstavlja zaposlenika.
	 * @param zaposlenik referenca na objekt klase Zaposlenik
	 */
	public void setZaposlenik(Zaposlenik zaposlenik) {
		this.zaposlenik = zaposlenik;
	}

	/**
	 * Dohvaæa i vraæa podatak o vrsti komunikacije.
	 * Vraæa String koji predstavlja vrstu komunikacije.
	 * @return vrsta komunikacije
	 */
	public String getVrstaKomunikacije() {
		return vrstaKomunikacije;
	}

	/**
	 * Postavlja podatak o vrsti komunikacije.
	 * Kao argument prima String koji predstavlja vrstu komunikacije. 
	 * @param vrstaKomunikacije vrsta komunikacije
	 */
	public void setVrstaKomunikacije(String vrstaKomunikacije) {
		this.vrstaKomunikacije = vrstaKomunikacije;
	}

	/**
	 * Dohvaæa i vraæa podatak o sadržaju komunikacije.
	 * Vraæa String koji predstavlja sadržaj komunikacije.
	 * @return sadržaj komunikacije
	 */
	public String getSadrzajKomunikacije() {
		return sadrzajKomunikacije;
	}

	/**
	 * Postavlja podatak o sadržaju komunikacije.
	 * Kao argument prima String koji predstavlja sadržaj komunikacije.
	 * @param sadrzajKomunikacije sadržaj komunikacije
	 */
	public void setSadrzajKomunikacije(String sadrzajKomunikacije) {
		this.sadrzajKomunikacije = sadrzajKomunikacije;
	}

	/**
	 * Dohvaæa i vraæa podatak o vremenu komunikacije. 
	 * Vraæa referencu na objekt klase LocalDateTime koji predstavlja
	 * vrijeme komunikacije.
	 * @return referenca na objjekt klase LocalDateTime
	 */
	public LocalDateTime getVrijemeKomunikacije() {
		return vrijemeKomunikacije;
	}

	/**
	 * Postavlja podatak o vremenu komunikacije. 
	 * Kao argument prima referencu na objekt klase LocalDateTime 
	 * koji prestavlja vrijeme komunikacije.
	 * @param vrijemeKomunikacije referenca na objekt LocalDateTime
	 */
	public void setVrijemeKomunikacije(LocalDateTime vrijemeKomunikacije) {
		this.vrijemeKomunikacije = vrijemeKomunikacije;
	}
	
}
