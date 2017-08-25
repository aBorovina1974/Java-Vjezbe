package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
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
	private static final Logger Logger = LoggerFactory.getLogger(ArtikliController.class);
	private static ObservableList<Artikl> observableListaArtikala;
	private List<Artikl> listaArtikala;

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
		
		observableListaArtikala = FXCollections.observableArrayList();
		artikliTableView.setItems(observableListaArtikala);
	}
	
	/**
	 * Prikazuje artikle filtrirane prema
	 * nazivu artikla.
	 * Poziva statiènu metodu za dohvat podataka o 
	 * artiklima iz baze podataka. 
	 */
	public void prikaziArtikle()
	{
		try 
		{
			listaArtikala = BazaPodataka.dohvatiArtikle();
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
		
		osvjeziArtikliTableView(filtriraniArtikli);
		artikliTableView.setItems(observableListaArtikala);
	}
	
	/**
	 * Osvježava TableView za prikaz artikala.
	 * Kao argument prima referencu na listu artikala.
	 * @param artikli referenca na listu artikala.
	 */
	public static void osvjeziArtikliTableView(List<Artikl> artikli)
	{
		observableListaArtikala.clear();
		observableListaArtikala.addAll(artikli);
	}
}
