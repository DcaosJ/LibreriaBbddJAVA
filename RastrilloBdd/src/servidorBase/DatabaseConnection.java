package servidorBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private Connection connection;
	//"jdbc:mysql://localhost/Rastrillo?user=root&password=root"
	public boolean connect(@NonNull String connectionString, String user, String password) {		
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
	public boolean disconnect() {
		try {
			this.connection.close();
			return true;
		} catch (SQLException e) {			
			return false;
		}
	}
	public Connection getConnection() {
		return this.connection;
	}
	
	
}

