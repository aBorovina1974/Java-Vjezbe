package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet osobe zaposlenika definiran 
 * korisnièkim imenom i šifrom. 
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
 * Inicijalizira podatke o korisnièkom imenu, imenu, prezimenu
 * i šifri zaposlenika.	
 * @param korisnickoIme podatak o korisnièkom imenu
 * @param ime podatak o imenu
 * @param prezime podatak o przimenu
 * @param sifraZaposlenika podatak o šifri zaposlenika
 */
	public Zaposlenik(String korisnickoIme, String ime, String prezime, String sifraZaposlenika) {
		super(ime, prezime);
		this.korisnickoIme = korisnickoIme;
		this.sifraZaposlenika = sifraZaposlenika;

	}
	
	/**
	 * Dohvaæa i vraæa ID zaposlenika.
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
	 * Dohvaæa i vraæa podatak o korisnièkom imenu zaposlenika.
	 * Vraæa String koji predstavlja korisnièko ime zaposlenika.
	 * @return korisnièko ime zaposlenika
	 */
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	/**
	 * Postavlja podatak o korisnièkom imenu zaposlenika.
	 * Kao argument prima String koji predstavlja
	 * korisnièko ime zaposlenika.
	 * @param korisnickoIme korisnièko ime zaposlenika
	 */
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	/**
	 * Dohvaæa i vraæa podatak o šifri zaposlenika.
	 * Vraæa String koji predstavlja šifru zaposlenika.
	 * @return šifra zaposlenika
	 */
	public String getSifraZaposlenika() {
		return sifraZaposlenika;
	}

	/**
	 * Postavlja podatak o šifri zaposlenika.
	 * Kao argument prima string koji predstavlja šifru zaposlenika.
	 * @param sifraZaposlenika šifra zaposlenika
	 */
	public void setSifraZaposlenika(String sifraZaposlenika) {
		this.sifraZaposlenika = sifraZaposlenika;
	}
	
}
