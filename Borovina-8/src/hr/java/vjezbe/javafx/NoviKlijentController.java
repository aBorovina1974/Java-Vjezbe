package hr.java.vjezbe.javafx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.entitet.Klijent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NoviKlijentController {
	
	private static final Logger Logger = LoggerFactory.getLogger(NoviKlijentController.class);
	
	@FXML
	private TextField prezimeTextField;
	
	@FXML
	private TextField imeTextField;
	
	@FXML
	private TextField oibTextField;
	
	@FXML
	private TextField brojTelefonaTextField;
	
	@FXML
	private TextField emailTextField;
	
	@FXML
	private DatePicker datumRodjenjaDatePicker;
	
	@FXML
	private Button spremiButton;
	
	/**
	 * Sprema podatke o novom klijentu u file.
	 * Poziva metodu za osvježavanje TableWiew-a 
	 * za prikaz klijenata.
	 */
	public void spremiKlijenta()
	{
		String oib, prezime, ime, telefon, email;
		LocalDate datum;
		oib = oibTextField.getText();
		prezime = prezimeTextField.getText();
		ime = imeTextField.getText();
		telefon = brojTelefonaTextField.getText();
		email = emailTextField.getText();
		datum = datumRodjenjaDatePicker.getValue();
		Optional<LocalDate> datumRodjenja = Optional.ofNullable(datum);

		File klijentiFile = new File("dat/klijenti.txt");
		try(FileWriter writer = new FileWriter(klijentiFile, true))
		{
			if(oib.isEmpty() == false && oib.length() == 11 &&
			   prezime.isEmpty() == false && ime.isEmpty() == false &&
			   telefon.isEmpty() == false && email.isEmpty() == false && 
			   datumRodjenja.isPresent() )
			{
			writer.write(oib + "\n");
			writer.write(prezime + "\n");
            writer.write(ime + "\n");
			writer.write(telefon + "\n");
			writer.write(email + "\n");
			writer.write(Main.dateTimeFormatter.format(datumRodjenja.get()) + "\n");
			writer.flush();
			
			List<Klijent> osvjezeniKlijenti = new ArrayList<>();
			osvjezeniKlijenti = Main.ucitavanjeKlijenata();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno spremanje klijenta!");
			alert.setHeaderText("Uspješno spremanje klijenta!");
			alert.setContentText("Uneseni podaci za klijenta su uspješno spremljeni.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
			Main.prikaziEkranKlijenata();
			KlijentiController.osvjeziKlijentiTableView(osvjezeniKlijenti);
			}
			else
			{
			StringBuilder sb = new StringBuilder();
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
			if(oib.isEmpty())
			{
			sb.append("Morate unijeti OIB!");
			sb.append("\n");
			}
			if(oib.length() != 11)
			{
			sb.append("OIB mora imati 11 znakova!");
			sb.append("\n");
			}
			if(telefon.isEmpty())
			{
			sb.append("Morate unijeti broj telefona!");
			sb.append("\n");
			}
			if(email.isEmpty())
			{
			sb.append("Morate unijeti E-mail!");
			sb.append("\n");
			}
			if(!datumRodjenja.isPresent())
			{
			sb.append("Morate unijeti datum roðenja!");
			}
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Neuspješno spremanje klijenta");
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
