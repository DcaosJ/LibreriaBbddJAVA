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
	 public List<Producto> getProductos() {
	        List<Producto> productos = new ArrayList<>();
	        String query = "SELECT * FROM producto";
	        try (Statement statement = connection.createStatement()) {
	            ResultSet rs = statement.executeQuery(query);
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
	        }
	        return productos;
	    }
		
	
	 public List<Cliente> getClientes() {
	        List<Cliente> clientes = new ArrayList<>();
	        String query = "SELECT * FROM cliente";
	        try (Statement statement = connection.createStatement()) {
	            ResultSet rs = statement.executeQuery(query);
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
	        }
	        return clientes;
	    }
	 
	 
	 public List<Pedido> getPedidos() {
	        List<Pedido> pedidos = new ArrayList<>();
	        String query = "SELECT * FROM pedido";
	        try (Statement statement = connection.createStatement()) {
	            ResultSet rs = statement.executeQuery(query);
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
	        }
	        return pedidos;
	    }
	
	
	
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
}
