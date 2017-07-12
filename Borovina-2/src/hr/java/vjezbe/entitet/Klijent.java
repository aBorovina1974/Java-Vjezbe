package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Klijent extends Osoba {

	private String oib;
    private String telefon;
	private String email;
	private LocalDate datumRodjenja;
	
	public Klijent(String oib, String prezime, String ime, String telefon, String email,
			       LocalDate datumRodjenja) {
		super(ime, prezime);
		this.oib = oib;
		this.telefon = telefon;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
}
