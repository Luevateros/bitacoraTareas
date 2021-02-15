import javafx.event.*;

/**
 *  Clase concreta de Evento para crear una SubVentana
 *  que muestra el progreso de la Tarea. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoProgreso extends Evento{

    private String clave;
    private int indice;
    private Controlador control;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     */
    public EventoProgreso(Controlador control, String clave, int indice){
        this.control = control;
        this.clave = clave;
        this.indice = indice;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            if(clave != null){

                SubVentana aviso;
                aviso = FabricaSubVentana.crear(control, "progreso", clave, indice);

                SingletonPila pila;
                pila = SingletonPila.getInstancia();
                pila.agregar(aviso);

            } // Acaba: != null

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
