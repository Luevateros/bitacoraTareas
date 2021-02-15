import javafx.event.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.time.format.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/**
 *	Clase abstracta de la cual se fabrican clases concretas
 *	del tipo Evento, los cuales agregan o quitan SubVentanas
 *	a la SingletonPila. Estos Evento se asignan a botones.
 *	Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public abstract class Evento{

	/**
     *  Método abstracto que genera el EventHandler a devolver.
     */
	public abstract EventHandler<ActionEvent> getEvento();

	/**
     *  Método para evaluar si los datos ingresados están incompletos.
	 *	@return <code>false<code> si todas las entradas tienen contenido.
	 *	 	   <code>true<code> si alguna no.
     */
	protected boolean datosIncompletos(TextField tfa, TextField tfb, TextField tfc, ComboBox cbd){
		if(tfa.getText().isEmpty())
			return true;
		if(tfb.getText().isEmpty())
			return true;
		if(tfc.getText().isEmpty())
			return true;
		if(cbd.getValue() == null)
			return true;

		return false;
	}

	/**
     *  Método para evaluar si la fecha y hora son válidas.
	 *	@return <code>true<code> si son válidas.
	 *	 	   <code>true<code> si no.
	 */
	protected boolean fechaHoraValida(String fecha, String hora){
		String formatoStr = "dd-MM-uuuu HH:mm";
		String fechaHora  = fecha + " " + hora;

		DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoStr). withResolverStyle(ResolverStyle.STRICT);

		try {
			LocalDateTime ldt;
			ldt = LocalDateTime.parse(fechaHora, formato);
		} catch (DateTimeParseException dtpe) {
			return false;
		}
		return true;
	}

	/**
     *  Método para evaluar si la fecha y hora ya pasaron.
	 *	@return <code>true<code> si aún no.
	 *	 	   <code>true<code> si ya.
	 */
	protected boolean fechaHoraFuturo(String fecha, String hora){

		String formatoStr = "dd-MM-uuuu HH:mm";
		String fechaHora  = fecha + " " + hora;

		DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoStr);

		LocalDateTime futura;
		futura = LocalDateTime.parse(fechaHora, formato);

		LocalDateTime ahora = LocalDateTime.now();

		if(futura.isAfter(ahora))
			return true;

		return false;

	}

}
