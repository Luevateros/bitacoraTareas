import javafx.event.*;

/**
 *  Clase concreta de Evento que agrega una SubVentana
 *  a SingletonPila. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoAgregarSubVentana extends Evento{

    private Controlador control;
    private String tipo;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param tipo de la SubVentana a crear.
     */
    public EventoAgregarSubVentana(Controlador control, String tipo){
        this.control = control;
        this.tipo = tipo;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

		EventHandler<ActionEvent> evento = e -> {

            control.setAcceso(true);

            SubVentana nueva;
            nueva = FabricaSubVentana.crear(control, tipo);

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.agregar(nueva);


		}; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
