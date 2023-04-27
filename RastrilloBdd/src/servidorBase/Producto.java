package servidorBase;

public class Producto {
	
	private int id_articulo;
	private String nombre_categoria;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock_cantidad;
	
	
	public Producto(int id_articulo, String nombre_categoria, String nombre, String descripcion, double precio,
			int stock_cantidad) {
		super();
		this.id_articulo = id_articulo;
		this.nombre_categoria = nombre_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock_cantidad = stock_cantidad;
	}


	public int getId_articulo() {
		return id_articulo;
	}


	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}


	public String getNombre_categoria() {
		return nombre_categoria;
	}


	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getStock_cantidad() {
		return stock_cantidad;
	}


	public void setStock_cantidad(int stock_cantidad) {
		this.stock_cantidad = stock_cantidad;
	}


	@Override
	public String toString() {
		return "Productor [id_articulo=" + id_articulo + ", nombre_categoria=" + nombre_categoria + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", precio=" + precio + ", stock_cantidad=" + stock_cantidad + "]";
	} 
	
	
}
