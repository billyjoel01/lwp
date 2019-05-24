package clinicacamillas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author billy.johnson
 */
public class ClinicaCamillas {

    public static final int CANTIDAD_CAMILLAS = 10;
    public static final int OPCION_INICIALIZAR_ESTADO = 1;
    public static final int OPCION_CONSULTAR_CAMILLA = 2;
    public static final int OPCION_REGISTRAR_PACIENTE = 3;
    public static final int OPCION_DAR_SALIDA = 4;
    public static final int OPCION_SALIR = 0;

    public static Map<Integer, Camilla> camillas = new HashMap();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String leer(String message) {
        String s;
        try {
            System.out.print(message);
            s = br.readLine();
        } catch (IOException ex) {
            System.out.println("Hubo un error de lectura, vuelva a intentar");
            s = null;
        }
        if (s == null) {
            s = leer(message);
        }
        return s;
    }

    public static Integer leerInteger(String message) {
        Integer d;
        try {
            d = Integer.parseInt(leer(message));
        } catch (NumberFormatException ex) {
            System.out.println("Valor incorrecto vuelva a intentar");
            d = null;
        }
        if (d == null) {
            d = leerInteger(message);
        }
        return d;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Programa para gestionar la informacióin de las " + CANTIDAD_CAMILLAS + " camillas de la clinica");
        inicializarEstadoCamillas();
        int opcion = -1;
        while (opcion != OPCION_SALIR) {
            opcion = leerInteger("Introduzca el numero de la opcion\n"
                    + OPCION_INICIALIZAR_ESTADO + " para inicializar el estado de las camillas\n"
                    + OPCION_CONSULTAR_CAMILLA + " para consultar una camilla\n"
                    + OPCION_REGISTRAR_PACIENTE + " para registrar un paciente\n"
                    + OPCION_DAR_SALIDA + " para dar salida a un paciente\n"
                    + OPCION_SALIR + " para salir del programa\n"
            );
            switch (opcion) {
                case OPCION_INICIALIZAR_ESTADO:
                    inicializarEstadoCamillas();
                    System.out.println("Se ha inicializado el estado a disponible de las " + CANTIDAD_CAMILLAS + " camillas");
                    break;
                case OPCION_CONSULTAR_CAMILLA:
                    consultarCamilla();
                    break;
                case OPCION_REGISTRAR_PACIENTE:
                    registrarPaciente();
                    break;
                case OPCION_DAR_SALIDA:
                    darSalida();
                    break;
                case OPCION_SALIR:
                    System.out.println("Opcion salir\nAdios");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
    }

    /**
     * Inicializa el estado de todas las camillas a disponible
     */
    public static void inicializarEstadoCamillas() {
        for (int i = 1; i <= CANTIDAD_CAMILLAS; i++) {
            camillas.put(i, new Camilla(i));
        }
    }

    /**
     * Evalúa si la camilla está disponible
     *
     * @param id
     * @return
     */
    public static boolean isCamillaDisponible(Integer id) {
        return camillas.get(id).isDisponible();
    }

    /**
     * Devuelve el paciente de una camilla
     *
     * @param id
     * @return Si la camilla está disponible devolverá null, de otro modo
     * devolvera el paciente que esté ocupando esa camilla
     */
    public static Paciente getPaciente(Integer id) {
        if (camillas.get(id) == null //Camilla no encontrada
                || camillas.get(id).isDisponible()) { //Si la camilla está disponible no tiene paciente
            return null;
        } else {
            return camillas.get(id).getPaciente();
        }
    }

    /**
     * Imprime las camillas disponibles
     */
    public static void mostrarCamillasDisponibles() {
        String s = "";
        for (Integer k : camillas.keySet()) {
            s += camillas.get(k).isDisponible() ? k + ", " : "";
        }
        s = s.substring(0, s.length() - 2);
        System.out.println("Camillas disponibles: " + s);
    }

    /**
     * Imprime las camillas ocupadas
     */
    public static void mostrarCamillasOcupadas() {
        String s = "";
        for (Integer k : camillas.keySet()) {
            s += !camillas.get(k).isDisponible() ? k + ", " : "";
        }
        s = s.substring(0, s.length() - 2);
        System.out.println("Camillas ocupadas: " + s);
    }

    /**
     * Opción consultar camilla
     */
    public static void consultarCamilla() {
        System.out.println("Opcion consultar camillas");
        mostrarCamillasDisponibles();
        mostrarCamillasOcupadas();
        if (leer("Introduzca 1 para consultar una camilla: ").equals("1")) {
            int id = leerInteger("Introduzca el id de la camilla a consultar: ");
            if (id < 1 || id > CANTIDAD_CAMILLAS) {
                System.out.println("Camilla fuera de rango");
            } else {
                if (isCamillaDisponible(id)) {
                    System.out.println("La camilla está disponible");
                } else {
                    System.out.println("La camilla está ocupada por el paciente " + camillas.get(id).getPaciente().getNombre());
                }
            }
        }
    }

    /**
     * Opcion registrar paciente
     */
    public static void registrarPaciente() {
        System.out.println("Opcion registrar paciente");
        mostrarCamillasDisponibles();
        int id = leerInteger("Introduzca la camilla del nuevo paciente: ");
        if (id > 0 && id <= CANTIDAD_CAMILLAS) {
            if (isCamillaDisponible(id)) {
                System.out.println("Datos del paciente:");
                String nombre = leer("Introduzca el nombre: ");
                String cedula = leer("Introduzca la cedula: ");
                camillas.get(id).setPaciente(new Paciente(nombre, cedula));
            } else {
                System.err.println("Esa camilla no está disponible");
            }
        } else {
            System.err.println("Camilla fuera del rango");
        }
    }

    /**
     * Opcion dar salida
     */
    public static void darSalida() {
        System.out.println("Opcion dar salida");
        mostrarCamillasOcupadas();
        int id = leerInteger("Introduzca la camilla del paciente: ");
        if (id > 0 && id <= CANTIDAD_CAMILLAS) {
            if (!isCamillaDisponible(id)) {
                Paciente p = camillas.get(id).getPaciente();
                System.out.println("Se le dará salida al paciente " + p.getNombre()
                        + "\ncon cedula: " + p.getCedula() + "\n\n");
                camillas.get(id).setPaciente(null);
            } else {
                System.err.println("Esa camilla no tiene paciente");
            }
        } else {
            System.err.println("Camilla fuera del rango");
        }
    }
}
