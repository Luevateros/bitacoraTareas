import javafx.event.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de Evento para confirmar que se
 *  eliminarán las Tareas seleccionadas. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoConfigEliminarAceptar extends Evento{

    private Controlador control;
    private CheckBox urgentes, pendientes, pasadas, todas;
    private SubVentana esta, anterior;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param urgentes guarda la respuesta a eliminar tareas urgentes.
     * 	@param pendientes guarda la respuesta a eliminar tareas pendientes.
     * 	@param pasadas guarda la respuesta a eliminar tareas pasadas.
     * 	@param todas guarda la respuesta a eliminar todas las tareas.
     * 	@param esta SubVentana que se cerrará.
     * 	@param anterior SubVentana que se cerrará.
     */
    public EventoConfigEliminarAceptar(Controlador control, Control urgentes, Control pendientes, Control pasadas, Control todas, SubVentana esta, SubVentana anterior){
        this.control = control;
        this.urgentes = (CheckBox) urgentes;
        this.pendientes = (CheckBox) pendientes;
        this.pasadas = (CheckBox) pasadas;
        this.todas = (CheckBox) todas;
        this.esta = esta;
        this.anterior = anterior;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.eliminarVentana(anterior);
            pila.eliminarVentana(esta);
            control.setAcceso(false);

            if(todas.isSelected())
                control.eliminarTodas();

            if(urgentes.isSelected())
                control.eliminarUrgentes();

            if(pendientes.isSelected())
                control.eliminarPendientes();

            if(pasadas.isSelected())
                control.eliminarPasadas();

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()
}
