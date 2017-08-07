package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ZaposleniciController {
	
	private static ObservableList<Zaposlenik> observableListaZaposlenika;
    private List<Zaposlenik> listaZaposlenika;
		
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
		
		observableListaZaposlenika = FXCollections.observableArrayList();
		zaposleniciTableView.setItems(observableListaZaposlenika);
	}
	
	/**
	 * Prikazuje zaposlenike filtrirane prema
	 * prezimenu zaposlenika 
	 */
	public void prikaziZaposlenike()
	{
		listaZaposlenika = Main.ucitavanjeZaposlenika();
		
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
		
		osvjeziZaposleniciTableView(filtriraniZaposlenici);
		zaposleniciTableView.setItems(observableListaZaposlenika);
	}
	
	/**
	 * Osvje�ava TableView za prikaz zaposlenika
	 * Kao argument prima referencu na listu zaposlenika
	 * @param zaposlenici referenca na listu zaposlenika
	 */
	public static void osvjeziZaposleniciTableView(List<Zaposlenik> zaposlenici)
	{
		observableListaZaposlenika.clear();
		observableListaZaposlenika.addAll(zaposlenici);
	}

}