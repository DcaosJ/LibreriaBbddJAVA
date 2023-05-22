package servidor.base.datos;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/Rastrillo", "root", "");


		DatabaseManager databaseManager = new DatabaseManager(databaseConnection);

		
		
		// Crear una instancia de SchemaManager
        SchemaManager schemaManager = new SchemaManager(databaseConnection);

        // Ruta del archivo de script para crear la base de datos
        String createScriptFilePath = "bbdd.sql";
        
        // Ruta del archivo de script para importar los datos
        String importScriptFilePath = "datos.sql";

        // Crear la base de datos
        schemaManager.createDatabase(createScriptFilePath);

        // Importar los datos a la base de datos
        schemaManager.importData(importScriptFilePath);

		
        // Obtener y mostrar la lista de productos
        List<Producto> productos = databaseManager.getProductos();
        System.out.println("Productos:");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
        
     // Obtener y mostrar la lista de clientes
        List<Cliente> clientes = databaseManager.getClientes();
        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }


        // Filtrar y mostrar la lista de productos filtrados
        HashMap<String, Object> filtroProductos = new HashMap<>();
        filtroProductos.put("nombre_categoria", "Electrónica");
        ArrayList<Producto> productosFiltrados = databaseManager.getProductosFiltrados(filtroProductos);
        System.out.println("Productos filtrados:");
        for (Producto producto : productosFiltrados) {
            System.out.println(producto);
        }
	

	}

}
