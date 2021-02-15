import java.util.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

/**
 *	Estrategía que elige la lista de Tareas y el método del
 *	Builder correspondiente para crear una bloque de tarea pasada.
 *	Parte de Strategy.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EstrategiaPasada implements EstrategiaBloque {

	/**
     *  Método para crear los bloques de las tareas pasadas.
	 *	@param vertical panel para guardar las tareas.
	 *	@param control con la lógica del proyecto.
	 */
	public void crearBloques(VBox vertical, Controlador control){

		BuilderBloque constructor = new BuilderBloque();
		List<Tarea> pasadas = control.getPasadas();
		List<Tag> tags = control.getTags();

		if(!pasadas.isEmpty()){
			int i = 0;
			for(Tarea t : pasadas){
				GridPane pasada = constructor.crearPasada(t, i++, tags, control);
				vertical.getChildren().add(pasada);
				VBox.setMargin(pasada, new Insets(0, 0, 12, 0));

			} // Acaba: for

		} // Acaba: if

	} // Acaba: crearBloques

}
