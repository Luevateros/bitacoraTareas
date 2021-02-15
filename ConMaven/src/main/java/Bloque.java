import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;

/**
 *  Clase que representa a una Tarea en pantalla.
 *  Parte de Builder.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

 public class Bloque{

	private GridPane principal;
    private Region franja;
    private Region datos;
    private Region tiempo;
    private Region editar;
    private Region check;

	/**
     *  Constructor de Bloque.
     */
	public Bloque(){
		principal = new GridPane();
		principal.setAlignment(Pos.CENTER);
		principal.setMinSize(324, 72);
        principal.setMaxSize(324, 72);
	}

    /**
     *  Método que asigna la parte correspondiente a la franja de color (Tag).
     *  @param sc parte del bloque con la representación gráfica de la franja.
     */
	public void setSeccionTag(BloqueSeccionFranja sc){
        this.franja = sc.getRegion();
	}

    /**
     *  Método que asigna la parte correspondiente a los datos (más adelante sera Estudio).
     *  @param se parte del bloque con la representación gráfica de los datos y progreso.
     */
	public void setSeccionEstudio(BloqueSeccionDatos se){
        this.datos = se.getRegion();
	}

    /**
     *  Método que asigna la parte correspondiente al tiempo.
     *  @param sr parte del bloque con la representación gráfica del tiempo.
     */
	public void setSeccionTiempo(BloqueSeccionTiempo sr){
        this.tiempo = sr.getRegion();
	}

    /**
     *  Método que asigna la parte correspondiente a la configuración de la tarea.
     *  @param se parte del bloque con la representación gráfica del botón para editar.
     */
	public void setSeccionConfig(BloqueSeccionEdicion se){
        this.editar = se.getRegion();
	}

    /**
     *  Método que asigna la parte correspondiente a la caja check.
     *  @param sc parte del bloque con la representación gráfica de la caja check.
     */
	public void setSeccionCheck(BloqueSeccionCheck sc){
        this.check = sc.getRegion();
	}

    /**
     *  Método que asigna el color y la opacidad del bloque.
     *  @param color.
     *  @param esOpaco dice si el bloque es opaco o translucido.
     */
	public void setFondo(String color, boolean esOpaco){
		String hexa = color + (esOpaco ? "" : "77");
		principal.setStyle("-fx-background-color: " + hexa + ";");
	}

    /**
     *  Método que asigna el color y la opacidad del bloque.
     *  @return GridPane gráfico con los datos de la tarea.
     */
	public GridPane getGridPane(){

        principal.setConstraints(franja,  0, 0,  1, 2);
        principal.setConstraints(datos,   1, 0,  9, 2);
		principal.setConstraints(tiempo, 10, 0,  2, 2);
		principal.setConstraints(editar, 13, 0,  1, 1);
		principal.setConstraints(check,  13, 1,  1, 1);
        principal.getChildren().addAll(franja, datos, tiempo, editar, check);

		return principal;
	}

}
