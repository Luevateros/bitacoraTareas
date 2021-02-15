import javafx.event.*;

/**
 *  Clase concreta de Evento que crea una SubVentana
 *  para avisar que se elimna una Tarea. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoEditarEliminar extends Evento{

    private Controlador control;
    private String clave;
    private int indice;
    private SubVentana anterior;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     * 	@param anterior SubVentana que se cerrará.
     */
    public EventoEditarEliminar(Controlador control, String clave, int indice, SubVentana anterior){
        this.control = control;
        this.clave = clave;
        this.indice = indice;
        this.anterior = anterior;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            SubVentana aviso;
            aviso = FabricaSubVentana.crear(control, "editarEliminar", clave, indice, anterior);

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.agregar(aviso);


        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
