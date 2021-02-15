import javafx.scene.text.Font;

/**
 *  Enum que guarda las fuentes que se usan en el proyecto.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public enum Fuente {

    TITULO  ("OpenSans-ExtraBold.ttf", 20),
    FECHA   ("OpenSans-SemiBold.ttf",  20),
    SUB_VEN ("OpenSans-Bold.ttf",      20),
    TIEMPO  ("OpenSans-Regular.ttf",   32),
    CABEZA  ("OpenSans-Regular.ttf",   24),
    TAREA   ("OpenSans-Regular.ttf",   18),
    UNIDAD  ("OpenSans-Regular.ttf",   15),
    HORA    ("OpenSans-Regular.ttf",   12);

	private String absoluta;
    private String relativa;
	private int tamanio;

    /**
     *  Constructor privado.
     * 	@param relativa ruta 'relativa' del archivo.
     * 	@param tamanio de la letra.
     */
	private Fuente (String relativa, int tamanio){
		this.relativa = relativa;
		this.tamanio  = tamanio;
        absoluta = "file:./recursos/fuentes/";

	}

    /**
     *  Devuelve un objeto de la clase Font con la fuente.
     */
	public Font get() {
		return Font.loadFont(absoluta + relativa, tamanio);
	}

}
