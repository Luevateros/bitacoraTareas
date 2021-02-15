import javafx.scene.layout.VBox;

/**
 *	Interfaz para crear el bloque adecuado para cada Tarea.
 *	Parte de Strategy.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public interface EstrategiaBloque {
	void crearBloques(VBox v, Controlador c);
}
