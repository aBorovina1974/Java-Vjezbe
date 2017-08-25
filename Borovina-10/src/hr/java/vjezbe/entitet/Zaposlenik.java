package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet osobe zaposlenika definiran 
 * korisni�kim imenom i �ifrom. 
 * @author Ante
 *
 */

public class Zaposlenik extends Osoba {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3645047536290456640L;
	private Integer id;
	private String korisnickoIme;
	private String sifraZaposlenika;

/**
 * Inicijalizira podatke o korisni�kom imenu, imenu, prezimenu
 * i �ifri zaposlenika.	
 * @param korisnickoIme podatak o korisni�kom imenu
 * @param ime podatak o imenu
 * @param prezime podatak o przimenu
 * @param sifraZaposlenika podatak o �ifri zaposlenika
 */
	public Zaposlenik(String korisnickoIme, String ime, String prezime, String sifraZaposlenika) {
		super(ime, prezime);
		this.korisnickoIme = korisnickoIme;
		this.sifraZaposlenika = sifraZaposlenika;

	}
	
	/**
	 * Dohva�a i vra�a ID zaposlenika.
	 * @return ID zaposlenika
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID zaposlenika
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID zaposlenika
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Dohva�a i vra�a podatak o korisni�kom imenu zaposlenika.
	 * Vra�a String koji predstavlja korisni�ko ime zaposlenika.
	 * @return korisni�ko ime zaposlenika
	 */
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	/**
	 * Postavlja podatak o korisni�kom imenu zaposlenika.
	 * Kao argument prima String koji predstavlja
	 * korisni�ko ime zaposlenika.
	 * @param korisnickoIme korisni�ko ime zaposlenika
	 */
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	/**
	 * Dohva�a i vra�a podatak o �ifri zaposlenika.
	 * Vra�a String koji predstavlja �ifru zaposlenika.
	 * @return �ifra zaposlenika
	 */
	public String getSifraZaposlenika() {
		return sifraZaposlenika;
	}

	/**
	 * Postavlja podatak o �ifri zaposlenika.
	 * Kao argument prima string koji predstavlja �ifru zaposlenika.
	 * @param sifraZaposlenika �ifra zaposlenika
	 */
	public void setSifraZaposlenika(String sifraZaposlenika) {
		this.sifraZaposlenika = sifraZaposlenika;
	}
	
}
