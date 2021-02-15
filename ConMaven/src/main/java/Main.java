import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

/**
 *  Clase Main del proyecto.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class Main extends Application {

    private Modelo modelo;
    private Controlador control;

    /**
     *  Método que ejecuta las instrucciones necesarias antes
     *  de que se muestren los elementos gráficos.
     */
    @Override
    public void init(){
        modelo = new Modelo();
        control = new Controlador(modelo);
    }

    /**
     *  Método que ejecuta todos los elementos en la GUI.
     */
    @Override
    public void start(Stage stage){
        control.mostrarVista(stage);
    }

    /**
     *  Método que se llama al cerrar la ventana de la app.
     */
    @Override
    public void stop(){
        control.cerrar();
    }

    public static void main(String[] args) {
        launch();
    }

}
