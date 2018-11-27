package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author billy.johnson
 */
public class MyConnection {

    private static MyConnection myConnection;
    private static final String CATALOG = "Taller";
    private static final String USER = "taller_user";
    private static final String PASSWORD = "taller_password";

    /**
     * Constructor de la clase Se encarga de cargar el driver a la base de datos
     */
    private MyConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Devuelve una instancia de la clase
     *
     * @return
     */
    public static MyConnection getInstance() {
        if (myConnection == null) {
            myConnection = new MyConnection();
        }
        return myConnection;
    }

    public Connection getConnection() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection("jdbc:derby://localhost:1527/" + CATALOG, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return cn;
    }

    /**
     * Metodo utilizado para probar la conexion a la base de datos
     *
     * @return
     */
    public static boolean testConnection() {
        Connection cn = null;
        boolean b = false;
        try {
            cn = MyConnection.getInstance().getConnection();
            b = cn != null && !cn.isClosed();
        } catch (SQLException ex) {
            System.out.println("Error al probar conexion");
            System.out.println(ex);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return b;
    }

}
