package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.baza.podataka.BazaPodataka;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.niti.AlarmiNit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PocetniEkranController {
	
	private static Logger logger = LoggerFactory.getLogger(PocetniEkranController.class);
	private Tvrtka tvrtka;
	
	@FXML
	private Label imeTvrtke;
	
	@FXML
	public void initialize()
	{
		try 
		{
			tvrtka = BazaPodataka.dohvatiTvrtku();
		} 
		catch (SQLException e)
		{
			logger.error(e.toString());
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			logger.error(e.toString());
			e.printStackTrace();
		}
		imeTvrtke.setText(tvrtka.getNazivTvrtke());
		logger.info("Ucitana tvrtka");
		
	}
	
	/**
	 * Poziva metodu za prikaz alarma
	 */
	public void postaviEkranAlarma()
	{
		Main.prikaziEkranAlarma();
		AlarmiNit.setShow(true);
	}
	
	/**
	 * Poziva metodu za prikaz klijenata
	 */
	public void postaviEkranKlijenata()
	{
		Main.prikaziEkranKlijenata();
	}
	
	/**
	 * Poziva metodu za prikaz zaposlenika
	 */
	public void postaviEkranZaposlenika()
	{
		Main.prikaziEkranZaposlenika();
	}
	
	/**
	 * Poziva metodu za prikaz artikala
	 */
	public void postaviEkranArtikala()
	{
		Main.prikaziEkranArtikala();
	}
    
	/**
	 * Poziva metodu za prikaz komunikacija
	 */
	public void postaviEkranKomunikacija()
	{
		Main.prikaziEkranKomunikacija();
	}
	
	/**
	 * Prikazuje ekran za unos podataka za novog klijenta.
	 */
	public void postaviEkranZaNovogKlijenta()
	{
		Main.prikaziEkranZaNovogKlijenta();
	}
	
	/**
	 * Prikazuje ekran za unos podataka za novog zaposlenika.
	 */
	public void postaviEkranZaNovogZaposlenika()
	{
		Main.prikaziEkranZaNovogZaposlenika();
	}
	
	/**
	 * Prikazuje ekran za unos podataka za novi artikal.
	 */
	public void postaviEkranZaNoviArtikal()
	{
		Main.prikaziEkranZaNoviArtikal();
	}
}
