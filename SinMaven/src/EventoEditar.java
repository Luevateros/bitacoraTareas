import javafx.event.*;

/**
 *  Clase concreta de Evento para crear una SubVentana
 *  para editar una Tarea. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoEditar extends Evento{

    private Controlador control;
    private String clave;
    private int indice;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     */
    public EventoEditar(Controlador control, String clave, int indice){
		this.control = control;
        this.clave = clave;
        this.indice = indice;
	}

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            SubVentana nueva;
            nueva = FabricaSubVentana.crear(control, "editar", clave, indice);

    		pila.agregar(nueva);
            control.setAcceso(true);

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
