package servidorBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Test {

	public static void main(String[] args) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		databaseConnection.connect("jdbc:mysql://localhost:3306/Rastrillo", "root", "");
		databaseConnection.getProducto().forEach(System.out::println);

	}

}
