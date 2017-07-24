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
	 * Konstruktor koji prima poruku o gre�ci.	
	 * @param message poruka o gre�ci
	 */
	public AlarmPredIstekomException(String message){
		super(message);
	}
	
	/**
	 * Konstruktor koji prima poruku o gre�ci i uzro�nu iznimku.	
	 * @param message poruka o gre�ci
	 * @param cause uzro�na iznimka
	 */
	public AlarmPredIstekomException(String message, Throwable cause){
		super(message, cause);
	}
	
	/**
	 * konstruktor koji prima uzro�nu iznimku.	
	 * @param cause uzro�na iznimka
	 */
	public AlarmPredIstekomException(Throwable cause){
		super(cause);
	}
	

}
