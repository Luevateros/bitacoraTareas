/**
 *	Clase que permiten al sistema asignar un color a una categoria.
 * 	@author Ricardo A. Luévano B.
 * 	@since 1.0
 */

public class Tag{

	private String categoria;
	private String color;

	/**
     *  Constructor de Tag. Para esta versión no se pueden
	 *	crear nuevas Tags, sólo las que vienen en el JSON.
	 * 	@param color.
     */
	public Tag(String color){
		this.categoria = "";
		this.color = color;;
	}

	/**
     *  Método que devuelve la categoria.
	 * 	@return categoria
     */
	public String getCategoria(){
		return categoria;
	}

	/**
     *  Método que devuelve el color.
	 * 	@return color
     */
	public String getColor(){
		return color;
	}

	/**
     *  Método paara signar la categoria.
	 * 	@param categoria nueva
     */
	public void setCategoria(String categoria){
		this.categoria = categoria;
	}

}
