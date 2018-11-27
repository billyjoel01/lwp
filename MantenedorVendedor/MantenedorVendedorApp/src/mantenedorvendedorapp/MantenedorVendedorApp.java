package mantenedorvendedorapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author billy.johnson
 */
public class MantenedorVendedorApp {

    private final BufferedReader br;
    private final Vendedor vendedor;

    public static final int OPCION_AGREGAR_VENDEDOR = 1;
    public static final int OPCION_BUSCAR_VENDEDOR = 2;
    public static final int OPCION_ELIMINAR_VENDEDOR = 3;
    public static final int OPCION_LISTAR_VENDEDORES = 4;
    public static final int OPCION_SALIR = 0;

    public MantenedorVendedorApp() {
        br = new BufferedReader(new InputStreamReader(System.in));
        vendedor = new Vendedor();
    }

    /**
     * Metodo utilizado para leer opciones
     *
     * @param message
     * @param min
     * @param max
     * @return
     */
    public int readOpcion(String message, int min, int max) {
        int opcion;
        try {
            System.out.println(message);
            opcion = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException ex) {
            opcion = -1;
        }
        if (opcion == -1 || (opcion < min || opcion > max)) {
            System.out.println("Opción incorrecta. Vuelva a intentarlo");
            opcion = readOpcion(message, min, max);
        }
        return opcion;
    }

    /**
     * Añade un vendedor a la base de datos
     */
    public void agregarVendedor(){
        vendedor.agregarVendedor();
    }

    /**
     * Busca un vendedor en la base de datos
     */
    public void buscarVendedor(){
        vendedor.buscarVendedor();
    }

    /**
     * Elimina un vendedor de la base de datos
     */
    public void eliminarVendedor(){
        vendedor.eliminarVendedor();
    }

    /**
     * Lista a todos los vendedores en la base de datos
     */
    public void listarVendedores(){
        vendedor.listarVendedores();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion = -1;
        MantenedorVendedorApp m = new MantenedorVendedorApp();
        while (opcion != OPCION_SALIR) {
            opcion = m.readOpcion("Introduzca " + OPCION_AGREGAR_VENDEDOR + " para agregar vendedor\n"
                    + "Introduzca " + OPCION_BUSCAR_VENDEDOR + " buscar un vendedor\n"
                    + "Introduzca " + OPCION_ELIMINAR_VENDEDOR + " eliminar un vendedor\n"
                    + "Introduzca " + OPCION_LISTAR_VENDEDORES + " listar vendedores\n",
                    OPCION_SALIR, OPCION_LISTAR_VENDEDORES);
            switch(opcion){
                case OPCION_SALIR:
                    System.out.println("Adios");
                    break;
                case OPCION_AGREGAR_VENDEDOR:
                    m.agregarVendedor();
                    break;
                case OPCION_BUSCAR_VENDEDOR:
                    m.buscarVendedor();
                    break;
                case OPCION_ELIMINAR_VENDEDOR:
                    m.eliminarVendedor();
                    break;
                case OPCION_LISTAR_VENDEDORES:
                    m.listarVendedores();
                    break;
            }
        }
    }

}
