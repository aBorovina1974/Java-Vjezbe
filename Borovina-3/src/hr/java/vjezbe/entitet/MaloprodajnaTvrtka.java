package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet tvrtke maloprodajna tvrtka definiran artiklima.
 * 
 * @author Ante
 *
 */

public class MaloprodajnaTvrtka extends Tvrtka {
	
	private Artikl[] artikli;

/**
 * Inicijalizira podatke o nazivu tvrtke, OIB-u tvrtke i artiklima	
 * @param nazivTvrtke podatak o nazivu tvrtke
 * @param oibTvrtke podatak o OIB-u tvrtke
 * @param artikli podatak o artiklima
 */
	public MaloprodajnaTvrtka(String nazivTvrtke, String oibTvrtke, Artikl[] artikli){
		super(nazivTvrtke, oibTvrtke);
		this.artikli = artikli;
	}

	/**
	 * Dohvaæa i vraæa podatke o artiklima.
	 * Vraæa referencu na polje artikala koje predstavlja artikle.
	 * @return referenca na polje artikala
	 */
	public Artikl[] getArtikli() {
		return artikli;
	}

	/**
	 * Postavlja podatke o artiklima. 
	 * Kao argument prima polje artikala koje predstavlja
	 * podatke o artiklima.
	 * @param artikli referenca  na polje artikala
	 */
	public void setArtikli(Artikl[] artikli) {
		this.artikli = artikli;
	}

}
