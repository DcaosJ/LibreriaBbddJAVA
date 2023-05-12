package servidor.base.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

;

public class DatabaseManager {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement psStatement = null;
	private boolean productosUpdate = false;
	private ArrayList<Producto> productoData;
	/**
	 * Constructor especializado en inicializar objetos
	 * de tipo DatabaseManager a partir de un objeto de conexión
	 * que no puede ser nulo
	 * @param connection Objeto de conexión
	 */
	public DatabaseManager(@NonNull Connection connection) {
		this.connection = connection;

		try {
			this.statement = connection.createStatement();
			this.productoData =  new ArrayList<Producto>();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//1--Se debe poder consultar los datos de cualquiera de las tablas de la bbdd
	/**
	 * 
	 * @return
	 */
	public List<Producto> getProductos() {
		List<Producto> productos = null;
		try {
			PreparedStatement ps = this.connection.
					prepareStatement("SELECT * FROM producto");
			ResultSet rs = ps.executeQuery();
			productos = new ArrayList<Producto>();
			while (rs.next()) {
				productos.add(new Producto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDouble(5),
						rs.getInt(6)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}
	/**
	 * 
	 * @return
	 */

	public List<Cliente> getClientes() {
		List<Cliente> clientes = null;
		try {
			PreparedStatement ps = this.connection.
					prepareStatement("SELECT * FROM cliente");
			ResultSet rs = ps.executeQuery();
			clientes = new ArrayList<Cliente>();
			while (rs.next()) {
				clientes.add(new Cliente(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	/**
	 * 
	 * @return
	 */

	public List<Pedido> getPedidos() {
		List<Pedido> pedidos = null;
		try {
			PreparedStatement ps = this.connection.
					prepareStatement("SELECT * FROM pedido");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pedidos.add(new Pedido(
						rs.getInt(1),
						rs.getDouble(2),
						rs.getString(3),
						rs.getInt(4)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidos;
	}


	//2 --Se debe poder filtrar los datos de cualquiera de las tablas de la bbdd, al menos por 2 campos

	public void getData (String source, ArrayList<String> fields,
			HashMap<String,Object> filter) {

	}
	/**
	 * 
	 * @param filter
	 * @return productos
	 */
	public ArrayList<Producto> getProductos(HashMap<String,Object> filter){
		ArrayList<Producto> productos = null;
		int i=0, type=Types.INTEGER;
		String whereData="";

		for(String key:filter.keySet()) {
			whereData+=key+"=? AND"; 
		}

		whereData = whereData.substring(0, whereData.length()-6);
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT id_articulo,"
					+ "nombre_categoria,descripcion,precio,stock_cantidad "
					+ "FROM producto WHERE " + whereData);
			for(Object value:filter.values()) {
				if(value instanceof Integer) {
					type = Types.INTEGER;
				}else if(value instanceof Float) {
					type = Types.FLOAT;
				}else if(value instanceof Double) {
					type = Types.DOUBLE;
				}else if(value instanceof String) {
					type = Types.VARCHAR;
				}
				ps.setObject(++i, value, type);				
			}

			ResultSet rs = ps.executeQuery();
			productos = new ArrayList<Producto>();
			while(rs.next()) {
				productos.add(new Producto(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),  
						rs.getString(4),
						rs.getDouble(5),
						rs.getInt(6)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}
	/**
	 * 
	 * @param filter
	 * @return clientes
	 */
	public ArrayList<Cliente> getClientes(HashMap<String,Object> filter){
		ArrayList<Cliente> clientes = null;
		int i=0, type=Types.INTEGER;
		String whereData="";

		for(String key:filter.keySet()) {
			whereData+=key+"=? AND"; 
		}

		whereData = whereData.substring(0, whereData.length()-6);
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT id_cliente,"
					+ "nombre,dni"
					+ "FROM producto WHERE " + whereData);
			for(Object value:filter.values()) {
				if(value instanceof Integer) {
					type = Types.INTEGER;
				}else if(value instanceof Float) {
					type = Types.FLOAT;
				}else if(value instanceof Double) {
					type = Types.DOUBLE;
				}else if(value instanceof String) {
					type = Types.VARCHAR;
				}
				ps.setObject(++i, value, type);				
			}

			ResultSet rs = ps.executeQuery();
			clientes = new ArrayList<Cliente>();
			while(rs.next()) {
				clientes.add(new Cliente(rs.getInt(1),
						rs.getString(2),
						rs.getString(3)));  

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	/**
	 * 
	 * @param filter
	 * @return pedidos
	 */	
	public ArrayList<Pedido> getPedidos(HashMap<String,Object> filter){
		ArrayList<Pedido> pedidos = null;
		int i=0, type=Types.INTEGER;
		String whereData="";

		for(String key:filter.keySet()) {
			whereData+=key+"=? AND"; 
		}

		whereData = whereData.substring(0, whereData.length()-6);
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT id_cliente,"
					+ "nombre,dni"
					+ "FROM producto WHERE " + whereData);
			for(Object value:filter.values()) {
				if(value instanceof Integer) {
					type = Types.INTEGER;
				}else if(value instanceof Float) {
					type = Types.FLOAT;
				}else if(value instanceof Double) {
					type = Types.DOUBLE;
				}else if(value instanceof String) {
					type = Types.VARCHAR;
				}
				ps.setObject(++i, value, type);				
			}

			ResultSet rs = ps.executeQuery();
			pedidos = new ArrayList<Pedido>();
			while(rs.next()) {
				pedidos.add(new Pedido(rs.getInt(1),
						rs.getDouble(2),
						rs.getString(3),
						rs.getInt(4)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidos;
	}
	//4--Se debe poder modificar cualquiera de los registros de una tabla---
	/**
	 * 
	 * @param producto
	 * @return
	 */
	public boolean updateProducto(Producto producto) {
		boolean updated = false;
		String changes="";
		try {
			changes = "UPDATE producto SET " +  
					"',?='" + producto.getNombre() + "',?='" + producto.getDescripcion() +
					"',?='" + producto.getPrecio() + "',?='" + producto.getStock_cantidad() +
					"' WHERE id=" + producto.getId_articulo();

			updated= (this.statement.executeUpdate(changes, new String[] {"nombre_categoria",
					"nombre","descripcion","precio", "stock_cantidad"}))>0;
					this.statement.close();
		}catch(SQLException e) {
			return updated;
		}	
		return updated;
	}
	/**
	 * 
	 * @param cliente
	 * @return
	 */
	public boolean updateCliente(Cliente cliente) {
		boolean updated = false;
		String changes="";
		try {
			changes = "UPDATE cliente SET ?='" + cliente.getNombre() +
					"',?='" + cliente.getDni() +
					"' WHERE id=" + cliente.getId_cliente();

			updated= (this.statement.executeUpdate(changes, new String[] {"nombre",
			"dni"}))>0;
			this.statement.close();
		}catch(SQLException e) {
			return updated;
		}	
		return updated;
	}
	/**
	 * 
	 * @param pedido
	 * @return
	 */
	public boolean updatePedido(Pedido pedido) {
		boolean updated = false;
		String changes="";
		try {
			changes = "UPDATE pedido SET ?='" + pedido.getPago() +
					"',?='" + pedido.getPago() + 	"',?='" + pedido.getFecha() +
					"',?='" + pedido.getId_cliente() +
					"' WHERE id_pedido=" + pedido.getId_pedido();

			updated= (this.statement.executeUpdate(changes, new String[] {"pago",
					"fecha", "id_cliente"}))>0;
					this.statement.close();
		}catch(SQLException e) {
			return updated;
		}	
		return updated;
	}
	//5--Se debe poder añadir nuevos registros a una tabla, de uno en uno o varios---	
	/**
	 * Añade un nuevo producto a la base de datos
	 * @param producto Producto que se va a añadir
	 * @return True si se consigue añadir el nuevo Producto
	 */
	public boolean addProducto(Producto producto) {
		String insert="";
		boolean added=false;
		try {
			insert = "'" + producto.getId_articulo() +
					"','" +  producto.getNombre_categoria() +
					"','" +  producto.getNombre() +
					"','" +  producto.getDescripcion() +
					"','" +  producto.getPrecio() +
					"','" + producto.getStock_cantidad() + "'";
			added = (this.statement.executeUpdate("INSERT INTO producto (id_articulo,"
					+ "nombre_categoria, nombre, descripcion, precio, "
					+ "stock_cantidad VALUES(" + insert +")"))>0;
					this.statement.close();
					return added;
		}catch(SQLException e) {
			return added;
		}
	}
	/**
	 * Añade un nuevo cliente a la base de datos
	 * @param cliente Cliente que se va a añadir
	 * @return True si se consigue añadir el nuevo cliente
	 */
	public boolean addCliente(Cliente cliente) {
		String insert="";
		boolean added=false;
		try {
			insert = "'" + cliente.getId_cliente() +
					"','" +  cliente.getNombre() +
					"','" + cliente.getDni() + "'";
			added = (this.statement.executeUpdate("INSERT INTO cliente (id_cliente,"
					+ "nombre, dni, VALUES(" + insert +")"))>0;
					this.statement.close();
					return added;
		}catch(SQLException e) {
			return added;
		}
	}
	/**
	 * Añade un nuevo pedido a la base de datos
	 * @param pedido Pedido que se va a añadir
	 * @return True si se consigue añadir el nuevo pedido
	 */
	public boolean addPedido(Pedido pedido) {
		String insert="";
		boolean added=false;
		try {
			insert = "'" + pedido.getId_pedido() +
					"','" +  pedido.getPago() +
					"','" +  pedido.getFecha() +
					"','" + pedido.getId_cliente() + "'";
			added = (this.statement.executeUpdate("INSERT INTO pedido (id_pedido,"
					+ "pago, fecha, id_cliente VALUES(" + insert +")"))>0;
					this.statement.close();
					return added;
		}catch(SQLException e) {
			return added;
		}
	}
	//--6 Se debe poder eliminar registros de una tabla, de uno en uno o varios---- 
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteProducto(int id) {
		boolean delete = false;

		try {
			this.psStatement = this.connection.prepareStatement("DELETE FROM produto WHERR id = ?");

			delete = this.psStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return delete;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCliente(int id) {
		boolean delete = false;

		try {
			this.psStatement = this.connection.prepareStatement("DELETE FROM cliente WHERR id = ?");

			delete = this.psStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return delete;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deletePedido(int id) {
		boolean delete = false;

		try {
			this.psStatement = this.connection.prepareStatement("DELETE FROM pedido WHERR id = ?");

			delete = this.psStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return delete;
	}
	//--8 Se podrán importar datos de una tabla desde un fichero XML

	//--9 Se podrán exportar datos de una tabla desde hacia un fichero XML, no tiene porque ser todos los datos de la tabla
}
