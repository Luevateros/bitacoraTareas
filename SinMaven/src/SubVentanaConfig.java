import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de SubVentana donde se muestra
 *  los ajustes globales de la app.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaConfig extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param control con la lógica del proyecto.
     */
    public SubVentanaConfig(Controlador control){

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label("Configuraciones");
        etiqueta.setStyle("-fx-padding: 20 20 30 20;");

        HBox hbCabeza = new HBox();
        hbCabeza.setAlignment(Pos.CENTER);
        hbCabeza.getChildren().add(etiqueta);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Contenido central * * * * * * */

        /* Etiquetas. */
        Label labUrgent = new Label("Eliminar urgentes");
        Label labPendie = new Label("Eliminar pendientes");
        Label labPasada = new Label("Eliminar pasadas");
        Label labTodas  = new Label("Eliminar todas");

        /* Espacios entre etiquetas y cajas de texto. */
        Region espUrgent = new Region();
        Region espPendie = new Region();
        Region espPasada = new Region();
        Region espTodas  = new Region();
        Region espacio   = new Region();
        setAnchoPrioritario(espUrgent, espPendie, espPasada, espTodas, espacio);

        /* Cajas de texto donde el usuario ingresa los datos. */
        CheckBox cbUrgent = new CheckBox();
        CheckBox cbPendie = new CheckBox();
        CheckBox cbPasada = new CheckBox();
        CheckBox cbTodas  = new CheckBox();

        /* Cada HBox corresponde a una 'etiqueta + textField'. */
        HBox boxUrgent = new HBox();
        HBox boxPendie = new HBox();
        HBox boxPasada = new HBox();
        HBox boxTodas  = new HBox();
        boxUrgent.getChildren().addAll(labUrgent, espUrgent, cbUrgent);
        boxPendie.getChildren().addAll(labPendie, espPendie, cbPendie);
        boxPasada.getChildren().addAll(labPasada, espPasada, cbPasada);
        boxTodas .getChildren().addAll(labTodas,  espTodas,  cbTodas);

        /* Botón 'Editar categorias' */
        Button editar = new Button("Editar categorias");

        Evento evEdita = FabricaEvento.crear(control, "configCategorias", this);
        editar.setOnAction(evEdita.getEvento());

        HBox boxEditar = new HBox();
        boxEditar.setAlignment(Pos.CENTER);
        boxEditar.getChildren().add(editar);

        setAncho(230, boxUrgent, boxPendie, boxPasada, boxTodas, boxEditar);

        VBox hbCuerpo = new VBox(10);
        hbCuerpo.setAlignment(Pos.CENTER);
        hbCuerpo.getChildren().addAll(boxUrgent, boxPendie, boxPasada, boxTodas, boxEditar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * * * Botones * * * * * * * * * */
        Button aceptar = new Button("Aceptar");
        Button cancela = new Button("Cancelar");
        aceptar.setStyle("-fx-padding: 10 20 20 0;");
        cancela.setStyle("-fx-padding: 10 0 20 20;");

        Evento evAcepta = FabricaEvento.crear(control, "configAceptar", cbUrgent, cbPendie, cbPasada, cbTodas, this);
        Evento evCancel = FabricaEvento.crear(control, "cancelar");
        aceptar.setOnAction(evAcepta.getEvento());
        cancela.setOnAction(evCancel.getEvento());

        HBox hbPie = new HBox();
        hbPie.setAlignment(Pos.CENTER);
        hbPie.getChildren().addAll(cancela, espacio, aceptar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * Ajustes apariencia elementos * * * * * * */
        setFuente(Fuente.CABEZA.get(), ColorApp.NEGRO.getWeb(),
                  etiqueta);
        setFuente(Fuente.TAREA.get(), ColorApp.NEGRO.getWeb(),
                  labUrgent, labPendie, labPasada, labTodas);
        setFuente(Fuente.SUB_VEN.get(), ColorApp.NEGRO.getWeb(),
                  editar, aceptar, cancela);
        setFondoTransparente(boxUrgent, boxPendie, boxPasada, boxTodas, boxEditar, aceptar, cancela, editar, hbCabeza, hbCuerpo, hbPie);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Fondo SubVentana * * * * * * * */
        VBox cajaBlanca = new VBox();
        setAncho(300, cajaBlanca);
        setAlto (320, cajaBlanca);
        cajaBlanca.setAlignment(Pos.CENTER);
        cajaBlanca.setStyle("-fx-background-color: #ffeedd;");
        cajaBlanca.getChildren().addAll(hbCabeza, hbCuerpo, hbPie);

        getChildren().add(cajaBlanca);
        setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        /* * * * * * * * * * * * * * * * * * * * * * */

    }

}
