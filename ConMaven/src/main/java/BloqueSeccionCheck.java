import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 *	Clase para crear la sección del bloque Tarea que muestra
 *  el botón para editar los datos de la Tarea.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

 public class BloqueSeccionCheck implements BloqueSeccion{

    private HBox central;

    /**
     *  Constructor de la sección del Bloque.
     * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
     *  @param estaCompleta dice si la Tarea está completa o no.
     *  @param control con la lógica del proyecto.
     */
    public BloqueSeccionCheck(String clave, int indice, boolean estaCompleta, Controlador control){

        CheckBox check = new CheckBox();
        check.setSelected(estaCompleta);

        Evento evento = FabricaEvento.crear(control, "check", clave, indice, check);
        check.setOnAction(evento.getEvento());

        central = new HBox(check);
        central.setAlignment(Pos.CENTER);
        central.setMinSize(36, 36);
        central.setMaxSize(36, 36);

    }

    /**
     *  Devuelve la parte.
     * 	@return central.
     */
    public Region getRegion(){
        return central;
    }

}
