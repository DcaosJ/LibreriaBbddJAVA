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
	public ArrayList<Producto> getProducto(){
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
						rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
		
	}			
}
