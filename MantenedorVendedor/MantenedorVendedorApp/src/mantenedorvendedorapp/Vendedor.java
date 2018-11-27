package mantenedorvendedorapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import utils.MyQuery;

/**
 *
 * @author billy.johnson
 */
public class Vendedor {

    private int id;
    private String created_at;
    private String rut;
    private String nombre;
    private String apepat;
    private String apemat;
    private int sueldo;

    public Vendedor() {
        id = 0;
        sueldo = 0;
        created_at = "";
        rut = "";
        nombre = "";
        apemat = "";
        apepat = "";
    }

    public Vendedor(String rut, String nombre, String apepat, String apemat, int sueldo) {
        this.rut = rut;
        this.nombre = nombre;
        this.apepat = apepat;
        this.apemat = apemat;
        this.sueldo = sueldo;
    }

    /**
     * Lee los datos del vendedor por consola
     *
     * @return
     */
    public static Vendedor leerDatos() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Vendedor v = new Vendedor();
        try {
            System.out.println("Introduzca el rut del vendedor: ");
            v.setRut(br.readLine());
            System.out.println("Introduzca el nombre del vendedor: ");
            v.setNombre(br.readLine());
            System.out.println("Introduzca el apellido paterno del vendedor: ");
            v.setApepat(br.readLine());
            System.out.println("Introduzca el apellido materno del vendedor: ");
            v.setApemat(br.readLine());
            System.out.println("Introduzca el sueldo del vendedor: ");
            v.setSueldo(Integer.parseInt(br.readLine()));
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return v;
    }

    /**
     * Permite insertar los datos de un vendedor en la base de datos taller.
     * Retorna el numero de vendeores insertados.
     *
     * @param rut
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param sueldoBase
     * @return
     */
    private int insertarDatos(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, int sueldoBase) {
        return new Vendedor(rut, nombre, apellidoPaterno, apellidoMaterno, sueldoBase).record();
    }

    /**
     * Permite buscar en la base de datos y deplegarlos en pantalla.
     *
     * @param rut
     */
    private void buscarDatos(String rut) {
        Vendedor v = searchByRut(rut);
        System.out.println("id: " + v.getId());
        System.out.println("created at: " + v.getCreated_at());
        System.out.println("nombre: " + v.getNombre());
        System.out.println("apellido paterno: " + v.getApepat());
        System.out.println("apellido materno: " + v.getApemat());
        System.out.println("sueldo base: " + v.getSueldo());
    }

    /**
     * Permitir eliminar los del vendedor cuyo rut coincida con el dado como
     * parámetro.
     *
     * @param rut
     * @return devuelve 0 si no encontró al vendedor
     */
    private int eliminarDatos(String rut) {
        return deleteByRut(rut);
    }

    public void buscarVendedor() {
        System.out.println("Buscar vendedor...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Ingrese un rut a buscar: ");
            String rutLocal = br.readLine();
            if (Vendedor.isExist(rutLocal)) {
                buscarDatos(rutLocal);
            } else {
                System.out.println("No existen datos asociados al rut ingresado");
            }

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Rutina para agregar un vendedor
     */
    public void agregarVendedor() {
        System.out.println("Agregar vendedor...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Primero ingrese un rut para validar que no exista");
            String rutLocal = br.readLine();
            if (Vendedor.isExist(rutLocal)) {
                System.out.println("El rut que deseas ingresar ya está registrado");
            } else {
                Vendedor v = leerDatos();
                insertarDatos(v.getRut(), v.getNombre(), v.getApepat(), v.getApemat(), v.getSueldo());
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void eliminarVendedor() {
        System.out.println("Eliminar vendedor...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Primero ingrese un rut para validar que exista");
            String rutLocal = br.readLine();
            if (Vendedor.isExist(rutLocal)) {
                buscarDatos(rutLocal);
                String opcion;
                do {
                    System.out.println("¿Estas seguro de elimninar estos datos?");
                    System.out.println("Introduce Y para eliminar");
                    System.out.println("Introduce N para cancelar");
                    opcion = br.readLine();
                    switch (opcion) {
                        case "Y":
                            eliminarDatos(rutLocal);
                            break;
                        case "N":
                            System.out.println("Eliminar vendedor cancelado!!");
                            break;
                        default:
                            System.out.println("Opción incorrecta");
                            break;
                    }
                } while (!opcion.equalsIgnoreCase("Y") || !opcion.equalsIgnoreCase("N"));
            } else {
                System.out.println("El rut que deseas eliminar no está registrado");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void listarVendedores() {
        List<String[]> l = MyQuery.getInstance().executeQuery("select ID, CREATED_AT, NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, SUELDOBASE from VENDEDOR");
        l.forEach(row -> {
            for (String r : row) {
                System.out.print(r + "\t");
            }
            System.out.println();
        });
    }

    /**
     * Realiza una busqueda en la base de datos de un vendedor por su <b>rut</b>
     *
     * @param rut
     * @return
     */
    public Vendedor searchByRut(String rut) {
        List<String[]> l = MyQuery.getInstance().executeQuery("select ID, CREATED_AT, NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, SUELDOBASE from VENDEDOR where RUT = ?", rut);
        Vendedor v = new Vendedor();
        if (l != null && !l.isEmpty()) {
            v.setId(Integer.parseInt(l.get(0)[0]));
            v.setCreated_at(l.get(0)[1]);
            v.setNombre(l.get(0)[2]);
            v.setApepat(l.get(0)[3]);
            v.setApemat(l.get(0)[4]);
            v.setSueldo(Integer.parseInt(l.get(0)[5]));
            v.setRut(rut);
            return v;
        } else {
            return null;
        }
    }

    /**
     * Elimina los datos del vendedor de la base de datos
     *
     * @param rut
     * @return el id del vendedor eliminado
     */
    public int deleteByRut(String rut) {
        Vendedor v = searchByRut(rut);
        if (v != null && v.getId() != 0) {
            if (MyQuery.getInstance().executeStatement("delete from VENDEDOR WHERE RUT = ?", v.getRut())) {
                return v.getId();
            }
        }
        return 0;
    }

    /**
     * Guarda el vendedor en la base de datos
     *
     * @return
     */
    public int record() {
        int record = 0;
        if (MyQuery.getInstance().executeStatement("insert into VENDEDOR (RUT, NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, SUELDOBASE) values (?,?,?,?,?)", new String[]{rut, nombre, apepat, apemat, String.valueOf(sueldo)})) {
            record = Integer.parseInt(MyQuery.getInstance().executeQuery("select count(*) from VENDEDOR").get(0)[0]);
        }
        return record;
    }

    /**
     * Evalua si el vendedor existe
     *
     * @return
     */
    public boolean isExist() {
        if (id == 0) {
            return false;
        } else {
            return Integer.parseInt(MyQuery.getInstance().executeQuery("select count(*) from VENDEDOR where ID = ?", String.valueOf(id)).get(0)[0]) > 0;
        }
    }

    /**
     * Evalúa si el rut de un vendedor está registrado
     *
     * @param rut
     * @return
     */
    public static boolean isExist(String rut) {
        return Integer.parseInt(MyQuery.getInstance().executeQuery("select count(*) from VENDEDOR where RUT = ?", rut).get(0)[0]) > 0;
    }

    /**
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apepat
     */
    public String getApepat() {
        return apepat;
    }

    /**
     * @param apepat the apepat to set
     */
    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    /**
     * @return the apemat
     */
    public String getApemat() {
        return apemat;
    }

    /**
     * @param apemat the apemat to set
     */
    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    /**
     * @return the sueldo
     */
    public int getSueldo() {
        return sueldo;
    }

    /**
     * @param sueldo the sueldo to set
     */
    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the created_at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
