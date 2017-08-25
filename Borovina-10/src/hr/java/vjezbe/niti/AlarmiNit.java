package hr.java.vjezbe.niti;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.javafx.Main;
import hr.java.vjezbe.javafx.NovaKomunikacijaController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Predstavlja novu nit koja poziva metodu za 
 * dohvaæanje alarma s najbližim vremenom isteka
 * te traži od korisnika potvrdu o obavljanju 
 * komunikacije za taj alarm.
 * @author Ante
 *
 */
public class AlarmiNit extends Thread 
{
	private static final Logger Logger = LoggerFactory.getLogger(AlarmiNit.class);
	private static Integer brojKreiranihNiti = 0;
	
    private static Boolean show  = true;
    
	public static Boolean getShow() {
		return show;
	}
	
	public static void setShow(Boolean show) {
		AlarmiNit.show = show;
	}
	
	public void run()
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run() 
			{
				try 
				{
					Alarm alarmPredIstekom = BazaPodataka.dohvatiAlarmePredIstekom();
					Optional<Alarm> alarm = Optional.ofNullable(alarmPredIstekom);
					if(alarm.isPresent())
					{
					ButtonType confirmButton = new ButtonType("Da", ButtonBar.ButtonData.OK_DONE);
					ButtonType cancelButton = new ButtonType("Ne", ButtonBar.ButtonData.CANCEL_CLOSE);
					Alert alert = new Alert(AlertType.CONFIRMATION, "Alarm za korisnika " +
					alarmPredIstekom.getKlijent().getPrezime() + " " +
					alarmPredIstekom.getKlijent().getIme() + " s opisom '" + alarmPredIstekom.getOpisAlarma() +
					"' istjeèe " + DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm ")
					.format(alarmPredIstekom.getVrijemeAlarma()) + "Želite li unjeti podatke o komunikaciji?",
					confirmButton, cancelButton);
					
					alert.setTitle("Upozorenje");
					alert.setHeaderText("Alarm pred istekom");
	                    if(show)
	                    {
	                    AlarmiNit.show = false;
						Optional<ButtonType> response = alert.showAndWait();
						if(response.isPresent() && response.get() == confirmButton)
						{
							Main.prikaziEkranZaposlenika();
							NovaKomunikacijaController.setKlijent(alarmPredIstekom.getKlijent());
						}
						else if (response.isPresent() && response.get() == cancelButton)
						{
						AlarmiNit.show = true;
						}
						}
						
					}
					brojKreiranihNiti++;
					Logger.info("Kreirana je " + brojKreiranihNiti + ". nit.");
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
		});
		
	}
}
