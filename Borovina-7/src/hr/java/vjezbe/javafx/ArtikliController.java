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

public class ArtikliController {
	
	//private List<Klijent> listaKlijenata;
	//private List<Zaposlenik> listaZaposlenika;
	//private List<Alarm> listaAlarma;
	private List<Artikl> listaArtikala;
	//private MaloprodajnaTvrtka tvrtka;

	@FXML
	private TextField artikliFilterTextField;
	
	@FXML
	private TableView<Artikl> artikliTableView;
	
	@FXML
	private TableColumn<Artikl, String> nazivColumn;
	
	@FXML
	private TableColumn<Artikl, String> kategorijaColumn;
	
	@FXML
	public void initialize()
	{
		nazivColumn.setCellValueFactory(new
		PropertyValueFactory<Artikl, String>("naziv"));
		
		kategorijaColumn.setCellValueFactory(new
		Callback<TableColumn.CellDataFeatures<Artikl,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Artikl, String> param) {
			return new ReadOnlyObjectWrapper<String>(param.getValue().getKategorija().toString());
			}
		});
		
		//listaKlijenata = Main.ucitavanjeKlijenata();
		//listaZaposlenika = Main.ucitavanjeZaposlenika();
		//listaAlarma = Main.ucitavanjeAlarma();
		listaArtikala = Main.ucitavanjeArtikala();
		//tvrtka = Main.ucitavanjeTvrtke();
		//tvrtka.setKlijenti(listaKlijenata);
		//tvrtka.setZaposlenici(listaZaposlenika);
		//tvrtka.setArtikli(listaArtikala);
	}
	
	/**
	 * Prikazuje artikle filtrirane prema
	 * nazivu artikla
	 */
	public void prikaziArtikle()
	{
		List<Artikl> filtriraniArtikli = new ArrayList<>();
		if(artikliFilterTextField.getText().isEmpty() == false)
		{
			filtriraniArtikli = listaArtikala.stream()
					                      .filter(p -> p.getNaziv().contains(
					                    		  artikliFilterTextField.getText()))
					                      .collect(Collectors.toList());
		}
		else
		{
			filtriraniArtikli = listaArtikala;
		}
		ObservableList<Artikl> listaArtikala = FXCollections.
		observableArrayList(filtriraniArtikli);
		artikliTableView.setItems(listaArtikala);
	}
}
