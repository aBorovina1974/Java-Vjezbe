package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.ProdajaArtikla;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.entitet.Zaposlenik;
import hr.java.vjezbe.iznimke.AlarmIstekaoException;
import hr.java.vjezbe.iznimke.AlarmPredIstekomException;

public class Glavna {
	public static final int BROJ_OSOBA = 4;
	public static final int BROJ_ARTIKALA = 3;
	public static int brojUsluga;
	public static int aktivniAlarmi;
	private static final  Logger LOGGER = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {
		
		Klijent[] klijenti = new Klijent[Tvrtka.BROJ_KLIJENATA];
		Zaposlenik[] zaposlenici = new Zaposlenik[Tvrtka.BROJ_ZAPOSLENIKA];
		Osoba[] osobe = new Osoba[BROJ_OSOBA];
		Scanner s = new Scanner(System.in);
		System.out.println("UNOS PODATAKA:");
		LOGGER.info("Zapoèet unos podataka");
		
		
		for(int i = 0, k = 0, z = 0; i < osobe.length; i++)
		{
			
			if(i < Tvrtka.BROJ_KLIJENATA)
			{
				LOGGER.info("Zapoèet unos " + (k + 1) + "." + " KLIJENTA:");
				System.out.println("UNESITE " + (k + 1) + ". KLIJENTA");
				osobe[i] = unesiPodatkeKlijenta(s);
				LOGGER.info("Unesen klijent " + osobe[i].getPrezime() + " " + osobe[i].getIme());
	            k++;
			}
			
			else if(i >= Tvrtka.BROJ_KLIJENATA & i < (Tvrtka.BROJ_KLIJENATA + Tvrtka.BROJ_ZAPOSLENIKA))
			{
				LOGGER.info("Zapoèet unos " + (z + 1) + "." + " ZAPOSLENIKA:");
				System.out.println("UNESITE " + (z + 1) + ". ZAPOSLENIKA");
				osobe[i] = unesiPodatkeZaposlenika(s);
				LOGGER.info("Unesen zaposlenik " + osobe[z].getPrezime() + " " + osobe[z].getIme());
				z++;
			}	
			
			else if(i > (Tvrtka.BROJ_KLIJENATA + Tvrtka.BROJ_ZAPOSLENIKA))
			{
				break;
			}
		}

		
		Arrays.sort(osobe,(o1, o2) -> o1.getPrezime().compareTo(o2.getPrezime()));
		
		System.out.println("UNESITE POATKE O TVRTCI:");
		LOGGER.info("Zapoèet unos tvrtke");
		MaloprodajnaTvrtka tvrtka = unesiPodatkeOTvrtci(s);
		LOGGER.info("Unešena tvrtka " + "'" + tvrtka.getNazivTvrtke() + "'");
		
		tvrtka.setKlijenti(klijenti);
		tvrtka.setZaposlenici(zaposlenici);
		LOGGER.info("Postavljeni klijenti i zaposlenici za tvrtku");
		
		System.out.println("Klijenti i zaposlenici tvrtke:");
		LOGGER.info("Ispis klijenata i zaposlenika tvrtke");
		for(int i = 0, k = 0, z = 0; i < osobe.length; i++)
		{

			System.out.println("Prezime i ime: " + osobe[i].getPrezime() + " " + osobe[i].getIme());
			LOGGER.info("Ispis osobe " + osobe[i].getPrezime() + " " + osobe[i].getIme());
			if(osobe[i] instanceof Klijent)
			{
			   LOGGER.info("Osba je klijent");	
			   System.out.println("Osoba je klijent");
			   klijenti[k] = (Klijent) osobe[i];
			   k++;
			}
			else if(osobe[i] instanceof Zaposlenik)
			{
			   LOGGER.info("Osba je zaposlenik");		
			   System.out.println("Osoba je zaposlenik");
			   zaposlenici[z] = (Zaposlenik) osobe[i];
			   z++;
			}
		}


        
		brojUsluga = unesiCijeliBroj("Unesite broj usluga koje želite izvršiti:", s);
		aktivniAlarmi = brojUsluga;
        unesiUsluge(tvrtka, brojUsluga, s);
		s.close();
		
		Alarm[] alarmi = tvrtka.getAlarmi();

        while(true)
        {
        	try 
        	{
				Thread.sleep(10000);
			} 
        	catch (InterruptedException e1) 
        	{
				e1.printStackTrace();
			}
        	for(int i = 0, brojAlarma = brojUsluga; i < brojAlarma; i++)
        	{
        		try 
        		{
				    provjeriAlarm(alarmi[i]);
				} 
        		catch (AlarmPredIstekomException e) 
        		{
        			LOGGER.info(e.getMessage() + alarmi[i].getOpisAlarma(), e);
        			System.out.println("Za manje od 1 minute æe biti vrijeme alarma " + alarmi[i].getOpisAlarma());
				}
        		catch (AlarmIstekaoException e) 
        		{
        			LOGGER.info(e.getMessage() + alarmi[i].getOpisAlarma(), e);
                    System.out.println("Sljedeæi alarm je istekao: " + alarmi[i].getOpisAlarma());
                    aktivniAlarmi--;
				} 

        	}
        	
        	if(aktivniAlarmi == 0)
        		break;
        }

	}
	
	/**
	 * Traži od korisnika unos podataka o klijentu, kreira objekt
	 * klase Klijent inicijaliziran unesenim podacima, te vraæa
	 * referencu na kreirani objekt.
	 * Kao argument prima referencu na objekt klase Scanner.
	 * @param scanner referenca na objekt klase Scanner
	 * @return referenca na kreirani objekt klase Klijent
	 */
	public static Klijent unesiPodatkeKlijenta(Scanner scanner){
		
		String oib, prezime, ime, telefon, email;
		
		
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
		
		LocalDate datumRodjenja = unesiDatum("Datum roðenja klijenta(dd.MM.yyyy):", scanner);
		
		Klijent klijent = new Klijent(oib, prezime, ime, telefon, email, datumRodjenja);
		
		return klijent;
	}
	
	/**
	 * Traži od korisnika unos podataka o zaposleniku, kreira objekt
	 * klase Zaposlenik inicijaliziran unesenim podacima, te vraæa
	 * referencu na kreirani objekt.
	 * Kao argument prima referencu na objekt klase Scanner.
	 * @param scanner referenca na objekt klase Scanner
	 * @return referenca na kreirani objekt klase Zaposlenik
	 */
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
	
	/**
	 * Traži od korisnika unos podataka o tvrtci, kreira objekt
	 * klase Tvrtka inicijaliziran unesenim podacima, te vraæa 
	 * referencu na kreirani objekt.
	 * Kao argument prima referencu na objekt klase Scanner.
	 * @param scanner referenca na objekt klase Scanner
	 * @return referenca na kreirani objekt klase Tvrtka
	 */
	public static MaloprodajnaTvrtka unesiPodatkeOTvrtci(Scanner scanner){
		
		String nazivTvrtke, oibTvrtke, nazivArtikla, kategorija;
		
		Artikl[] artikli = new Artikl[BROJ_ARTIKALA];
		
		System.out.println("Unesite naziv tvrtke:");
		nazivTvrtke = scanner.nextLine();
		LOGGER.info("Unešen naziv tvrtke");
		
		System.out.println("Unesite OIB tvrtke:");
		oibTvrtke = scanner.nextLine();
		LOGGER.info("Unešen OIB tvrtke");
		
		System.out.println("Unesite tri artikla:");
		for(int i = 0; i < BROJ_ARTIKALA; i++)
		{
			System.out.println("Unesite naziv " + (i + 1) + ". artikla:");
			nazivArtikla = scanner.nextLine();
			
			System.out.println("Unesite kategoriju " + (i + 1) + ". artikla:");
			kategorija = scanner.nextLine();
			
			artikli[i] = new Artikl(nazivArtikla, kategorija);
			LOGGER.info("Unešen artikl " + "'" + artikli[i].getNaziv() + "'");
		}
		
		MaloprodajnaTvrtka tvrtka = new MaloprodajnaTvrtka(nazivTvrtke, oibTvrtke, artikli);
		return tvrtka;
	}
	
	/**
	 * Kao argumente prima referencu na objekt klase MaloprodajnaTvrtka, 
	 * broj usluga, te referencu na objekt klase Scanner. Na osnovu proslijeðenog
	 * broja usluga traži od korisnika unos podataka za pojedinu uslugu, te kreira 
	 * objekte tih usluga inicijalizirane proslijeðenim podacima. 
	 * Za svaku kreiranu uslugu kreira jedan objekt klase Alarm, sa inicijaliziranim
	 * klijentom dohvaæenim iz usluge, te opisom i vremenom alarma. Na kraju 
	 * kreirane alarme dodaje u tvrtku.
	 * @param tvrtka referenca na objekt klase MaloprodajnaTvrtka
	 * @param brUsluga podatak o broju usluga
	 * @param scanner referenca na objekt klase Scanner
	 */
	public static void unesiUsluge(MaloprodajnaTvrtka tvrtka, int brUsluga, Scanner scanner){
		int brKlijenta, artiklBr;
		String vrstaUsluge, opisUsluge;
		BigDecimal cijenausluge = new BigDecimal(0), ukupnaCijena = new BigDecimal(0);
        Klijent[] klijenti = tvrtka.getKlijenti();
        Alarm[] alarmi = new Alarm[Tvrtka.BROJ_ALARMA];
        LOGGER.info("Poèetak unošenja " + brUsluga + "usluga");
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
        	
    		
    	    brKlijenta = unesiCijeliBroj("Odabir:", scanner,Tvrtka.BROJ_KLIJENATA);
    	    LOGGER.info("Odabran je klijent " + klijenti[brKlijenta - 1].getPrezime() + " " + klijenti[brKlijenta - 1].getIme());
    	    
    	    System.out.println("Vrsta usluge:");
    		vrstaUsluge = scanner.nextLine();
    		LOGGER.info("Odabrana vrsta usluge: " + vrstaUsluge);
    		
    		System.out.println("Opis usluge:");
    		opisUsluge = scanner.nextLine();
    		LOGGER.info("Odabran opis usluge: " + opisUsluge);
    		
    		boolean nastaviPetlju = false;
    		do
    		{
    			try
    			{
    				System.out.println("Cijena usluge:");
    				cijenausluge = scanner.nextBigDecimal();
    				nastaviPetlju = false;
    			}
    			catch(InputMismatchException e)
    			{
    				System.out.println("Pogrešan unos! Morate unijeti brojèanu vrijednost.");
    				scanner.nextLine();
    				nastaviPetlju = true;
    			}
    			LOGGER.info("Odabrana cijena usluge " + cijenausluge + " KN");
    		}
    		while(nastaviPetlju);
            scanner.nextLine();
            
    		LocalDate datumUsluge = unesiDatum("Datum usluge(dd.MM.yyyy)", scanner);
    		
    		System.out.println("ODABIR ARTIKLA:");
    		for(int z = 0 ; z < tvrtka.getArtikli().length; z++)
    		{
    			System.out.println((z + 1) + ". ARTIKL:");
    			System.out.println("Naziv artikla: " + tvrtka.getArtikli()[z].getNaziv());
    			System.out.println("Kategorija artikla: " + tvrtka.getArtikli()[z].getKategorija());
    		}

    		artiklBr = unesiCijeliBroj("Odabir:", scanner, BROJ_ARTIKALA);
            LOGGER.info("Odabran je artikl: " + tvrtka.getArtikli()[artiklBr - 1].getNaziv());
    		
    		ProdajaArtikla usluga = new ProdajaArtikla(klijenti[brKlijenta - 1], vrstaUsluge, opisUsluge, datumUsluge, 
    				                                   cijenausluge, false, false, tvrtka.getArtikli()[artiklBr - 1] );
    		
    		alarmi[i] = kreiranjeAlarma(usluga);
    		LOGGER.info("Kreiran alarm za uslugu");
    		
    		int brojArtikala = unesiCijeliBroj("Unesite broj artikala koje želite prodati:", scanner);
    		LOGGER.info("Unesen broj artikala za prodaju: " + brojArtikala);
    		
    		ukupnaCijena = ukupnaCijena.add(usluga.prodaja(brojArtikala));
    		LOGGER.info("Ukupna cijena svih artikala: " + ukupnaCijena + " KN");
        }
        
        tvrtka.setAlarmi(alarmi);
        LOGGER.info("POstavljeni alarmi Zaposlenik cijelu tvrtku");
        System.out.println("Ukupna cijena prodanih artikala: " + ukupnaCijena);
        
	}
	
	/**
	 * Kreira objekt klase Alarm, te vraæa referencu na kreirani objekt 
	 * inicijaliziran podacima iz proslijeðene usluge. 
	 * @param usluga referenca na objekt klase Usluga
	 * @return referenca na kreirani objekt klase Alarm
	 */
	public static Alarm kreiranjeAlarma(Usluga usluga)
	{
		Klijent klijent = usluga.getKlijent();
		String opisAlarma = "Povratna informacija za obavljenu uslugu:\n " + usluga.getOpisUsluge();
		LocalDateTime vrijemeAlarma = LocalDateTime.now().plusMinutes(1);
		Boolean statusAlarma = true;
		Alarm alarm = new Alarm(klijent, opisAlarma, vrijemeAlarma, statusAlarma);
		return alarm;
	}
	
	
    /**
     * Provjerava da li je vrijeme alarma isteklo. Kao argument
     * prima referencu na objekt klase Alarm.
     * @param alarm referenca na objekt klase Alarm
     */
	public static void provjeriAlarm(Alarm alarm) throws AlarmPredIstekomException
	{
		
		if(alarm != null)
		{
			if(alarm.getVrijemeAlarma().isAfter(LocalDateTime.now()))
			{
				throw new  AlarmPredIstekomException();
			}
			
			else if(alarm.getVrijemeAlarma().equals(LocalDateTime.now()) ||
					alarm.getVrijemeAlarma().isBefore(LocalDateTime.now()))
			{
				if(alarm.getStatusAlarma() == true)
				{
				alarm.setStatusAlarma(false);
				throw new AlarmIstekaoException();
				}
			}
			
		}
		
	}
	
	/**
	 * Traži od korisnika unos cijelog broja s moguænošæu specificiranja 
	 * maksimalne vrijednosti.Provjerava da li je unesena vrijednost cijeli broj,
	 * i ako je proslijeðena maksimalna vrijednost, da li ta vrijednost prelazi 
	 * postavljenu maksimalnu vrijednost. Ako unesena vrijednost nije cijeli broj
	 * hvata iznimku InputMismatchException i traži od korisnika ponovni ispravni unos.
	 * Ako unesena vrijednost prelazi maksimalnu vrijednost traži od korisnika
	 * ponovni ispravni unos. Kao argumente prima text u vezi kojega se unos 
	 * cijelobrojne vrijednosti traži, referencu na objekt klase Scanner, te
	 * kao opcionalan argument maksimalnu moguæu vrijednost.
	 * @param text tekst u vezi kojega se unos cijelobrojne vrijednosti traži
	 * @param scanner referenca na objekt klase Scanner
	 * @param maxValue maksimalna vrijednost(opcionalno)
	 * @return ispravna vrijednost
	 */
	public static int unesiCijeliBroj(String text, Scanner scanner, int...maxValue)
	{
		int value = 0;
		boolean nastaviPetlju = false;
		do
		{
			try
			{
				System.out.println(text);
		        value = scanner.nextInt();	
		        if(maxValue.length > 0)
		        {
		        	if(value > maxValue[0])
		        	{
		        		LOGGER.info("Unesena vrijednost izvan dozvoljenog raspona");
			        	System.out.println("Pogrešan unos! Odabrana vrijednost ne postoji.");
			        	scanner.nextLine();
			        	nastaviPetlju = true;
		        	}
		        	else if(value <= maxValue[0])
		        	{		        		
		        		nastaviPetlju = false;
		        		scanner.nextLine();
		        	}

		        }
		        else if(maxValue.length == 0)
		        {
		        	nastaviPetlju = false;
		        	scanner.nextLine();
		        }
		       
			}
			catch(InputMismatchException e)
			{
				LOGGER.info(e.getMessage(), e);
				System.out.println("Pogrešan unos! Morate unijeti cijelobrojnu vrijednost.");
				scanner.nextLine();
				nastaviPetlju = true;
			}

		}
		while(nastaviPetlju);
		return value;
	}
	

	/**
	 * Traži od korisnika unos datuma specificiranog formata. Provjerava da li 
	 * je uneseni datum ispravnog formata, te ako je vraæa taj datum. Ako uneseni datum
	 * nije ispravnog fomata hvata iznimku DateTimeParseException i traži od
	 * korisnika ponovni, ispravni unos. Kao argumente prima text
	 * u vezi kojega se unos datuma traži, te referencu na objekt klase Scanner.
	 * @param text tekst u vezi kojega se unos datuma traži
	 * @param scanner referenca na objekt klase Scanner 
	 * @return referenca na vraæeni datum klase LocalDate
	 */
	public static LocalDate unesiDatum(String text, Scanner scanner)
	{
		LocalDate date = null;
		String parseString = null;
		boolean nastaviPetlju = false;
		do
		{
			try
			{
	    		System.out.println(text);
	    		parseString = scanner.nextLine();
	    		DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	    		date = LocalDate.parse(parseString, dFormatter);
	    		nastaviPetlju = false;
	    		
			}
			catch(DateTimeParseException e)
			{
				LOGGER.info("Unesen neispravan format datuma: " + parseString + e.getMessage(), e);
				System.out.println("Pogrešan unos! Morate unijeti ispravan format(dd.MM.yyyy)");
				nastaviPetlju = true;
			}
		}
		while(nastaviPetlju);
		return date;
	}
	
	
}
