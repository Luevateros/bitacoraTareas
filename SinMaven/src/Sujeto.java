/**
 *	Interfaz para el Sujeto. Parte de Observer.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public interface Sujeto {
    void notificar();
    void agregarObservador(Observador observador);
    void removerObservador(Observador observador);
}
