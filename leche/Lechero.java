import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author billy.johnson
 */
public class Lechero {

    BufferedReader br;

    /**
     * id de la nueva entrega
     */
    private int nuevoIndice;

    private int nuevoProductorId;

    private final Map<Integer, Productor> productores;

    /**
     * Promedio global hist�rico de botellas entregadas por un productor en la
     * compa��a
     */
    private double PGBE;

    public static final int OPCION_SALIR = 0;
    public static final int OPCION_REGISTRAR_PRODUCTOR = 1;
    public static final int OPCION_REGISTRAR_ENTREGA = 2;
    public static final int OPCION_MOSTRAR_PRODUCTORES = 3;
    public static final int OPCION_MOSTRAR_CANTIDAD_BOTELLAS_X_ENTREGA = 4;
    public static final int OPCION_AJUSTAR_PGBE = 5;
    public static final int OPCION_MOSTRAR_SI_PROMEDIO_MAYOR_PGBE = 6;
    public static final int OPCION_MOSTRAR_CAMBIO_PORCENTUAL_LINEAL = 7;
    public static final int OPCION_MOSTRAR_CAMBIO_PORCENTUAL_LINEAL_TODOS = 8;

    /**
     * Cantidad maxima de productores que permite el programa
     */
    public static final int MAX_CANTIDAD_PRODUCTORES = 12;

    public Lechero() {
        br = new BufferedReader(new InputStreamReader(System.in));
        PGBE = nuevoIndice = nuevoProductorId = 0;
        productores = new HashMap();
    }

    /**
     * Utilizado para obtener el nuevo id de entrega
     *
     * @return
     */
    public int getNuevoIndice() {
        nuevoIndice++;
        return nuevoIndice;
    }

    /**
     * Utiizado para obtener el nuevo id de un productor
     *
     * @return
     */
    public int getNuevoProductorId() {
        nuevoProductorId++;
        return nuevoProductorId;
    }

    /**
     * Registra un nuevo productor en el arreglo/mapa de productores
     *
     * @param p
     */
    public void registrarProductor(Productor p) {
        productores.put(p.getId(), p);
    }

    /**
     * Devuelve la cantidad de botellas entregadas por productor
     *
     * @param id_productor id del productor que se desea
     * @return
     */
    public Integer calcularTotalBotellasProductor(int id_productor) {
        Productor p = buscarProductor(id_productor);
        int total = 0;
        if (p != null) {
            total = p.getTotalBotellasEntregadas();
        }
        return total;
    }

    /**
     * Devuelve un objeto Productor si lo encuentra, de otro modo null
     *
     * @param idProductor id del productor que se desea encontrar
     * @return
     */
    public Productor buscarProductor(int idProductor) {
        return productores.get(idProductor);
    }

    /**
     * Retorna el indice donde se encuentra el productor
     *
     * @param idProductor id del productor que se desea buscar
     * @return
     */
    public int buscarProductorIndex(int idProductor) {
        return 0;
    }

    /**
     * Recicbe el id del productor y devuelve una lista con las entregas
     * realizadas
     *
     * @param idProductor
     * @return
     */
    public List<Entrega> getBotellasPorEntrega(int idProductor) {
        Productor p = buscarProductor(idProductor);
        List<Entrega> entregas = new ArrayList();
        if (p != null) {
            p.getEntregas().forEach((k, v) -> {
                entregas.add(v);
            });
        }
        return entregas;
    }

    /**
     * Determina si el promedio de botellas entregadas de un productor es mayor
     * al PGBE
     *
     * @param idProductor
     * @return
     */
    public boolean isPromedioMayorPGBE(int idProductor) {
        Productor p = buscarProductor(idProductor);
        if (p != null) {
            return p.getTotalBotellasEntregadas() > PGBE;
        } else {
            return false;
        }
    }

    /**
     * Devuelve
     *
     * @param entregas
     * @return
     */
    public Double getCambioPorcentualLineal(List<Entrega> entregas) {
        Double cambioPorcentualLineal = null;
        if (!entregas.isEmpty()) {
            Entrega primeraEntrega = entregas.get(0);
            Entrega ultimaEntrega = entregas.get(entregas.size() - 1);
            double denominador = primeraEntrega.getBotellas();
            cambioPorcentualLineal = (ultimaEntrega.getBotellas() - primeraEntrega.getBotellas()) / denominador;
        }
        return cambioPorcentualLineal;
    }

    /**
     * Devuelve un mapa con los cambios porcentuales de todos productores
     *
     * @return key id del productor, value cambio porcentual lineal
     */
    public Map<Integer, Double> getCambioPorcentualProductores() {
        Map<Integer, Double> cambios = new HashMap();
        productores.forEach((k, v) -> {
            cambios.put(k, getCambioPorcentualLineal(getBotellasPorEntrega(v.getId())));
        });
        return cambios;
    }

    /**
     * set a value to PGBE
     *
     * @param PGBE
     */
    private void setPGBE(double PGBE) {
        this.PGBE = PGBE;
    }

    public double getPGBE() {
        return PGBE;
    }

    /**
     * Actualiza el promedio hist�rico de botellas entregadas por un productor
     * en la compa��a
     *
     * @param pgbe
     */
    public void actualizarPromedioGlobalHistoricoDeBotellasEntregadas(int pgbe) {
        setPGBE(pgbe);
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
            System.out.println("Opci�n incorrecta. Vuelva a intentarlo");
            opcion = readOpcion(message, min, max);
        }
        return opcion;
    }

    public static void main(String[] args) {

//        String[] promotions = {
//            "PS0091253",
//            "PS0077672",
//            "PS0093314",
//            "PS0093311",
//            "PS0094681",
//            "PS0094127",
//            "PS0094126",
//            "PS0091999",
//            "PS0081485",
//            "PS0085909",
//            "PS0091953",
//            "PS0092410",
//            "PS0092619",
//            "PS0092411",
//            "PS0093819",
//            "PS0093817",
//            "PS0093813",
//            "PS0093811",
//            "PS0094264",
//            "PS0092803",
//            "PS0093643",
//            "PS0091577",
//            "PS0091860",
//            "PS0090269",
//            "PS0093309",
//            "PS0093303",
//            "PS0081542",
//            "PS0091998",
//            "PS0092959",
//            "PS0093230",
//            "PS0091848",
//            "PS0092377",
//            "PS0092637"
//        };
        Lechero l = new Lechero();
        int opcion = -1;
        while (opcion != OPCION_SALIR) {
            opcion = l.readOpcion("Introduzca " + OPCION_REGISTRAR_PRODUCTOR + " para registrar productor\n"
                    + "Introduzca " + OPCION_REGISTRAR_ENTREGA + " para registrar entrega\n"
                    + "Introduzca " + OPCION_MOSTRAR_PRODUCTORES + " para mostrar productores\n"
                    + "Introduzca " + OPCION_MOSTRAR_CANTIDAD_BOTELLAS_X_ENTREGA + " para mostrar la cantidad de botellas entregadas por productor\n"
                    + "Introduzca " + OPCION_AJUSTAR_PGBE + " para ajustar el promedio global hist�rico de botellas entregadas (PGBE) por productor\n"
                    + "Introduzca " + OPCION_MOSTRAR_SI_PROMEDIO_MAYOR_PGBE + " para mostrar si el promedio de botellas entregadas es mayor que el PGBE\n"
                    + "Introduzca " + OPCION_MOSTRAR_CAMBIO_PORCENTUAL_LINEAL + " para mostrar el cambio porcentual lineal de un productor\n"
                    + "Introduzca " + OPCION_MOSTRAR_CAMBIO_PORCENTUAL_LINEAL_TODOS + " para mostrar el cambio porcentual lineal de todos los productores\n"
                    + "Introduzca " + OPCION_SALIR + " para salir\n",
                    OPCION_SALIR, OPCION_MOSTRAR_CAMBIO_PORCENTUAL_LINEAL_TODOS);
            switch (opcion) {
                case OPCION_SALIR:
                    System.out.println("Adios");
                    break;
                case OPCION_REGISTRAR_PRODUCTOR:
                    l.registrarProductor();
                    break;
                case OPCION_REGISTRAR_ENTREGA:
                    l.registrarEntrega();
                    break;
                case OPCION_MOSTRAR_PRODUCTORES:
                    l.mostrarProductores();
                    break;
                case OPCION_MOSTRAR_CANTIDAD_BOTELLAS_X_ENTREGA:
                    l.mostrarBotellasPorEntrega();
                    break;
                case OPCION_AJUSTAR_PGBE:
                    l.ajustarPGBE();
                    break;
                case OPCION_MOSTRAR_SI_PROMEDIO_MAYOR_PGBE:
                    l.mostrarSiPromedioMayorQuePGBE();
                    break;
                case OPCION_MOSTRAR_CAMBIO_PORCENTUAL_LINEAL:
                    l.mostrarCambioPorcentualLinealTotal();
                    break;
            }
        }
    }

    private Productor leerProductor() {
        Productor p = null;
        while (p == null) {
            try {
                System.out.println("Introduzca el id del productor");
                int idProductor = Integer.parseInt(br.readLine());
                p = buscarProductor(idProductor);
                if (p == null) {
                    System.out.println("Productor no encontrado\nVuelva a intentarlo");
                }
            } catch (IOException | NumberFormatException ex) {
                System.out.println("Id de productor incorrecto\nVuelva a introducirlo");
            }
        }
        return p;
    }

    /**
     * Metodo utilizado para registrar un productor
     */
    public void registrarProductor() {
        if (productores.size() <= MAX_CANTIDAD_PRODUCTORES) {
            Productor p = new Productor();
            p.setId(getNuevoProductorId());
            int opcion = -1;
            while (opcion != 1) {
                try {
                    System.out.println("Intetroduzca el nombre del nuevo productor");
                    p.setNombre(br.readLine());
                    opcion = readOpcion("Est� bien el nombre : " + p.getNombre() + "?\n"
                            + "Introduce 1 para confirmar el nombre\n"
                            + "Introduce 2 para cambiarlo\n",
                            1, 2);
                } catch (IOException ex) {
                    System.out.println("Hubo un problema al leer el nombre");
                }
            }
            productores.put(p.getId(), p);
            System.out.println("Productor registrado");
        } else {
            System.out.println("No se puede registrar mas productores, se lleg� al limite maximo de " + MAX_CANTIDAD_PRODUCTORES);
        }
    }

    /**
     * Metodo para registrar una entrega a un productor
     */
    public void registrarEntrega() {
        Productor p = leerProductor();
        System.out.println("Productor " + p.getNombre());
        Entrega e = new Entrega();
        String nombre = "";
        int botellas = 0;
        boolean ioError;
        do {
            ioError = false;
            try {
                System.out.println("Introduzca el nombre del cliente");
                nombre = br.readLine();
                System.out.println("Introduzca la cantidad de botellas");
                botellas = Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException ex) {
                System.out.println("Hubo un error de lectura\nvolvamos");
                ioError = true;
            }
        } while (ioError);
        e.setBotellas(botellas);
        e.setCliente(nombre);
        p.addEntrega(e);
        System.out.println("Entrega registrada");
    }

    /**
     * Muestra a los productores que est�n registrados
     */
    public void mostrarProductores() {
        System.out.println("A continuaci�n los productores registrados");
        productores.forEach((k, v) -> {
            System.out.println(k + ": " + v.getNombre() + "\tentregas: " + v.getCantidadEntregas());
        });
    }

    /**
     * Muestra las botellas por entrega de un productor
     */
    public void mostrarBotellasPorEntrega() {
        Productor p = leerProductor();
        p.getEntregas().forEach((k, v) -> {
            System.out.println("Entrega " + k + "\t botellas: " + v.getBotellas());
        });
    }

    /**
     * Metodo utilizado para ajustar el PGBE
     */
    public void ajustarPGBE() {
        boolean ioError;
        do {
            ioError = false;
            try {
                System.out.println("Introduzca el nuevo valor del promedio globla hist�rico de botellas entregadas por productor");
                int pgbe = Integer.parseInt(br.readLine());
                actualizarPromedioGlobalHistoricoDeBotellasEntregadas(pgbe);
                System.out.println("PGBE ajustado");
            } catch (IOException | NumberFormatException ex) {
                System.out.println("Hubo un error de lectura, volvamos a intentar");
                ioError = true;
            }
        } while (ioError);
    }

    /**
     * Metodo utilizado para mostrar si el promedio de botellas es mayor que
     * PGBE
     */
    public void mostrarSiPromedioMayorQuePGBE() {
        Productor p = leerProductor();
        System.out.println("Productor: " + p.getNombre() + (isPromedioMayorPGBE(p.getId()) ? " Su promedio es mayor que PGBE" : " Su promedio es menor que PSBE"));
    }

    /**
     * Muestra el cambio porcentual lineal de un productor
     */
    public void mostrarCambioPorcentualLineal() {
        Productor p = leerProductor();
        System.out.println("Productor " + p.getNombre() + "\tcambio porcentual lineal: " + getCambioPorcentualLineal(getBotellasPorEntrega(p.getId())));
    }

    /**
     * Muestra del cambio porcentual lineal de todos los productores
     */
    public void mostrarCambioPorcentualLinealTotal() {
        Map<Integer, Double> cambios = getCambioPorcentualProductores();
        System.out.println("A continuaci�n el cambio porcentual de todos productores");
        cambios.forEach((k, v) -> {
            System.out.println("id: " + k + "\t" + "Productor: " + productores.get(k).getNombre() + "\tcambio porcentual lineal: " + v);
        });
    }
}
