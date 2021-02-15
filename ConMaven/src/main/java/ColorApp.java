import javafx.scene.paint.Color;

/**
 *  Enum que guarda los colores que se usan en el proyecto.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public enum ColorApp {

    VERDE     ("#3aaa81"),// Fondo
    PURPURA   ("#8c92e2"),

    LILA      ("#9966ff"),// Tags
    CORAL     ("#ef609a"),
    NARANJA   ("#fc9e49"),
    MIEL      ("#fcd842"),
    LIMON     ("#99ff66"),
    AZUL      ("#37dddd"),

    OLIVO     ("#66cc99"),// Progreso
    MANDARINA ("#fc5d38"),
    GRIS      ("#bcbcbc"),

    PLATANO   ("#fcff83"),// Bloque
    BLANCO    ("#ffffff"),

    CEREZA    ("#ff0000"),// Tiempo
    NEGRO     ("#000000");

    /* Valor hexadecimal del color. */
	private String hexa;

    /**
     *  Constructor privado.
     * 	@param hexa valor del color.
     */
	private ColorApp (String hexa){
		this.hexa = hexa;
	}

    /**
     *  Método para obtener un objeto de la clase Color.
     *  @return Color objeto.
     */
	public Color getWeb() {
		return Color.web(hexa);
	}

    /**
     *  Método para obtener la representación hexadecimal del color.
     *  @return hexa
     */
    public String getHexa() {
		return hexa;
	}
}
