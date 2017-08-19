package hr.java.vjezbe.entitet;

public enum VrstaUsluge {

	PRODAJNA, PRAVNA, OSTALO;
	private Integer id;
	
	/**
	 * Dohvaæa i vraæa ID vrste usluge.
	 * @return ID vrste usluge
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID vrste usluge
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID vrste usluge
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
