package hr.java.vjezbe.entitet;

public enum KategorijaArtikla {

	SOFTVER, ELEKTROTEHNIKA, MEHANIKA, OSTALO;
	private Integer id;
	
	/**
	 * Dohvaæa i vraæa ID kategorije artikla.
	 * @return ID kategorije artikla
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID kategorije artikla
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID kategorije artikla
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
