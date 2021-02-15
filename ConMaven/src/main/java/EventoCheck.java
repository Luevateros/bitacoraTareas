import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

/**
 *  Clase concreta de Evento para completar
 *  una Tarea. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoCheck extends Evento{

    private Controlador control;
    private CheckBox check;
    private String clave;
    private int indice;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     * 	@param check objeto con el valor de la casilla.
     */
    public EventoCheck(Controlador control, String clave, int indice, Region check){
        this.control = control;
        this.clave = clave;
        this.indice = indice;
		this.check = (CheckBox) check;
	}

    @Override
    public EventHandler<ActionEvent> getEvento(){

		EventHandler<ActionEvent> evento = e -> {

            if (check.isSelected())
                control.setCompleta(clave, indice);

		}; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
