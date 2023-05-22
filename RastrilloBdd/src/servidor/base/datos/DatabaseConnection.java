package servidor.base.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * DatabaseConnection se encarga de establecer y administrar la conexión con la base de datos.
 * Utiliza la API JDBC para interactuar con la base de datos.
 * @author jd
 *
 */
public class DatabaseConnection {
	
	private Connection connection;
	private String connectionString;
	private String user;
	private String password;
	
	
	/**
     * Crea una nueva instancia de DatabaseConnection con la información de conexión especificada.
     * @param connectionString la cadena de conexión JDBC para la base de datos
     * @param user el nombre de usuario para la conexión a la base de datos
     * @param password la contraseña para la conexión a la base de datos
     */
	public DatabaseConnection ( String connectionString, String user, String password) {
		this.connectionString = connectionString;
		this.user = user;
		this.password = password;
	}
	//"jdbc:mysql://localhost/Rastrillo?user=root&password=root"
	
	 /**
     * Establece la conexión con la base de datos utilizando la información de conexión proporcionada.
     * @return true si la conexión se estableció correctamente, false de lo contrario
     */
	public boolean connect() {		
		try {
			//cargar el driver
			DriverManager.registerDriver (new com.mysql.cj.jdbc.Driver());

			//otra manera de cargar el controlador
			//Class.forName("com.mysql.cj.jdbc.Driver");			

			//crear un objeto de conexión
			this.connection = 
					DriverManager.getConnection(connectionString, user, password);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return connection==null?false:true;
	}
	/**
     * Cierra la conexión con la base de datos.
     * @return true si la conexión se cerró correctamente, false de lo contrario
     */
	public boolean disconnect() {
		try {
			this.connection.close();
			return true;
		} catch (SQLException e) {			
			return false;
		}
	}
	/**
     * Obtiene el objeto Connection actualmente utilizado para la conexión a la base de datos.
     * @return el objeto Connection actual
     */
	public Connection getConnection() {
		return this.connection;
	}
}
