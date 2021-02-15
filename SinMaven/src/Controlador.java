import java.util.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.animation.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;

/**
 *  Clase que corresponde al Controlador del patrón MVC.
 *  y Observador del Sujeto (Modelo).
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class Controlador implements Observador {

    private Vista  vista;
    private Modelo modelo;
    /* Fecha y horas actuales. */
    private Label fechaActual;
    private Label horaActual;

    /**
     *  Constructor de Controlador.
     * 	@param modelo que guarda los datos.
     */
    public Controlador(Modelo modelo){
        this.modelo = modelo;
        modelo.iniciar();
        modelo.agregarObservador(this);
        vista = new Vista(this);
        iniciarTiempo();
        setAcceso(false);
    }

    /**
     *  Método para iniciar la actualización automática
     *  y la hora/fecha en pantalla.
     */
    private void iniciarTiempo(){

        /* Formato deseado para fecha y hora actual. */
        String fecha = "dd/MM/yyyy";
        String hora  = "HH:mm";

        /* Objetos para obtener fecha/hora en el formato deseado. */
        DateTimeFormatter ff = DateTimeFormatter.ofPattern(fecha);
        DateTimeFormatter fh  = DateTimeFormatter.ofPattern(hora);

        /* Objeto que nos permite repetir una acción cada cierto tiempo. */

        fechaActual = new Label();
        horaActual  = new Label();


        Timeline enCadaSeg;
        enCadaSeg = new Timeline(
                    new KeyFrame(
                    Duration.seconds(1), e->{
                        LocalDateTime ahora = LocalDateTime.now();
                        fechaActual.setText(ahora.format(ff));
                        horaActual .setText(ahora.format(fh));
                    }));

        enCadaSeg.setCycleCount(Animation.INDEFINITE);
        enCadaSeg.play();

        Timeline enCadaMin;
        enCadaMin = new Timeline(
                    new KeyFrame(
                    Duration.seconds(5), e->{
                        actualizar();
                    }));

        enCadaMin.setCycleCount(Animation.INDEFINITE);
        enCadaMin.play();
    }

    /**
     *  Método para establecer el candado que evita
     *  que se actualice mientras se edita.
     * 	@param acceso.
     */
    public void setAcceso(boolean acceso){
        modelo.setAcceso(acceso);
    }

    /**
     *  Método para que Vista crea y muestre el contenido.
     * 	@param stage.
     */
    public void mostrarVista(Stage stage){
        vista.crearPantalla();
        vista.mostrarPantalla(stage);
        actualizar();
    }

    /**
     *  Método para que Vista se actualice.
     *  Método de interfaz Observer.
     */
    public void mostrar(){
        vista.renovarPantalla();
    }

    /**
     *  Método para que Modelo escriba los objetos antes de cerrar.
     */
    public void cerrar(){
        modelo.cerrar();
    }

    /**
     *  Método para actualizar el Modelo cada 5 seg.
     */
    private void actualizar(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        proxy.actualizar();
    }

    /**
     *  Método para saber si hay Tags con categoria.
     *  Los que tengan sólo "" se ignoran.
     * 	@return <code>true<code> si hay.
	 *			<code>false<code> si no.
     */
    public boolean hayTags(){
        List<Tag> tags = modelo.getTags();
        for(Tag t : tags){
            if(!t.getCategoria().equals(""))
                return true;
        }
        return false;
    }

    /**
     *  Método para obtener la imagen en un archivo.
     * 	@return la imagen en formato ImageView.
     */
    public ImageView getImagenEn(String tipo){
        return modelo.getImagenEn(tipo);
    }

    /**
     *  Método para obtener la fecha actual del contador.
     * 	@return fecha en formato Label.
     */
    public Label getFechaActual(){
        return fechaActual;
    }

    /**
     *  Método para obtener la hora actual del contador.
     * 	@return hora en formato Label.
     */
    public Label getHoraActual(){
        return horaActual;
    }

    /**
     *  Método para obtener el titulo de la Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea.
     * 	@return titulo
     */
    public String getTitulo(String clave, int indice){
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        return proxy.getTitulo();
    }

    /**
     *  Método para obtener el categoria de la Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea.
     * 	@return categoria
     */
    public String getCategoria(String clave, int indice){
        setAcceso(true);
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        String s = proxy.getCategoria();
        setAcceso(false);
        return s;
    }

    /**
     *  Método para obtener el color de la Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea.
     * 	@return color
     */
    public String getColor(String clave, int indice){
        setAcceso(true);
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        String s = proxy.getColor();
        setAcceso(false);
        return s;
    }

    /**
     *  Método para obtener la fecha de la Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea.
     * 	@return fecha
     */
    public String getFecha(String clave, int indice){
        setAcceso(true);
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        String s = proxy.getFecha();
        setAcceso(false);
        return s;
    }

    /**
     *  Método para obtener la hora de la Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea.
     * 	@return hora
     */
    public String getHora(String clave, int indice){
        setAcceso(true);
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        String s = proxy.getHora();
        setAcceso(false);
        return s;
    }

    /**
     *  Método para obtener el progreso de la Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea.
     * 	@return progreso
     */
    public double getProgreso(String clave, int indice){
        setAcceso(true);
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        double d = proxy.getProgreso();
        setAcceso(false);
        return d;
    }

    /**
     *  Método para obtener una copia de los Tags.
	 * 	No se obtiene la lista orignal.
     * 	@return tags
     */
    public List<Tag> getTags(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        List<Tag> t = proxy.getTags();
        return t;
    }

    /**
     *  Método para obtener una copia de las Tareas urgentes.
	 * 	No devuelve la lista orignal (y sus referencias).
     * 	@return urgentes
     */
    public List<Tarea> getUrgentes(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        List<Tarea> t = proxy.getUrgentes();
        return t;
    }

    /**
     *  Método para obtener una copia de las Tareas pendientes.
	 * 	No devuelve la lista orignal (y sus referencias).
     * 	@return pendientes
     */
    public List<Tarea> getPendientes(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        List<Tarea> t = proxy.getPendientes();
        return t;
    }

    /**
     *  Método para obtener una copia de las Tareas pasadas.
	 * 	No devuelve la lista orignal (y sus referencias).
     * 	@return pasadas
     */
    public List<Tarea> getPasadas(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        List<Tarea> t = proxy.getPasadas();
        return t;
    }

    /**
     *  Método para asignar el progreso de la Tarea.
	 *  La temporal es la tarea que se obtuvo inicio.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea
	 *	@param progreso
     */
    public void setProgreso(String clave, int indice, double progreso){
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        proxy.setProgreso(progreso);
    }

    /**
     *  Método para completar la Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea
     */
    public void setCompleta(String clave, int indice){
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        proxy.setCompleta();
    }

    /**
     *  Método para crear y agregar una Tarea nueva al Modelo.
	 * 	@param titulo
	 * 	@param categoria
	 * 	@param fecha
	 * 	@param hora
     */
    public void nuevaTarea(String titulo, String categoria, String fecha, String hora){
        ModeloProxy proxy = new ModeloProxy(modelo);
        proxy.nuevaTarea(titulo, categoria, fecha, hora);
    }

    /**
     *  Método para editar todos los atributos de la Tarea.
	 * 	@param titulo
	 * 	@param categoria
	 * 	@param fecha
	 * 	@param hora
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea
     */
    public void editarTarea(String titulo, String categoria, String fecha, String hora, String clave, int indice){
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        proxy.editarTarea(clave, titulo, categoria, fecha, hora);
    }

    /**
     *  Método para eliminar una Tarea.
	 * 	@param clave de la lista.
     * 	@param indice de la Tarea
     */
    public void eliminarTarea(String clave, int indice){
        ModeloProxy proxy = new ModeloProxy(modelo, clave, indice);
        proxy.eliminarTarea(clave);
    }

    /**
     *  Método para eliminar todas las Tareas.
     */
    public void eliminarTodas(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        proxy.eliminarTodas();
    }

    /**
     *  Método para eliminar las Tareas urgentes.
     */
    public void eliminarUrgentes(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        proxy.eliminarUrgentes();
    }

    /**
     *  Método para eliminar las Tareas pendientes.
     */
    public void eliminarPendientes(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        proxy.eliminarPendientes();
    }

    /**
     *  Método para eliminar las Tareas pasadas.
     */
    public void eliminarPasadas(){
        ModeloProxy proxy = new ModeloProxy(modelo);
        proxy.eliminarPasadas();
    }

    /**
     *  Método para asignar las nuevas categorias.
	 * 	@param ss nueva lista.
     */
    public void setNuevaCategoria(List<String> ss){
        ModeloProxy proxy = new ModeloProxy(modelo);
        proxy.setNuevaCategoria(ss);
    }

}
