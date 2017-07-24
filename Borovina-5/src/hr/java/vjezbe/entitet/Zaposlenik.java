package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet osobe zaposlenika definiran 
 * korisnièkim imenom i šifrom. 
 * @author Ante
 *
 */

public class Zaposlenik extends Osoba {
	
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
