package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet artikla koji je definiram nazivom artikla
 * i kategorijom artikla.
 * @author Ante
 *
 */
public class Artikl {

	private String naziv;
	private String kategorija;
/**
 * Inicijalizira podatak o nazivu artikla i kategoriji artikla.	
 * @param naziv
 * @param kategorija
 */
	public Artikl(String naziv, String kategorija) {
		this.naziv = naziv;
		this.kategorija = kategorija;
	}

	/**
	 * Dohvaæa i vraæa podatak o nazivu artikla.
	 * Vraæa String koji predstavlja naziv artikla.
	 * @return naziv artikla
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Postavlja podatak o nazivu artikla. Kao argument
	 * prima String koji predstavlja naziv artikla.
	 * @param naziv naziv artikla
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Dohvaæa i vraæa podatak o kategoriji artikla.
	 * Vraæa String koji predstavlja kategoriju artikla.
	 * @return kategorija artikla
	 */
	public String getKategorija() {
		return kategorija;
	}

	/**
	 * Postavlja podatak o kategoriji artikla. Kao argument
	 * prima String koji predstavlja kategoriju artikla.
	 * @param kategorija kategorija artikla
	 */
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
	
	
	
	
}
