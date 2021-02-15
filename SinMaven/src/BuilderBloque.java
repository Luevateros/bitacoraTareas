import java.util.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *  Clase para construir los bloques de Tarea por defecto.
 *  Su construcción simula una cadena de ensamblaje.
 *  Parte de Builder.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class BuilderBloque {

    /**
     *  Método para crear un bloque gráfico que representa una Tarea urgente.
     *  @param tarea objeto con los atributos de la Tarea (es copia, no la que se encuentra en el Modelo).
     *  @param indice único para este bloque (necesario para editar más adelante la Tarea).
     *  @param tags lista con las categoria/color.
     *  @return GridPane (bloque) con la información de la Tarea.
     */
    public GridPane crearUrgente(Tarea tarea, int indice, List<Tag> tags, Controlador control){

        Bloque bloque = bloquePorDefecto(
                            tarea, indice, tags, "urgente",
                            ColorApp.MANDARINA.getHexa(),
                            ColorApp.CEREZA,
                            ColorApp.PLATANO.getHexa(),
                            true, control);

        GridPane urgente = bloque.getGridPane();

        bloque = null; // Elimina la referencia.

        return urgente;

    } // Acaba: bloqueUrgente

    /**
     *  Método para crear un bloque gráfico que representa una Tarea pendiente.
     *  @param tarea objeto con los atributos de la Tarea (es copia, no la que se encuentra en el Modelo).
     *  @param indice único para este bloque (necesario para editar más adelante la Tarea).
     *  @param tags lista con las categoria/color.
     *  @return GridPane (bloque) con la información de la Tarea.
     */
    public GridPane crearPendiente( Tarea tarea, int indice, List<Tag> tags, Controlador control){

        Bloque bloque = bloquePorDefecto(
                            tarea, indice, tags, "pendiente",
                            ColorApp.OLIVO.getHexa(),
                            ColorApp.NEGRO,
                            ColorApp.BLANCO.getHexa(),
                            true, control);

        GridPane pendiente = bloque.getGridPane();

        bloque = null; // Elimina la referencia.

        return pendiente;

    } // Acaba: bloquePendiente

    /**
     *  Método para crear un bloque gráfico que representa una Tarea pasada.
     *  @param tarea objeto con los atributos de la Tarea (es copia, no la que se encuentra en el Modelo).
     *  @param indice único para este bloque (necesario para editar más adelante la Tarea).
     *  @param tags lista con las categoria/color.
     *  @return GridPane (bloque) con la información de la Tarea.
     */
    public GridPane crearPasada( Tarea tarea, int indice, List<Tag> tags, Controlador control){

        Bloque bloque = bloquePorDefecto(
                            tarea, indice, tags, "pasada",
                            ColorApp.GRIS.getHexa(),
                            ColorApp.NEGRO,
                            ColorApp.BLANCO.getHexa(),
                            false, control);

        GridPane pasada = bloque.getGridPane();

        bloque = null; // Elimina la referencia.

        return pasada;

    } // Acaba: bloquePasada

    /**
     *  Método para crear un bloque con las caracteristicas por defecto.
     *  @param tarea objeto con los atributos de la Tarea (es copia, no la que se encuentra en el Modelo).
     *  @param indice único para este bloque (necesario para editar más adelante la Tarea).
     *  @param tags lista con las categoria/color.
     *  @param clave del bloque (urgente, pendiente, pasada).
     *  @param colorProgreso color de la barra de progreso.
     *  @param colorTiempo color de los caracteres del tiempo restante.
     *  @param colorFondo color del Fondo.
     *  @param esOpaco <code>true<code> si la ventana es opaca,
     *                 <code>false<code> si es translucida.
     *  @return Bloque con los cambios hechos.
     */
    private Bloque bloquePorDefecto( Tarea tarea, int indice, List<Tag> tags, String clave, String colorProgreso, ColorApp colorTiempo, String colorFondo, boolean esOpaco, Controlador control){

        Bloque bloque = new Bloque();
        Tag t = tags.get(tarea.getIndice());

        bloque.setSeccionTag(new BloqueSeccionFranja(t.getColor()));

        bloque.setSeccionEstudio(new BloqueSeccionDatos(clave, indice, tarea.getTitulo(), t.getCategoria(), colorProgreso, tarea.getProgreso(), control));

        bloque.setSeccionTiempo(new BloqueSeccionTiempo(tarea.getFechaHora(), colorTiempo));

        bloque.setSeccionConfig(new BloqueSeccionEdicion(clave, indice, control));

        bloque.setSeccionCheck(new BloqueSeccionCheck(clave, indice, tarea.estaCompleta(), control));

        bloque.setFondo(colorFondo, esOpaco);

        return bloque;

    } // Acaba: bloquePorDefecto

}
