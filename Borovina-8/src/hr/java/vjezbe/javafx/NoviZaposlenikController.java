package hr.java.vjezbe.javafx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NoviZaposlenikController {

private static final Logger Logger = LoggerFactory.getLogger(NoviKlijentController.class);
	
	@FXML
	private TextField korisnickoImeTextField;
	
	@FXML
	private TextField prezimeTextField;
	
	@FXML
	private TextField imeTextField;
	
	@FXML
	private TextField sifraTextField;
	
	@FXML
	private Button spremiButton;
	
	/**
	 * Sprema podatke o novom zaposleniku u file.
	 * Poziva metodu za osvježavanje TableView-a
	 * za prikaz zaposlenika
	 */
	public void spremiZaposlenika()
	{
		String korisnickoIme, prezime, ime, sifra;
		korisnickoIme = korisnickoImeTextField.getText();
		prezime = prezimeTextField.getText();
		ime = imeTextField.getText();
		sifra = sifraTextField.getText();
		File zaposleniciFile = new File("dat/zaposlenici.txt");
		try(FileWriter writer = new FileWriter(zaposleniciFile, true))
		{
			if(korisnickoIme.isEmpty() == false && prezime.isEmpty() == false &&
			   ime.isEmpty() == false && sifra.isEmpty() == false)
			{
			writer.write(korisnickoIme + "\n");
			writer.write(ime + "\n");
            writer.write(prezime + "\n");
			writer.write(sifra + "\n");
			writer.flush();
			
			List<Zaposlenik> osvjezeniZaposlenici = new ArrayList<>();
			osvjezeniZaposlenici = Main.ucitavanjeZaposlenika();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno spremanje zaposlenika!");
			alert.setHeaderText("Uspješno spremanje zaposlenika!");
			alert.setContentText("Uneseni podaci za zaposlenika su uspješno spremljeni.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
			Main.prikaziEkranZaposlenika();
			ZaposleniciController.osvjeziZaposleniciTableView(osvjezeniZaposlenici);
			}
			else
			{
			StringBuilder sb = new StringBuilder();
			if(korisnickoIme.isEmpty())
			{
			sb.append("Morate unijeti korisnièko ime!");
			sb.append("\n");
			}
			if(prezime.isEmpty())
			{
			sb.append("Morate unijeti prezime!");
			sb.append("\n");
			}
			if(ime.isEmpty())
			{
			sb.append("Morate unijeti ime!");
			sb.append("\n");
			}
			if(sifra.isEmpty())
			{
			sb.append("Morate unijeti šifru!");
			sb.append("\n");
			}
			
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Neuspješno spremanje zaposlenika");
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
