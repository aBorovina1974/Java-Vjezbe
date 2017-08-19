package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Tvrtka;
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
	 * Poziva metodu za spremanje podataka o 
	 * novom klijentu u bazu podataka.
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
		
			if(oib.isEmpty() == false && oib.length() == 11 &&
			   prezime.isEmpty() == false && ime.isEmpty() == false &&
			   telefon.isEmpty() == false && email.isEmpty() == false && 
			   datumRodjenja.isPresent() )
			{
			Boolean exists = null;
		    try 
		    {
				exists = BazaPodataka.provjeriOib(oib);
			} 
		    catch (SQLException e) 
		    {
				Logger.error(e.toString());
				e.printStackTrace();
			} 
		    catch (IOException e) 
		    {
				Logger.error(e.toString());
				e.printStackTrace();
			}
		    if(exists)
		    {
		    	Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setTitle("Nedozvoljeni OIB!");
				errorAlert.setHeaderText("Greška pri unosu OIB-a!");
				errorAlert.setContentText("Uneseni OIB pripada drugom klijentu!");
				errorAlert.showAndWait();	
		    }
		    else
		    {
			Tvrtka tvrtka = null;
			try 
			{
				tvrtka = BazaPodataka.dohvatiTvrtku();
			} 
			catch (SQLException e) 
			{
				Logger.error(e.toString());
				e.printStackTrace();
			} 
			catch (IOException e)
			{
				Logger.error(toString());
				e.printStackTrace();
			}	
			Klijent klijent = new Klijent(oib, prezime, ime, telefon, email, datumRodjenja.get());
			try 
			{
				BazaPodataka.spremiKlijenta(klijent, tvrtka);
			} 
			catch (SQLException e) 
			{
				Logger.error(e.toString());
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				Logger.error(e.toString());
				e.printStackTrace();
			}
			
			List<Klijent> osvjezeniKlijenti = new ArrayList<>();
			try 
			{
				osvjezeniKlijenti = BazaPodataka.dohvatiKlijente();
			} 
			catch (SQLException e) 
			{
				Logger.error(e.toString());
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				Logger.error(e.toString());
				e.printStackTrace();
			}
			
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

}
