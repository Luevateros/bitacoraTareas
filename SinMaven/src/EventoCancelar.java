import javafx.event.*;

/**
 *  Clase concreta de Evento para cancelar o salir
 *  de una SubVentana. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoCancelar extends Evento{

    private Controlador control;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     */
    public EventoCancelar(Controlador control){
        this.control = control;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            control.setAcceso(false);

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.eliminarUltimo();

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
