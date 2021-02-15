import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import java.util.*;

/**
 *  Clase concreta de SubVentana donde se puede editar
 *  o eliminar una Tarea particular.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaEditar extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     */
    public SubVentanaEditar(Controlador control, String clave, int indice){

        String titulo = control.getTitulo(clave, indice);
        String catego = control.getCategoria(clave, indice);
        String fecha  = control.getFecha(clave, indice);
        String hora   = control.getHora(clave, indice);

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label("Editar tarea");
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

        /* Espacios entre etiquetas y cajas de texto. */
        Region espTitulo = new Region();
        Region espCatego = new Region();
        Region espFecha  = new Region();
        Region espHora   = new Region();
        Region espacio   = new Region();
        setAnchoPrioritario(espTitulo, espCatego, espFecha, espHora, espacio);

        /* Cajas de texto donde el usuario ingresa los datos. */
        TextField txfTitulo = new TextField(titulo);
        TextField txfFecha  = new TextField(fecha);
        TextField txfHora   = new TextField(hora);
        ComboBox<String> cmbCatego = new ComboBox<>();
        List<Tag> tags = control.getTags();
        for(Tag t : tags){
            String c = t.getCategoria();
            if(!c.equals(""))
                cmbCatego.getItems().add(c);
        }

        cmbCatego.getSelectionModel().select(catego);
        setAncho(160, txfTitulo, txfFecha, txfHora, cmbCatego);

        /* Eventos de los TextField que NO generan nuevas
        SubVentanas (por eso no se crean con FabricaEvento)
        pero si condicionan el tipo de entrada en cada una. */
        setEventoTextFieldTitulo(txfTitulo);
        setEventoTextFieldFecha(txfFecha);
        setEventoTextFieldHora(txfHora);

        /* Cada HBox corresponde a una fila 'etiqueta + textField'. */
        HBox boxTitulo = new HBox();
        HBox boxCateg  = new HBox();
        HBox boxFecha  = new HBox();
        HBox boxHora   = new HBox();
        boxTitulo.getChildren().addAll(labTitulo, espTitulo, txfTitulo);
        boxCateg .getChildren().addAll(labCatego, espCatego, cmbCatego);
        boxFecha .getChildren().addAll(labFecha,  espFecha,  txfFecha);
        boxHora  .getChildren().addAll(labHora,   espHora,   txfHora);

        /* Botón 'Eliminar' tarea. */
        Button eliminar = new Button("Eliminar");
        eliminar.setStyle("-fx-padding: 20 0 20 0;");

        Evento evElimina = FabricaEvento.crear(control, "editarEliminar", clave, indice, this);
        eliminar.setOnAction(evElimina.getEvento());

        HBox boxElimin = new HBox();
        boxElimin.setAlignment(Pos.CENTER);
        boxElimin.getChildren().add(eliminar);

        setAncho(230, boxTitulo, boxCateg, boxFecha, boxHora, boxElimin);

        VBox hbCuerpo = new VBox(10);
        hbCuerpo.setAlignment(Pos.CENTER);
        hbCuerpo.getChildren().addAll(boxTitulo, boxCateg, boxFecha, boxHora, boxElimin);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * * * Botones * * * * * * * * * */
        Button aceptar = new Button("Aceptar");
        Button cancela = new Button("Cancelar");
        aceptar.setStyle("-fx-padding: 10 20 20 0;");
        cancela.setStyle("-fx-padding: 10 0 20 20;");

        Evento evAcepta = FabricaEvento.crear(control, "editarAceptar", clave, indice, txfTitulo, txfFecha, txfHora, cmbCatego);
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
                  labTitulo, labCatego, labFecha, labHora);
        setFuente(Fuente.SUB_VEN.get(), ColorApp.NEGRO.getWeb(),
                  eliminar, aceptar, cancela);
        setFondoTransparente(boxTitulo, boxCateg, boxFecha, boxHora, boxElimin, aceptar, cancela, eliminar, hbCabeza, hbCuerpo, hbPie);
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
