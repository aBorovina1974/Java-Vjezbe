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
import hr.java.vjezbe.entitet.Alarm;
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

public class AlarmiController {
	private static final Logger Logger = LoggerFactory.getLogger(AlarmiController.class);

	private List<Alarm> listaAlarma;
	
	@FXML
	private TextField alarmiFilterTextField;
	
	@FXML
	private TableView<Alarm> alarmiTableView;
	
	@FXML
	private TableColumn<Alarm, String> klijentColumn;
	
	@FXML
	private TableColumn<Alarm, String> opisColumn;
	
	@FXML
	private TableColumn<Alarm, String> vrijemeColumn;
	
	@FXML
	public void initialize()
	{
		
		klijentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Alarm,String>,
				                          ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Alarm, String> param) {
				
				return new ReadOnlyObjectWrapper<String>(param.getValue().getKlijent().getPrezime() +
						                                 " " + param.getValue().getKlijent().getIme());
			}
		});
						                                 
			
		
		opisColumn.setCellValueFactory(new 
		PropertyValueFactory<Alarm, String>("opisAlarma"));
		
		vrijemeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Alarm,String>,
				                          ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Alarm, String> param) {
		
				return new ReadOnlyObjectWrapper<String>(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm ").format(param.
						                                 getValue().getVrijemeAlarma()));
			}
		});
		
		try 
		{
			listaAlarma = BazaPodataka.dohvatiAlarme();
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
	 * Prikazuje alarme filtrirane prema OIB-u
	 * klijenta
	 */
	public void prikaziAlarme()
	{
		List<Alarm> filtriraniAlarmi = new ArrayList<>();
		if(alarmiFilterTextField.getText().isEmpty() == false)
		{
			filtriraniAlarmi = listaAlarma.stream()
					                      .filter(p -> p.getKlijent().getOib().contains(
					                    		  alarmiFilterTextField.getText()))
					                      .collect(Collectors.toList());
		}
		else
		{
			filtriraniAlarmi = listaAlarma;
		}
		ObservableList<Alarm> listaAlarma = FXCollections.observableArrayList(filtriraniAlarmi);
		alarmiTableView.setItems(listaAlarma);
	}
	
}
