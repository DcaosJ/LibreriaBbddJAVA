package servidor.base.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * DatabaseManager se encarga de gestionar las operaciones de base de datos,
 * incluyendo consultas y actualizaciones de datos.
 * Utiliza la API JDBC para interactuar con la base de datos.
 * Requiere una instancia válida de DatabaseConnection para establecer la conexión.
 * @author jd
 *
 */

public class DatabaseManager {

	private  DatabaseConnection databaseConnection = null;
	/**
	 * Constructor especializado en inicializar objetos
	 * de tipo DatabaseManager a partir de un objeto de conexión
	 * que no puede ser nulo
	 * @param connection Objeto de conexión
	 */
	public DatabaseManager(@NonNull DatabaseConnection connection) {
		this.databaseConnection = connection;
	}

	//1--Se debe poder consultar los datos de cualquiera de las tablas de la bbdd
	/**
	 * Obtiene la lista de productos almacenados en la base de datos.
	 * @return una lista de objetos Producto
	 */
	public List<Producto> getProductos() {
		Connection connection=null;
		PreparedStatement ps = null;
		List<Producto> productos = null;
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la consulta SQL para obtener los productos
			ps = connection.prepareStatement("SELECT * FROM producto");
			productos = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			// Iterar sobre los resultados y crear objetos Producto
			while (rs.next()) {
				Producto producto = new Producto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDouble(5),
						rs.getInt(6)
						);
				productos.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return productos;
	}
	/**
	 * Obtiene la lista de clientes almacenados en la base de datos.
	 * @return una lista de objetos Cliente
	 */
	public List<Cliente> getClientes() {
		Connection connection=null;
		PreparedStatement ps = null;
		List<Cliente> clientes = new ArrayList<>();
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la consulta SQL para obtener los clientes
			ps = connection.prepareStatement("SELECT * FROM cliente");
			clientes = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			// Iterar sobre los resultados y crear objetos Cliente
			while (rs.next()) {
				Cliente cliente = new Cliente(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)

						);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return clientes;
	}
	/**
	 * Obtiene la lista de pedidos almacenados en la base de datos.
	 * @return una lista de objetos pedido
	 */
	public List<Pedido> getPedidos() {
		Connection connection=null;
		PreparedStatement ps = null;
		List<Pedido> pedidos = new ArrayList<>();
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la consulta SQL para obtener los pedidos
			ps = connection.prepareStatement("SELECT * FROM pedido");
			pedidos =  new ArrayList<>();
			ResultSet rs = ps.executeQuery();

			// Iterar sobre los resultados y crear objetos Pedido
			while (rs.next()) {
				Pedido pedido = new Pedido(
						rs.getInt(1),
						rs.getDouble(2),
						rs.getString(3),
						rs.getInt(4)
						);
				pedidos.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return pedidos;
	}


	//2 --Se debe poder filtrar los datos de cualquiera de las tablas de la bbdd, al menos por 2 campos

	public void getData (String source, ArrayList<String> fields,
			HashMap<String,Object> filter) {
		// Implementación del método para obtener datos
	}
	/**
	 * Obtiene una lista de productos filtrados según el filtro especificado.
	 * @param filter el filtro a aplicar
	 * @return una lista de productos filtrados
	 */
	public ArrayList<Producto> getProductosFiltrados(HashMap<String,Object> filter){
		Connection connection=null;
		ArrayList<Producto> productos = null;
		PreparedStatement ps = null;
		int i=0, type=Types.INTEGER;
		String whereData="";

		// Establecer la conexión a la base de datos
		this.databaseConnection.connect();
		connection = this.databaseConnection.getConnection();

		// Construir la cláusula WHERE para el filtro
		for(String key:filter.keySet()) {
			whereData+=key+"=? AND"; 
		}

		whereData = whereData.substring(0, whereData.length()-6);
		try {
			// Preparar la consulta SQL para obtener los productos filtrados
			ps = connection.prepareStatement("SELECT id_articulo,"
					+ "nombre_categoria,descripcion,precio,stock_cantidad "
					+ "FROM producto WHERE " + whereData);
			// Establecer los valores del filtro en la consulta preparada
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
			// Iterar sobre los resultados y crear objetos Producto
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
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return productos;
	}
	/**
	 * Obtiene una lista de clientes filtrados según el filtro especificado.
	 * @param filter el filtro a aplicar
	 * @return una lista de clientes filtrados
	 */
	public ArrayList<Cliente> getClientesFiltrados(HashMap<String,Object> filter){
		Connection connection=null;
		ArrayList<Cliente> clientes = null;
		PreparedStatement ps = null;
		int i=0, type=Types.INTEGER;
		String whereData="";

		// Establecer la conexión a la base de datos
		this.databaseConnection.connect();
		connection = this.databaseConnection.getConnection();

		// Construir la cláusula WHERE para el filtro
		for(String key:filter.keySet()) {
			whereData+=key+"=? AND"; 
		}

		whereData = whereData.substring(0, whereData.length()-6);
		try {
			// Preparar la consulta SQL para obtener los productos filtrados
			ps = connection.prepareStatement("SELECT id_cliente,"
					+ "nombre,dni"
					+ "FROM  WHERE " + whereData);
			// Establecer los valores del filtro en la consulta preparada
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
			// Iterar sobre los resultados y crear objetos Cliente
			ResultSet rs = ps.executeQuery();
			clientes = new ArrayList<Cliente>();
			while(rs.next()) {
				clientes.add(new Cliente(rs.getInt(1),
						rs.getString(2),
						rs.getString(3)));  
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return clientes;
	}
	/**
	 * Obtiene una lista de pedidos filtrados según el filtro especificado.
	 * @param filter el filtro a aplicar
	 * @return una lista de pedidos filtrados
	 */
	public ArrayList<Pedido> getPedidosFiltrados(HashMap<String,Object> filter){
		Connection connection=null;
		ArrayList<Pedido> pedidos = null;
		PreparedStatement ps = null;
		int i=0, type=Types.INTEGER;
		String whereData="";

		// Establecer la conexión a la base de datos
		this.databaseConnection.connect();
		connection = this.databaseConnection.getConnection();

		// Construir la cláusula WHERE para el filtro
		for(String key:filter.keySet()) {
			whereData+=key+"=? AND"; 
		}

		whereData = whereData.substring(0, whereData.length()-6);
		try {
			// Preparar la consulta SQL para obtener los productos filtrados
			ps = connection.prepareStatement("SELECT id_pedido,"
					+ "nombre_producto, pago, fecha, id_cliente"
					+ "FROM pedido WHERE " + whereData);
			// Establecer los valores del filtro en la consulta preparada
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
			// Iterar sobre los resultados y crear objetos Pedido
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
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return pedidos;
	}
	//3--Se pueden obtener los datos de una consulta de manera ordenada por alguno de los campos seleccionados
	/**
	 * Obtiene una lista de productos filtrados y ordenados según las columnas especificadas.
	 * @param columnOrders la lista de columnas y órdenes de ordenamiento
	 * @return una lista de productos filtrados y ordenados
	 */
	public List<Producto> getProductosFiltradosOrdenados(List<ColumnOrder> columnOrders) {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Producto> productos = new ArrayList<>();
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			//Constuye la sentencia de consulta
			StringBuilder queryBuilder = new StringBuilder("SELECT * FROM producto");

			// Agregar ordenamiento de columnas
			if (!columnOrders.isEmpty()) {
				queryBuilder.append(" ORDER BY");
				for (ColumnOrder columnOrder : columnOrders) {
					queryBuilder.append(" ").append(columnOrder.getIndex()).append(" ").append(columnOrder.getOrder()).append(",");
				}
				queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Eliminar la última coma
			}
			// Preparar la sentencia SQL
			ps = connection.prepareStatement(queryBuilder.toString());
			ResultSet rs = ps.executeQuery();

			// Iterar sobre los resultados y crear objetos Producto
			while (rs.next()) {
				Producto producto = new Producto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDouble(5),
						rs.getInt(6)
						);
				productos.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return productos;
	}
	/** Obtiene una lista de clientes filtrados y ordenados según las columnas especificadas.
	 * @param filter el filtro a aplicar para la consulta
	 * @param columnOrders  la lista de columnas y órdenes de ordenamiento
	 * @return una lista de clientes filtrados y ordenados
	 */
	public ArrayList<Cliente> getClienteFiltradosOrdenados(HashMap<String, Object> filter, List<ColumnOrder> columnOrders) {
		Connection connection = null;
		ArrayList<Cliente> clientes = null;
		PreparedStatement ps = null;
		int i = 0;
		int type = Types.INTEGER;
		String whereData = "";

		// Establecer la conexión a la base de datos
		this.databaseConnection.connect();
		connection = this.databaseConnection.getConnection();

		// Construir la cláusula WHERE del filtro
		for (String key : filter.keySet()) {
			whereData += key + "=? AND";
		}

		whereData = whereData.substring(0, whereData.length() - 6);
		try {
			//Constuye la sentencia de consulta
			StringBuilder queryBuilder = new StringBuilder("SELECT id_cliente, nombre, dni, "
					+ " FROM cliente WHERE ");
			queryBuilder.append(whereData);

			// Agregar ordenamiento de columnas
			if (!columnOrders.isEmpty()) {
				queryBuilder.append(" ORDER BY");
				for (ColumnOrder columnOrder : columnOrders) {
					queryBuilder.append(" ").append(columnOrder.getIndex()).append(" ").append(columnOrder.getOrder()).append(",");
				}
				queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Eliminar la última coma
			}
			// Preparar la sentencia SQL
			ps = connection.prepareStatement(queryBuilder.toString());

			// Establecer los valores de los parámetros del filtro
			for (Object value : filter.values()) {
				if (value instanceof Integer) {
					type = Types.INTEGER;
				} else if (value instanceof Float) {
					type = Types.FLOAT;
				} else if (value instanceof Double) {
					type = Types.DOUBLE;
				} else if (value instanceof String) {
					type = Types.VARCHAR;
				}
				ps.setObject(++i, value, type);
			}

			ResultSet rs = ps.executeQuery();
			clientes = new ArrayList<Cliente>();
			// Iterar sobre los resultados y crear objetos Cliente
			while(rs.next()) {
				clientes.add(new Cliente(rs.getInt(1),
						rs.getString(2),
						rs.getString(3))); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return clientes;
	}

	/** Obtiene una lista de pedidos filtrados y ordenados según las columnas especificadas.
	 * @param filter el filtro a aplicar para la consulta
	 * @param columnOrders  la lista de columnas y órdenes de ordenamiento
	 * @return una lista de pedidos filtrados y ordenados
	 */
	public ArrayList<Pedido> getPedidosFiltradosOrdenados(HashMap<String, Object> filter, List<ColumnOrder> columnOrders) {
		Connection connection = null;
		ArrayList<Pedido> pedidos = null;
		PreparedStatement ps = null;
		int i = 0;
		int type = Types.INTEGER;
		String whereData = "";

		// Establecer la conexión a la base de datos
		this.databaseConnection.connect();
		connection = this.databaseConnection.getConnection();

		// Construir la cláusula WHERE del filtro
		for (String key : filter.keySet()) {
			whereData += key + "=? AND";
		}

		whereData = whereData.substring(0, whereData.length() - 6);
		try {
			//Constuye la sentencia de consulta
			StringBuilder queryBuilder = new StringBuilder("SELECT id_pedido, nombre_producto, pago, "
					+ "fecha, id_cliente FROM pedido WHERE ");
			queryBuilder.append(whereData);

			// Agregar ordenamiento de columnas
			if (!columnOrders.isEmpty()) {
				queryBuilder.append(" ORDER BY");
				for (ColumnOrder columnOrder : columnOrders) {
					queryBuilder.append(" ").append(columnOrder.getIndex()).append(" ").append(columnOrder.getOrder()).append(",");
				}
				queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Eliminar la última coma
			}
			// Preparar la sentencia SQL
			ps = connection.prepareStatement(queryBuilder.toString());

			// Establecer los valores de los parámetros del filtro
			for (Object value : filter.values()) {
				if (value instanceof Integer) {
					type = Types.INTEGER;
				} else if (value instanceof Float) {
					type = Types.FLOAT;
				} else if (value instanceof Double) {
					type = Types.DOUBLE;
				} else if (value instanceof String) {
					type = Types.VARCHAR;
				}
				ps.setObject(++i, value, type);
			}

			ResultSet rs = ps.executeQuery();
			pedidos = new ArrayList<Pedido>();
			// Iterar sobre los resultados y crear objetos Pedido
			while (rs.next()) {
				pedidos.add(new Pedido(rs.getInt(1),
						rs.getDouble(2),
						rs.getString(3),
						rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return pedidos;
	}





	//4--Se debe poder modificar cualquiera de los registros de una tabla---
	/**
	 * Actualiza un producto en la base de datos.
	 * @param producto el producto a actualizar
	 * @return true si la actualización se realizó con éxito, false de lo contrario
	 */
	public boolean updateProducto(Producto producto) {
		boolean updated = false;
		Connection connection=null;
		PreparedStatement ps = null;
		String query = "";
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la sentencia SQL de actualización
			query = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock_cantidad = ? WHERE id = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getDescripcion());
			ps.setDouble(3, producto.getPrecio());
			ps.setInt(4, producto.getStock_cantidad());
			ps.setInt(5, producto.getId_articulo());

			// Ejecutar la sentencia SQL de actualización
			int rowsAffected = ps.executeUpdate();
			updated =  rowsAffected > 0;
		}catch(SQLException e) {
			return updated;
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		// Devolver el resultado de la actualización
		return updated;
	}
	/**
	 * Actualiza un cliente en la base de datos.
	 * @param cliente el cliente a actualizar
	 * @return true si la actualización se realizó con éxito, false de lo contrario
	 */
	public boolean updateCliente(Cliente cliente) {
		boolean updated = false;
		PreparedStatement ps = null;
		Connection connection=null;
		String query = "";
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la sentencia SQL de actualización
			query = "UPDATE cliente SET nombre = ?, dni = ? WHERE id = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getDni());
			ps.setInt(3, cliente.getId_cliente());

			// Ejecutar la sentencia SQL de actualización
			int rowsAffected = ps.executeUpdate();
			updated = rowsAffected > 0;
		}catch(SQLException e) {
			return updated;
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		// Devolver el resultado de la actualización
		return updated;
	}
	/**
	 * Actualiza un pedido en la base de datos.
	 * @param pedido el pedido a actualizar
	 * @return true si la actualización se realizó con éxito, false de lo contrario
	 */
	public boolean updatePedido(Pedido pedido) {
		boolean updated = false;
		Connection connection=null;
		PreparedStatement ps = null;
		String query = "";
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la sentencia SQL de actualización
			query = "UPDATE pedido SET pago = ?, fecha = ?, id_cliente = ? WHERE id_pedido = ?";
			ps = connection.prepareStatement(query);
			ps.setDouble(1, pedido.getPago());
			ps.setString(2, pedido.getFecha());
			ps.setInt(3, pedido.getId_cliente());
			ps.setInt(4, pedido.getId_pedido());

			// Ejecutar la sentencia SQL de actualización
			int rowsAffected = ps.executeUpdate();
			updated = rowsAffected > 0;
		}catch(SQLException e) {
			return updated;
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		// Devolver el resultado de la actualización
		return updated;
	}



	//5--Se debe poder añadir nuevos registros a una tabla, de uno en uno o varios	
	/**
	 * Añade un nuevo producto a la base de datos
	 * @param producto Producto que se va a añadir
	 * @return True si se consigue añadir el nuevo producto, false de lo contrario
	 */
	public boolean addProducto(Producto producto) {
		Connection connection=null;
		PreparedStatement ps = null;
		boolean added=false;
		String query= "";
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la sentencia SQL de inserción
			query = "INSERT INTO producto (id_articulo, nombre_categoria, nombre, descripcion, precio, stock_cantidad) VALUES (?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, producto.getId_articulo());
			ps.setString(2, producto.getNombre_categoria());
			ps.setString(3, producto.getNombre());
			ps.setString(4, producto.getDescripcion());
			ps.setDouble(5, producto.getPrecio());
			ps.setInt(6, producto.getStock_cantidad());

			// Ejecutar la sentencia SQL de inserción
			int rowsAffected = ps.executeUpdate();
			added = rowsAffected > 0;
		}catch(SQLException e) {
			return added;
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return added;
	}

	/**
	 * Añade un nuevo cliente a la base de datos
	 * @param cliente Cliente que se va a añadir
	 * @return True si se consigue añadir el nuevo cliente, false de lo contrario
	 */
	public boolean addCliente(Cliente cliente) {
		Connection connection=null;
		PreparedStatement ps = null;
		boolean added=false;
		String query = "";
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la sentencia SQL de inserción
			query = "INSERT INTO cliente (id_cliente, nombre, dni) VALUES (?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cliente.getId_cliente());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getDni());

			// Ejecutar la sentencia SQL de inserción
			int rowsAffected = ps.executeUpdate();
			added = rowsAffected > 0;

		}catch(SQLException e) {
			return added;
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return added;
	}
	/**
	 * Añade un nuevo pedido a la base de datos
	 * @param pedido Pedido que se va a añadir
	 * @return True si se consigue añadir el nuevo pedido, false de lo contrario
	 */
	public boolean addPedido(Pedido pedido) {
		Connection connection = null;
		PreparedStatement ps = null;
		boolean added=false;
		String query = "";
		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Preparar la sentencia SQL de inserción
			query = "INSERT INTO pedido (id_pedido, pago, fecha, id_cliente) VALUES (?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, pedido.getId_pedido());
			ps.setDouble(2, pedido.getPago());
			ps.setString(3, pedido.getFecha());
			ps.setInt(4, pedido.getId_cliente());

			// Ejecutar la sentencia SQL de inserción
			int rowsAffected = ps.executeUpdate();
			added = rowsAffected > 0;
		}catch (SQLException e) {
			e.printStackTrace();
			return added;
		}finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
		return added;
	}

	//-6 Se debe poder eliminar registros de una tabla, de uno en uno o varios 


	/**
	 * Elimina registros de la tabla de productos que coinciden con el campo y el valor especificados.
	 * @param fieldName el nombre del campo en la tabla de productos
	 * @param value el valor que se utilizará para buscar y eliminar los registros
	 * @return true si se eliminaron registros, false de lo contrario
	 */
	public boolean deleteProducto(String fieldName, Object value) {
		boolean deleted = false;
		Connection connection = null;
		PreparedStatement ps = null;
		String query = "";

		try {
	        // Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

	        // Preparar la sentencia SQL de eliminación
			query = "DELETE FROM producto WHERE " + value + " = ?";
			ps = connection.prepareStatement(query);
			ps.setObject(1, value);

	        // Ejecutar la sentencia SQL de eliminación
			int rowsAffected = ps.executeUpdate();
			deleted = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
	    // Devolver el resultado de la eliminación
		return deleted;
	}

	/**
	 * Elimina registros de la tabla de clientes que coinciden con el campo y el valor especificados.
	 * @param fieldName el nombre del campo en la tabla de clientes
	 * @param value el valor que se utilizará para buscar y eliminar los registros
	 * @return true si se eliminaron registros, false de lo contrario
	 */
	public boolean deleteCliente(String fieldName, Object value) {
		boolean deleted = false;
		Connection connection = null;
		PreparedStatement ps = null;
		String query = "";

		try {
	        // Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

	        // Preparar la sentencia SQL de eliminación
			query = "DELETE FROM cliente WHERE " + value + " = ?";
			ps = connection.prepareStatement(query);
			ps.setObject(1, value);

	        // Ejecutar la sentencia SQL de eliminación
			int rowsAffected = ps.executeUpdate();
			deleted = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
	    // Devolver el resultado de la eliminación
		return deleted;
	}
	
	/**
	 * Elimina registros de la tabla de pedidos que coinciden con el campo y el valor especificados.
	 * @param fieldName el nombre del campo en la tabla de pedidos
	 * @param value el valor que se utilizará para buscar y eliminar los registros
	 * @return true si se eliminaron registros, false de lo contrario
	 */
	public boolean deletePedido(String fieldName, Object value) {
		boolean deleted = false;
		Connection connection = null;
		PreparedStatement ps = null;
		String query = "";

		try {
	        // Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

	        // Preparar la sentencia SQL de eliminación
			query = "DELETE FROM pedido WHERE " + value + " = ?";
			ps = connection.prepareStatement(query);
			ps.setObject(1, value);

	        // Ejecutar la sentencia SQL de eliminación
			int rowsAffected = ps.executeUpdate();
			deleted = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//cerramos la conexión
			this.databaseConnection.disconnect();
		}
	    // Devolver el resultado de la eliminación
		return deleted;
	}
}
