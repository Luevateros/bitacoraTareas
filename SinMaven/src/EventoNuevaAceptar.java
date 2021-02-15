import javafx.event.*;
import javafx.scene.control.*;

/**
 *  Clase concreta de Evento que puede confirmar
 *  la creación de una Tarea o avisar de los datos inválidos.
 *  Parte de Factory.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class EventoNuevaAceptar extends Evento{

    private Controlador control;
    private TextField titulo, fecha, hora;
    private ComboBox categ;
    private SubVentana s;

    /**
     *  Constructor del evento.
     * 	@param control con la lógica del proyecto.
     * 	@param a guarda valores ingresados por el usuario.
     * 	@param b guarda valores ingresados por el usuario.
     * 	@param c guarda valores ingresados por el usuario.
     * 	@param d guarda valores ingresados por el usuario.
     * 	@param s SubVentana que se cerrará.
     */
    public EventoNuevaAceptar(Controlador control, Control a, Control b, Control c, Control d, SubVentana s){
        this.control = control;
        this.titulo = (TextField) a;
        this.fecha = (TextField) b;
        this.hora = (TextField) c;
        this.categ = (ComboBox) d;
        this.s = s;
    }

    @Override
    public EventHandler<ActionEvent> getEvento(){

		EventHandler<ActionEvent> evento = e -> {

            SingletonPila pila;
            pila = SingletonPila.getInstancia();

            if(datosIncompletos(titulo, fecha, hora, categ)){
                pila.agregar(FabricaSubVentana.crear("incompleta"));
            }
            else if(fecha.getText().length() != 10){
                pila.agregar(FabricaSubVentana.crear("fechaIncompleta"));
            }
            else if(hora.getText().length() != 5){
                pila.agregar(FabricaSubVentana.crear("horaIncompleta"));
            }
            else if(!fechaHoraValida(fecha.getText(), hora.getText())){
                pila.agregar(FabricaSubVentana.crear("invalida"));
            }
            else if(!fechaHoraFuturo(fecha.getText(), hora.getText())){
                pila.agregar(FabricaSubVentana.crear("fechaAnterior"));
            }
            else{

                pila.eliminarVentana(s);
                control.setAcceso(false);

                control.nuevaTarea(titulo.getText(),
                                   (String) categ.getValue(),
                                   fecha.getText(),
                                   hora.getText());

            }

		}; // Acaba: el lambda

		return evento;

	} // Acaba: getEvento()

}
