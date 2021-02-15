import javafx.event.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de Evento para aceptar los cambios
 *  hechos en las configuraciones. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoConfigAceptar extends Evento{

    private Controlador control;
    private CheckBox urgentes, pendientes, pasadas, todas;
    private SubVentana anterior;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param urgentes guarda la respuesta a eliminar tareas urgentes.
     * 	@param pendientes guarda la respuesta a eliminar tareas pendientes.
     * 	@param pasadas guarda la respuesta a eliminar tareas pasadas.
     * 	@param todas guarda la respuesta a eliminar todas las tareas.
     * 	@param anterior SubVentana que se cerrará.
     */
    public EventoConfigAceptar(Controlador control, Control urgentes, Control pendientes, Control pasadas, Control todas, SubVentana anterior){
        this.control = control;
        this.urgentes = (CheckBox) urgentes;
        this.pendientes = (CheckBox) pendientes;
        this.pasadas = (CheckBox) pasadas;
        this.todas = (CheckBox) todas;
        this.anterior = anterior;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            SingletonPila pila;
            pila = SingletonPila.getInstancia();

            /* Nada seleccionado: se cierra la ventana. */
            if(!urgentes.isSelected() && !pendientes.isSelected() && !pasadas.isSelected() && !todas.isSelected()){
                control.setAcceso(false);
                pila.eliminarUltimo();

            }

            else{
                /* Se selecionó al menos una opción. */

                SubVentana aviso;
                aviso = FabricaSubVentana.crear(control, "configEliminar", urgentes, pendientes, pasadas, todas, anterior);
                pila.agregar(aviso);
                control.setAcceso(true);

            } // Acaba: else, se selecionó al menos una opción.

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
