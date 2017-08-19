package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Tvrtka;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PocetniEkranController {
	
	private static Logger logger = LoggerFactory.getLogger(PocetniEkranController.class);
	private Tvrtka tvrtka;
	
	@FXML
	private Label imeTvrtke;
	
	@FXML
	public void initialize()
	{
		try 
		{
			tvrtka = BazaPodataka.dohvatiTvrtku();
		} 
		catch (SQLException e)
		{
			logger.error(e.toString());
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			logger.error(e.toString());
			e.printStackTrace();
		}
		imeTvrtke.setText(tvrtka.getNazivTvrtke());
		logger.info("Ucitana tvrtka");
	}
	
	/**
	 * Poziva metodu za prikaz alarma
	 */
	public void postaviEkranAlarma()
	{
		Main.prikaziEkranAlarma();
	}
	
	/**
	 * Poziva metodu za prikaz klijenata
	 */
	public void postaviEkranKlijenata()
	{
		Main.prikaziEkranKlijenata();
	}
	
	/**
	 * Poziva metodu za prikaz zaposlenika
	 */
	public void postaviEkranZaposlenika()
	{
		Main.prikaziEkranZaposlenika();
	}
	
	/**
	 * Poziva metodu za prikaz artikala
	 */
	public void postaviEkranArtikala()
	{
		Main.prikaziEkranArtikala();
	}
	
	/**
	 * Prikazuje ekran za unos podataka za novog klijenta.
	 */
	public void prikaziEkranZaNovogKlijenta()
	{
		try 
		{
			BorderPane noviKlijentPane = FXMLLoader.load(Main.class.getResource("NoviKlijent.fxml"));
			Scene scene = new Scene(noviKlijentPane, 600, 450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} 
		catch (IOException e) 
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje ekran za unos podataka za novog zaposlenika.
	 */
	public void prikaziEkranZaNovogZaposlenika()
	{
		try 
		{
			BorderPane noviZaposlenikPane = FXMLLoader.load(Main.class.getResource("NoviZaposlenik.fxml"));
			Scene scene = new Scene(noviZaposlenikPane, 600, 450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} 
		catch (IOException e) 
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prikazuje ekran za unos podataka za novi artikal.
	 */
	public void prikaziEkranZaNoviArtikal()
	{
		try 
		{
			BorderPane noviArtikalPane = FXMLLoader.load(Main.class.getResource("NoviArtikal.fxml"));
			Scene scene = new Scene(noviArtikalPane, 600, 450);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} 
		catch (IOException e) 
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
