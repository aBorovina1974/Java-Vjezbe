package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.ProdajaArtikla;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;

public class Glavna {
	public static final int BROJ_OSOBA = 4;
	public static final int BROJ_ARTIKALA = 3;
	public static int brUsluga;
	    

	public static void main(String[] args) {
		
		Klijent[] klijenti = new Klijent[Tvrtka.BROJ_KLIJENATA];
		Zaposlenik[] zaposlenici = new Zaposlenik[Tvrtka.BROJ_ZAPOSLENIKA];
		
		Osoba[] osobe = new Osoba[BROJ_OSOBA];
		
		Scanner s = new Scanner(System.in);

		System.out.println("UNOS PODATAKA:");
		
		
		
		for(int i = 0, k = 0, z = 0; i < osobe.length; i++)
		{
			if(i < Tvrtka.BROJ_KLIJENATA)
			{
				System.out.println("UNESITE " + (k + 1) + ". KLIJENTA");
				osobe[i] = unesiPodatkeKlijenta(s);
	            k++;
			}
			
			else if(i >= Tvrtka.BROJ_KLIJENATA & i < (Tvrtka.BROJ_KLIJENATA + Tvrtka.BROJ_ZAPOSLENIKA))
			{
				System.out.println("UNESITE " + (z + 1) + ". ZAPOSLENIKA");
				osobe[i] = unesiPodatkeZaposlenika(s);
				z++;
			}	
			
			else if(i > (Tvrtka.BROJ_KLIJENATA + Tvrtka.BROJ_ZAPOSLENIKA))
			{
				break;
			}
		}

		
		Arrays.sort(osobe,(o1, o2) -> o1.getPrezime().compareTo(o2.getPrezime()));
		
		System.out.println("UNESITE POATKE O TVRTCI:");
		MaloprodajnaTvrtka tvrtka = unesiPodatkeOTvrtci(s);
		
		
		
		System.out.println("Klijenti i zaposlenici tvrtke:");
		for(int i = 0, k = 0, z = 0; i < osobe.length; i++)
		{
			System.out.println("Prezime i ime: " + osobe[i].getPrezime() + " " + osobe[i].getIme());
			if(osobe[i] instanceof Klijent)
			{
			   System.out.println("Osoba je klijent");
			   klijenti[k] = (Klijent) osobe[i];
			   k++;
			}
			else if(osobe[i] instanceof Zaposlenik)
			{
			   System.out.println("Osoba je zaposlenik");
			   zaposlenici[z] = (Zaposlenik) osobe[i];
			   z++;
			}
		}

		tvrtka.setKlijenti(klijenti);
		tvrtka.setZaposlenici(zaposlenici);
		
		System.out.println("Unesite broj usluga koje želite izvršiti:");
        brUsluga = s.nextInt();
        s.nextLine();
        unesiUsluge(tvrtka, brUsluga, s);
		s.close();
		
		
	}
	
	public static Klijent unesiPodatkeKlijenta(Scanner scanner){
		
		String oib, prezime, ime, telefon, email, datum;
		
		
		System.out.println("Unesite OIB klijenta:");
		oib = scanner.nextLine();
		
		System.out.println("Unesite prezime klijenta:");
		prezime = scanner.nextLine();
		
		System.out.println("Unesite ime klijenta:");
		ime = scanner.nextLine();
		
		System.out.println("Unesite broj telefona klijenta:");
		telefon = scanner.nextLine();
		
		System.out.println("Unesite E-mail adresu klijenta:");
		email = scanner.nextLine();
		
		System.out.println("Datum roðenja klijenta (dd.MM.yyyy):");
		datum = scanner.nextLine();
		
		DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate datumRodjenja = LocalDate.parse(datum, dFormatter);
		
		Klijent klijent = new Klijent(oib, prezime, ime, telefon, email, datumRodjenja);
		
		return klijent;
	}
	
	public static Zaposlenik unesiPodatkeZaposlenika(Scanner scanner){
		
		String korisnickoIme, ime, prezime, sifraZaposlenika;
		
		System.out.println("Unesite korisnièko ime zaposlenika:");
		korisnickoIme = scanner.nextLine();
		
		System.out.println("Unesite ime zaposlenika:");
		ime = scanner.nextLine();
		
		System.out.println("Unesite prezime zaposlenika");
		prezime = scanner.nextLine();
		
		System.out.println("Unesite šifru zaposlenika:");
		sifraZaposlenika = scanner.nextLine();
		
		Zaposlenik zaposlenik = new Zaposlenik(korisnickoIme, ime, prezime, sifraZaposlenika);
		return zaposlenik;
	}
	
	public static MaloprodajnaTvrtka unesiPodatkeOTvrtci(Scanner scanner){
		
		String nazivTvrtke, oibTvrtke, nazivArtikla, kategorija;
		
		Artikl[] artikli = new Artikl[BROJ_ARTIKALA];
		
		System.out.println("Unesite naziv tvrtke:");
		nazivTvrtke = scanner.nextLine();
		
		System.out.println("Unesite OIB tvrtke:");
		oibTvrtke = scanner.nextLine();
		
		System.out.println("Unesite tri artikla:");
		for(int i = 0; i < BROJ_ARTIKALA; i++)
		{
			System.out.println("Unesite naziv " + (i + 1) + ". artikla:");
			nazivArtikla = scanner.nextLine();
			
			System.out.println("Unesite kategoriju " + (i + 1) + ". artikla:");
			kategorija = scanner.nextLine();
			
			artikli[i] = new Artikl(nazivArtikla, kategorija);
		}
		
		MaloprodajnaTvrtka tvrtka = new MaloprodajnaTvrtka(nazivTvrtke, oibTvrtke, artikli);
		return tvrtka;
	}
	
	
	public static void unesiUsluge(MaloprodajnaTvrtka tvrtka, int brUsluga, Scanner scanner){
		int brKlijenta, artiklBr;
		String vrstaUsluge, opisUsluge, datum;
		BigDecimal cijenausluge, ukupnaCijena = new BigDecimal(0);
        Klijent[] klijenti = tvrtka.getKlijenti();
        for(int i = 0; i < brUsluga; i++)
        {
        	System.out.println("UNESITE " + (i + 1) + ". USLUGU:\n");
        	System.out.println("ODABERITE REDNI BROJ KLIJENTA:");
        	for(int j = 0; j < Tvrtka.BROJ_KLIJENATA; j++)
    		{
    			System.out.println((j + 1) + ". KLIJENT");
    			System.out.println("OIB klijenta:\n" + klijenti[j].getOib());
    			System.out.println("Prezime klijenta:\n" + klijenti[j].getPrezime());
    			System.out.println("Ime klijenta:\n" + klijenti[j].getIme());
    	        System.out.println("Broj telefona Klijenta:\n" + klijenti[j].getTelefon());
    	        System.out.println("E-mail adresa klijenta:\n" + klijenti[j].getEmail());
    			System.out.println("Datum roðenja klijenta:\n" + klijenti[j].getDatumRodjenja().toString());
                System.out.println("\n");
            }
        	
    		System.out.println("Odabir:");
    	    brKlijenta = scanner.nextInt();
    	    scanner.nextLine();
    		System.out.println("Vrsta usluge:");
    		vrstaUsluge = scanner.nextLine();
    		System.out.println("Opis usluge:");
    		opisUsluge = scanner.nextLine();
    		System.out.println("Cijena usluge:");
    		cijenausluge = scanner.nextBigDecimal();
    		scanner.nextLine();
    		System.out.println("Datum usluge:");
    		datum = scanner.nextLine();
    		DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    		LocalDate datumUsluge = LocalDate.parse(datum, dFormatter);
    		
    		System.out.println("ODABIR ARTIKLA:");
    		for(int z = 0 ; z < tvrtka.getArtikli().length; z++)
    		{
    			System.out.println((z + 1) + ". ARTIKL:");
    			System.out.println("Naziv artikla: " + tvrtka.getArtikli()[z].getNaziv());
    			System.out.println("Kategorija artikla: " + tvrtka.getArtikli()[z].getKategorija());
    		}

    		System.out.println("Odabir:");
    		artiklBr = scanner.nextInt();
    		scanner.nextLine();
    		
    		/*Za objekte klase ProdajaArtikla nisam kreirao polje tih objekata, kako
    		 bi ih metodom setUsluge dodao u tvrtku u data member Usluga[] usluge jer sam 
    		 pretpostavio da je on namjenjen za držanje razlièitih usluga koje tvrtka
    		 nudi a koje æe biti izveene klase klase Usluga kao i klasa ProdajaArtikla
    		 pa me ispravite ako sam pogrešno shvatio */
    		ProdajaArtikla usluga = new ProdajaArtikla(klijenti[brKlijenta - 1], vrstaUsluge, opisUsluge, datumUsluge, 
    				                                   cijenausluge, false, false, tvrtka.getArtikli()[artiklBr - 1] );
    		System.out.println("Unesite broj artikala koje želite prodati:");
    		int brojArtikala = scanner.nextInt();
    		ukupnaCijena = ukupnaCijena.add(usluga.prodaja(brojArtikala));
        }

		System.out.println("Ukupna cijena prodanih artikala: " + ukupnaCijena);
        
	}
	
}
