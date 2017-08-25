package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Komunikacija;
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

public class KomunikacijeController {

	private static final Logger Logger = LoggerFactory.getLogger(KlijentiController.class);
	private static ObservableList<Komunikacija> observableListaKomunikacija;
	private List<Komunikacija> listaKomunikacija;
	
	@FXML
	private TextField komunikacijeFilterTextField;
	
	@FXML
	private TableView<Komunikacija> komunikacijeTableView;
	
	@FXML
	private TableColumn<Komunikacija, String> klijentColumn;
	
	@FXML
	private TableColumn<Komunikacija, String> zaposlenikColumn;
	
	@FXML
	private TableColumn<Komunikacija, String> vrstaKomunikacijeColumn;
	
	@FXML
	private TableColumn<Komunikacija, String> sadrzajColumn;
	
	@FXML
	private TableColumn<Komunikacija, String> vrijemeColumn;
	
	
	@FXML
	public void initialize()
	{
		klijentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Komunikacija,String>, 
				                          ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Komunikacija, String> param) {
				return new ReadOnlyObjectWrapper<String>(param.getValue().getKlijent().getPrezime() + 
						                                 " " + param.getValue().getKlijent().getIme());
			}
		});
		 
		zaposlenikColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Komunikacija,String>, 
				                             ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Komunikacija, String> param) {
				return new ReadOnlyObjectWrapper<String>(param.getValue().getZaposlenik().getPrezime() +
						                                 " " + param.getValue().getZaposlenik().getIme());
			}
		});
		
	    vrstaKomunikacijeColumn.setCellValueFactory(
	    new Callback<TableColumn.CellDataFeatures<Komunikacija,String>,ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Komunikacija, String> param) {
				return new ReadOnlyObjectWrapper<String>(param.getValue().getVrstaKomunikacije().toString());
			}
		});
		
		sadrzajColumn.setCellValueFactory(new 
		PropertyValueFactory<Komunikacija, String>("sadrzajKomunikacije"));
		
		vrijemeColumn.setCellValueFactory(new 
		Callback<TableColumn.CellDataFeatures<Komunikacija,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Komunikacija, String> param) {
				return new ReadOnlyObjectWrapper<String>(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:ss").format(
						                                 param.getValue().getVrijemeKomunikacije()));
			}
			

		});
		
		observableListaKomunikacija = FXCollections.observableArrayList();
		komunikacijeTableView.setItems(observableListaKomunikacija);		
	}
	
	/**
	 * Prikazuje komunikacije filtrirane prema
	 * prezimenu klijenta.
	 * Poziva statiènu metodu za dohvat podataka o komunikacijama 
	 * iz baze podataka.
	 */
	public void prikaziKomunikacije()
	{	
		try 
		{
			listaKomunikacija = BazaPodataka.dohvatiKomunikacije();
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

		List<Komunikacija> filtriraneKomunikacije = new ArrayList<>();
		if(komunikacijeFilterTextField.getText().isEmpty() == false)
		{
			filtriraneKomunikacije = listaKomunikacija.stream()
					                      .filter(k -> k.getKlijent().getPrezime().contains(
					                    		  komunikacijeFilterTextField.getText()))
					                      .collect(Collectors.toList());
		}
		else
		{
			filtriraneKomunikacije = listaKomunikacija;
		}
		
		osvjeziKomunikacijeTableView(filtriraneKomunikacije);
		komunikacijeTableView.setItems(observableListaKomunikacija);
	}
	
	/**
	 * Osvježava TableView za prikaz komunikacija.
	 * Kao argument prima referencu na listu komunikacija.
	 * @param klijenti referenca na listu komunikacija.
	 */
	public static void osvjeziKomunikacijeTableView(List<Komunikacija> komunikacije)
	{
		observableListaKomunikacija.clear();
		observableListaKomunikacija.addAll(komunikacije);
	}
	
}
