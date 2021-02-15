import javafx.scene.control.*;

/**
 *  Clase Factory que se encarga de fabricar las SubVentanas.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class FabricaSubVentana{

	/**
     *  Método para crear SubVentana que sólo muestran avisos.
     * 	@param tipo de SubVentana.
     */
	public static SubVentana crear(String tipo){

		SubVentana sv  = null;

		if("invalida".equalsIgnoreCase(tipo)){
			String titulo  = "Entrada inválida";
			String mensaje1 = "La fecha y hora";
			String mensaje2 = "no son válidas.";;
			sv = new SubVentanaAviso(titulo, mensaje1, mensaje2);
		}
		if("incompleta".equalsIgnoreCase(tipo)){
			String titulo  = "Datos incompletos";
			String mensaje1 = "Llena todas las entradas";
			sv = new SubVentanaAviso(titulo, mensaje1, "");
		}
		if("fechaIncompleta".equalsIgnoreCase(tipo)){
			String titulo  = "Fecha incompleta";
			String mensaje1 = "Debe tener formato";
			String mensaje2 = "dd-mm-aaaa";
			sv = new SubVentanaAviso(titulo, mensaje1, mensaje2);
		}
		if("horaIncompleta".equalsIgnoreCase(tipo)){
			String titulo  = "Hora incompleta";
			String mensaje1 = "Debe tener formato";
			String mensaje2 = "hh:mm";
			sv = new SubVentanaAviso(titulo, mensaje1, mensaje2);
		}
		if("fechaAnterior".equalsIgnoreCase(tipo)){
			String titulo  = "Fecha inválida";
			String mensaje1 = "La tarea se debe";
			String mensaje2 = "entregar a futuro";
			sv = new SubVentanaAviso(titulo, mensaje1, mensaje2);
		}

        return sv;

	}

	/**
     *  Método para crear SubVentana que requieren sólo el acceso al Controlador.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de SubVentana.
     */
	public static SubVentana crear(Controlador control, String tipo){

		SubVentana sv  = null;

		if("nueva".equalsIgnoreCase(tipo))
			sv = new SubVentanaNuevaTarea(control);
		if("config".equalsIgnoreCase(tipo))
			sv = new SubVentanaConfig(control);

        return sv;

	}

	/**
     *  Método para crear SubVentana que requieren la SubVentana anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de SubVentana.
	 * 	@param anterior.
     */
	public static SubVentana crear(Controlador control, String tipo, SubVentana anterior){

		SubVentana sv  = null;

		if("editarCateg".equalsIgnoreCase(tipo))
			sv = new SubVentanaEditarCategorias(control, anterior);

        return sv;

	}

	/**
     *  Método para crear SubVentana que requieren los datos de una Tarea.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de SubVentana.
	 * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
     */
	public static SubVentana crear(Controlador control, String tipo, String clave, int indice){

		SubVentana sv  = null;

		if("editar".equalsIgnoreCase(tipo))
			sv = new SubVentanaEditar(control, clave, indice);
		if("progreso".equalsIgnoreCase(tipo))
			sv = new SubVentanaProgreso(control, clave, indice);

        return sv;

	}

	/**
     *  Método para crear SubVentana que requieren los datos de una Tarea y la SubVentana anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de SubVentana.
	 * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
	 * 	@param anterior.
     */
	public static SubVentana crear(Controlador control, String tipo, String clave, int indice, SubVentana anterior){

		SubVentana sv  = null;

		if("editarEliminar".equalsIgnoreCase(tipo))
			sv = new SubVentanaEditarEliminar(control, clave, indice, anterior);

        return sv;

	}

	/**
     *  Método para crear SubVentana que requieren los datos de una Tarea y la SubVentana anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de SubVentana.
	 * 	@param a objeto con la opción del usuario.
	 * 	@param b objeto con la opción del usuario.
	 * 	@param c objeto con la opción del usuario.
	 * 	@param d objeto con la opción del usuario.
	 * 	@param anterior.
     */
	public static SubVentana crear(Controlador control, String tipo, Control a, Control b, Control c, Control d, SubVentana anterior){

		SubVentana sv  = null;

		if("configEliminar".equalsIgnoreCase(tipo))
			sv = new SubVentanaConfigEliminar(control, a, b, c, d, anterior);

        return sv;

	}

}
