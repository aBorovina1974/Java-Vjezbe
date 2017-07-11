package hr.java.vjezbe.entitet;


public class Tvrtka {
	
	public static final int BROJ_KLIJENATA = 2;
	public static final int BROJ_ZAPOSLENIKA = 2;
	public static final int BROJ_KOMUNIKACIJA = 2;
	public static final int BROJ_USLUGA = 2;
	public static final int BROJ_ALARMA = 2;
	
	private String nazivTvrtke;
	private String oibTvrtke;
	private Klijent[] klijenti = new Klijent[BROJ_KLIJENATA];
	private Zaposlenik[] zaposlenici = new Zaposlenik[BROJ_ZAPOSLENIKA];
	private Komunikacija[] komunikacije = new Komunikacija[BROJ_KOMUNIKACIJA];
	private Usluga[] usluge = new Usluga[BROJ_USLUGA];
	private Alarm[] alarmi = new Alarm[BROJ_ALARMA];
	
	
	public Tvrtka(String nazivTvrtke, String oibTvrtke) {
		this.nazivTvrtke = nazivTvrtke;
		this.oibTvrtke = oibTvrtke;
	}


	public String getNazivTvrtke() {
		return nazivTvrtke;
	}


	public void setNazivTvrtke(String nazivTvrtke) {
		this.nazivTvrtke = nazivTvrtke;
	}


	public String getOibTvrtke() {
		return oibTvrtke;
	}


	public void setOibTvrtke(String oibTvrtke) {
		this.oibTvrtke = oibTvrtke;
	}


	public Klijent[] getKlijenti() {
		return klijenti;
	}


	public void setKlijenti(Klijent[] klijenti) {
		this.klijenti = klijenti;
	}


	public Zaposlenik[] getZaposlenici() {
		return zaposlenici;
	}


	public void setZaposlenici(Zaposlenik[] zaposlenici) {
		this.zaposlenici = zaposlenici;
	}


	public Komunikacija[] getKomunikacije() {
		return komunikacije;
	}


	public void setKomunikacije(Komunikacija[] komunikacije) {
		this.komunikacije = komunikacije;
	}


	public Usluga[] getUsluge() {
		return usluge;
	}


	public void setUsluge(Usluga[] usluge) {
		this.usluge = usluge;
	}


	public Alarm[] getAlarmi() {
		return alarmi;
	}


	public void setAlarmi(Alarm[] alarmi) {
		this.alarmi = alarmi;
	}

}
