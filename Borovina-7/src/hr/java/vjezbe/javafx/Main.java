package hr.java.vjezbe.javafx;
	
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.KategorijaArtikla;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");	
	
	private static BorderPane root;
	private static Stage primaryStage;
	@Override
	public void start(Stage stage) {
		primaryStage = stage;
		try {
		    root = (BorderPane)FXMLLoader.load(getClass().getResource("PocetniEkran.fxml"));
			Scene scene = new Scene(root,600,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setCenterPane(BorderPane centerPane)
	{
		root.setCenter(centerPane);
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran za
	 * pretragu klijenata
	 */
	public static void prikaziEkranKlijenata()
	{
		try 
		{
			BorderPane klijentiPane = FXMLLoader.load(Main.class.getResource("Klijenti.fxml"));
			Main.setCenterPane(klijentiPane);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran
	 * za pretragu zaposlenika
	 */
	public static void prikaziEkranZaposlenika()
	{
		try 
		{
			BorderPane zaposleniciPane = FXMLLoader.load(Main.class.getResource("Zaposlenici.fxml"));
			Main.setCenterPane(zaposleniciPane);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran za
	 * pretragu artikala
	 */
	public static void prikaziEkranArtikala()
	{
		try 
		{
			BorderPane artikliPane = FXMLLoader.load(Main.class.getResource("Artikli.fxml"));
			Main.setCenterPane(artikliPane);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml atoteku i prikazuje ekran za
	 * pretragu alarma
	 */
	public static void prikaziEkranAlarma()
	{
		try 
		{
			root = (BorderPane)FXMLLoader.load(Main.class.getResource("PocetniEkran.fxml"));
			Scene scene = new Scene(root,600,450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Uèitava informacije o klijentima iz specificiranog file-a.
	 * Na osnovu uèitanih informacija kreira i vraæa listu objekata 
	 * klase Klijent.
	 * @return referenca na listu klijenata
	 */
	public static List<Klijent> ucitavanjeKlijenata()
	{
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
		    }
		return klijenti;
		
	}
	
	/**
	 * Uèitava informacije o zaposlenicima iz specificiranog file-a.
	 * Na osnovu uèitanih informacija kreira i vraæa listu objekata 
	 * klase Zaposlenik.
	 * @return referenca na listu zaposlenika
	 */
	public static List<Zaposlenik> ucitavanjeZaposlenika()
	{
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
			LOGGER.error("Došlo je do pogreške u èitanju datoteke" + e1.getMessage());
		    }
		return zaposlenici;
	}
	
	/**
	 * Uèitava informacije o alarmima iz specificiranog file-a.
	 * Na osnovu uèitanih informacija kreira i vraæa listu objekata 
	 * klase Alarm.
	 * @return referenca na listu alarma
	 */
	public static List<Alarm> ucitavanjeAlarma()
	{
		LOGGER.info("Zapoèelo uèitavanje alarma");
		
		List<String> params = new ArrayList<>();
		String oibKlijenta, prezimeKlijenta, imeKlijenta, telefonKlijenta, 
		emailKlijenta, opisAlarma;
		LocalDate datumRodjenjaKlijenta = null;
		LocalDateTime vrijemeAlarma = null;
		Boolean statusAlarma = null;
		
		List<Alarm> alarmi = new ArrayList<>();
		
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("dat/alarmi.txt"));
			String line = bReader.readLine();
			while(line != null)
			{
				if(!line.isEmpty())
				{
				params.add(line);
				}
				
				if(params.size() == (Alarm.class.getConstructors()[0].getParameterCount() - 1) +
				   Klijent.class.getConstructors()[0].getParameterCount())
				{
					oibKlijenta = params.get(0);
					prezimeKlijenta = params.get(1);
					imeKlijenta = params.get(2);
					telefonKlijenta = params.get(3);
					emailKlijenta = params.get(4);
					datumRodjenjaKlijenta = LocalDate.parse(params.get(5), 
							                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
					opisAlarma = params.get(6);
					vrijemeAlarma = LocalDateTime.parse(params.get(7), 
							                            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
					statusAlarma = (params.get(8).equals("AKTIVAN")) ? true : ((params.get(8) == "NEAKTIVAN") ? false : null);
							
					Klijent klijent = new Klijent(oibKlijenta, prezimeKlijenta, imeKlijenta,
							                      telefonKlijenta, emailKlijenta, datumRodjenjaKlijenta);
					
					Alarm alarm = new Alarm(klijent, opisAlarma, vrijemeAlarma, statusAlarma);
					alarmi.add(alarm);
					params.clear();
					
				}
				line = bReader.readLine();
				
			}
			bReader.close();
			} 
		    catch (IOException e1) 
		    {
			LOGGER.error("Došlo je do pogreške u èitanju datoteke" + e1.getMessage());
		    }
		return alarmi;
	}
	
	/**
	 * Uèitava podatke o tvrtci iz specificiranog file-a.
	 * Poziva metodu uèitajArtikle da dobavi referencu na
	 * listu uèitanih artikala.
	 * Kreira objekt klase MaloprodajnaTvrtka i vraæa referencu na kreirani objekt.
	 * @return referenca na maloprodajnu tvrtku.
	 */
	public static MaloprodajnaTvrtka ucitavanjeTvrtke()
	{
		LOGGER.info("Zapoèelo uèitavanje maloprodajne tvrtke");
		MaloprodajnaTvrtka tvrtka = null;
		List<Artikl> artikli = ucitavanjeArtikala();
		
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
			LOGGER.error("Došlo je do pogreške u èitanju datoteke" + e1.getMessage());
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
	public static List<Artikl> ucitavanjeArtikala()
	{
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
			LOGGER.error("Došlo je do pogreške u èitanju datoteke" + e1.getMessage());
			e1.printStackTrace();
		    }
		return artikli;
	}
}
