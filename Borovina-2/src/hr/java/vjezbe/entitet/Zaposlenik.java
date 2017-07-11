package hr.java.vjezbe.entitet;

public class Zaposlenik {
	
	private String korisnickoIme;
	private String ime;
	private String prezime;
	private String sifraZaposlenika;

	
	public Zaposlenik(String korisnickoIme, String ime, String prezime, String sifraZaposlenika) {
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.sifraZaposlenika = sifraZaposlenika;

	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getSifraZaposlenika() {
		return sifraZaposlenika;
	}


	public void setSifraZaposlenika(String sifraZaposlenika) {
		this.sifraZaposlenika = sifraZaposlenika;
	}
	
}
