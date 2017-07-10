package hr.java.vjezbe.entitet;
import java.time.LocalDateTime;

public class Alarm {
	
	private Klijent klijent;
	private String opisAlarma;
	private LocalDateTime vrijemeAlarma;
	private Boolean statusAlarma;
	
	
	public Alarm(Klijent klijent, String opisAlarma, LocalDateTime vrijemeAlarma, 
			     Boolean statusAlarma) {
		
		this.klijent = klijent;
		this.opisAlarma = opisAlarma;
		this.vrijemeAlarma = vrijemeAlarma;
		this.statusAlarma = statusAlarma;
	}


	public Klijent getKlijent() {
		return klijent;
	}


	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}


	public String getOpisAlarma() {
		return opisAlarma;
	}


	public void setOpisAlarma(String opisAlarma) {
		this.opisAlarma = opisAlarma;
	}


	public LocalDateTime getVrijemeAlarma() {
		return vrijemeAlarma;
	}


	public void setVrijemeAlarma(LocalDateTime vrijemeAlarma) {
		this.vrijemeAlarma = vrijemeAlarma;
	}


	public Boolean getStatusAlarma() {
		return statusAlarma;
	}


	public void setStatusAlarma(Boolean statusAlarma) {
		this.statusAlarma = statusAlarma;
	}
	
}
