import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import java.util.*;

/**
 *  Clase concreta de SubVentana donde se puede
 *  crear una nueva Tarea. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaNuevaTarea extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param control con la lógica del proyecto.
     */
    public SubVentanaNuevaTarea(Controlador control){

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label("Nueva tarea");
        etiqueta.setStyle("-fx-padding: 20 20 30 20;");

        HBox hbCabeza = new HBox();
        hbCabeza.setAlignment(Pos.CENTER);
        hbCabeza.getChildren().add(etiqueta);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Contenido central * * * * * * */
        /* Etiquetas. */
        Label labTitulo = new Label("Título");
        Label labCatego = new Label("Categoría");
        Label labFecha  = new Label("Fecha");
        Label labHora   = new Label("Hora");
        Label sinCateg1 = new Label("Sin categorias. Tienes que");
        Label sinCateg2 = new Label("agregar nuevas en menú.");

        /* Espacios entre etiquetas y cajas de texto. */
        Region espTitulo = new Region();
        Region espCatego = new Region();
        Region espFecha  = new Region();
        Region espHora   = new Region();
        Region espacio   = new Region();
        setAnchoPrioritario(espTitulo, espCatego, espFecha, espHora, espacio);

        /* Cajas de texto donde el usuario ingresa los datos. */
        TextField txfTitulo = new TextField("Tarea pendiente");
        TextField txfFecha  = new TextField("dd-mm-aaaa");
        TextField txfHora   = new TextField("hh:mm (24 hrs)");
        ComboBox<String> cmbCatego = new ComboBox<>();

        List<Tag> tags = control.getTags();
        for(Tag t : tags){
            String c = t.getCategoria();
            if(!c.equals(""))
                cmbCatego.getItems().add(c);
        }

        setAncho(160, txfTitulo, txfFecha, txfHora, cmbCatego);

        /* Eventos de los TextField que NO generan nuevas SubVentanas
            pero condicionan el tipo de entrada en cada una. */
        setEventoTextFieldTitulo(txfTitulo);
        setEventoTextFieldFecha(txfTitulo);
        setEventoTextFieldHora(txfTitulo);

        /* Cada HBox corresponde a una fila 'etiqueta + textField'. */
        HBox boxTitulo = new HBox();
        HBox boxCatego = new HBox();
        HBox boxFecha  = new HBox();
        HBox boxHora   = new HBox();
        setAncho(260, boxTitulo, boxCatego, boxFecha, boxHora);

        boxTitulo.getChildren().addAll(labTitulo, espTitulo, txfTitulo);
        boxCatego.getChildren().addAll(labCatego, espCatego, cmbCatego);
        boxFecha .getChildren().addAll(labFecha,  espFecha,  txfFecha);
        boxHora  .getChildren().addAll(labHora,   espHora,   txfHora);

        VBox hbCuerpo = new VBox(10);
        hbCuerpo.setAlignment(Pos.CENTER);

        boolean hayTags = control.hayTags();
        if(!hayTags)
            hbCuerpo.getChildren().addAll(boxTitulo, sinCateg1,  sinCateg2, boxCatego, boxFecha, boxHora);
        else
            hbCuerpo.getChildren().addAll(boxTitulo, boxCatego, boxFecha, boxHora);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * * * Botones * * * * * * * * * */
        Button aceptar = new Button("Aceptar");
        Button cancela = new Button("Cancelar");
        aceptar.setStyle("-fx-padding: 10 20 20 0;");
        cancela.setStyle("-fx-padding: 10 0 20 20;");

        Evento evAcepta = FabricaEvento.crear(control, "nuevaAceptar", txfTitulo, txfFecha, txfHora, cmbCatego, this);
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
                  labTitulo, labCatego, labFecha, labHora, sinCateg1,  sinCateg2);
        setFuente(Fuente.SUB_VEN.get(), ColorApp.NEGRO.getWeb(),
                  aceptar, cancela);
        setFondoTransparente(boxTitulo, boxCatego, boxFecha, boxHora, aceptar, cancela, hbCabeza, hbCuerpo, hbPie);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Fondo SubVentana * * * * * * * */
        VBox cajaBlanca = new VBox();
        setAncho(300, cajaBlanca);
        if(!hayTags)
            setAlto (360, cajaBlanca);
        else
            setAlto (320, cajaBlanca);
        cajaBlanca.setAlignment(Pos.CENTER);
        cajaBlanca.setStyle("-fx-background-color: #ffeedd;");
        cajaBlanca.getChildren().addAll(hbCabeza, hbCuerpo, hbPie);

        getChildren().add(cajaBlanca);
        setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        /* * * * * * * * * * * * * * * * * * * * * * */

    }

}
