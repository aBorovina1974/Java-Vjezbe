package hr.java.vjezbe.javafx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.entitet.Artikl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NoviArtikalController {

private static final Logger Logger = LoggerFactory.getLogger(NoviKlijentController.class);
	
	@FXML
	private TextField nazivArtiklaTextField;
	
	@FXML
	private TextField kategorijaArtiklaTextField;
	
	@FXML
	private Button spremiButton;
	/**
	 * Sprema podatke o novom artiklu u file.
	 * Poziva metodu za osvježavanje TableView-a
	 * za prikaz artikala.
	 */
	public void spremiArtikal()
	{
		String nazivArtikla, kategorijaArtikla;
		nazivArtikla = nazivArtiklaTextField.getText();
		kategorijaArtikla = kategorijaArtiklaTextField.getText();
		File artikliFile = new File("dat/artikli.txt");
		try(FileWriter writer = new FileWriter(artikliFile, true))
		{
			if(nazivArtikla.isEmpty() == false && kategorijaArtikla.isEmpty() == false)
			{
			writer.write(nazivArtikla + "\n");
			writer.write(kategorijaArtikla + "\n");
			writer.flush();
			
			List<Artikl> osvjezeniArtikli = new ArrayList<>();
			osvjezeniArtikli = Main.ucitavanjeArtikala();
			
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
			if(kategorijaArtikla.isEmpty())
			{
			sb.append("Morate unijeti kategoriju artikla!");
			}
			
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Neuspješno spremanje artikla");
			errorAlert.setHeaderText("Potrebno je ispraviti sljedeæe pogreške:");
			errorAlert.setContentText(sb.toString());
			errorAlert.showAndWait();
			}
			
		}
		catch(IOException e)
		{
			Logger.error("Pogreška kod spremanjapodataka", e);
			e.printStackTrace();
		}
	}
}
