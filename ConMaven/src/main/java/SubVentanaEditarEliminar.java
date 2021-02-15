import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de SubVentana donde se muestra un
 *  mensaje antes de eliminar una Tarea.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaEditarEliminar extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     * 	@param anterior SubVentana que se cerrará.
     */
    public SubVentanaEditarEliminar(Controlador control, String clave, int indice, SubVentana anterior){

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label("Eliminar tarea");
        etiqueta.setStyle("-fx-padding: 20 20 15 20;");

        HBox hbCabeza = new HBox();
        hbCabeza.setAlignment(Pos.CENTER);
        hbCabeza.getChildren().add(etiqueta);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Contenido central * * * * * * */
        String titulo = control.getTitulo(clave, indice);
        String catego = control.getCategoria(clave, indice);

        /* Etiquetas. */
        Label lb1 = new Label("¿Quieres eliminar");
        Label lb2 = new Label(catego + ": " + titulo + "?");

        VBox hbCuerpo = new VBox();
        hbCuerpo.setAlignment(Pos.CENTER);
        hbCuerpo.getChildren().addAll(lb1, lb2);
        setAncho(230, hbCuerpo);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * * * Botones * * * * * * * * * */
        Button aceptar = new Button("Aceptar");
        Button cancela = new Button("Cancelar");
        aceptar.setStyle("-fx-padding: 10 20 20 0;");
        cancela.setStyle("-fx-padding: 10 0 20 20;");

        Evento evAcepta = FabricaEvento.crear(control, "editarEliminarAceptar", clave, indice, this, anterior);
        Evento evCancel = FabricaEvento.crear(control, "cancelar");
        aceptar.setOnAction(evAcepta.getEvento());
        cancela.setOnAction(evCancel.getEvento());

        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        HBox hbPie = new HBox();
        hbPie.setAlignment(Pos.CENTER);
        hbPie.getChildren().addAll(cancela, espacio, aceptar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * Ajustes apariencia elementos * * * * * * */
        setFuente(Fuente.CABEZA.get(), ColorApp.NEGRO.getWeb(),
                  etiqueta);
        setFuente(Fuente.TAREA.get(), ColorApp.NEGRO.getWeb(),
                  lb1, lb2);
        setFuente(Fuente.SUB_VEN.get(), ColorApp.NEGRO.getWeb(),
                  aceptar, cancela);
        setFondoTransparente(aceptar, cancela, hbCabeza, hbCuerpo, hbPie);
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
