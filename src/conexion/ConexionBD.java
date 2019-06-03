package conexion;

import java.sql.*;

public class ConexionBD {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "tienda";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL_PARAM = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + URL_PARAM;

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la base de datos " + DATABASE);
            ex.printStackTrace();
            System.exit(1);
        }

        return conexion;
    }

}
