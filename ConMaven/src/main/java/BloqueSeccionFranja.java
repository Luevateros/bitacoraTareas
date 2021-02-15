import javafx.scene.layout.*;

/**
 *	Clase para crear la sección del bloque Tarea que muestra
 *  el color de la categoría y una separación.
 *  Parte de Builder.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

 public class BloqueSeccionFranja implements BloqueSeccion{

    private HBox horizontal;

    /**
     *  Constructor de la sección del Bloque.
     * 	@param color de la franja.
     */
    public BloqueSeccionFranja(String color){

        Region franja = new Region();
        Region vacio  = new Region();
        setTamanio(franja, 12, 72);
        setTamanio(vacio,  12, 72);
        franja.setStyle("-fx-background-color: " + color + "; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 0 0 0 1; " +
                        "-fx-border-color: black;" +
                        "-fx-padding: 0 0 0 0; ");
        vacio.setStyle( "-fx-padding: 0 0 0 0;" +
                        "-fx-background-color: transparent;");

        horizontal = new HBox();
        horizontal.getChildren().addAll(franja, vacio);
        horizontal.setStyle("-fx-background-color: transparent;" +
                            "-fx-background: transparent;" +
                            "-fx-padding: 0 0 0 0;");
    }

    /**
     *  Devuelve la parte.
     * 	@return boton.
     */
    public Region getRegion(){
        return horizontal;
    }

    /**
     *  Método par asignar el ancho y alto a una región.
     * 	@return r region.
     * 	@return ancho.
     * 	@return alto.
     */
    private void setTamanio(Region r, int ancho, int alto){
        r.setMinSize(ancho, alto);
        r.setMaxSize(ancho, alto);
    }

}
