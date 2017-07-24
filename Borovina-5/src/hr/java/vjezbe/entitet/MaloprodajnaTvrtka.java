package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet tvrtke maloprodajna tvrtka definiran artiklima.
 * 
 * @author Ante
 *
 */

public class MaloprodajnaTvrtka extends Tvrtka {
	
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
