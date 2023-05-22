package servidor.base.datos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SchemaManager se encarga de gestionar el esquema de la base de datos,
 * incluyendo la creación de la base de datos y la importación de datos.
 * @author jd
 *
 */
public class SchemaManager {
	/**
     * La conexión a la base de datos.
     */
	private  DatabaseConnection databaseConnection = null;
	/**
     * Crea una nueva instancia de `SchemaManager`.
     * @param connection la conexión a la base de datos
     */
	public SchemaManager(@NonNull DatabaseConnection connection) {
		this.databaseConnection = connection;
	}
	/**
     * Crea la base de datos utilizando un archivo de script SQL "bbdd.sql".
     * @param scriptFilePath la ruta del archivo de script
     */
	public void createDatabase(String scriptFilePath) {
		Connection connection=null;

		try {
			// Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();


			// Leer el archivo de script
			StringBuilder script = new StringBuilder();
			BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath));
			String line;
			while ((line = reader.readLine()) != null) {
				script.append(line);
				script.append(System.lineSeparator());
			}
			reader.close();

			// Ejecutar el script para crear la base de datos
			Statement statement = connection.createStatement();
			statement.executeUpdate(script.toString());
			statement.close();

			System.out.println("Base de datos creada exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// Cerrar la conexión
			this.databaseConnection.disconnect();
		}
	}
	/**
     * Importa datos a la base de datos utilizando un archivo de script SQL.
     * @param scriptFilePath la ruta del archivo de script
     */
	public void importData(String scriptFilePath) {
		Connection connection=null;

		try {
            // Establecer la conexión a la base de datos
			this.databaseConnection.connect();
			connection = this.databaseConnection.getConnection();

			// Leer el archivo de script
			StringBuilder script = new StringBuilder();
			BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath));
			String line;
			while ((line = reader.readLine()) != null) {
				script.append(line);
				script.append(System.lineSeparator());
			}
			reader.close();

			// Ejecutar el script para importar los datos
			Statement statement = connection.createStatement();
			statement.executeUpdate(script.toString());
			statement.close();
			System.out.println("Datos importados exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// Cerrar la conexión
			this.databaseConnection.disconnect();
		}
	}

}

