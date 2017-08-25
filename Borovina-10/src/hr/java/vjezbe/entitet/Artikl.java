package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Predstavlja entitet artikla koji je definiram nazivom artikla
 * i kategorijom artikla.
 * @author Ante
 *
 */
public class Artikl implements Serializable{

    private Integer id;
	private static final long serialVersionUID = -7267440308513123716L;
	private String naziv;
	private KategorijaArtikla kategorija;
/**
 * Inicijalizira podatak o nazivu artikla i kategoriji artikla.	
 * @param naziv
 * @param kategorija
 */
	public Artikl(String naziv, KategorijaArtikla kategorija) {
		this.naziv = naziv;
		this.kategorija = kategorija;
	}
	
	/**
	 * Dohvaæa i vraæa ID artikla.
	 * @return ID artikla
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID artikla
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID artikla
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
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
	public KategorijaArtikla getKategorija() {
		return kategorija;
	}

	/**
	 * Postavlja podatak o kategoriji artikla. Kao argument
	 * prima String koji predstavlja kategoriju artikla.
	 * @param kategorija kategorija artikla
	 */
	public void setKategorija(KategorijaArtikla kategorija) {
		this.kategorija = kategorija;
	}
	
	
	
	
}
