import java.util.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

/**
 *	Estrategía que elige la lista de Tareas y el método del
 *	Builder correspondiente para crear una bloque de tarea urgente.
 *	Parte de Strategy.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EstrategiaUrgente implements EstrategiaBloque {

	/**
     *  Método para crear los bloques de las tareas urgentes.
	 *	@param vertical panel para guardar las tareas.
	 *	@param control con la lógica del proyecto.
	 */
	public void crearBloques(VBox vertical, Controlador control){

		BuilderBloque constructor = new BuilderBloque();
		List<Tarea> urgentes = control.getUrgentes();
		List<Tag> tags = control.getTags();

		if(!urgentes.isEmpty()){
			int i = 0;
			for(Tarea t : urgentes){
				GridPane urgente = constructor.crearUrgente(t, i++, tags, control);
				vertical.getChildren().add(urgente);
				VBox.setMargin(urgente, new Insets(0, 0, 12, 0));

			} // Acaba: for

		} // Acaba: if

	} // Acaba: crearBloques

}
