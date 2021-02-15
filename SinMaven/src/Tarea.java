import java.time.*;
import java.time.format.*;

/**
 *  Clase que guarda los datos de cada Tarea.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class Tarea implements Comparable<Tarea>{

	private String titulo;
	private String fecha;
	private String hora;
	private int indice;
	private double progreso;

	/**
     *  Constructor de Tarea. Para crear una Tarea,
	 *	la fecha y hora se validaron antes.
     * 	@param titulo.
     * 	@param fecha.
	 * 	@param fecha.
	 * 	@param indice de la categoría en List<Tag>.
     */
	public Tarea(String titulo, String fecha, String hora, int indice){
		this.titulo = titulo;
		this.indice = indice;
		this.fecha = fecha;
		this.hora = hora;
		progreso = 0.0;
	}

	/**
     *  Método para obtener el titulo de la Tarea.
	 * 	@return titulo
     */
	public String getTitulo(){
		return titulo;
	}

	/**
     *  Método para obtener la fecha y hora en formato LocalDateTime.
	 * 	@return fechaHora
     */
	public LocalDateTime getFechaHora(){
		String cadena = fecha + " " + hora;
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
		LocalDateTime fechaHora = LocalDateTime.parse(cadena, f);
		return fechaHora;
	}

	/**
     *  Método para obtener la fecha en formato String.
	 * 	@return fecha
     */
	public String getFecha(){
		return fecha;
	}

	/**
     *  Método para obtener la hora en formato String.
	 * 	@return hora
     */
	public String getHora(){
		return hora;
	}

	/**
     *  Método para obtener el indice en formato String.
	 * 	@return indice
     */
	public int getIndice(){
		return indice;
	}

	/**
     *  Método para obtener el progreso.
	 * 	@return progreso
     */
	public double getProgreso(){
		return progreso;
	}

	/**
     *  Método para saber si la Tarea está completa.
	 * 	@return <code>true<code> si está completa.
	 *			<code>false<code> si no.
     */
	public boolean estaCompleta(){
		return progreso >= 1.0;
	}

	/**
     *  Método para saber si la Tarea ya venció.
	 * 	@return <code>true<code> si ya venció.
	 *			<code>false<code> si no.
     */
	public boolean yaVencio(LocalDateTime actual){
		return actual.isAfter(getFechaHora());
	}

	/**
     *  Método para asignar el titulo de la Tarea.
	 * 	@param nuevo
     */
	public void setTitulo(String nuevo){
		titulo = nuevo;
	}

	/**
     *  Método para asignar el indice de la Tarea.
	 * 	@param nueva
     */
	public void setIndice(int nueva){
		indice = nueva;
	}

	/**
     *  Método para asignar la fecha de la Tarea.
	 * 	@param nueva
     */
	public void setFecha(String nueva){
		fecha = nueva;
	}

	/**
     *  Método para asignar la hora de la Tarea.
	 * 	@param nueva
     */
	public void setHora(String nueva){
		hora = nueva;
	}

	/**
     *  Método para asignar el progreso de la Tarea.
	 * 	@param nuevo
     */
	public void setProgreso(double nuevo){
		progreso = nuevo;
	}

	/**
     *  Método para imprimir una representación de la Tarea.
     */
	public String toString(){

		StringBuilder sb = new StringBuilder();

		LocalDateTime actual = LocalDateTime.now();

		sb.append("   Tarea: " + titulo + "\n");
		sb.append("  Indice: " + indice + "\n");
		sb.append(" Entrega: " + fecha + " " + hora +"\n");
		sb.append("Progreso: " + progreso + "\n");
		sb.append("Completa: " + (estaCompleta() ? "si" : "no") + "\n");
		sb.append(" Vencida: " + (yaVencio(actual)   ? "si" : "no") + "\n");

		return sb.toString();
	}

	/**
     *	Método que nos dice si esta Tarea es igual al objeto recibido.
     *	@param o el objeto que queremos saber si es igual a la Tarea.
     *	@return <code>true</code> si el objeto recibido es instancia de Tarea,
     *         					 y tiene los mismos elementos.
     */
    @Override
	public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        Tarea t = (Tarea) o;

        if(!titulo.equals(t.getTitulo()))
            return false;

		if(indice != t.getIndice())
            return false;

		LocalDateTime otro = t.getFechaHora();
		DateTimeFormatter ff = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		DateTimeFormatter fh = DateTimeFormatter.ofPattern("HH:mm");
		String fecha2 = otro.format(ff);
		String hora2  = otro.format(fh);
		if(!(fecha + " " + hora).equals(fecha2 + " " + hora2))
            return false;

        if(progreso != t.getProgreso())
            return false;

        return true;

    }

	/**
     *	Método para comparar esta Tarea con otra a partir del LocalDateTime.
	 *	Necesaria para ordenar.
     *	@param t la otra Tarea.
     *	@return -1 si esta Tarea si esta es antes.
     *           0 si ambas son iguales.
     *           1 si esta Tarea si esta es después.
     */
    @Override
	public int compareTo(Tarea t) {

		LocalDateTime este = getFechaHora();
		LocalDateTime otro = t.getFechaHora();

        return este.compareTo(otro);

    }

}
