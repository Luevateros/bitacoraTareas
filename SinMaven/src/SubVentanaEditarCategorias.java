import java.util.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.event.*;

/**
 *  Clase concreta de SubVentana donde se puede
 *  cambiar el nombre a las categorias.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class SubVentanaEditarCategorias extends SubVentana{

    /**
     *  Constructor de la ventana.
     * 	@param control con la lógica del proyecto.
     * 	@param anterior SubVentana que se cerrará.
     */
    public SubVentanaEditarCategorias(Controlador control, SubVentana anterior){

        /* * * * * * Título de SubVentana * * * * * */
        Label etiqueta = new Label("Editar categorias");
        etiqueta.setStyle("-fx-padding: 20 20 30 20;");

        HBox hbCabeza = new HBox();
        hbCabeza.setAlignment(Pos.CENTER);
        hbCabeza.getChildren().add(etiqueta);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Contenido central * * * * * * */

        /* * * * * * * Contenido central * * * * * * */

        VBox hbCuerpo = new VBox(10);
        hbCuerpo.setAlignment(Pos.CENTER);

        /* Se crean las filas 'TextField + " " + Color'. */
        List<TextField> tfs = agregaTxtField(hbCuerpo, control.getTags());

        /* * * * * * * * * Botones * * * * * * * * * */
        Button aceptar = new Button("Aceptar");
        Button cancela = new Button("Cancelar");
        aceptar.setStyle("-fx-padding: 10 20 20 0;");
        cancela.setStyle("-fx-padding: 10 0 20 20;");

        Evento evAcepta = FabricaEvento.crear(control, "configCategoriasAceptar", tfs, this, anterior);
        Evento evCancela = FabricaEvento.crear(control, "cancelar");
        aceptar.setOnAction(evAcepta.getEvento());
        cancela.setOnAction(evCancela.getEvento());

        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        HBox hbPie = new HBox();
        hbPie.setAlignment(Pos.CENTER);
        hbPie.getChildren().addAll(cancela, espacio, aceptar);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * Ajustes apariencia elementos * * * * * * */
        setFuente(Fuente.CABEZA.get(), ColorApp.NEGRO.getWeb(),
                  etiqueta);
        setFuente(Fuente.SUB_VEN.get(), ColorApp.NEGRO.getWeb(),
                  aceptar, cancela);
        setFondoTransparente(aceptar, cancela, hbCabeza, hbCuerpo, hbPie);
        /* * * * * * * * * * * * * * * * * * * * * * */


        /* * * * * * * Fondo SubVentana * * * * * * * */
        VBox cajaBlanca = new VBox();
        setAncho(300, cajaBlanca);
        setAlto (350, cajaBlanca);
        cajaBlanca.setAlignment(Pos.CENTER);
        cajaBlanca.setStyle("-fx-background-color: #ffeedd;");
        cajaBlanca.getChildren().addAll(hbCabeza, hbCuerpo, hbPie);

        getChildren().add(cajaBlanca);
        setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        /* * * * * * * * * * * * * * * * * * * * * * */

    }

    /**
     *  Método para crear una lista con TextField y agregar dichos
     *  objetos al cuerpo de la ventana.
     * 	@param cuerpo.
     * 	@param categorias.
     */
    private List<TextField> agregaTxtField(VBox cuerpo, List<Tag> tags){

        List<TextField> tfs = new ArrayList<>();

        for(Tag t : tags){

            String cat = t.getCategoria();
            TextField tf = new TextField(cat);
            setAncho(100, tf);
            setEventoTextFieldCategoria(tf);

            Region espacio = new Region();
            Region color = new Region();
            color.setStyle("-fx-padding: 0 0 0 0; " +
                           "-fx-background-color: " +
                            t.getColor() + " ;");
            setTamanio(25, 25, espacio, color);

            HBox hb = new HBox();
            setAncho(150, hb);
            setFondoTransparente(espacio, hb);
            hb.getChildren().addAll(tf, espacio, color);

            cuerpo.getChildren().add(hb);

            tfs.add(tf);
        }

        return tfs;

    }

    /**
     *  Método para limitar el tipo de caracters al TextField.
     * 	@param txtfld al que se ingresan caracteres.
     */
    private void setEventoTextFieldCategoria(TextField txtfld){

        txtfld.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
            public void handle(KeyEvent event) {

                if(!Character.isLetter(event.getCharacter().charAt(0)) &&
                   !Character.isDigit(event.getCharacter().charAt(0)) &&
                   !event.getCharacter().equals(" ") &&
                   txtfld.getText().length() > 7){
                    event.consume();
                } // Acaba: if

            } // Acaba: handle(KeyEvent event)

        });
    }

}
