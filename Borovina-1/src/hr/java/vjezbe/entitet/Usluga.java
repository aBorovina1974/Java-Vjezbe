package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Usluga {
	
	private Klijent klijent;
	private String vrstaUsluge;
	private String opisUsluge;
	private LocalDate datumUsluge;
	private BigDecimal cijenaUsluge;
	private Boolean uslugaObavljena = false;
	private Boolean uslugaNaplacena = false;
	
	
	public Usluga(Klijent klijent, String vrstaUsluge, String opisUsluge, LocalDate datumUsluge,
			      BigDecimal cijenaUsluge, Boolean uslugaObavljena, Boolean uslugaNaplacena) {

		this.klijent = klijent;
		this.vrstaUsluge = vrstaUsluge;
		this.opisUsluge = opisUsluge;
		this.datumUsluge = datumUsluge;
		this.cijenaUsluge = cijenaUsluge;
		this.uslugaObavljena = uslugaObavljena;
		this.uslugaNaplacena = uslugaNaplacena;
	}


	public Klijent getKlijent() {
		return klijent;
	}


	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}


	public String getVrstaUsluge() {
		return vrstaUsluge;
	}


	public void setVrstaUsluge(String vrstaUsluge) {
		this.vrstaUsluge = vrstaUsluge;
	}


	public String getOpisUsluge() {
		return opisUsluge;
	}


	public void setOpisUsluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}


	public LocalDate getDatumUsluge() {
		return datumUsluge;
	}


	public void setDatumUsluge(LocalDate datumUsluge) {
		this.datumUsluge = datumUsluge;
	}


	public BigDecimal getCijenaUsluge() {
		return cijenaUsluge;
	}


	public void setCijenaUsluge(BigDecimal cijenaUsluge) {
		this.cijenaUsluge = cijenaUsluge;
	}


	public Boolean getUslugaObavljena() {
		return uslugaObavljena;
	}


	public void setUslugaObavljena(Boolean uslugaObavljena) {
		this.uslugaObavljena = uslugaObavljena;
	}


	public Boolean getUslugaNaplacena() {
		return uslugaNaplacena;
	}


	public void setUslugaNaplacena(Boolean uslugaNaplaæena) {
		this.uslugaNaplacena = uslugaNaplaæena;
	}
	
	
	
	

}
