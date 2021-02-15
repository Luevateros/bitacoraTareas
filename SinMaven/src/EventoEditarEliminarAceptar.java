import javafx.event.*;

/**
 *  Clase concreta de Evento para confirmar que
 *  se eliminará una Tarea. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoEditarEliminarAceptar extends Evento{

    private Controlador control;
    private String clave;
    private int indice;
    private SubVentana anterior, esta;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     * 	@param esta SubVentana que se cerrará.
     * 	@param anterior SubVentana que se cerrará.
     */
    public EventoEditarEliminarAceptar(Controlador control, String clave, int indice, SubVentana esta, SubVentana anterior){
        this.control = control;
        this.clave = clave;
        this.indice = indice;
        this.anterior = anterior;
        this.esta = esta;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.eliminarVentana(anterior);
            pila.eliminarVentana(esta);

            control.setAcceso(false);
            control.eliminarTarea(clave, indice);


        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
