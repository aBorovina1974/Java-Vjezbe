package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Klijent {

	private String oib;
	private String prezime;
	private String ime;
	private String telefon;
	private String email;
	private LocalDate datumRodjenja;
	
	public Klijent(String oib, String prezime, String ime, String telefon, String email,
			       LocalDate datumRodjenja) {
		this.oib = oib;
		this.prezime = prezime;
		this.ime = ime;
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

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
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
