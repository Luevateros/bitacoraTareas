import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.ImageView;

/**
 *	Clase para crear la sección del bloque Tarea que muestra
 *  el botón para editar los datos de la Tarea.
 *  Parte de Builder.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

 public class BloqueSeccionEdicion implements BloqueSeccion{

    private Button boton;

    /**
     *  Constructor de la sección del Bloque.
     * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
     *  @param control con la lógica del proyecto.
     */
    public BloqueSeccionEdicion(String clave, int indice, Controlador control){

        boton = new Button();
        ImageView imagen = control.getImagenEn("editar");
        boton.setGraphic(imagen);
        boton.setMinSize(36, 36);
        boton.setMaxSize(36, 36);
        boton.setStyle("-fx-background-color: transparent;");

        Evento editar = FabricaEvento.crear(control, "editar", clave, indice);
        boton.setOnAction(editar.getEvento());

    }

    /**
     *  Devuelve la parte.
     * 	@return boton.
     */
    public Region getRegion(){
        return boton;
    }

}
