package hr.java.vjezbe.javafx;
	
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.niti.AlarmiNit;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");	
	
	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private static BorderPane root;
	private static Stage primaryStage;
	@Override
	public void start(Stage stage) {
		primaryStage = stage;
		try 
		{
		    root = (BorderPane)FXMLLoader.load(getClass().getResource("PocetniEkran.fxml"));
			Scene scene = new Scene(root,600,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					scheduler.shutdownNow();
				}
			});
		} 
		catch(Exception e) 
		{
			LOGGER.error(e.getMessage());
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
			AlarmiNit.setShow(false);
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
			AlarmiNit.setShow(false);
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
			AlarmiNit.setShow(false);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran za
	 * pretragu alarma, te svakih deset sekundi
	 * kreira novu nit za pretragu alarma pred istekom.
	 */
	public static void prikaziEkranAlarma()
	{
		try 
		{
			BorderPane alarmiPane = FXMLLoader.load(Main.class.getResource("Alarmi.fxml"));
			Main.setCenterPane(alarmiPane);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		scheduler = Executors.newScheduledThreadPool(1);
		AlarmiNit alarmiNit = new AlarmiNit();
		scheduler.scheduleAtFixedRate(alarmiNit, 0, 10, TimeUnit.SECONDS);
		
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran za 
	 * pretragu komunikacija.
	 */
	public static void prikaziEkranKomunikacija()
	{
		try 
		{
			BorderPane komunikacijePane = FXMLLoader.load(Main.class.getResource("Komunikacije.fxml"));
			Main.setCenterPane(komunikacijePane);
			AlarmiNit.setShow(false);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran 
	 * za unos poataka za novog klijenta.
	 */
	public static void prikaziEkranZaNovogKlijenta()
	{
		try 
		{
			BorderPane noviKlijentPane = FXMLLoader.load(Main.class.getResource("NoviKlijent.fxml"));
			Scene scene = new Scene(noviKlijentPane, 600, 450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			AlarmiNit.setShow(false);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran
	 * za unos podataka za novog zaposlenika.
	 */
	public static void prikaziEkranZaNovogZaposlenika()
	{
		try 
		{
			BorderPane noviZaposlenikPane = FXMLLoader.load(Main.class.getResource("NoviZaposlenik.fxml"));
			Scene scene = new Scene(noviZaposlenikPane, 600, 450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			AlarmiNit.setShow(false);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran
	 * za unos podataka za novi artikal.
	 */
	public static void prikaziEkranZaNoviArtikal()
	{
		try 
		{
			BorderPane noviArtikalPane = FXMLLoader.load(Main.class.getResource("NoviArtikal.fxml"));
			Scene scene = new Scene(noviArtikalPane, 600, 450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			AlarmiNit.setShow(false);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Uèitava fxml datoteku i prikazuje ekran za
	 * unos podataka za novu komunikaciju.
	 */
	public static void prikaziEkranZaNovuKomunikaciju()
	{
		try 
		{
			BorderPane novaKomunikacijaPane = FXMLLoader.load(Main.class.getResource("NovaKomunikacija.fxml"));
			Scene scene = new Scene(novaKomunikacijaPane, 600, 450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
