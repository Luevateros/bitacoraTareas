import javafx.event.*;

/**
 *  Clase concreta de Evento para crear una SubVentana
 *  para editar las categorias. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoConfigCategorias extends Evento{

    private Controlador control;
    private SubVentana anterior;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param anterior SubVentana que se cerrará.
     */
    public EventoConfigCategorias(Controlador control, SubVentana anterior){
        this.control = control;
        this.anterior = anterior;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            SubVentana edicion;
            edicion = FabricaSubVentana.crear(control, "editarCateg", anterior);

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.agregar(edicion);
            control.setAcceso(true);

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
