import javafx.scene.layout.Region;
import javafx.scene.control.*;
import java.util.*;

/**
 *  Clase Factory que se encarga de fabricar los Eventos.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class FabricaEvento{

	/**
     *  Método para crear un Evento para regresar.
     * 	@param tipo de Evento.
     */
	public static Evento crear(String tipo){

		Evento ev = null;

		if("regresar".equalsIgnoreCase(tipo))
			ev = new EventoRegresar();

		return ev;

	}

	/**
     *  Método para crear un Evento que requieren sólo el acceso al Controlado.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
     */
	public static Evento crear(Controlador control, String tipo){

		Evento ev = null;

		if("regresar".equalsIgnoreCase(tipo))
			ev = new EventoRegresar();
		if("cancelar".equalsIgnoreCase(tipo))
			ev = new EventoCancelar(control);
		if("nueva".equalsIgnoreCase(tipo))
			ev = new EventoAgregarSubVentana(control, "nueva");
		if("config".equalsIgnoreCase(tipo))
			ev = new EventoAgregarSubVentana(control, "config");

		return ev;

	}

	/**
	 *  Método para crear Evento que requieren la SubVentana anterior.
	 * 	@param control con la lógica del proyecto.
	 * 	@param tipo de Evento.
	 * 	@param anterior.
	 */
	public static Evento crear(Controlador control, String tipo, SubVentana anterior){

		Evento ev = null;

		if("configCategorias".equalsIgnoreCase(tipo))
			ev = new EventoConfigCategorias(control, anterior);

		return ev;

	}

	/**
     *  Método para crear Evento que requieren la SubVentana anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param tfs objetos que guardan lo ingresado por el usuario.
	 * 	@param esta SubVentana a eliminar.
	 * 	@param anterior SubVentana a eliminar.
     */
	public static Evento crear(Controlador control, String tipo, List<TextField> tfs, SubVentana esta, SubVentana anterior){

		Evento ev = null;

		if("configCategoriasAceptar".equalsIgnoreCase(tipo))
			ev = new EventoConfigCategoriasAceptar(control, tfs, esta, anterior);

		return ev;

	}

	/**
     *  Método para crear Evento que requieren los datos de una Tarea.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
     */
	public static Evento crear(Controlador control, String tipo, String clave, int indice){

		Evento ev = null;

		if("editar".equalsIgnoreCase(tipo))
			ev = new EventoEditar(control, clave, indice);
		if("progreso".equalsIgnoreCase(tipo))
			ev = new EventoProgreso(control, clave, indice);

		return ev;

	}

	/**
     *  Método para crear Evento que requieren los datos de una Tarea.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
	 * 	@param anterior SubVentana a eliminar.
     */
	public static Evento crear(Controlador control, String tipo, String clave, int indice, SubVentana anterior){

		Evento ev = null;
		if("editarEliminar".equalsIgnoreCase(tipo))
			ev = new EventoEditarEliminar(control, clave, indice, anterior);
		return ev;

	}

	/**
     *  Método para crear Evento que requiere esta SubVentana y la anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
	 * 	@param esta SubVentana a eliminar.
	 * 	@param anterior SubVentana a eliminar.
     */
	public static Evento crear(Controlador control, String tipo, String clave, int indice, SubVentana esta, SubVentana anterior){

		Evento ev = null;
		if("editarEliminarAceptar".equalsIgnoreCase(tipo))
			ev = new EventoEditarEliminarAceptar(control, clave, indice, esta, anterior);
		return ev;

	}

	/**
     *  Método para crear Evento que requiere esta SubVentana y la anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
	 * 	@param region con el valor guardado.
     */
	public static Evento crear(Controlador control, String tipo, String clave, int indice, Region region){

		Evento ev = null;

		if("check".equalsIgnoreCase(tipo))
			ev = new EventoCheck(control, clave, indice, region);

		return ev;

	}

	/**
     *  Método para crear Evento que requiere esta SubVentana y la anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param clave para acceder a la Tarea.
	 * 	@param indice para acceder a la Tarea.
	 * 	@param a guarda valores ingresados por el usuario.
     * 	@param b guarda valores ingresados por el usuario.
     * 	@param c guarda valores ingresados por el usuario.
     * 	@param d guarda valores ingresados por el usuario.
	 * 	@param anterior SubVentana a eliminar.
     */
	public static Evento crear(Controlador control, String tipo, Control a, Control b, Control c, Control d, SubVentana anterior){

		Evento ev = null;

		if("nuevaAceptar".equalsIgnoreCase(tipo))
			ev = new EventoNuevaAceptar(control, a, b, c, d, anterior);
		if("configAceptar".equalsIgnoreCase(tipo))
			ev = new EventoConfigAceptar(control, a, b, c, d, anterior);

		return ev;

	}

	/**
     *  Método para crear Evento que requiere esta SubVentana y la anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param a guarda valores ingresados por el usuario.
     * 	@param b guarda valores ingresados por el usuario.
     * 	@param c guarda valores ingresados por el usuario.
     * 	@param d guarda valores ingresados por el usuario.
     */
	public static Evento crear(Controlador control, String tipo, String clave, int indice, Control a, Control b, Control c, Control d){

		Evento ev = null;
		if("editarAceptar".equalsIgnoreCase(tipo))
			ev = new EventoEditarAceptar(control, clave, indice, a, b, c, d);
		return ev;

	}

	/**
     *  Método para crear Evento que requiere esta SubVentana y la anterior.
	 * 	@param control con la lógica del proyecto.
     * 	@param tipo de Evento.
	 * 	@param a guarda valores ingresados por el usuario.
     * 	@param b guarda valores ingresados por el usuario.
     * 	@param c guarda valores ingresados por el usuario.
     * 	@param d guarda valores ingresados por el usuario.
	 * 	@param esta SubVentana a eliminar.
	 * 	@param anterior SubVentana a eliminar.
     */
	public static Evento crear(Controlador control, String tipo, Control a, Control b, Control c, Control d, SubVentana esta, SubVentana anterior){

		Evento ev = null;


		if("configEliminarAceptar".equalsIgnoreCase(tipo))
			ev = new EventoConfigEliminarAceptar(control, a, b, c, d, esta, anterior);

        return ev;

	}

}
