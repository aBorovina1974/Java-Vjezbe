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
		try 
		{
		    root = (BorderPane)FXMLLoader.load(getClass().getResource("PocetniEkran.fxml"));
			Scene scene = new Scene(root,600,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
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
			BorderPane alarmiPane = FXMLLoader.load(Main.class.getResource("Alarmi.fxml"));
			Main.setCenterPane(alarmiPane);
		} 
		catch (IOException e) 
		{
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
