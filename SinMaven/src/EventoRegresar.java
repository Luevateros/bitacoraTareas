import javafx.event.*;

/**
 *  Clase concreta de Evento para regresar
 *  de una SubVentana. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoRegresar extends Evento{

    /**
     *  Constructor del evento.
     */
    public EventoRegresar(){}

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.eliminarUltimo();

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
