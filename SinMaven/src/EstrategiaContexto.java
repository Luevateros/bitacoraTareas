import javafx.scene.layout.VBox;

/**
 *	Clase para ejecutar la estrategia en tiempo de ejecución.
 *	Parte de Strategy.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EstrategiaContexto{

	private EstrategiaBloque estrategia;
	private Controlador control;

	/**
     *  Método para asignar la estrategia adecuada.
	 *	@param estrategia para crear los bloques.
	 *	@param control con la lógica del proyecto.
	 */
	public void setEstrategia(EstrategiaBloque estrategia, Controlador control){
		this.estrategia  = estrategia;
		this.control     = control;
	}

	/**
     *  Método para que la estrategia construya los bloques.
	 *	@param vertical panel para guardar las tareas.
	 */
	public void agregarTareas(VBox vertical){
		estrategia.crearBloques(vertical, control);
	}

}
