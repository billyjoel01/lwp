package utils;

/**
 *
 * @author billy.johnson
 */
public class CreateTableVendedor {

    /**
     * Crea la tabla Vendedor en la base de datos
     *
     * @return
     */
    public static boolean create() {
        String sqlCommand = "CREATE TABLE vendedor ("
                + "id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                + "rut varchar(10) NOT NULL,"
                + "nombre varchar(20) NOT NULL,"
                + "apellidoPaterno varchar(25) NOT NULL,"
                + "apellidoMaterno varchar(35) DEFAULT NULL,"
                + "sueldoBase int NOT NULL,"
                + "PRIMARY KEY (rut)"
                + ")";
        return MyQuery.getInstance().executeStatement(sqlCommand);
    }

    public static void main(String[] args) {
        CreateTableVendedor.create();
    }

}
