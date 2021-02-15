import java.util.*;

/**
 *  Interfaz usada por el Modelo y su Proxy.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public interface InterfazProxy{

	void actualizar();
	Tarea getTarea(String clave, int indice);
	List<Tag> getTags();
	List<Tarea> getUrgentes();
	List<Tarea> getPendientes();
	List<Tarea> getPasadas();
	void nuevaTarea(Tarea nueva);
	void eliminarTarea(Tarea tarea, String clave);
	void eliminarTodas();
    void eliminarUrgentes();
    void eliminarPendientes();
    void eliminarPasadas();

}
