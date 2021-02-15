import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de SubVentana donde se muestra y
 *  se puede cambiar el progreso de una Tarea particular.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaProgreso extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     */
    public SubVentanaProgreso(Controlador control, String clave, int indice){

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label("Elige el avance");
        etiqueta.setStyle("-fx-padding: 20 20 15 20;");

        HBox hbCabeza = new HBox();
        hbCabeza.setAlignment(Pos.CENTER);
        hbCabeza.getChildren().add(etiqueta);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Contenido central * * * * * * */
        double progreso = control.getProgreso(clave, indice);
        Slider slider = new Slider(0, 1, progreso);
        slider.setStyle("-fx-padding: 20 0 20 0;");
        slider.setOnMouseReleased(e -> {
            control.setProgreso(clave, indice, slider.getValue());
        });

        HBox hbCuerpo = new HBox();
        hbCuerpo.setAlignment(Pos.CENTER);
        hbCuerpo.getChildren().add(slider);
        setAncho(230, hbCuerpo);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * * * Botones * * * * * * * * * */
        Button regresar = new Button("Regresar");
        regresar.setStyle("-fx-padding: 20 0 0 0;");

        Evento evRegresar = FabricaEvento.crear("regresar");
        regresar.setOnAction(evRegresar.getEvento());

        HBox hbPie = new HBox();
        hbPie.setAlignment(Pos.CENTER);
        hbPie.getChildren().add(regresar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * Ajustes apariencia elementos * * * * * * */
        setFuente(Fuente.CABEZA.get(), ColorApp.NEGRO.getWeb(),
                  etiqueta);
        setFuente(Fuente.SUB_VEN.get(), ColorApp.NEGRO.getWeb(),
                  regresar);
        setFondoTransparente(hbCabeza, hbCuerpo, hbPie, regresar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Fondo SubVentana * * * * * * * */
        VBox cajaBlanca = new VBox();
        setAncho(300, cajaBlanca);
        setAlto (180, cajaBlanca);
        cajaBlanca.setAlignment(Pos.CENTER);
        cajaBlanca.setStyle("-fx-background-color: #ffeedd;");
        cajaBlanca.getChildren().addAll(hbCabeza, hbCuerpo, hbPie);

        getChildren().add(cajaBlanca);
        setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        /* * * * * * * * * * * * * * * * * * * * * * */
    }

}
