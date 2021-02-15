import java.io.*;
import java.util.*;
import javafx.scene.image.Image;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;
import javafx.scene.image.ImageView;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.nio.file.*;

/**
 *  Clase que corresponde al Modelo del patrón MVC.
 *  y Sujeto al que observa el Controlador.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class Modelo implements Sujeto, InterfazProxy{

    private List<Observador> observadores;
    /* Tareas cuya fecha vence en menos de un día. */
    private List<Tarea> urgentes;
    /* Tareas que no han vencido y están incompletas. */
    private List<Tarea> pendientes;
    /* Tareas completas o vencidas. */
    private List<Tarea> pasadas;
    /* Tag: color/categoria. */
    private List<Tag> tags;
    /* 'Candado' para impedir que se actualice mientras se edita. */
    private boolean acceso;
    /* Ruta de 'recursos' y nombres de archivos. */
    private final String RECURSOS, ICONOS_SUP, ICONOS_INF, BASE, TAGS, CONFIG, EDITAR;

    /**
     *  Constructor de Modelo.
     */
    public Modelo(){

        observadores = new ArrayList<>();
        pendientes = new ArrayList<>();
        urgentes = new ArrayList<>();
        pasadas = new ArrayList<>();

        RECURSOS    = "src/main/recursos/";
        ICONOS_SUP  = "iconosSuperior.png";
        ICONOS_INF  = "iconosInferior.png";
        CONFIG      = "config.png";
        EDITAR      = "editar.png";
        BASE = "base.json";
        TAGS = "tags.json";

        acceso = false;

    }

    /**
     *  Método para iniciar la lectura y
     *  recuperación de los objetos.
     */
    public void iniciar(){
        /* Objeto para parsear el archivo JSON a una estructura. */
        Gson gson = new Gson();
        leerTags(gson);
        leerBase(gson);
    }

    /**
     *  Método para leer las Tareas.
     *  @param gson objeto para parsear JSON a Objeto
     */
    private void leerBase(Gson gson){

        BufferedReader buffer = null;

        try{
			/* Se lee el archivo. */
			buffer = new BufferedReader(new FileReader(RECURSOS + BASE));

			/* Se forza el tipo de objeto a parsear. */
			Type listType = new TypeToken<List<Tarea>>(){}.getType();

			/* Se parsea el archivo en 'buffer' con el objeto 'listType'*/
            List<Tarea> todas = gson.fromJson(buffer, listType);

			/*- - - - - - - - - Revisamos contenido - - - - - - - - - */
            /* Si el JSON está vacío(*), se inicia el Modelo sin Tareas. */
            /* (*) = no está vacío, pero sólo tiene una Tarea que no cuenta. */
            if (todas.size() == 1){
                Tarea unica = todas.get(0);
                if(unica.getTitulo().equals("vacia")
                    && unica.getIndice() == 7
                    && unica.getProgreso() == 999.0)
                        return;
            }

            /* Separamos las Tareas según su LocalDateTime. */
            LocalDateTime ahora = LocalDateTime.now();
            for(Tarea t : todas){
                LocalDateTime futuro = t.getFechaHora();
                if(t.yaVencio(ahora) || t.estaCompleta()){
                    pasadas.add(t);
                }
                else if(ChronoUnit.DAYS.between(ahora, futuro) < 1){
                    urgentes.add(t);
                }
                else{
                    pendientes.add(t);
                }
            }

            /* Si hay elementos en cada lista, se ordenan por caducidad. */
            if(urgentes.size() > 1)
                Collections.sort(urgentes);
            if(pendientes.size() > 1)
                Collections.sort(pendientes);
            if(pasadas.size() > 1)
                Collections.sort(pasadas);

		} catch (IOException e) {
   			e.printStackTrace();
  		}

    }

    /**
     *  Método para leer las Tags.
     *  @param gson objeto para parsear JSON a Objeto
     */
    private void leerTags(Gson gson){

        BufferedReader buffer = null;

        try{

            /* Se lee el archivo. */
			buffer = new BufferedReader(new FileReader(RECURSOS + TAGS));

			/* Se forza el tipo de objeto a parsear. */
			Type listType = new TypeToken<List<Tag>>(){}.getType();

			/* Se parsea el archivo en 'buffer' con el objeto 'listType'*/
			tags = gson.fromJson(buffer, listType);

		} catch (IOException e) {
   			e.printStackTrace();
  		}

    }

    /**
     *  Método para escribir los objetos en JSON
     *  antes de cerrar el programa.
     */
    public void cerrar(){

        /* Constructor para parsear una estructura a una cadena,
            con un formato más legible. */
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

        escribirBase(gson);
        escribirTags(gson);
    }

    /**
     *  Método para escribir las Tareas en JSON.
     */
    private void escribirBase(Gson gson){

        /* No habría problema con unir todas las listas pues
           este método sólo se llama al terminar el programa. */
        if(!pendientes.isEmpty()){
            for(Tarea t : pendientes)
                urgentes.add(t);
        }
        if(!pasadas.isEmpty()){
            for(Tarea t : pasadas)
                urgentes.add(t);
        }

        if(urgentes.isEmpty()){
            Tarea t = new Tarea("vacia", "01-01-2000", "00:00", 7);
            t.setProgreso(999.0);
            urgentes.add(t);
        }

        /* Se parsea una estructura a una cadena como archivo JSON. */
        String json = gson.toJson(urgentes);

        try {
			/* Se escribe el archivo JSON a partir de la cadena. */
			FileWriter writer = new FileWriter(RECURSOS + BASE);
			writer.write(json);
			writer.close();

		}catch (IOException e) {
			e.printStackTrace();
		}

    }

    /**
     *  Método para escribir las Tags en JSON.
     */
    private void escribirTags(Gson gson){

        /* Se parsea una estructura a una cadena como archivo JSON. */
        String json = gson.toJson(tags);

        try {
			/* Se escribe el archivo JSON a partir de la cadena. */
			FileWriter writer = new FileWriter(RECURSOS + TAGS);
			writer.write(json);
			writer.close();

		}catch (IOException e) {
			e.printStackTrace();
		}

    }

    /**
     *  Método para obtener la imagen en un archivo.
     * 	@return la imagen en formato ImageView.
     */
    public ImageView getImagenEn(String tipo){

        String ruta = "";

        if("inferior".equals(tipo))
            ruta = RECURSOS + ICONOS_INF;
        if("superior".equals(tipo))
            ruta = RECURSOS + ICONOS_SUP;
        if("config".equals(tipo))
            ruta = RECURSOS + CONFIG;
        if("editar".equals(tipo))
            ruta = RECURSOS + EDITAR;

        ImageView imagenView = null;

        try {
            Image imagen   = new Image(new FileInputStream(ruta));
            imagenView = new ImageView(imagen);
        } catch(FileNotFoundException e) {
            System.out.println("El archivo no se encontró. " + e);
        }

        return imagenView;

    }

    /**
     *  Método para establecer el candado
     * 	@param acceso.
     */
    public void setAcceso(boolean acceso){
        this.acceso = acceso;
    }

    /**
     *  Método para actualizar las listas. Esto sucede cada 5 seg
     *  o cada que haya un cambio en la base (nueva, eliminar, editar).
     *  Se utiliza un iterador para recorrer y editar las listas al
     *  mismo tiempo.
     */
    public void actualizar(){

        /* 'Candado' para que no se actualicen los datos
            automáticamente mientras el usuario tiene una
            subVentana abierta y quiera modificar algo. */
        if(acceso)
            return;

        actualizarPendientes();
        actualizarUrgentes();

    }

    /**
     *  Método para obtener la Tarea.
	 * 	@param clave para buscar la Tarea a modificar.
	 * 	@param indice para buscar la Tarea a modificar.
     */
    public Tarea getTarea(String clave, int i){

        Tarea tarea = null;

        if("urgente".equals(clave) && !urgentes.isEmpty()){
            if(i >= 0 && i <= urgentes.size() - 1)
                tarea = urgentes.get(i);
        }
        if("pendiente".equals(clave) && !pendientes.isEmpty()){
            if(i >= 0 && i <= pendientes.size() - 1)
                tarea = pendientes.get(i);
        }
        if("pasada".equals(clave) && !pasadas.isEmpty()){
            if(i >= 0 && i <= pasadas.size() - 1)
                tarea = pasadas.get(i);
        }

        return tarea;

    }

    /**
     *  Método para obtener las Tags.
     * 	@return tags
     */
    public List<Tag> getTags(){
        return tags;
	}

    /**
     *  Método para obtener las Tareas urgentes.
     * 	@return urgentes
     */
    public List<Tarea> getUrgentes(){
        return urgentes;
	}

    /**
     *  Método para obtener las Tareas pendientes.
     * 	@return urgentes
     */
	public List<Tarea> getPendientes(){
        return pendientes;
	}

    /**
     *  Método para obtener las Tareas pasadas.
     * 	@return urgentes
     */
	public List<Tarea> getPasadas(){
        return pasadas;
	}

    /**
     *  Método para agregar una Tarea nueva.
	 * 	@param nueva
     */
    public void nuevaTarea(Tarea nueva){
        LocalDateTime futuro = nueva.getFechaHora();
        LocalDateTime ahora  = LocalDateTime.now();
        if(ChronoUnit.DAYS.between(ahora, futuro) < 1)
            agregaUrgentes(nueva);
		else
            agregaPendientes(nueva);
        notificar();
	}

    /**
     *  Método para eliminar una Tarea.
	 * 	@param tarea
	 * 	@param clave de a qué lista pertenece.
     */
    public void eliminarTarea(Tarea tarea, String clave){

        if("urgente".equals(clave) && !urgentes.isEmpty()){
            if(urgentes.contains(tarea))
                urgentes.remove(tarea);
        }
        if("pendiente".equals(clave) && !pendientes.isEmpty()){
            if(pendientes.contains(tarea))
                pendientes.remove(tarea);
        }
        if("pasada".equals(clave) && !pasadas.isEmpty()){
            if(pasadas.contains(tarea))
                pasadas.remove(tarea);
        }
        notificar();
    }

    /**
     *  Método para eliminar todas las Tareas.
     */
    public void eliminarTodas(){
        urgentes = new ArrayList<>();
        pendientes = new ArrayList<>();
        pasadas = new ArrayList<>();
        notificar();
    }

    /**
     *  Método para eliminar las Tareas urgentes.
     */
    public void eliminarUrgentes(){
        urgentes = new ArrayList<>();
        notificar();
    }

    /**
     *  Método para eliminar las Tareas pendientes.
     */
    public void eliminarPendientes(){
        pendientes = new ArrayList<>();
        notificar();
    }

    /**
     *  Método para eliminar las Tareas pasadas.
     */
    public void eliminarPasadas(){
        pasadas = new ArrayList<>();
        notificar();
    }

    /**
     *  Método para ordenar las listas de urgentes y pendientes.
     */
    public void ordenar(){
        Collections.sort(urgentes);
        Collections.sort(pendientes);
        notificar();
    }

    /**
     *  Método para agregar tareas a urgentes.
     */
    private void agregaUrgentes(Tarea t){
        urgentes.add(urgentes.size(), t);
        Collections.sort(urgentes);
    }

    /**
     *  Método para agregar tareas a pendientes.
     */
    private void agregaPendientes(Tarea t){
        pendientes.add(pendientes.size(), t);
        Collections.sort(pendientes);
    }

    /**
     *  Método para actualizar las tareas urgentes.
     */
    private void actualizarUrgentes(){

        boolean huboCambios = false;

        Iterator<Tarea> iterUrge = urgentes.iterator();
        while(iterUrge.hasNext()){
            Tarea tarea = iterUrge.next();
            LocalDateTime futuro = tarea.getFechaHora();
            LocalDateTime ahora  = LocalDateTime.now();
            if(ChronoUnit.DAYS.between(ahora, futuro) > 1) {
                agregaPendientes(tarea);
                iterUrge.remove();
                huboCambios = true;
            }
            if(tarea.yaVencio(ahora) || tarea.estaCompleta()) {
                pasadas.add(0, tarea);
                iterUrge.remove();
                huboCambios = true;
            }
        }

        if(huboCambios){
            notificar();
        }

    }

    /**
     *  Método para actualizar las tareas pendientes.
     */
    private void actualizarPendientes(){

        boolean huboCambios = false;

        Iterator<Tarea> iterPend = pendientes.iterator();
        while(iterPend.hasNext()){
            Tarea tarea = iterPend.next();
            LocalDateTime futuro = tarea.getFechaHora();
            LocalDateTime ahora  = LocalDateTime.now();
            if(ChronoUnit.DAYS.between(ahora, futuro) < 1) {
                agregaUrgentes(tarea);
                iterPend.remove();
                huboCambios = true;
            }else if(tarea.estaCompleta()){
                /* Siempre se agrega al inicio de la lista. */
                pasadas.add(0, tarea);
                iterPend.remove();
                huboCambios = true;
            }
        }

        if(huboCambios){
            notificar();
        }

    }

    /**
     *  Método para notificar a los observadores.
     */
    public void notificar(){
        for(Observador o : observadores){
            o.mostrar();
        }
    }

    /**
     *  Método para agregar observadores.
     *  @param o observador.
     */
    public void agregarObservador(Observador o){
        observadores.add(o);
    }

    /**
     *  Método para eliminar observadores.
     *  @param o observador.
     */
    public void removerObservador(Observador o){
        if(observadores.contains(o))
            observadores.remove(o);
    }

}
