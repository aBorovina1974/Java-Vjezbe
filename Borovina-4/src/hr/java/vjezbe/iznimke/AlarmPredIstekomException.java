package hr.java.vjezbe.iznimke;
/**
 * Predstavlja entitet iznimke koja se baca dok je alarm aktivan.
 * @author Ante
 *
 */
public class AlarmPredIstekomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1839626077178708769L;

	/**
	 * Konstruktor bez parametara.	
	 */
	public AlarmPredIstekomException(){
		super("Alarm pred istekom!");
	}
	
	/**
	 * Konstruktor koji prima poruku o grešci.	
	 * @param message poruka o grešci
	 */
	public AlarmPredIstekomException(String message){
		super(message);
	}
	
	/**
	 * Konstruktor koji prima poruku o grešci i uzroènu iznimku.	
	 * @param message poruka o grešci
	 * @param cause uzroèna iznimka
	 */
	public AlarmPredIstekomException(String message, Throwable cause){
		super(message, cause);
	}
	
	/**
	 * konstruktor koji prima uzroènu iznimku.	
	 * @param cause uzroèna iznimka
	 */
	public AlarmPredIstekomException(Throwable cause){
		super(cause);
	}
	

}
