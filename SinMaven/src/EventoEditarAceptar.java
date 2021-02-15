import javafx.event.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de Evento para confirmar los
 *  cambios de una Tarea. Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoEditarAceptar extends Evento{

    private Controlador control;
    private TextField titulo, fecha, hora;
    private ComboBox categ;
    private String clave;
    private int indice;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param clave para buscar la Tarea a modificar.
     * 	@param indice para buscar la Tarea a modificar.
     * 	@param a guarda valores ingresados por el usuario.
     * 	@param b guarda valores ingresados por el usuario.
     * 	@param c guarda valores ingresados por el usuario.
     * 	@param d guarda valores ingresados por el usuario.
     */
    public EventoEditarAceptar(Controlador control, String clave, int indice, Control a, Control b, Control c, Control d){
        this.control = control;
        this.clave = clave;
        this.indice = indice;
        this.titulo = (TextField) a;
        this.fecha = (TextField) b;
        this.hora = (TextField) c;
        this.categ = (ComboBox) d;

    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

		EventHandler<ActionEvent> evento = e -> {

            if(titulo != null && categ != null &&
               fecha != null && hora != null){

                SingletonPila pila;
                pila = SingletonPila.getInstancia();
        		if(datosIncompletos(titulo, fecha, hora, categ))
                   pila.agregar(FabricaSubVentana.crear("incompleta"));
                else if(fecha.getText().length() != 10)
                    pila.agregar(FabricaSubVentana.crear("fechaIncompleta"));
                else if(hora.getText().length() != 5)
                    pila.agregar(FabricaSubVentana.crear("horaIncompleta"));
                else if(!fechaHoraValida(fecha.getText(), hora.getText()))
                    pila.agregar(FabricaSubVentana.crear("invalida"));
                else if(!fechaHoraFuturo(fecha.getText(), hora.getText()))
                    pila.agregar(FabricaSubVentana.crear("fechaAnterior"));
                else{
                    pila.eliminarUltimo();
                    control.setAcceso(false);

                    control.editarTarea(titulo.getText(),
                                        (String) categ.getValue(),
                                        fecha.getText(),
                                        hora.getText(),
                                        clave, indice);
                }

            } // Acaba: if null

		}; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
