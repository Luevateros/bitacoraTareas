import java.util.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;

/**
 *  Clase abstracta de la cual se fabrican clases concretas
 *  del tipo SubVentana. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public abstract class SubVentana extends StackPane{

    /**
     *  Método para crear cajas con las 'n' categorias.
     * 	@param cmb objeto donde se guardan las categorias.
     * 	@param tags lista con categorias.
     */
    protected void agregaOpciones(ComboBox<String> cmb, List<Tag> tags){
        if(!tags.isEmpty())
            for(Tag t : tags){
                String c = t.getCategoria();
                if(!c.equals(""))
                    cmb.getItems().add(c);
            }
    }

    /**
     *  Método para ajustar el tamaño de varios objetos del tipo Region.
     * 	@param ancho.
     * 	@param alto.
     * 	@param regiones.
     */
    protected void setTamanio(int ancho, int alto, Region... regiones){
        for (Region r : regiones){
            r.setMinSize(ancho, alto);
            r.setMaxSize(ancho, alto);
        }
    }

    /**
     *  Método para ajustar las fuentes de varios objetos del tipo Labeled.
     * 	@param f fuente.
     * 	@param c color.
     * 	@param lbs etiquetas.
     */
    protected void setFuente(Font f, Color c, Labeled... lbs){
        for (Labeled l : lbs){
            l.setFont(f);
            l.setTextFill(c);
        }
    }

    /**
     *  Método para ajustar el ancho de varios objetos del tipo Region.
     * 	@param ancho.
     * 	@param regiones.
     */
    protected void setAncho(int ancho, Region... regiones){
        for (Region r : regiones){
            r.setMinWidth(ancho);
            r.setMaxWidth(ancho);
        }

    }

    /**
     *  Método para ajustar el alto de varios objetos del tipo Region.
     * 	@param alto.
     * 	@param regiones.
     */
    protected void setAlto(int alto, Region... regiones){
        for (Region r : regiones){
            r.setMinHeight(alto);
            r.setMaxHeight(alto);
        }

    }

    /**
     *  Método para ajustar el fondo transparente de varios objetos del tipo Region.
     * 	@param regiones.
     */
    protected void setFondoTransparente(Region... regiones){
        for (Region r : regiones)
            r.setStyle("-fx-background-color: transparent;" +
                        "-fx-background: transparent;");
    }

    /**
     *  Método para ajustar el ancho a 'prioritario'.
     * 	@param regiones.
     */
    protected void setAnchoPrioritario(Region... regiones){
        for (Region r : regiones)
            HBox.setHgrow(r, Priority.ALWAYS);
    }

    /**
     *  Método que permite ingresar letras, números, ' ' y menos de 15 caracteres.
     * 	@param txtfld caja donde se ingresan datos.
     */
    protected void setEventoTextFieldTitulo(TextField txtfld){

        txtfld.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
            public void handle(KeyEvent event) {

                if(!Character.isLetter(event.getCharacter().charAt(0)) &&
                   !Character.isDigit(event.getCharacter().charAt(0)) &&
                   !event.getCharacter().equals(" ") &&
                   txtfld.getText().length() > 14){
                    event.consume();
                } // Acaba: if

            } // Acaba: handle(KeyEvent event)

        });
    }

    /**
     *  Método que permite ingresar números, '-' y menos de 10 caracteres.
     * 	@param txtfld caja donde se ingresan datos.
     */
    protected void setEventoTextFieldFecha(TextField txtfld){

        txtfld.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
            public void handle(KeyEvent event) {

                if(!Character.isDigit(event.getCharacter().charAt(0)) &&
                   !event.getCharacter().equals("-")  &&
                   txtfld.getText().length() > 9){
                    event.consume();
                } // Acaba: if

            } // Acaba: handle(KeyEvent event)

        });
    }

    /**
     *  Método que permite ingresar números, ':' y menos de 5 caracteres
     * 	@param txtfld caja donde se ingresan datos.
     */
    protected void setEventoTextFieldHora(TextField txtfld){

        txtfld.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
            public void handle(KeyEvent event) {

                if(!Character.isDigit(event.getCharacter().charAt(0)) &&
                   !event.getCharacter().equals(":") &&
                   txtfld.getText().length() > 4){
                    event.consume();
                } // Acaba: if

            } // Acaba: handle(KeyEvent event)

        });
    }

}
