package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Predstavlja entitet komunikacije koji je definiran klijentom,
 * zaposlenikom, vrstom komunikacije, sadr�ajem komunikacije i
 * vremenom komunikacije.
 * 
 * @author Ante
 *
 */

public class Komunikacija implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -291212359221543377L;
	private Integer id;
	private Klijent klijent;
	private Zaposlenik zaposlenik;
	private VrstaKomunikacije vrstaKomunikacije;
	private String sadrzajKomunikacije;
	private LocalDateTime vrijemeKomunikacije;
	


	/**
	 * Inicijalizira podatke o klijentu, zaposleniku, vrsti komunikacije,
	 * sadr�aju komunikacije i vremenu komunikacije.
	 * @param klijent podatak o klijentu
	 * @param zaposlenik podatak o zaposleniku
	 * @param vrstaKomunikacije podatak o vrsti komunikacije
	 * @param sadrzajKomunikacije podatak o sadr�aju komunikacije
	 * @param vrijemeKomunikacije podatak o vremenu komunikacije
	 */
	
	public Komunikacija(Klijent klijent, Zaposlenik zaposlenik, VrstaKomunikacije vrstaKomunikacije, 
			            String sadrzajKomunikacije, LocalDateTime vrijemeKomunikacije) {
		
		this.klijent = klijent;
		this.zaposlenik = zaposlenik;
		this.vrstaKomunikacije = vrstaKomunikacije;
		this.sadrzajKomunikacije = sadrzajKomunikacije;
		this.vrijemeKomunikacije = vrijemeKomunikacije;
	}
	
	/**
	 * Dohva�a i vra�a ID komunikacije.
	 * @return ID komunikacije
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID komunikacije
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID komunikacije
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Dohva�a i vra�a podatak o klijentu.
	 * Vra�a referencu na objekt klase Klijent koji predstavlja klijenta
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
	 * Dohva�a i vra�a podatak o zaposleniku.
	 * Vra�a referencu na objekt klase Zaposlenik koji predstavlja zaposlenika.
	 * @return referenca na objekt klase Zaposlenik
	 */
	public Zaposlenik getZaposlenik() {
		return zaposlenik;
	}

	/**
	 * Postavlja podatak o zaposleniku.
	 * Kao argument vra�a referencu na objekt klase Zaposlenik 
	 * koji predstavlja zaposlenika.
	 * @param zaposlenik referenca na objekt klase Zaposlenik
	 */
	public void setZaposlenik(Zaposlenik zaposlenik) {
		this.zaposlenik = zaposlenik;
	}

	/**
	 * Dohva�a i vra�a podatak o vrsti komunikacije.
	 * Vra�a String koji predstavlja vrstu komunikacije.
	 * @return vrsta komunikacije
	 */
	public VrstaKomunikacije getVrstaKomunikacije() {
		return vrstaKomunikacije;
	}

	/**
	 * Postavlja podatak o vrsti komunikacije.
	 * Kao argument prima String koji predstavlja vrstu komunikacije. 
	 * @param vrstaKomunikacije vrsta komunikacije
	 */
	public void setVrstaKomunikacije(VrstaKomunikacije vrstaKomunikacije) {
		this.vrstaKomunikacije = vrstaKomunikacije;
	}

	/**
	 * Dohva�a i vra�a podatak o sadr�aju komunikacije.
	 * Vra�a String koji predstavlja sadr�aj komunikacije.
	 * @return sadr�aj komunikacije
	 */
	public String getSadrzajKomunikacije() {
		return sadrzajKomunikacije;
	}

	/**
	 * Postavlja podatak o sadr�aju komunikacije.
	 * Kao argument prima String koji predstavlja sadr�aj komunikacije.
	 * @param sadrzajKomunikacije sadr�aj komunikacije
	 */
	public void setSadrzajKomunikacije(String sadrzajKomunikacije) {
		this.sadrzajKomunikacije = sadrzajKomunikacije;
	}

	/**
	 * Dohva�a i vra�a podatak o vremenu komunikacije. 
	 * Vra�a referencu na objekt klase LocalDateTime koji predstavlja
	 * vrijeme komunikacije.
	 * @return referenca na objjekt klase LocalDateTime
	 */
	public LocalDateTime getVrijemeKomunikacije() {
		return vrijemeKomunikacije;
	}

}
