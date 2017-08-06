package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ZaposleniciController {
	
	//private List<Klijent> listaKlijenata;
	private List<Zaposlenik> listaZaposlenika;
	//private List<Alarm> listaAlarma;
	//private List<Artikl> listaArtikala;
	//private MaloprodajnaTvrtka tvrtka;
	
	@FXML
	private TextField zaposleniciFilterTextField;
	
	@FXML
	private TableView<Zaposlenik> zaposleniciTableView;
	
	@FXML
	private TableColumn<Zaposlenik, String> korisnickoImeColumn;
	
	@FXML
	private TableColumn<Zaposlenik, String> prezimeColumn;
	
	@FXML
	private TableColumn<Zaposlenik, String> imeColumn;
	
	@FXML
	private TableColumn<Zaposlenik, String> sifraColumn;
	
	@FXML
	public void initialize()
	{
		korisnickoImeColumn.setCellValueFactory(new
		PropertyValueFactory<Zaposlenik, String>("korisnickoIme"));
		
		prezimeColumn.setCellValueFactory(new 
		PropertyValueFactory<Zaposlenik, String>("prezime"));
		
		imeColumn.setCellValueFactory(new
		PropertyValueFactory<Zaposlenik, String>("ime"));
		
		sifraColumn.setCellValueFactory(new
		PropertyValueFactory<Zaposlenik, String>("sifraZaposlenika"));
		
		//listaKlijenata = Main.ucitavanjeKlijenata();
		listaZaposlenika = Main.ucitavanjeZaposlenika();
		//listaAlarma = Main.ucitavanjeAlarma();
		//listaArtikala = Main.ucitavanjeArtikala();
		//tvrtka = Main.ucitavanjeTvrtke();
		//tvrtka.setKlijenti(listaKlijenata);
		//tvrtka.setZaposlenici(listaZaposlenika);
		//tvrtka.setArtikli(listaArtikala);
	}
	
	/**
	 * Prikazuje zaposlenike filtrirane prema
	 * prezimenu zaposlenika 
	 */
	public void prikaziZaposlenike()
	{
		List<Zaposlenik> filtriraniZaposlenici = new ArrayList<>();
		if(zaposleniciFilterTextField.getText().isEmpty() == false)
		{
			filtriraniZaposlenici = listaZaposlenika.stream()
					                      .filter(p -> p.getPrezime().contains(
					                    		  zaposleniciFilterTextField.getText()))
					                      .collect(Collectors.toList());
		}
		else
		{
			filtriraniZaposlenici = listaZaposlenika;
		}
		ObservableList<Zaposlenik> listaZaposlenika = FXCollections.
		observableArrayList(filtriraniZaposlenici);
		zaposleniciTableView.setItems(listaZaposlenika);
	}

}
