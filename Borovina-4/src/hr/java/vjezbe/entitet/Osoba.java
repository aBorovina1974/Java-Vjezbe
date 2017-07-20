package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet osobe definiran imenom i prezimenom osobe.
 * @author Ante
 *
 */
public abstract class Osoba {

	private String ime;
	private String prezime;
	
	/**
	 * Inicijalizira podatak o imenu i prezimenu osobe.
	 * @param ime podatak o imenu
	 * @param prezime podatak o prezimenu
	 */
	public Osoba(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
	}

	/**
	 * Dohvaæa podatak o imenu osobe.
	 * Vraæa String koji predstavlja ime osobe.
	 * @return ime osobe
	 */
	public String getIme() {
		return ime;
	}
	
	/**
	 * Postavlja podatak o imenu osobe.
	 * Kao argument prima String koji predstavlja ime osobe
	 * @param ime ime osobe
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	/**
	 * Dohvaæa i vraæa podatak o prezimenu osobe.
	 * Vraæa String koji predstavlja prezime osobe.
	 * @return prezime osobe
	 */
	public String getPrezime() {
		return prezime;
	}
	
	/**
	 * Postavlja podatak o prezimenu osobe.
	 * Kao argument prima String koji predstavlja prezime osobe.
	 * @param prezime prezime osobe
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	
}
