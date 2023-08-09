package armario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionMVC {
     String DB_URL = "jdbc:mysql://localhost:3306/meu_bd_armario";
     String DB_USER = "root";
     String DB_PASSWORD = "533266";

    public Connection getConnection() throws SQLException {
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	 Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
             
             return conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro do driver do msql.", e);
        }

       
        
    }
}
