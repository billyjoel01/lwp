package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author billy.johnson
 */
public class MyQuery {

    private static MyQuery myQuery;

    private MyQuery() {
        MyConnection.getInstance();
    }

    public static MyQuery getInstance() {
        if (myQuery == null) {
            myQuery = new MyQuery();
        }
        return myQuery;
    }

    /**
     * Devuelve las tablas que tiene la base de datos
     *
     * @return
     */
    public List<String[]> getTables() {
        List<String[]> l = new ArrayList();
        try {
            Connection cn = MyConnection.getInstance().getConnection();
            ResultSet rs = cn.getMetaData().getTables("Taller", null, "%", null);
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i < columnCount; i++) {
                System.out.print(rs.getMetaData().getColumnName(i) + "\t");
            }

            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getString(i + 1);
                }
                l.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public boolean executeStatement(String sqlCommand) {
        return executeStatement(sqlCommand, "");
    }

    /**
     * Ejecuta un comando a la base de datos con 1 parámetro
     * @param sqlCommand
     * @param param
     * @return 
     */
    public boolean executeStatement(String sqlCommand, String param) {
        return executeStatement(sqlCommand, new String [] {param});
    }
    
    /**
     * Ejecuta un comnado a la base de datos
     *
     * @param sqlCommand
     * @param params
     * @return
     */
    public boolean executeStatement(String sqlCommand, String[] params) {
        Connection cn = null;
        PreparedStatement pst = null;
        int afectados = 0;
        try {
            cn = MyConnection.getInstance().getConnection();
            pst = cn.prepareStatement(sqlCommand);
            if (params != null && sqlCommand.contains("?")) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            afectados = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar sentencia: " + sqlCommand);
            System.out.println("Parametros: ");
            if (params != null) {
                for (String param : params) {
                    System.out.println(param);
                }
            }
            ex.printStackTrace(System.out);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return afectados > 0;
    }

    /**
     * Ejecuta una consulta a la base de datos sin parámetros
     * @param sqlCommand
     * @return 
     */
    public List<String[]> executeQuery(String sqlCommand) {
        return executeQuery(sqlCommand, "");
    }

    /**
     * Ejecuta una consulta a la base de datos con 1 parámetro
     * @param sqlCommand
     * @param param
     * @return 
     */
    public List<String[]> executeQuery(String sqlCommand, String param) {
        return executeQuery(sqlCommand, new String[]{param});
    }

    /**
     * Ejecuta una consulta a la base de datos
     *
     * @param sqlCommand
     * @param params
     * @return
     */
    public List<String[]> executeQuery(String sqlCommand, String[] params) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs;
        List<String[]> ls = null;
        try {
            cn = MyConnection.getInstance().getConnection();
            pst = cn.prepareStatement(sqlCommand);
            if (params != null && sqlCommand.contains("?")) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            rs = pst.executeQuery();
            int cols = rs.getMetaData().getColumnCount();
            String[] row;
            ls = new ArrayList();
            while (rs.next()) {
                row = new String[cols];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getString(i + 1);
                }
                ls.add(row);
            }
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar query: " + sqlCommand);
            ex.printStackTrace(System.out);
        } finally {
            try {
                if (pst != null && !pst.isClosed()) {
                    pst.close();
                }
                if (cn != null && !cn.isClosed()) {
                    cn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return ls;
    }

    /**
     * Este método es para realizar operaciones por lotes como <b>insert</b>,
     * <b>update</b> o <b>delete</b>
     * Es similar al método executeStatement con la diferencia que este para
     * ejecutar una sentencia con multiples parametros. Por ejemplo: Si tenemos
     * una tabla <b>empleados</b> con los campos:
     * <ul>
     * <li>id</li>
     * <li>Nombre</li>
     * <li>Apellido</li>
     * </ul>
     * Y queremos hacer un <b>insert</b> con los siguientes parametros
     * <ul>
     * <li>{1, "Billy", "Johnson"}</li>
     * <li>{2, "Veronica", "Lopez"}</li>
     * <li>{3, "Andres", "Lasso"}</li>
     * </ul>
     * En lugar de hacer 3 llamados al método <b>executeStatement</b> podemos
     * hacer un solo llamado a este método <b>executeBatch</b>.
     *
     * Este método ejecuta los comandos de 1000 a la ves.
     *
     * @param sqlCommand: Comando que se enviará a la base de datos. Ejemplo:
     * insert into empleados (id, Nombre, Apellido) values (?,?,?)
     * @param paramsList: Lista que contiene un arreglo con los parametros del
     * comnado
     * @param cn Objeto con la conexión a la base de datos
     */
    public void executeBatch(String sqlCommand, List<String[]> paramsList, Connection cn) {
        PreparedStatement pst;
        try {
            if (cn != null && !cn.isClosed()) {
                cn.setAutoCommit(false);
                pst = cn.prepareStatement(sqlCommand);
                int contador = 0;
                int index = 0;
                for (String[] params : paramsList) {
                    contador++;
                    index++;
                    for (int i = 0; i < params.length; i++) {
                        pst.setObject((i + 1), params[i]);
                    }
                    pst.addBatch();
                    if (contador >= 1000 || index + 1 == paramsList.size()) {
                        pst.executeBatch();
                        cn.commit();
                        contador = 0;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                if (cn != null && !cn.isClosed()) {
                    cn.commit();
                    cn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

    }
}
