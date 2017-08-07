package hr.java.vjezbe.entitet;

import java.time.LocalDate;
/**
 * Predstavlja entitet osobe  klijenta koji je definiran OIB-om,
 * telefonskim brojem, email adresom i datumom roðenja klijenta.
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
 * adresi i datumu roðenja.	
 * @param oib podatak o OIB-u
 * @param prezime podatak o prezimenu
 * @param ime podatak o imenu
 * @param telefon podatak o telefonskom broju
 * @param email podatak o email adresi
 * @param datumRodjenja podatak o datumu roðenja
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
	 * Dohvaæa i vraæa podatak o OIB-u klijenta.
	 * Vraæa String koji predstavlja OIB klijenta.
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
	 * Dohvaæa i vraæa podatak o telefonskom broju klijenta.
	 * Vraæa String koji predstavlja telefonski broj klijenta.
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
	 * Dohvaæa i vraæa podatak o email adresi klijenta.
	 * Vraæa String koji predstavlja email adresu klijenta.
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
	 * Dohvaæa i vraæa podatak o datumu roðenja klijenta.
	 * Vraæa referencu na objekt klase LocalDate koji predstavlja
	 * datum roðenja klijenta.
	 * @return referenca na objekt klase LocalDate
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	/**
	 * Postavlja podatak o datumu roðenja klijenta. 
	 * Kao argument prima referencu na objekt klase LocalDate koji
	 * predstavlja datum roðenja klijenta.
	 * @param datumRodjenja referenca na objekt klase LocalDate
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
}
