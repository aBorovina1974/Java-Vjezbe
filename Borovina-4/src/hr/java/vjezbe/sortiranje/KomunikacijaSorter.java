package hr.java.vjezbe.sortiranje;

import java.time.LocalDateTime;
import java.util.Comparator;

import hr.java.vjezbe.entitet.Komunikacija;
/**
 * Definira tehniku sortiranja za objekte klase Komunikacija.
 * @author Ante
 *
 */
public class KomunikacijaSorter implements Comparator<Komunikacija> {

	@Override
	public int compare(Komunikacija k1, Komunikacija k2) {

		LocalDateTime t1 = k1.getVrijemeKomunikacije();
		LocalDateTime t2 = k1.getVrijemeKomunikacije();
		if(t1.compareTo(t2) != 0)
		{
			return t1.compareTo(t2);
		}
		else
		{
			String p1 = k1.getKlijent().getPrezime();
			String p2 = k1.getKlijent().getPrezime();
			return p1.compareTo(p2);	
		}
	}

	
}
