package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet tvrtke maloprodajna tvrtka definiran artiklima.
 * 
 * @author Ante
 *
 */

public class MaloprodajnaTvrtka extends Tvrtka {
	
	private Integer id;
	private List<Artikl> artikli;

/**
 * Inicijalizira podatke o nazivu tvrtke, OIB-u tvrtke i artiklima	
 * @param nazivTvrtke podatak o nazivu tvrtke
 * @param oibTvrtke podatak o OIB-u tvrtke
 * @param artikli podatak o artiklima
 */
	public MaloprodajnaTvrtka(String nazivTvrtke, String oibTvrtke, List<Artikl> artikli){
		super(nazivTvrtke, oibTvrtke);
		this.artikli = artikli;
	}
	
	/**
	 * Dohvaæa i vraæa ID maloprodajne tvrtke.
	 * @return ID maloprodajne tvrtke
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID maloprodajne tvrtke
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID maloprodajne tvrtke
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Dohvaæa i vraæa podatke o artiklima.
	 * Vraæa referencu na polje artikala koje predstavlja artikle.
	 * @return referenca na polje artikala
	 */
	public List<Artikl> getArtikli() {
		return artikli;
	}

	/**
	 * Postavlja podatke o artiklima. 
	 * Kao argument prima polje artikala koje predstavlja
	 * podatke o artiklima.
	 * @param artikli referenca  na polje artikala
	 */
	public void setArtikli(List<Artikl> artikli) {
		this.artikli = artikli;
	}

}
