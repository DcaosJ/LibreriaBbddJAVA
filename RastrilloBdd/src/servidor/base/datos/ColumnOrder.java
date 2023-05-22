package servidor.base.datos;
/**
 * 
 * Representa el orden de una columna en una consulta de base de datos.
 * @author jd
 *
 */
public class ColumnOrder {
	private int index;
	private String order;
	
	/**
     * Crea una instancia de ColumnOrder con el índice y el orden especificados.
     * @param index el índice de la columna
     * @param order el orden de la columna ("ASC" para ascendente, "DESC" para descendente)
     */
	public ColumnOrder(int index, String order) {
		this.index = index;
		this.order = order;
	}
	/**
     * Obtiene el índice de la columna.
     * @return el índice de la columna
     */
	public int getIndex() {
		return index;
	}
	/**
     * Establece el índice de la columna.
     * @param index el índice de la columna
     */
	public void setIndex(int index) {
		this.index = index;
	}
	 /**
     * Obtiene el orden de la columna.
     * @return el orden de la columna ("ASC" para ascendente, "DESC" para descendente)
     */
	public String getOrder() {
		return order;
	}
	 /**
     * Establece el orden de la columna.
     * @param order el orden de la columna ("ASC" para ascendente, "DESC" para descendente)
     */
	public void setOrder(String order) {
		this.order = order;
	}
	
}
