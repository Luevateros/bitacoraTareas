import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de SubVentana donde se muestra un
 *  aviso antes de eliminar varias Tareas.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaConfigEliminar extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param control con la lógica del proyecto.
     * 	@param urgentes guarda la respuesta a eliminar tareas urgentes.
     * 	@param pendientes guarda la respuesta a eliminar tareas pendientes.
     * 	@param pasadas guarda la respuesta a eliminar tareas pasadas.
     * 	@param todas guarda la respuesta a eliminar todas las tareas.
     * 	@param anterior SubVentana que se cerrará.
     */
    public SubVentanaConfigEliminar(Controlador control, Control urgentes, Control pendientes, Control pasadas, Control todas, SubVentana anterior){

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label("Eliminar tareas");
        etiqueta.setStyle("-fx-padding: 20 20 15 20;");

        HBox hbCabeza = new HBox();
        hbCabeza.setAlignment(Pos.CENTER);
        hbCabeza.getChildren().add(etiqueta);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Contenido central * * * * * * */
        Label mnsj1 = new Label("¿Seguro que deseas");
        Label mnsj2 = new Label("continuar?");

        VBox hbCuerpo = new VBox();
        hbCuerpo.setAlignment(Pos.CENTER);
        hbCuerpo.getChildren().addAll(mnsj1, mnsj2);
        setAncho(230, hbCuerpo);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * * * Botones * * * * * * * * * */
        Button aceptar = new Button("Aceptar");
        Button cancela = new Button("Cancelar");
        aceptar.setStyle("-fx-padding: 10 20 20 0;");
        cancela.setStyle("-fx-padding: 10 0 20 20;");

        Evento evAcepta = FabricaEvento.crear(control, "configEliminarAceptar", urgentes, pendientes, pasadas, todas, this, anterior);
        Evento evCancel = FabricaEvento.crear(control, "cancelar");
        aceptar.setOnAction(evAcepta.getEvento());
        cancela.setOnAction(evCancel.getEvento());

        Region espacio = new Region();
        setAnchoPrioritario(espacio);

        HBox hbPie = new HBox();
        hbPie.setAlignment(Pos.CENTER);
        hbPie.getChildren().addAll(cancela, espacio, aceptar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * Ajustes apariencia elementos * * * * * * */
        setFuente(Fuente.CABEZA.get(), ColorApp.NEGRO.getWeb(),
                  etiqueta);
        setFuente(Fuente.TAREA.get(), ColorApp.NEGRO.getWeb(),
                  mnsj1, mnsj2);
        setFuente(Fuente.SUB_VEN.get(), ColorApp.NEGRO.getWeb(),
                  aceptar, cancela);
        setFondoTransparente(hbCabeza, hbCuerpo, hbPie, aceptar, cancela);
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
