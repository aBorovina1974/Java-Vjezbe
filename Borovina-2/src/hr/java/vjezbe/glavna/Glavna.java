package hr.java.vjezbe.glavna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;

public class Glavna {
	    

	public static void main(String[] args) {
		
		Klijent[] klijenti = new Klijent[Tvrtka.BROJ_KLIJENATA];
		Zaposlenik[] zaposlenici = new Zaposlenik[Tvrtka.BROJ_ZAPOSLENIKA];
		
		Scanner s = new Scanner(System.in);

		System.out.println("UNOS PODATAKA:");
		
		for(int i = 0; i < Tvrtka.BROJ_KLIJENATA; i++)
		{
			System.out.println("UNESITE " + (i + 1) + "." + " KLIJENTA");
			klijenti[i] = unesiPodatkeKlijenta(s);
		}
		
		for(int i = 0; i < Tvrtka.BROJ_ZAPOSLENIKA; i++)
		{
			System.out.println("UNESITE " + (i + 1) + "." + " ZAPOSLENIKA");
			zaposlenici[i] = unesiPodatkeZaposlenika(s);
		}
		
		
		System.out.println("UNESITE POATKE O TVRTCI:");
		Tvrtka tvrtka = unesiPodatkeOTvrtci(s);
		
		s.close();
		
		tvrtka.setKlijenti(klijenti);
		tvrtka.setZaposlenici(zaposlenici);

		
		System.out.println("ISPIS PODATAKA:");
		ispisPodataka(tvrtka);
		
		
	}
	
	public static Klijent unesiPodatkeKlijenta(Scanner scanner){
		
		String oib, prezime, ime, telefon, email, datum;
		
		
		System.out.println("Unesite OIB klijenta:");
		oib = scanner.next();
		
		System.out.println("Unesite prezime klijenta:");
		prezime = scanner.next();
		
		System.out.println("Unesite ime klijenta:");
		ime = scanner.next();
		
		System.out.println("Unesite broj telefona klijenta:");
		telefon = scanner.next();
		
		System.out.println("Unesite E-mail adresu klijenta:");
		email = scanner.next();
		
		System.out.println("Datum roðenja klijenta (dd.MM.yyyy):");
		datum = scanner.next();
		
		DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate datumRodjenja = LocalDate.parse(datum, dFormatter);
		
		Klijent klijent = new Klijent(oib, prezime, ime, telefon, email, datumRodjenja);
		
		return klijent;
	}
	
	public static Zaposlenik unesiPodatkeZaposlenika(Scanner scanner){
		
		String korisnickoIme, ime, prezime, sifraZaposlenika;
		
		System.out.println("Unesite korisnièko ime zaposlenika:");
		korisnickoIme = scanner.next();
		
		System.out.println("Unesite ime zaposlenika:");
		ime = scanner.next();
		
		System.out.println("Unesite prezime zaposlenika");
		prezime = scanner.next();
		
		System.out.println("Unesite šifru zaposlenika:");
		sifraZaposlenika = scanner.next();
		
		Zaposlenik zaposlenik = new Zaposlenik(korisnickoIme, ime, prezime, sifraZaposlenika);
		return zaposlenik;
	}
	
	public static Tvrtka unesiPodatkeOTvrtci(Scanner scanner){
		
		String nazivTvrtke, oibTvrtke;
		
		System.out.println("Unesite naziv tvrtke:");
		nazivTvrtke = scanner.next();
		
		System.out.println("Unesite OIB tvrtke\n");
		oibTvrtke = scanner.next();
		
		Tvrtka tvrtka = new Tvrtka(nazivTvrtke, oibTvrtke);
		return tvrtka;
	}
	
	public static void ispisPodataka(Tvrtka tvrtka){
		
		System.out.println("Naziv Tvrtke:\n" + tvrtka.getNazivTvrtke());
		System.out.println("OIB tvrtke:\n" + tvrtka.getOibTvrtke());
		System.out.println("\n");
		
		
		Klijent[] klijenti = tvrtka.getKlijenti();
		for(int i = 0; i < klijenti.length; i++)
		{
			System.out.println("OIB klijenta:\n" + klijenti[i].getOib());
			System.out.println("Prezime klijenta:\n" + klijenti[i].getPrezime());
			System.out.println("Ime klijenta:\n" + klijenti[i].getIme());
	        System.out.println("Broj telefona Klijenta:\n" + klijenti[i].getTelefon());
	        System.out.println("E-mail adresa klijenta:\n" + klijenti[i].getEmail());
			System.out.println("Datum roðenja klijenta:\n" + klijenti[i].getDatumRodjenja().toString());
			System.out.println("\n");
		}
		
	
		Zaposlenik[] zaposlenici = tvrtka.getZaposlenici();
		for(int i = 0; i < zaposlenici.length; i++)
		{
		    System.out.println("Korisnièko ime zaposlenika:\n" + zaposlenici[i].getKorisnickoIme());
			System.out.println("Ime zaposlenika:\n" + zaposlenici[i].getIme());
			System.out.println("Prezme zaposlenika:\n" + zaposlenici[i].getPrezime());
	        System.out.println("Šifra zaposlenika:\n" + zaposlenici[i].getSifraZaposlenika());
	        System.out.println("\n");
	    }
		



	}

}
