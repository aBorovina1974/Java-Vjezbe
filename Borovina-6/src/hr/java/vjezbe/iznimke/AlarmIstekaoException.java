package hr.java.vjezbe.iznimke;
/**
 * Predstavlja entitet iznimke koja se baca po isteku alarma.
 * @author Ante
 *
 */
public class AlarmIstekaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -757575664591881907L;

	/**
	 * Konstruktor bez parametara.	
	 */	
	public AlarmIstekaoException(){
		super("Alarm je istekao!");
	}
	
	/**
	 * Konstruktor koji prima poruku o gre�ci.	
	 * @param message poruka o gre�ci
	 */
	public AlarmIstekaoException(String message){
		super(message);
	}
	
	/**
	 * Konstruktor koji prima poruku o gre�ci i uzro�nu iznimku.	
	 * @param message poruka o gre�ci
	 * @param cause uzro�na iznimka
	 */
	public AlarmIstekaoException(String message, Throwable cuse){
		super(message, cuse);
	}
	
	/**
	 * konstruktor koji prima uzro�nu iznimku.	
	 * @param cause uzro�na iznimka
	 */
	public AlarmIstekaoException(Throwable cause){
		super(cause);
	}
}
