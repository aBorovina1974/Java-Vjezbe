package hr.java.vjezbe.javafx;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Komunikacija;
import hr.java.vjezbe.entitet.VrstaKomunikacije;
import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NovaKomunikacijaController {

	private static final Logger Logger = LoggerFactory.getLogger(NovaKomunikacijaController.class);
	
	private static Klijent klijent;
	private static Zaposlenik zaposlenik;
	

	public static Klijent getKlijent() {
		return klijent;
	}

	public static void setKlijent(Klijent klijent) {
		NovaKomunikacijaController.klijent = klijent;
	}

	public static Zaposlenik getZaposlenik() {
		return zaposlenik;
	}

	public static void setZaposlenik(Zaposlenik zaposlenik) {
		NovaKomunikacijaController.zaposlenik = zaposlenik;
	}

	@FXML
	TextField klijentTextField;
	
	@FXML
	TextField zaposlenikTextField;
	
	@FXML
	ComboBox<VrstaKomunikacije> comboVrstaKomunikacije;
	
	@FXML
	TextArea textAreaSadrzaj;
	
	@FXML
	Button spremiButton;
	
	@FXML
	public void initialize()
	{
		klijentTextField.setText(klijent.getPrezime() + " " + klijent.getIme());
		
		zaposlenikTextField.setText(zaposlenik.getPrezime() + " " + zaposlenik.getIme());

		try 
		{
			comboVrstaKomunikacije.getItems().addAll(BazaPodataka.dohvatiVrsteKomunikacije());
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
	 * Sprema podatke o novoj komunikaciji u bazu podataka.
	 * Poziva metodu za osvježavanje TableView-a za 
	 * priaz komunikacija.
	 */
	public void spremiKomunikaciju()
	{
		VrstaKomunikacije vrstaKomunikacije = comboVrstaKomunikacije.getSelectionModel().getSelectedItem();
		String sadrzajKomunikacije = textAreaSadrzaj.getText();
		LocalDateTime vrijemeKomunikacije = LocalDateTime.now();
		
		if(klijent != null && zaposlenik != null && vrstaKomunikacije != null &&
		   sadrzajKomunikacije.isEmpty() == false && vrijemeKomunikacije != null)
		{
			Komunikacija komunikacija = new Komunikacija(klijent, zaposlenik, 
			vrstaKomunikacije, sadrzajKomunikacije, vrijemeKomunikacije);
			
			try 
			{
				BazaPodataka.spremiKomunikaciju(komunikacija);
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
			
			List<Komunikacija> osvjezeneKomunikacije = new ArrayList<>();
			
			try 
			{
				osvjezeneKomunikacije = BazaPodataka.dohvatiKomunikacije();
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
			alert.setTitle("Uspješno spremanje komunikacija!");
			alert.setHeaderText("Uspješno spremanje komunikacije!");
			alert.setContentText("Uneseni podaci za komunikaciju su uspješno spremljeni.");
			alert.showAndWait();
			Stage stage = (Stage) spremiButton.getScene().getWindow();
			stage.close();
			Main.prikaziEkranKomunikacija();
			KomunikacijeController.osvjeziKomunikacijeTableView(osvjezeneKomunikacije);
		}
		else
		{
			StringBuilder sBuilder = new StringBuilder();
			
			if(vrstaKomunikacije == null)
			{
			sBuilder.append("Morate odabrati vrstu komunikacije!");
			sBuilder.append("\n");
			}
			else if(sadrzajKomunikacije.isEmpty() == true)
			{
			sBuilder.append("Morate unijeti sadržaj!");
			}
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Neuspješno spremanje artikla");
			errorAlert.setHeaderText("Potrebno je ispraviti sljedeæe pogrešku:");
			errorAlert.setContentText(sBuilder.toString());
			errorAlert.showAndWait();
		}
		
	}
}
