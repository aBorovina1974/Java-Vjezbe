package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class KlijentiController {
	
	private List<Klijent> listaKlijenata;
	//private List<Zaposlenik> listaZaposlenika;
	//private List<Alarm> listaAlarma;
	//private List<Artikl> listaArtikala;
	//private MaloprodajnaTvrtka tvrtka;
	
	@FXML
	private TextField klijentiFilterTextField;
	
	@FXML
	private TableView<Klijent> klijentiTableView;
	
	@FXML
	private TableColumn<Klijent, String> oibColumn;
	
	@FXML
	private TableColumn<Klijent, String> prezimeColumn;
	
	@FXML
	private TableColumn<Klijent, String> imeColumn;
	
	@FXML
	private TableColumn<Klijent, String> brojTelefonaColumn;
	
	@FXML
	private TableColumn<Klijent, String> emailColumn;
	
	@FXML
	private TableColumn<Klijent, String> datumRodjenjaColumn;
	
	@FXML
	public void initialize()
	{
		oibColumn.setCellValueFactory(new 
		PropertyValueFactory<Klijent, String>("oib"));
		
		prezimeColumn.setCellValueFactory(new 
		PropertyValueFactory<Klijent, String>("prezime"));
		
		imeColumn.setCellValueFactory(new 
		PropertyValueFactory<Klijent, String>("ime"));
		
		brojTelefonaColumn.setCellValueFactory(new 
		PropertyValueFactory<Klijent, String>("telefon"));
		
		emailColumn.setCellValueFactory(new 
		PropertyValueFactory<Klijent, String>("email"));
		
		datumRodjenjaColumn.setCellValueFactory(new 
		Callback<TableColumn.CellDataFeatures<Klijent,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Klijent, String> param) {
				
			return new ReadOnlyObjectWrapper<String>(Main.dateTimeFormatter.format(param.
					                                 getValue().getDatumRodjenja()));
			}
		});
		
		listaKlijenata = Main.ucitavanjeKlijenata();
		//listaZaposlenika = Main.ucitavanjeZaposlenika();
		//listaAlarma = Main.ucitavanjeAlarma();
		//listaArtikala = Main.ucitavanjeArtikala();
		//tvrtka = Main.ucitavanjeTvrtke();
		//tvrtka.setKlijenti(listaKlijenata);
		//tvrtka.setZaposlenici(listaZaposlenika);
		//tvrtka.setArtikli(listaArtikala);
	}
	
	/**
	 * Prikazuje klijente filtrirane prema
	 * prezimenu klijenta
	 */
	public void prikaziklijente()
	{
		List<Klijent> filtriraniKlijenti = new ArrayList<>();
		if(klijentiFilterTextField.getText().isEmpty() == false)
		{
			filtriraniKlijenti = listaKlijenata.stream()
					                      .filter(p -> p.getPrezime().contains(
					                    		  klijentiFilterTextField.getText()))
					                      .collect(Collectors.toList());
		}
		else
		{
			filtriraniKlijenti = listaKlijenata;
		}
		ObservableList<Klijent> listaKlijenata = FXCollections.observableArrayList(filtriraniKlijenti);
		klijentiTableView.setItems(listaKlijenata);
	}
	

}
