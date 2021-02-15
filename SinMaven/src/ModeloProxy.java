import java.util.*;
import java.time.LocalDateTime;

/**
*	Intermediario del Controlador para que este acceda y modificar
*	una Tarea en particular en Modelo. Así se impide que la Vista
*	tenga acceso a una referencia (de la Tarea) dentro del Modelo.
*	Como sólo existe para acciones específicas, sólo se crea
*	en tiempo de ejecución.
* 	@author Ricardo A. Luévano B.
* 	@since 1.0
*/

public class ModeloProxy implements InterfazProxy{
	private Modelo modeloReal;
	private Tarea temporal;

	/**
	*	Constructor para obtener datos de una Tarea.
	*/
	public ModeloProxy(Modelo modeloReal, String clave, int indice){
		this.modeloReal = modeloReal;
		temporal = getTarea(clave, indice);
	}

	/**
	*	Constructor para actualizar y obtener listas.
	*/
	public ModeloProxy(Modelo modeloReal){
		this.modeloReal = modeloReal;
		temporal = null;
	}

	/**
     *  Método para actualiza al Modelo.
     */
	public void actualizar(){
		modeloReal.actualizar();
	}

	/**
     *  Método para obtener la Tarea del Modelo.
	 * 	@param clave para buscar la Tarea a modificar.
	 * 	@param indice para buscar la Tarea a modificar.
     */
	public Tarea getTarea(String clave, int indice){
		return modeloReal.getTarea(clave, indice);
	}

	/**
     *  Método para obtener una copia de los Tags.
	 * 	No devuelve la lista orignal (y sus referencias).
     */
	public List<Tag> getTags(){
		List<Tag> tags = modeloReal.getTags();
		if(!tags.isEmpty())
            return List.copyOf(tags);
        List<Tag> vacia = new ArrayList<>();
        return vacia;
	}

	/**
     *  Método para obtener una copia de las Tareas urgentes.
	 * 	No devuelve la lista orignal (y sus referencias).
     */
	public List<Tarea> getUrgentes(){
		List<Tarea> urgentes = modeloReal.getUrgentes();
		if(!urgentes.isEmpty())
            return List.copyOf(urgentes);
        List<Tarea> vacia = new ArrayList<>();
        return vacia;
	}

	/**
     *  Método para obtener una copia de las Tareas pendientes.
	 * 	No devuelve la lista orignal (y sus referencias).
     */
	public List<Tarea> getPendientes(){
		List<Tarea> pendientes = modeloReal.getPendientes();
		if(!pendientes.isEmpty())
            return List.copyOf(pendientes);
        List<Tarea> vacia = new ArrayList<>();
        return vacia;
	}

	/**
     *  Método para obtener una copia de las Tareas pasadas.
	 * 	No devuelve la lista orignal (y sus referencias).
     */
	public List<Tarea> getPasadas(){
		List<Tarea> pasadas = modeloReal.getPasadas();
		if(!pasadas.isEmpty())
            return List.copyOf(pasadas);
        List<Tarea> vacia = new ArrayList<>();
        return vacia;
	}

	/**
     *  Método para agregar una Tarea nueva al Modelo.
	 *  Método de la InterfazModelo.
	 * 	@param nueva
     */
	public void nuevaTarea(Tarea nueva){
		modeloReal.nuevaTarea(nueva);
	}

	/**
     *  Método para eliminar una Tarea en el Modelo.
	 *  Método de la InterfazModelo.
	 * 	@param tarea
	 * 	@param clave de a qué lista pertenece.
     */
	public void eliminarTarea(Tarea tarea, String clave){
		modeloReal.eliminarTarea(tarea, clave);
	}

	/**
     *  Método para eliminar todas las Tareas.
     */
	public void eliminarTodas(){
        modeloReal.eliminarTodas();
    }

	/**
     *  Método para eliminar las Tareas urgentes.
     */
	public void eliminarUrgentes(){
        modeloReal.eliminarUrgentes();
    }

	/**
     *  Método para eliminar las Tareas pendientes.
     */
	public void eliminarPendientes(){
        modeloReal.eliminarPendientes();
    }

	/**
     *  Método para eliminar las Tareas pasadas.
     */
	public void eliminarPasadas(){
        modeloReal.eliminarPasadas();
    }

	/**
     *  Método para obtener el titulo de la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
	 *	@return titulo
     */
	public String getTitulo(){
		return temporal.getTitulo();
	}

	/**
     *  Método para obtener la categoria de la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
	 *	@return categoria
     */
	public String getCategoria(){
		List<Tag> tags = modeloReal.getTags();
		Tag t = tags.get(temporal.getIndice());
		return t.getCategoria();
	}

	/**
     *  Método para obtener el color de la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
	 *	@return color
     */
	public String getColor(){
		List<Tag> tags = modeloReal.getTags();
		Tag t = tags.get(temporal.getIndice());
		return t.getColor();
	}

	/**
     *  Método para obtener la fecha de la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
	 *	@return fecha
     */
	public String getFecha(){
		return temporal.getFecha();
	}

	/**
     *  Método para obtener la hora de la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
	 *	@return hora
     */
	public String getHora(){
		return temporal.getHora();
	}

	/**
     *  Método para obtener el progreso de la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
	 *	@return progreso
     */
	public double getProgreso(){
		return temporal.getProgreso();
	}

	/**
     *  Método para asignar el progreso de la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
	 *	@param progreso
     */
	public void setProgreso(double progreso){
		temporal.setProgreso(progreso);
		actualizar();

		/* Se notifica en caso de que el 'progreso'
			no se tanto que complete la Tarea. */
		modeloReal.notificar();
	}

	/**
     *  Método para completar la Tarea temporal.
	 *  La temporal es la tarea que se obtuvo inicio.
     */
	public void setCompleta(){
		temporal.setProgreso(1.0);
		actualizar();
	}

	/**
     *  Método para crear y agregar una Tarea nueva al Modelo.
	 *  Usa el método de la InterfazModelo.
	 * 	@param titulo
	 * 	@param categoria
	 * 	@param fecha
	 * 	@param hora
     */
	public void nuevaTarea(String titulo, String categoria, String fecha, String hora){
		int i = buscaIndice(categoria);
		nuevaTarea(new Tarea(titulo, fecha, hora, i));
	}

	/**
     *  Método para editar todos los atributos de la Tarea temporal.
	 * 	@param clave de la lista.
	 * 	@param titulo
	 * 	@param categoria
	 * 	@param fecha
	 * 	@param hora
     */
	public void editarTarea(String clave, String titulo, String categoria, String fecha, String hora){
        temporal.setTitulo(titulo);
		temporal.setFecha(fecha);
		temporal.setHora(hora);
		temporal.setIndice(buscaIndice(categoria));
		LocalDateTime ahora  = LocalDateTime.now();
		LocalDateTime futuro = temporal.getFechaHora();
		if(clave.equalsIgnoreCase("pasada") && (temporal.getProgreso() >= 1.0 || futuro.isAfter(ahora))){
			temporal.setProgreso(0.0);
			nuevaTarea(temporal);
			eliminarTarea(temporal, "pasada");
		}
		else{
			actualizar();
			modeloReal.ordenar();
		}
    }

	/**
     *  Método para eliminar una Tarea nueva al Modelo.
	 *  Usa el método de la InterfazModelo.
	 * 	@param clave de la lista
     */
	public void eliminarTarea(String clave){
        eliminarTarea(temporal, clave);
    }

	/**
     *  Método para asignar las nuevas categorias.
	 *	Necesariamente la lista 'nueva' y 'tags' tienen
	 *	la misma longitud, pues 'nueva' se crea de 'tags'
	 * 	@param nueva
     */
	public void setNuevaCategoria(List<String> nueva){

		boolean cambios = false;
		List<Tag> tags = modeloReal.getTags();
		for(int i = 0; i < tags.size(); i++){
			String s = nueva.get(i);
			if(!s.equals("")){
				Tag t = tags.get(i);
				t.setCategoria(s);
				cambios = true;
			}
		}
		if(cambios)
			modeloReal.notificar();

    }

	/**
     *  Método para obtener el índice que representa la Tag
	 * 	@return el índice
     */
	private int buscaIndice(String categoria){

		List<Tag> tags = modeloReal.getTags();
		for(int i = 0; i < tags.size(); i++){
			Tag t = tags.get(i);
			if(t.getCategoria().equals(categoria))
				return i;
		}
		return 0;

	}

}
