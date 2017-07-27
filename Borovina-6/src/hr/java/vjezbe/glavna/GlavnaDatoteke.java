package hr.java.vjezbe.glavna;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import hr.java.vjezbe.entitet.VrstaKomunikacije;
import hr.java.vjezbe.entitet.Zaposlenik;
import hr.java.vjezbe.iznimke.AlarmIstekaoException;
import hr.java.vjezbe.iznimke.AlarmPredIstekomException;
import hr.java.vjezbe.sortiranje.KomunikacijaSorter;


public class GlavnaDatoteke {
	
	public static int brojUsluga;
	public static int aktivniAlarmi;
	public static int zaposlenikBr;
	public static int komunikacijaBr;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlavnaDatoteke.class);

	public static void main(String[] args) {
       
		List<Klijent> klijentiTvrtke = ucitajKlijente();
		List<Zaposlenik> zaposleniciTvrtke = ucitajZaposlenike();
        
		MaloprodajnaTvrtka tvrtka = ucitajTvrtku();
		
		tvrtka.setKlijenti(klijentiTvrtke);
		tvrtka.setZaposlenici(zaposleniciTvrtke);
		LOGGER.info("Postavljeni klijenti i zaposlenici za tvrtku");
		
		Scanner s = new Scanner(System.in);

		brojUsluga = Glavna.unesiCijeliBroj("Unesite broj usluga koje želite izvršiti:", s);
		aktivniAlarmi = brojUsluga;
        Glavna.unesiUsluge(tvrtka, brojUsluga, s);
                          
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
				    Glavna.provjeriAlarm(alarmi.get(i));
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

        				tvrtka.getZaposlenici().stream()
        				                       .forEach(z ->{
        				                    	            System.out.println((tvrtka.getZaposlenici().indexOf(z)
        				                    	            		           + 1) + "." + " ZAPOSLENIK"); 
        				                    	            System.out.println("PREZIME: " + z.getPrezime());
        				                    	            System.out.println("IME: " + z.getIme());
        				                    	            System.out.println("ŠIFRA: " + z.getSifraZaposlenika());
        				                                    });
        				
        				zaposlenikBr = Glavna.unesiCijeliBroj("Odabir >> ", s, tvrtka.getZaposlenici().size());
        				for(int k = 0; k < VrstaKomunikacije.values().length; k++)
        				{
        					System.out.println((k + 1) + ". " + VrstaKomunikacije.values()[k]);
        				}
        				komunikacijaBr = Glavna.unesiCijeliBroj("Odabir >> ", s, VrstaKomunikacije.values().length);
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
        Glavna.ispisKomunikacija(tvrtka);
        
        KomunikacijaSorter kSorter = new KomunikacijaSorter();
        long vrijemeBezLambdi, vrijemeSaLambdama;
        
        
        
        vrijemeBezLambdi = Glavna.sortirajBezLambdi(tvrtka.getKomunikacije(), kSorter);
        
        vrijemeSaLambdama = Glavna.sortirajSaLambdamaIStreamovima(tvrtka.getKomunikacije());
        
        System.out.println("Ispis sortiranih komunikacija");
        Glavna.ispisKomunikacija(tvrtka);
        
        System.out.println("Sortiranje bez lambdi je trajalo: " + vrijemeBezLambdi + " ms");
        System.out.println("Sortiranje s lambdama i streamovima je trajalo: " + vrijemeSaLambdama + " ms");
        
        serijaliziraj(tvrtka.getKomunikacije(), "komunikacije.dat");
        serijaliziraj(tvrtka.getUsluge(), "usluge.dat");
        serijaliziraj(tvrtka.getAlarmi(), "alarmi.dat");
        
	}
	
	/**
	 * Uèitava informacije o klijentima iz specificiranog file-a.
	 * Na osnovu uèitanih informacija kreira i vraæa listu objekata 
	 * klase Klijent.
	 * @return referenca na listu klijenata
	 */
	public static List<Klijent> ucitajKlijente()
	{
		System.out.println("Uèitavanje klijenata...");
		LOGGER.info("Zapoèelo uèitavanje klijenata");
		
		List<String> params = new ArrayList<>();
		String oib, prezime, ime, telefon, email;
		LocalDate datumRodjenja = null;
		List<Klijent> klijenti = new ArrayList<>();
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("dat/klijenti.txt"));
			String line = bReader.readLine();
			while(line != null)
			{
				if(!line.isEmpty())
				{
				params.add(line);
				}

				if(params.size() == Klijent.class.getConstructors()[0].getParameterCount())
				{
					oib = params.get(0);
					prezime = params.get(1);
					ime = params.get(2);
					telefon = params.get(3);
					email = params.get(4);
					datumRodjenja = LocalDate.parse(params.get(5), 
							                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
					
					Klijent klijent = new Klijent(oib, prezime, ime, telefon, email, datumRodjenja);
                    klijenti.add(klijent);
                    params.clear();
				}
				line = bReader.readLine();
			}
			bReader.close();
			} 
		    catch (IOException e1) 
		    {
			LOGGER.error("Došlo je do pogreške u èitanju datoteke");
			System.out.println("Došlo je do pogreške u èitanju datoteke");
			e1.printStackTrace();
		    }
		return klijenti;
		
	}
	
	/**
	 * Uèitava informacije o zaposlenicima iz specificiranog file-a.
	 * Na osnovu uèitanih informacija kreira i vraæa listu objekata 
	 * klase Zaposlenik.
	 * @return referenca na listu zaposlenika
	 */
	public static List<Zaposlenik> ucitajZaposlenike()
	{
		System.out.println("Uèitavanje zaposlenika...");
		LOGGER.info("Zapoèelo uèitavanje zaposlenika");
		
		List<String> params = new ArrayList<>();
		String korisnickoIme, ime, prezime, sifraZaposlenika;
		List<Zaposlenik> zaposlenici = new ArrayList<>();
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("dat/zaposlenici.txt"));
			String line = bReader.readLine();
			while(line != null)
			{
				if(!line.isEmpty())
				{
				params.add(line);
				}
				
				if(params.size() == Zaposlenik.class.getConstructors()[0].getParameterCount())
				{
					korisnickoIme = params.get(0);
					ime = params.get(1);
					prezime = params.get(2);
					sifraZaposlenika = params.get(3);
					
					Zaposlenik zaposlenik = new Zaposlenik(korisnickoIme, ime, prezime, sifraZaposlenika);
                    zaposlenici.add(zaposlenik);
                    params.clear();
				}
				line = bReader.readLine();
				
			}
			bReader.close();
			} 
		    catch (IOException e1) 
		    {
			LOGGER.error("Došlo je do pogreške u èitanju datoteke");
			System.out.println("Došlo je do pogreške u èitanju datoteke");
			e1.printStackTrace();
		    }
		return zaposlenici;
	}
	
	/**
	 * Uèitava podatke o tvrtci iz specificiranog file-a.
	 * Poziva metodu uèitajArtikle da dobavi referencu na
	 * listu uèitanih artikala.
	 * Kreira objekt klase MaloprodajnaTvrtka i vraæa referencu na kreirani objekt.
	 * @return referenca na maloprodajnu tvrtku.
	 */
	public static MaloprodajnaTvrtka ucitajTvrtku()
	{
		System.out.println("Uèitavanje maloprodajne tvrtke...");
		LOGGER.info("Zapoèelo uèitavanje maloprodajne tvrtke");
		MaloprodajnaTvrtka tvrtka = null;
		List<Artikl> artikli = ucitajArtikle();
		
		List<String> params = new ArrayList<>();
		String nazivTvrtke, oibTvrtke;
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("dat/tvrtka.txt"));
			String line = bReader.readLine();
			while(line != null)
			{
				if(!line.isEmpty())
				{
				params.add(line);
				}
				
				line = bReader.readLine();
				
			}
			bReader.close();
			nazivTvrtke = params.get(0);
			oibTvrtke = params.get(1);
			tvrtka = new MaloprodajnaTvrtka(nazivTvrtke, oibTvrtke, artikli);
			} 
		    catch (IOException e1) 
		    {
			LOGGER.error("Došlo je do pogreške u èitanju datoteke");
			System.out.println("Došlo je do pogreške u èitanju datoteke");
			e1.printStackTrace();
		    }
		return tvrtka;
	}
	
	/**
	 * Uèitava informacije o artiklima iz specificiranog file-a.
	 * Na osnovu uèitanih informacija kreira i vraæa listu objekata 
	 * klase Artikl.
	 * @return referenca na listu artikala
	 */
	public static List<Artikl> ucitajArtikle()
	{
		System.out.println("Uèitavanje artikala...");
		LOGGER.info("Zapoèelo uèitavanje artikala");
		
		List<String> params = new ArrayList<>();
		String nazivArtikla, kategorija;
		KategorijaArtikla kategorijaArtikla;
		List<Artikl> artikli = new ArrayList<>();
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("dat/artikli.txt"));
			String line = bReader.readLine();
			while(line != null)
			{
				if(!line.isEmpty())
				{
				params.add(line);
				}
				
				if(params.size() == Artikl.class.getConstructors()[0].getParameterCount())
				{
					nazivArtikla = params.get(0);
					kategorija = params.get(1);
					
					switch (kategorija) 
					{
					case "SOFTVER":
						kategorijaArtikla = KategorijaArtikla.SOFTVER;
						break;

					case "ELEKTROTEHNIKA":
						kategorijaArtikla = KategorijaArtikla.ELEKTROTEHNIKA;
						break;
						
					case "MEHANIKA":
						kategorijaArtikla = KategorijaArtikla.MEHANIKA;
						break;
					
					default:
						kategorijaArtikla = KategorijaArtikla.OSTALO;
					}
					
					Artikl artikl = new Artikl(nazivArtikla, kategorijaArtikla);
					artikli.add(artikl);
					params.clear();
					
				}
				line = bReader.readLine();
				
			}
			bReader.close();
			} 
		    catch (IOException e1) 
		    {
			LOGGER.error("Došlo je do pogreške u èitanju datoteke");
			System.out.println("Došlo je do pogreške u èitanju datoteke");
			e1.printStackTrace();
		    }
		return artikli;
	}
	
	/**
	 * Serijalizira objekte iz liste bilo kojeg type parametra.
	 * Kao argumente prima referencu na listu i string koji prestavlja
	 * ime file-a u koji se objekti serializiraju. 
	 * @param list referenca na listu
	 * @param fileName ime file-a
	 */
	public static <T> void serijaliziraj(List<T> list, String fileName)
	{
		
	    if(list != null && list.size() != 0)
	    {	
		T o = list.get(0);
		o.getClass().getTypeName();
		
		LOGGER.info("Zapoèeta serijalizacija objekata klase " + list.get(0).getClass().getTypeName());
		System.out.println("Zapoèeta serijalizacija objekata klase " + list.get(0).getClass().getTypeName());
		try {
				FileOutputStream fos = new FileOutputStream(fileName);
				try 
				{
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					for(T o1 : list)
					{
						oos.writeObject(o1);
					}
					oos.close();
				} 
				catch (IOException e) 
				{
                    LOGGER.error(e.getMessage());
                }
				
		    } 
		    catch (FileNotFoundException e) 
		    {
		    	LOGGER.error(e.getMessage());
		    }
		
		LOGGER.info("Završena serijalizacija objekata klase " + list.get(0).getClass().getTypeName());
		System.out.println("Završena serijalizacija objekata klase " + list.get(0).getClass().getTypeName());
		}
	}
	
}
