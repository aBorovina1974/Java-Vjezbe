package hr.java.vjezbe.entitet;

public enum VrstaKomunikacije {

	VERBALNA, PISMENA, ELEKTRONICKA, OSTALO;
	private Integer id;
	
	/**
	 * Dohvaæa i vraæa ID vrste komunikacije.
	 * @return ID vrste komunikacije
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID vrste komunikacije
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID vrste komunikacije
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
