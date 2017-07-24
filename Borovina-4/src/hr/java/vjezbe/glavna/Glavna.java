package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.KategorijaArtikla;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Komunikacija;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.ProdajaArtikla;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.entitet.VrstaKomunikacije;
import hr.java.vjezbe.entitet.VrstaUsluge;
import hr.java.vjezbe.entitet.Zaposlenik;
import hr.java.vjezbe.iznimke.AlarmIstekaoException;
import hr.java.vjezbe.iznimke.AlarmPredIstekomException;
import hr.java.vjezbe.sortiranje.KomunikacijaSorter;

public class Glavna {
	public static final int BROJ_OSOBA = 4;
	public static final int BROJ_ARTIKALA = 3;
	public static int brojUsluga;
	public static int aktivniAlarmi;
	public static int zaposlenikBr;
	public static int komunikacijaBr;
	private static final  Logger LOGGER = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {
		
		List<Klijent> klijenti = new ArrayList<>();
		List<Zaposlenik> zaposlenici = new ArrayList<>();
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
				klijenti.add(k, (Klijent)osobe[i]);
				LOGGER.info("Unesen klijent " + osobe[i].getPrezime() + " " + osobe[i].getIme());
	            k++;
			}
			
			else if(i >= Tvrtka.BROJ_KLIJENATA & i < (Tvrtka.BROJ_KLIJENATA + Tvrtka.BROJ_ZAPOSLENIKA))
			{
				LOGGER.info("Zapoèet unos " + (z + 1) + "." + " ZAPOSLENIKA:");
				System.out.println("UNESITE " + (z + 1) + ". ZAPOSLENIKA");
				osobe[i] = unesiPodatkeZaposlenika(s);
				zaposlenici.add(z, (Zaposlenik)osobe[i]); 
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
		for(int i = 0; i < osobe.length; i++)
		{

			System.out.println("Prezime i ime: " + osobe[i].getPrezime() + " " + osobe[i].getIme());
			LOGGER.info("Ispis osobe " + osobe[i].getPrezime() + " " + osobe[i].getIme());
			if(osobe[i] instanceof Klijent)
			{
			   LOGGER.info("Osba je klijent");	
			   System.out.println("Osoba je klijent");
			}
			else if(osobe[i] instanceof Zaposlenik)
			{
			   LOGGER.info("Osba je zaposlenik");		
			   System.out.println("Osoba je zaposlenik");
			}
		}


        
		brojUsluga = unesiCijeliBroj("Unesite broj usluga koje želite izvršiti:", s);
		aktivniAlarmi = brojUsluga;
        unesiUsluge(tvrtka, brojUsluga, s);
		
		List<Komunikacija> komunikacije = new ArrayList<>();
		List<Alarm> alarmi = tvrtka.getAlarmi();
        
        while(true)
        {
        	try 
        	{
				Thread.sleep(10000);
			} 
        	catch (InterruptedException e1) 
        	{
        		LOGGER.info(e1.getMessage());
				e1.printStackTrace();
			}
        	for(int i = 0, brojAlarma = brojUsluga; i < brojAlarma; i++)
        	{
        		try 
        		{
				    provjeriAlarm(alarmi.get(i));
				} 
        		catch (AlarmPredIstekomException e) 
        		{
        			LOGGER.info(e.getMessage() + alarmi.get(i).getOpisAlarma(), e);
        			System.out.println("Za manje od 1 minute æe biti vrijeme alarma " + alarmi.get(i).getOpisAlarma());
        			System.out.println("Želite li obaviti komunikaciju s klijentom (DA/NE)?");
        			System.out.print("Unos >> ");
        			String odabir = s.nextLine();
        			if(odabir.equals("DA"))
        			{
        				System.out.println("Odaberite zaposlenika koji æe obaviti komunikaciju:");
        				for(int z = 0; z < zaposlenici.size(); z++)
        				{
        					System.out.println((z + 1) + " ZAPOSLENIK:");
        					System.out.println("PREZIME: " + zaposlenici.get(z).getPrezime());
        					System.out.println("IME: " + zaposlenici.get(z).getIme());
        					System.out.println("ŠIFRA: " + zaposlenici.get(z).getSifraZaposlenika());
        				}
        				zaposlenikBr = unesiCijeliBroj("Odabir >> ", s, zaposlenici.size());
        				for(int k = 0; k < VrstaKomunikacije.values().length; k++)
        				{
        					System.out.println((k + 1) + ". " + VrstaKomunikacije.values()[k]);
        				}
        				komunikacijaBr = unesiCijeliBroj("Odabir >> ", s, VrstaKomunikacije.values().length);
        				String sadrzajKomunikacije = "Povratna informacija o obavljenoj usluzi";
        				Komunikacija komunikacija = new Komunikacija(tvrtka.getUsluge().get(i).getKlijent(), 
        						                                     tvrtka.getZaposlenici().get(zaposlenikBr - 1), 
        						                                     VrstaKomunikacije.values()[komunikacijaBr - 1],
        						                                     sadrzajKomunikacije, LocalDateTime.now());
        				
        				komunikacije.add(komunikacija);
        			}

        			
				}
        		catch (AlarmIstekaoException e) 
        		{
        			LOGGER.info(e.getMessage() + alarmi.get(i).getOpisAlarma(), e);
                    System.out.println("Sljedeæi alarm je istekao: " + alarmi.get(i).getOpisAlarma());
                    aktivniAlarmi--;
				} 

        	}
        	
        	if(aktivniAlarmi == 0)
        		break;
        }
        
        tvrtka.setKomunikacije(komunikacije);
        s.close();
        
        System.out.println("Ispis svih komunikacija:");
        ispisKomunikacija(tvrtka);
        
        KomunikacijaSorter kSorter = new KomunikacijaSorter();
        long vrijemeBezLambdi, vrijemeSaLambdama;
        
        
        
        vrijemeBezLambdi = sortirajBezLambdi(tvrtka.getKomunikacije(), kSorter);
        
        vrijemeSaLambdama = sortirajSaLambdamaIStreamovima(tvrtka.getKomunikacije());
        
        System.out.println("Ispis sortiranih komunikacija");
        ispisKomunikacija(tvrtka);
        
        System.out.println("Sortiranje bez lambdi je trajalo: " + vrijemeBezLambdi + " ms");
        System.out.println("Sortiranje s lambdama i streamovima je trajalo: " + vrijemeSaLambdama + " ms");
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
	 * @return referenca na kreirani objekt klase MaloprodajnaTvrtka
	 */
	public static MaloprodajnaTvrtka unesiPodatkeOTvrtci(Scanner scanner){
		
		String nazivTvrtke, oibTvrtke, nazivArtikla;
		KategorijaArtikla kategorija;
		int artikalBr;
		List<Artikl> artikli = new ArrayList<>();
		
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
    	    for(int a = 0; a < KategorijaArtikla.values().length -1; a++)
    	    {
    	    	System.out.println((a + 1) + ". " + KategorijaArtikla.values()[a]);	
    	    }
    	    
    	    artikalBr = unesiCijeliBroj("Odabir >> ", scanner);
    	    if(artikalBr >= 1 && artikalBr < KategorijaArtikla.values().length)
    	    {
    	    	kategorija = KategorijaArtikla.values()[artikalBr -1];
    	    }
    	    else 
    	    {
				kategorija = KategorijaArtikla.OSTALO;
			}
			
			artikli.add(i, new Artikl(nazivArtikla, kategorija));
			LOGGER.info("Unešen artikl " + "'" + artikli.get(i).getNaziv() + "'");
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
		int brKlijenta, artiklBr, uslugaBr;
		String opisUsluge;
		VrstaUsluge vrstaUsluge = null;
		BigDecimal cijenausluge = new BigDecimal(0), ukupnaCijena = new BigDecimal(0);
        List<Klijent> klijenti = tvrtka.getKlijenti();
        List<ProdajaArtikla> usluge = new ArrayList<>(); 
        List<Alarm> alarmi = new ArrayList<>();
        LOGGER.info("Poèetak unošenja " + brUsluga + "usluga");
        for(int i = 0; i < brUsluga; i++)
        {
        	System.out.println("UNESITE " + (i + 1) + ". USLUGU:\n");
        	System.out.println("ODABERITE REDNI BROJ KLIJENTA:");
        	for(int j = 0; j < klijenti.size(); j++)
    		{
    			System.out.println((j + 1) + ". KLIJENT");
    			System.out.println("OIB klijenta:\n" + klijenti.get(j).getOib());
    			System.out.println("Prezime klijenta:\n" + klijenti.get(j).getPrezime());
    			System.out.println("Ime klijenta:\n" + klijenti.get(j).getIme());
    	        System.out.println("Broj telefona Klijenta:\n" + klijenti.get(j).getTelefon());
    	        System.out.println("E-mail adresa klijenta:\n" + klijenti.get(j).getEmail());
    			System.out.println("Datum roðenja klijenta:\n" + klijenti.get(j).getDatumRodjenja().toString());
                System.out.println("\n");
            }
        	
    		
    	    brKlijenta = unesiCijeliBroj("Odabir:", scanner, tvrtka.getKlijenti().size());
    	    LOGGER.info("Odabran je klijent " + klijenti.get(brKlijenta - 1).getPrezime() + " " + klijenti.get(brKlijenta - 1).getIme());
    	    
    	    System.out.println("Odaberite vrstu usluge unosom rednog broja:");
    	    for(int u = 0; u < VrstaUsluge.values().length -1; u++)
    	    {
    	    	System.out.println((u + 1) + ". " + VrstaUsluge.values()[u]);	
    	    }
    	    
    	    uslugaBr = unesiCijeliBroj("Odabir >> ", scanner);
    	    if(uslugaBr >= 1 && uslugaBr < VrstaUsluge.values().length)
    	    {
    	    	vrstaUsluge = VrstaUsluge.values()[uslugaBr -1];
    	    }
    	    else 
    	    {
				vrstaUsluge = VrstaUsluge.OSTALO;
			}	
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
    		for(int z = 0 ; z < tvrtka.getArtikli().size(); z++)
    		{
    			System.out.println((z + 1) + ". ARTIKL:");
    			System.out.println("Naziv artikla: " + tvrtka.getArtikli().get(z).getNaziv());
    			System.out.println("Kategorija artikla: " + tvrtka.getArtikli().get(z).getKategorija());
    		}

    		artiklBr = unesiCijeliBroj("Odabir:", scanner, tvrtka.getArtikli().size());
            LOGGER.info("Odabran je artikl: " + tvrtka.getArtikli().get(artiklBr - 1).getNaziv());
    		
    		ProdajaArtikla usluga = new ProdajaArtikla(klijenti.get(brKlijenta - 1), vrstaUsluge, opisUsluge, datumUsluge, 
    				                                   cijenausluge, false, false, tvrtka.getArtikli().get(artiklBr - 1));
    	    usluge.add(i, usluga);
    		
    		alarmi.add(i, kreiranjeAlarma(usluga));  
    		LOGGER.info("Kreiran alarm za uslugu");
    		
    		int brojArtikala = unesiCijeliBroj("Unesite broj artikala koje želite prodati:", scanner);
    		LOGGER.info("Unesen broj artikala za prodaju: " + brojArtikala);
    		
    		ukupnaCijena = ukupnaCijena.add(usluga.prodaja(brojArtikala));
    		
    		LOGGER.info("Ukupna cijena svih artikala: " + ukupnaCijena + " KN");
        }
        tvrtka.setUsluge(usluge);
        tvrtka.setAlarmi(alarmi);
        LOGGER.info("Postavljeni alarmi za cijelu tvrtku");
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
	
	/**
	 * Ispisuje sve komunikacije za pojedinu tvrtku.
	 * Kao argument prima referencu na objekt klase Tvrtka.
	 * @param tvrtka referenca na objekt klase tvrtka.
	 */
	public static void ispisKomunikacija(Tvrtka tvrtka)
	{
        for(int i = 0; i < tvrtka.getKomunikacije().size(); i++)
        {
        	System.out.println("Podaci za " + (i + 1) + ". komunikaciju:");
        	
        	System.out.println("Prezime i ime klijenta: " + 
        	tvrtka.getKomunikacije().get(i).getKlijent().getPrezime() + " " +
        	tvrtka.getKomunikacije().get(i).getKlijent().getIme());
        	
        	System.out.println("Prezime i ime zaposlenika: " + 
        	tvrtka.getKomunikacije().get(i).getZaposlenik().getPrezime() + " " +
        	tvrtka.getKomunikacije().get(i).getZaposlenik().getIme());
        	
        	System.out.println("Vrsta komunikacije: " +
        	tvrtka.getKomunikacije().get(i).getVrstaKomunikacije());
        	
        	System.out.println("Sadržaj komunikacije: " + 
        	tvrtka.getKomunikacije().get(i).getSadrzajKomunikacije());
        	
        	System.out.println("Vrijeme komunikacije: " +
            tvrtka.getKomunikacije().get(i).getVrijemeKomunikacije());
        }
	}
	
	/**
	 * Sortira listu pozivajuæi sort metodu Collections 
	 * utility klase. Kao argumente prima referencu na listu i
	 * referencu na Comparator objekt, odnosno na implementirajuæu klasu
	 * suèelja Comparator. Vraæa long vrijednost koja predstavlja vrijeme
	 * potrebno da se obavi sortiranje.
	 * @param komunikacije referenca na listu
	 * @param c referenca na Comparator
	 * @return vrijeme potrebno da se obavi sortiranje
	 */
	public static long sortirajBezLambdi(List<Komunikacija> komunikacije, Comparator<Komunikacija> c)
	{
        
    	long pocetakSortiranjaBezLambdi = System.currentTimeMillis();
    	Collections.sort(komunikacije, c);
    	long krajSortiranjaBezLambdi = System.currentTimeMillis();
    	return krajSortiranjaBezLambdi - pocetakSortiranjaBezLambdi;
    	
    }
	
	/**
	 * Sortira listu koristeæi lambda expression i stream.
	 * Kao argument prima referencu na listu.
	 * Vraæa long vrijednost koja predstavlja vrijeme
	 * potrebno da se obavi sortiranje.
	 * @param komunikacije referenca na listu
	 * @return vrijeme potrebno da se obavi sortiranje
	 */
	public static long sortirajSaLambdamaIStreamovima(List<Komunikacija> komunikacije)
	{
		long pocetakSortiranjaSaLambdama = System.currentTimeMillis();
		komunikacije.stream().sorted((k1, k2) -> { LocalDateTime t1 = k1.getVrijemeKomunikacije();
		                                           LocalDateTime t2 = k1.getVrijemeKomunikacije();
		                                           if(t1.compareTo(t2) != 0)
		                                           {
			                                        return t1.compareTo(t2);
		                                           }
		                                           else
		                                           {
			                                       String p1 = k1.getKlijent().getPrezime();
			                                       String p2 = k1.getKlijent().getPrezime();
			                                       return p1.compareTo(p2);	
			                                       }
		                                         });
		
		long krajSortiranjaSaLambdama = System.currentTimeMillis();
		return krajSortiranjaSaLambdama - pocetakSortiranjaSaLambdama;
	}
	


}
