import java.util.*;
import javafx.scene.*;
import javafx.scene.layout.*;

/**
 *  Clase que simula el comportamiento de una única pila,
 *  donde se muestran todo el contenido en pantalla.
 *  No puede haber más de una instancia de esta clase
 *  al mismo tiempo pues todo el contenido se crea dentro
 *  de esta. Cada que se actualiza el modelo, el contenido
 *  anterior se borra y en esta se crea de nuevo.
 * 	@author Ricardo A. Luévano B.
 *  @since 1.0
 */

public class SingletonPila extends StackPane{

    /*  Única instancia de esta clase. */
    private static SingletonPila INSTANCIA;

    /**
     *  Único constructor privado que genera la única pila.
     */
    private SingletonPila() {
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
        setStyle("-fx-background-color: transparent;" +
                 "-fx-background: transparent;");
    }

    /**
     *  Método que devuelve la única instancia de la pila
     *  @return INSTANCIA.
     */
    public synchronized static SingletonPila getInstancia() {
        if(INSTANCIA == null)
            INSTANCIA = new SingletonPila();
        return INSTANCIA;
    }

    /**
     *  Método que elimina todo contenido de la pila.
     */
    public void limpiar(){
        getChildren().clear();
    }

    /**
     *  Método para eliminar una SubVentana particular.
     *  @param sub la SubVentana particular.
     */
    public void eliminarVentana(SubVentana sub){
        if(getChildren().contains(sub))
            getChildren().remove(sub);
    }

    /**
     *  Método para eliminar el último elemento de la pila.
     */
    public void eliminarUltimo() {
        if (INSTANCIA != null){
            int i = getChildren().size();
            if(i > 1){
                getChildren().remove(i-1);
            }
        }
    }

    /**
     *  Método para agregar varios objetos a la pila.
     */
    public void agregar(Region... rs) {
        if (INSTANCIA != null && rs != null){
            int i = rs.length;
            for(Region r : rs){
                if(r != null)
                    getChildren().add(r);
            }
        }
    }


}
