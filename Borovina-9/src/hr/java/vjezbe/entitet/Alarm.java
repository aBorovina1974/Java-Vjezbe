package hr.java.vjezbe.entitet;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Alarm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1399811194561567219L;
	/**
	 * Predstavlja entitet alarma koji je definiran klijentom,
	 * opisom alarma, vremenom alarma i statusom alarma.
	 * 
	 * @author Ante
	 */
    private Integer id;
    private Klijent klijent;
	private String opisAlarma;
	private LocalDateTime vrijemeAlarma;
	private Boolean statusAlarma;
	
	
	/**
	 * Inicijalizira podatke o klijentu, opisu alarma, vremenu alarma 
	 * i statusu alarma.
	 * @param klijent podatak o klijentu
	 * @param opisAlarma podatak o opisu alarma
	 * @param vrijemeAlarma podatak o vremenu alarma
	 * @param statusAlarma podatak o statusu alarma
	 */
	public Alarm(Klijent klijent, String opisAlarma, LocalDateTime vrijemeAlarma, 
			     Boolean statusAlarma) {
		
		this.klijent = klijent;
		this.opisAlarma = opisAlarma;
		this.vrijemeAlarma = vrijemeAlarma;
		this.statusAlarma = statusAlarma;
	}
	
	/**
	 * Dohvaæa i vraæa ID alarma.
	 * @return ID alarma
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Postavlja podatak za ID alarma
	 * Kao argument prima referencu na objekt klase Integer
	 * koji predstavlja ID alarma
	 * @param id referenca na objekt klase Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

    /**
     * Dohvaæa i vraæa podatak o klijentu i vraæa
     * referencu na objekt klase Klijent koji prdestavlja klijenta.
     * @return referenca na objekt klase Klijent
     */
	public Klijent getKlijent() {
		return klijent;
	}

    /**
     * Postavlja podatak o klijentu.
     * Kao argument prima referncu na objekt klase Klijent
     * koji predstavlja klijenta.
     * @param klijent referenca na objekt klase Klijent
     */
	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

    /**
     * Dohvaæa i vraæa podatak o opisu alarma.
     * Vraæa String koji predstavlja opis alarma.
     * @return opis alarma
     */
	public String getOpisAlarma() {
		return opisAlarma;
	}

    /**
     * Postavljapodatak o opisu alarma. Kao argument prima String
     * koji prestavlja opis alarma.
     * @param opisAlarma opis alarma
     */
	public void setOpisAlarma(String opisAlarma) {
		this.opisAlarma = opisAlarma;
	}

    /**
     * Dohvaæa i vraæa vrijeme alarma.
     * Vraæa referencu na objekt LocalDateTime koji predstavlja
     * vrijeme alarma.
     * @return referenca na objekt klase LocalDateTime
     */
	public LocalDateTime getVrijemeAlarma() {
		return vrijemeAlarma;
	}

    /**
     * Postavljapodatak o vremenu alarma. Kao argument prima referencu
     * na objekt klase LocalDateTime koji predstavlja vrijeme alarma.
     * @param vrijemeAlarma referenca na objekt LocalDateTime
     */
	public void setVrijemeAlarma(LocalDateTime vrijemeAlarma) {
		this.vrijemeAlarma = vrijemeAlarma;
	}

    /**
     * Dohvaæa i vraæa podatak o statusu alarma.
     * Vraæa boolean vrijednost koja predstavlja status alarma.
     * @return status alarma
     */
	public boolean getStatusAlarma() {
		return statusAlarma;
	}

    /**
     * Postavlja podatak o statusu alarma. 
     * Kao argument prima boolean vrijednost koja
     * predstavlja status alarma.
     * @param statusAlarma status alarma
     */
	public void setStatusAlarma(boolean statusAlarma) {
		this.statusAlarma = statusAlarma;
	}
	
}
