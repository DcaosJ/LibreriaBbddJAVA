package servidorBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

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
	public ArrayList<Producto> getProductos(){
		ArrayList<Producto> productos = null;

		PreparedStatement ps;
		try {
			ps = this.connection.
					prepareStatement("SELECT id_articulo,nombre_categoria,"
							+ "descripcion,precio,stock_cantidad FROM producto");
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
	
	public ArrayList<Producto> getProductos(String autor, String editorial){
		ArrayList<Producto> productos = null;
		try {
			PreparedStatement ps = this.connection.
					prepareStatement("SELECT id_articulo,nombre_categoria,"
							+ "descripcion,precio,stock_cantidad FROM producto WHERE id_articulo=?"
							+ " AND nombre_categoria=?");
			ps.setString(1,autor);
			ps.setString(2, editorial);
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
	
	public void getData (String source, ArrayList<String> fields,
			HashMap<String,Object> filter) {
		
	}
	
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
	
}
