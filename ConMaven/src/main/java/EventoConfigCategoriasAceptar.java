import java.util.*;
import javafx.event.*;
import javafx.scene.control.TextField;

/**
 *  Clase concreta de Evento para confirmar los cambios
 *  al editar las categorias. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoConfigCategoriasAceptar extends Evento{

    private Controlador control;
    private List<TextField> tfs;
    private SubVentana esta, anterior;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param tfs objetos con las elecciones del usuario.
     * 	@param esta SubVentana que se cerrará.
     * 	@param anterior SubVentana que se cerrará.
     */
    public EventoConfigCategoriasAceptar(Controlador control, List<TextField> tfs, SubVentana esta, SubVentana anterior){
        this.control = control;
        this.tfs = tfs;
        this.esta = esta;
        this.anterior = anterior;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

        EventHandler<ActionEvent> evento = e -> {

            List<String> ss = new ArrayList<>();
            for(TextField tf : tfs){
                if(tf.getText().isEmpty())
                    ss.add("");
                else
                    ss.add(tf.getText());
            }

            SingletonPila pila;
            pila = SingletonPila.getInstancia();
            pila.eliminarVentana(anterior);
            pila.eliminarVentana(esta);
            control.setAcceso(false);

            control.setNuevaCategoria(ss);

        }; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
