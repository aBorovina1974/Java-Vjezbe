package hr.java.vjezbe.entitet;

import java.time.LocalDate;
/**
 * Predstavlja entitet osobe  klijenta koji je definiran OIB-om,
 * telefonskim brojem, email adresom i datumom ro�enja klijenta.
 * @author Ante
 *
 */
public class Klijent extends Osoba {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6594067182304535385L;
	private String oib;
    private String telefon;
	private String email;
	private LocalDate datumRodjenja;
/**
 * Inicijalizira podatke o OIB-u, prezimenu, imenu, telefonskom broju, email
 * adresi i datumu ro�enja.	
 * @param oib podatak o OIB-u
 * @param prezime podatak o prezimenu
 * @param ime podatak o imenu
 * @param telefon podatak o telefonskom broju
 * @param email podatak o email adresi
 * @param datumRodjenja podatak o datumu ro�enja
 */
	public Klijent(String oib, String prezime, String ime, String telefon, String email,
			       LocalDate datumRodjenja) {
		super(ime, prezime);
		this.oib = oib;
		this.telefon = telefon;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
	}

	/**
	 * Dohva�a i vra�a podatak o OIB-u klijenta.
	 * Vra�a String koji predstavlja OIB klijenta.
	 * @return OIB klijenta
	 */
	public String getOib() {
		return oib;
	}

	/**
	 * Postavlja podatak o OIB-u klijenta. Kao argument prima 
	 * String koji predstavlja OIB klijenta.
	 * @param oib OIB klijenta
	 */
	public void setOib(String oib) {
		this.oib = oib;
	}

	/**
	 * Dohva�a i vra�a podatak o telefonskom broju klijenta.
	 * Vra�a String koji predstavlja telefonski broj klijenta.
	 * @return telefonski broj klijenta.
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Postavlja podatak o telefonskom broju klijenta.
	 * Kao argument prima String koji predstavlja telefonski broj klijenta.
	 * @param telefon telefonski broj klijenta
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * Dohva�a i vra�a podatak o email adresi klijenta.
	 * Vra�a String koji predstavlja email adresu klijenta.
	 * @return email adresa klijenta.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Postavlja podatak o email adresi klijenta.
	 * Kao argument prima String koji predstavlja email adresu klijenta.
	 * @param email email adresa klijenta.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Dohva�a i vra�a podatak o datumu ro�enja klijenta.
	 * Vra�a referencu na objekt klase LocalDate koji predstavlja
	 * datum ro�enja klijenta.
	 * @return referenca na objekt klase LocalDate
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	/**
	 * Postavlja podatak o datumu ro�enja klijenta. 
	 * Kao argument prima referencu na objekt klase LocalDate koji
	 * predstavlja datum ro�enja klijenta.
	 * @param datumRodjenja referenca na objekt klase LocalDate
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
}
