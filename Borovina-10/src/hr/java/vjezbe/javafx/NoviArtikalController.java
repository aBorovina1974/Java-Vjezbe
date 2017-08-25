package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.KategorijaArtikla;
import hr.java.vjezbe.entitet.Tvrtka;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NoviArtikalController {

private static final Logger Logger = LoggerFactory.getLogger(NoviKlijentController.class);
	
	@FXML
	private TextField nazivArtiklaTextField;
	
	@FXML
	private ComboBox<KategorijaArtikla> comboKategorijaArtikla;
	
	@FXML
	private Button spremiButton;
	
	@FXML
	public void initialize()
	{
		try 
		{
			comboKategorijaArtikla.getItems().addAll(BazaPodataka.dohvatiKategorijeArtikala());
		} 
		catch (SQLException e) 
		{
			Logger.error(e.getMessage());
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			Logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Sprema podatke o novom artiklu u file.
	 * Poziva metodu za osvježavanje TableView-a
	 * za prikaz artikala.
	 */
	public void spremiArtikal()
	{
		String nazivArtikla;
		KategorijaArtikla kategorijaArtikla = null;
		nazivArtikla = nazivArtiklaTextField.getText();
		kategorijaArtikla = comboKategorijaArtikla.getSelectionModel().getSelectedItem();
		
			if(nazivArtikla.isEmpty() == false && kategorijaArtikla != null)
			{
			Artikl artikl = new Artikl(nazivArtikla, kategorijaArtikla);
			Tvrtka tvrtka = null;
			try 
			{
				tvrtka = BazaPodataka.dohvatiTvrtku();
			} 
			catch (SQLException e) 
			{
				Logger.error(e.getMessage());
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				Logger.error(e.getMessage());
				e.printStackTrace();
			}
			try 
			{
				BazaPodataka.spremiArtikl(artikl, tvrtka);
			} 
			catch (SQLException e) 
			{
				Logger.error(e.getMessage());
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				Logger.error(e.getMessage());
				e.printStackTrace();
			}
			
			List<Artikl> osvjezeniArtikli = new ArrayList<>();
			try
			{
				osvjezeniArtikli = BazaPodataka.dohvatiArtikle();
			} 
			catch (SQLException e)
			{
				Logger.error(e.getMessage());
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				Logger.error(e.getMessage());
				e.printStackTrace();
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno spremanje artikla!");
			alert.setHeaderText("Uspješno spremanje artikla!");
			alert.setContentText("Uneseni podaci za artikal su uspješno spremljeni.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
			Main.prikaziEkranArtikala();
			ArtikliController.osvjeziArtikliTableView(osvjezeniArtikli);
			}
			else
			{
			StringBuilder sb = new StringBuilder();
			if(nazivArtikla.isEmpty())
			{
			sb.append("Morate unijeti naziv artikla!");
			sb.append("\n");
			}
			if(kategorijaArtikla == null)
			{
			sb.append("Morate odabrati kategoriju artikla!");
			}
			
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Neuspješno spremanje artikla");
			errorAlert.setHeaderText("Potrebno je ispraviti sljedeæe pogreške:");
			errorAlert.setContentText(sb.toString());
			errorAlert.showAndWait();
			}
			
	}
}
