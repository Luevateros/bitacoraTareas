import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de SubVentana donde se muestra un
 *  aviso para valores inválidos o alertas.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaAviso extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param titulo de la SubVentana.
     * 	@param mensaje1 superior.
     * 	@param mensaje2 inferior.
     */
    public SubVentanaAviso(String titulo, String mensaje1, String mensaje2){

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label(titulo);
        etiqueta.setStyle("-fx-padding: 20 20 15 20;");

        HBox hbCabeza = new HBox();
        hbCabeza.setAlignment(Pos.CENTER);
        hbCabeza.getChildren().add(etiqueta);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Contenido central * * * * * * */
        Label mnsj1 = new Label(mensaje1);
        Label mnsj2 = new Label(mensaje2);

        VBox hbCuerpo = new VBox();
        hbCuerpo.setAlignment(Pos.CENTER);
        hbCuerpo.getChildren().addAll(mnsj1, mnsj2);
        setAncho(230, hbCuerpo);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * * * Botones * * * * * * * * * */
        Button regresar = new Button("Regresar");
        regresar.setStyle("-fx-padding: 10 0 0 0;");

        Evento evRegresa = FabricaEvento.crear("regresar");
        regresar.setOnAction(evRegresa.getEvento());

        HBox hbPie = new HBox();
        hbPie.setAlignment(Pos.CENTER);
        hbPie.getChildren().add(regresar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * Ajustes apariencia elementos * * * * * * */
        setFuente(Fuente.CABEZA.get(), ColorApp.NEGRO.getWeb(),
                  etiqueta);
        setFuente(Fuente.TAREA.get(), ColorApp.NEGRO.getWeb(),
                  mnsj1, mnsj2);
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
