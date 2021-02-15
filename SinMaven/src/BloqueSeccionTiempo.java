import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.animation.*;

/**
 *	Clase para crear la sección del bloque Tarea que muestra
 *  el tiempo (y sus unidades) restantes.  Parte de Builder.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

 public class BloqueSeccionTiempo implements BloqueSeccion{

    private VBox vertical;

    /**
     *  Constructor de la sección del Bloque.
     * 	@param fechaHora de la Tarea.
	 * 	@param color de las letras.
     */
    public BloqueSeccionTiempo(LocalDateTime fechaHora, ColorApp color){

        Label tiempo = new Label();
        Label unidad = new Label();

        Timeline enCadaSeg;
        enCadaSeg = new Timeline(
                    new KeyFrame(
                    Duration.seconds(1), e->{
                        tiempo.setText(diferenciaTiempo(fechaHora));
                        unidad.setText(diferenciaUnidad(fechaHora));
                    }));

        enCadaSeg.setCycleCount(Animation.INDEFINITE);
        enCadaSeg.play();

        tiempo.setFont(Fuente.TIEMPO.get());
        unidad.setFont(Fuente.UNIDAD.get());
        tiempo.setTextFill(color.getWeb());
        unidad.setTextFill(color.getWeb());

        vertical = new VBox();
        vertical.setSpacing(-4.0);
        vertical.setAlignment(Pos.CENTER);
        vertical.getChildren().addAll(tiempo, unidad);
        vertical.setMinSize(60, 72);
        vertical.setMaxSize(60, 72);

    }

    /**
     *  Devuelve la parte.
     * 	@return boton.
     */
    public Region getRegion(){
        return vertical;
    }

    /**
     *  Método para obtener la unidad correspondiente a la diferencia.
     * 	@return String que representa la diferencia.
     */
    private String diferenciaUnidad(LocalDateTime b){

        LocalDateTime a = LocalDateTime.now();

        if(ChronoUnit.YEARS.between(a, b) > 1)
            return "años";

        if(ChronoUnit.YEARS.between(a, b) == 1)
            return "año";

        if(ChronoUnit.MONTHS.between(a, b) > 1)
            return "meses";

        if(ChronoUnit.MONTHS.between(a, b) == 1)
            return "mes";

        if(ChronoUnit.DAYS.between(a, b) > 1)
            return "días";

        if(ChronoUnit.DAYS.between(a, b) == 1)
            return "día";

        if(ChronoUnit.HOURS.between(a, b) > 1)
            return "horas";

        if(ChronoUnit.HOURS.between(a, b) == 1)
            return "hora";

        if(ChronoUnit.MINUTES.between(a, b) >= 1)
            return "min";

        if(ChronoUnit.SECONDS.between(a, b) > 0)
            return "seg";

        return "---";

    }

    /**
     *  Método para obtener el tiempo correspondiente a la diferencia.
     * 	@return String que representa la diferencia.
     */
     private String diferenciaTiempo(LocalDateTime b){

        LocalDateTime a = LocalDateTime.now();

        if(ChronoUnit.YEARS.between(a, b) >= 1)
            return Long.toString(ChronoUnit.YEARS.between(a, b));

        if(ChronoUnit.MONTHS.between(a, b) >= 1)
            return Long.toString(ChronoUnit.MONTHS.between(a, b));

        if(ChronoUnit.DAYS.between(a, b) >= 1)
            return Long.toString(ChronoUnit.DAYS.between(a, b));

        if(ChronoUnit.HOURS.between(a, b) >= 1)
            return Long.toString(ChronoUnit.HOURS.between(a, b));

        if(ChronoUnit.MINUTES.between(a, b) >= 1)
            return Long.toString(ChronoUnit.MINUTES.between(a, b));

        if(ChronoUnit.SECONDS.between(a, b) >= 0)
            return Long.toString(ChronoUnit.SECONDS.between(a, b));

        return "0";

    }

}
