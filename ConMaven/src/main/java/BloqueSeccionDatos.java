import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.geometry.*;

/**
 *	Clase para crear la sección del bloque Tarea que muestra
 *  el título, la categoría y su progreso. Parte de Builder.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

 public class BloqueSeccionDatos implements BloqueSeccion{

    private Button boton;

    /**
     *  Constructor de la sección del Bloque.
     * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
     * 	@param titulo de la Tarea.
     * 	@param categoria de la Tarea.
     * 	@param colorProgreso.
     *  @param progreso de la Tarea.
     *  @param control con la lógica del proyecto.
     */
    public BloqueSeccionDatos(String clave, int indice, String titulo, String categoria, String colorProgreso, double progreso, Controlador control){

        Label datos = new Label(categoria + ": " + titulo);
        datos.setFont(Fuente.TAREA.get());
        datos.setTextFill(ColorApp.NEGRO.getWeb());
        datos.setPadding(new Insets(-6,0,0,0));
        datos.setMinSize(190, 36);
        datos.setMaxSize(190, 36);

        ProgressBar prog = new ProgressBar();
        prog.setMaxWidth(Double.MAX_VALUE);
        prog.setMaxHeight(Double.MAX_VALUE);
        prog.setProgress(progreso);
        prog.setStyle("-fx-accent: " + colorProgreso + ";" +
                      "-fx-background-color: transparent;");

        VBox datosProgreso = new VBox();
        datosProgreso.setAlignment(Pos.CENTER_LEFT);
        datosProgreso.setStyle("-fx-background-color: transparent;" +
                               "-fx-background: transparent;");
        datosProgreso.getChildren().addAll(datos, prog);

        boton = new Button();
        boton.setGraphic(datosProgreso);
        boton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY); // PROBAR -
        boton.setStyle("-fx-background-color: transparent;" +
                               "-fx-background: transparent;");

        Evento evento = FabricaEvento.crear(control, "progreso", clave, indice);
        boton.setOnAction(evento.getEvento());

    }

    /**
     *  Devuelve la parte.
     * 	@return boton.
     */
    public Region getRegion(){
        return boton;
    }

}
