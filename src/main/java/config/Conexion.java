package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Provee conexiones JDBC a la base de datos de la biblioteca. */
public final class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliotecaudb?useSSL=false&serverTimezone=America/El_Salvador&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private Conexion() { }

    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (ClassNotFoundException e) { throw new ExceptionInInitializerError(e); }
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
