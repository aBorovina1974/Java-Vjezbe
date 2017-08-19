package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Tvrtka;
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

			if(korisnickoIme.isEmpty() == false && prezime.isEmpty() == false &&
			   ime.isEmpty() == false && sifra.isEmpty() == false)
			{
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
			Zaposlenik zaposlenik = new Zaposlenik(korisnickoIme, ime, prezime, sifra);
			Boolean exists = null;
			try 
			{
				exists = BazaPodataka.provjeriSifruIKorisnickoIme(zaposlenik);
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
			if(exists)
			{
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Pogrešna šifra/korisnièko ime!");
			errorAlert.setHeaderText("Šifra/korisnièko ime se veæ koristi!");
			errorAlert.setContentText("Šifra/korisnièko ime se veæ koristi!");
			errorAlert.showAndWait();
			}
			else
			{
			try
			{
				BazaPodataka.spremiZaposlenika(zaposlenik, tvrtka);
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
			
			List<Zaposlenik> osvjezeniZaposlenici = new ArrayList<>();
			try
			{
				osvjezeniZaposlenici = BazaPodataka.dohvatiZaposlenike();
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
			alert.setTitle("Uspješno spremanje zaposlenika!");
			alert.setHeaderText("Uspješno spremanje zaposlenika!");
			alert.setContentText("Uneseni podaci za zaposlenika su uspješno spremljeni.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
			Main.prikaziEkranZaposlenika();
			ZaposleniciController.osvjeziZaposleniciTableView(osvjezeniZaposlenici);
			}
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
}
