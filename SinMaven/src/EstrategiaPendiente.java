import java.util.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

/**
 *	Estrategía que elige la lista de Tareas y el método del
 *	Builder correspondiente para crear una bloque de tarea pendiente.
 *	Parte de Strategy.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EstrategiaPendiente implements EstrategiaBloque {

	/**
     *  Método para crear los bloques de las tareas pendientes.
	 *	@param vertical panel para guardar las tareas.
	 *	@param control con la lógica del proyecto.
	 */
	public void crearBloques(VBox vertical, Controlador control){

		BuilderBloque constructor = new BuilderBloque();
		List<Tarea> pendientes = control.getPendientes();
		List<Tag> tags = control.getTags();

		if(!pendientes.isEmpty()){

			int i = 0;
			for(Tarea t : pendientes){
				GridPane pendiente = constructor.crearPendiente(t, i++, tags, control);
				vertical.getChildren().add(pendiente);
				VBox.setMargin(pendiente, new Insets(0, 0, 20, 0));

			} // Acaba: for

		} // Acaba: if

	} // Acaba: crearBloques

}
