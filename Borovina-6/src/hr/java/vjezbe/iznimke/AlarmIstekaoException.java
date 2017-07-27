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
	 * Konstruktor koji prima poruku o grešci.	
	 * @param message poruka o grešci
	 */
	public AlarmIstekaoException(String message){
		super(message);
	}
	
	/**
	 * Konstruktor koji prima poruku o grešci i uzroènu iznimku.	
	 * @param message poruka o grešci
	 * @param cause uzroèna iznimka
	 */
	public AlarmIstekaoException(String message, Throwable cuse){
		super(message, cuse);
	}
	
	/**
	 * konstruktor koji prima uzroènu iznimku.	
	 * @param cause uzroèna iznimka
	 */
	public AlarmIstekaoException(Throwable cause){
		super(cause);
	}
}
