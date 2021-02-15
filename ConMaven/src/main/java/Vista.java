import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.geometry.*;

/**
 *  Clase que corresponde a la Vista del patrón MVC.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class Vista {

    private Controlador control;
    /* Atributos para diferentes gráficos. */
    private BorderPane pantalla;
    /* Atributos para diferentes gráficos. */
    private final int BORDE, ENTRELINEA;
    /* Escena principal de JavaFX. */
    private Scene scene;

    /**
     *  Constructor de Vista.
     * 	@param control con la lógica.
     */
    public Vista(Controlador control){
        this.control = control;
        ENTRELINEA = 12;
        BORDE = 2;
    }

    /**
     *  Método para crear el contenido del panel principal.
     *  Incluye los elementos del celualr.
     */
    public void crearPantalla() {

        SingletonPila pila = SingletonPila.getInstancia();
        pila.limpiar();
        pila.agregar(getSeccionPrincipal());

        pantalla = new BorderPane();
        pantalla.setTop(getFranjaSuperior());
        pantalla.setCenter(pila);
        pantalla.setBottom(control.getImagenEn("inferior"));
        pantalla.setBackground(getFondoGradiente());

	}

    /**
     *  Método que crea la escena actual y
     *  configura el Stage.
     * 	@param stage.
     */
    public void mostrarPantalla(Stage stage) {

        scene = new Scene(pantalla, 360, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Modelado 2021-1: Proyecto Final");
        stage.show();

	}

    /**
     *  Método que actualizar lo mostrado en pantalla.
     */
    public void renovarPantalla(){
        crearPantalla();
        scene.setRoot(pantalla);
    }

    /**
     *  Método que crear la franja superior del celular.
     */
    private HBox getFranjaSuperior(){

        /* Etiquetas: compañia y hora actual. */
        Label comp = new Label("Telefonía");
        Label hora = control.getHoraActual();
        comp.setFont(Fuente.HORA.get());
        hora.setFont(Fuente.HORA.get());
        comp.setTextFill(ColorApp.BLANCO.getWeb());
        hora.setTextFill(ColorApp.BLANCO.getWeb());

        /* Espacio. */
        Region central = new Region();

        /* Iconos. */
        ImageView iconos = control.getImagenEn("superior");

        /* Layout para esta franja  */
        HBox superior = new HBox();
        HBox.setHgrow(central, Priority.ALWAYS);
        HBox.setMargin(comp,   new Insets(2, 2, 2, 2));
        HBox.setMargin(iconos, new Insets(4, 2, 0, 2));
        HBox.setMargin(hora,   new Insets(2, 4, 2, 2));
        superior.setStyle("-fx-background-color: #000000;");
        superior.getChildren().addAll(comp, central, iconos, hora);

        return superior;

    }

    /**
     *  Método que crear la seción principal.
     *  La que contiene todo elemento de la app.
     */
    private BorderPane getSeccionPrincipal() {

        /* Panel para desplazar y ver todas las tareas verticalmente. */
        ScrollPane scroll = new ScrollPane();
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        /* Panel que contiene a las subsecciones de tareas. */
        VBox vertical = new VBox();
        vertical.setAlignment(Pos.TOP_CENTER);
        vertical.getChildren().addAll(getTareas("urgentes"),
                                      getTareas("pendientes"),
                                      getTareas("pasadas"));

        /* Con AnchorPane, el ScrollPane ocupa toda la ventana. */
        AnchorPane ancla = new AnchorPane();
        AnchorPane.setTopAnchor(scroll, 0.0);
        AnchorPane.setBottomAnchor(scroll, 0.0);
        AnchorPane.setLeftAnchor(scroll, 0.0);
        AnchorPane.setRightAnchor(scroll, 0.0);

        /* Border a devolver. */
        BorderPane principal = new BorderPane();

        /* Se ajusta el color de los paneles a transparente. */
        setFondoTransparente("", scroll, vertical, ancla, principal);

        /* Como Scrollpane sólo puede contener un panel, en este
            va el panel con las tres secciones de tareas. */
        scroll.setContent(vertical);
        /* Scrollpane va dentro del ancla. */
        ancla.getChildren().add(scroll);
        /* AnchorPane ocupa toda la zona central. */
        principal.setCenter(ancla);
        /* HBox con herramientas van en la zona inferior. */
        principal.setBottom(getHerramientas());

        return principal;
    }

    /**
     *  Método que crear la seción de botones principales.
     */
    private HBox getHerramientas(){

        /* Botones. */
        Button nueva  = new Button("Nueva");
        nueva.setFont(Fuente.TITULO.get());
        nueva.setMaxHeight(Double.MAX_VALUE);
        nueva.setStyle("-fx-text-fill: #ffffff;" +
                       "-fx-background-color: transparent;");
        Evento evNueva = FabricaEvento.crear(control, "nueva");
        nueva.setOnAction(evNueva.getEvento());

        Button config = new Button();
        config.setGraphic(control.getImagenEn("config"));
        config.setMaxHeight(Double.MAX_VALUE);
        config.setStyle("-fx-background-color: transparent;");

        Evento evConfig = FabricaEvento.crear(control, "config");
        config.setOnAction(evConfig.getEvento());

        /* Espacios a los lados de la fecha. */
        Region izquierda = new Region();
        Region derecha   = new Region();

        /* Etiqueta con fecha actual. */
        Label fecha = control.getFechaActual();
        fecha.setTextFill(ColorApp.BLANCO.getWeb());
        fecha.setFont(Fuente.FECHA.get());
        fecha.setPadding(new Insets(7,0,0,5));

        /* Panel de la subseccion. */
        HBox herramientas = new HBox();
        HBox.setMargin(config, new Insets(0,  0, 0, 5));
        HBox.setMargin(nueva,  new Insets(0, 10, 0, 0));
        HBox.setHgrow(izquierda, Priority.ALWAYS);
        HBox.setHgrow(derecha,   Priority.ALWAYS);
        herramientas.getChildren().addAll(config,
                                          izquierda,
                                          fecha,
                                          derecha,
                                          nueva);

        /* Atributos gráficos de la sección. */
        StringBuilder extra = new StringBuilder();
        extra.append(" -fx-border-width: " + BORDE + " 0 0 0; ");
        extra.append(" -fx-border-color: white; ");
        setFondoTransparente(extra.toString(), herramientas);

        return herramientas;

    }

    /**
     *  Método que crear la seción de las Tareas.
     *  @param seccion de las tareas urgentes, pendientes o pasadas.
     */
    private VBox getTareas(String seccion){

        /* Título de sección. */
        Label titulo = new Label("Tareas " + seccion);
        titulo.setFont(Fuente.CABEZA.get());
        titulo.setPadding(new Insets(ENTRELINEA,0,ENTRELINEA,0));

        /* Panel con tareas. */
        VBox vertical = new VBox();
        vertical.setAlignment(Pos.CENTER);
        vertical.getChildren().add(titulo);

        /* Se agregan bloques que muestran la información de las Tareas.
            Para esto, se elige una estrategia, la cual elige la apariencia
            de la Tarea y de lista de donde se obtiene. */
        EstrategiaContexto contexto = new EstrategiaContexto();
        contexto.setEstrategia(eligeEstrategia(seccion), control);
        contexto.agregarTareas(vertical);

        /* Atributos gráficos de la sección. */
        StringBuilder extra = new StringBuilder();
        extra.append(" -fx-border-width: 0 0 " + BORDE + " 0; ");
        extra.append(" -fx-border-color: white; ");
        setFondoTransparente(extra.toString(), vertical);

        return vertical;

    }

    /**
     *  Método que crear el fondo de la app.
     */
    private Background getFondoGradiente(){
        Stop purpura = new Stop(0, ColorApp.PURPURA.getWeb());
        Stop verde   = new Stop(1, ColorApp.VERDE.getWeb());
        Stop[] stops = new Stop[] { purpura, verde};
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill bgf = new BackgroundFill(lg, CornerRadii.EMPTY, Insets.EMPTY);
        Background fondo = new Background(bgf);
        return fondo;
    }

    /**
     *  Método para asignar el fondo transparente de los objetos.
     *  @param extra cambios adicionales al estilo.
     *  @param regiones a modificar.
     */
    private void setFondoTransparente(String extra, Region... regiones){
        for (Region r : regiones)
            r.setStyle("-fx-background-color: transparent;" +
                        "-fx-background: transparent;" +
                        extra);
    }

    /**
     *  Método para elegir una de las estrategias.
     *  @param seccion que decide la estrategia.
     */
    private EstrategiaBloque eligeEstrategia(String seccion){

        EstrategiaBloque estrategia = null;

        if(seccion.equalsIgnoreCase("urgentes"))
            estrategia = new EstrategiaUrgente();
        if(seccion.equalsIgnoreCase("pendientes"))
            estrategia = new EstrategiaPendiente();
        if(seccion.equalsIgnoreCase("pasadas"))
            estrategia = new EstrategiaPasada();

        return estrategia;

	}

}
