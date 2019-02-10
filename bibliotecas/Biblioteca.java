package lwp.bibliotecas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

/**
 *
 * @author billy.johnson
 */
public class Biblioteca {

    Integer nextISBN;
    Integer nextDNI;
    Integer nextPrestamo;

    Map<String, Libro> libros;
    Map<String, Lector> lectores;
    Map<Integer, Prestamo> prestamos;

    public Biblioteca() {
        nextISBN = 1;
        nextDNI = 1;
        nextPrestamo = 1;

        libros = new HashMap();
        lectores = new HashMap();
        prestamos = new HashMap();

        //inicializamos 300 libros aleatorios
        for (int i = 0; i < 300; i++) {
            Libro libro = Libro.generarLibroAleatorio(getNextISBN().toString());
            libros.put(libro.getISBN(), libro);
        }

        //inicializamos 50 lectores
        for (int i = 0; i < 50; i++) {
            Lector lector = Lector.generarLectorAleatorio(getNextDNI().toString());
            lectores.put(lector.getDni(), lector);
        }

//        //Realizamos 150 prestamos aleatorios
//        for (int i = 0; i < 150; i++) {
//            Prestamo prestamo = new Prestamo();
//            prestamo.setId(getNextPrestamo());
//            //Solo se permiten 3 prestamos por lector
//            String dni;
//            int prestamosLector;
//            do {
//                dni = lectores.get(MyUtils.getInstance().getRandomNumber(1, 50).toString()).getDni();
//                prestamosLector = 0;
//                for (int k : prestamos.keySet()) {
//                    if (dni.equals(prestamos.get(k).getLector().getDni())) {
//                        prestamosLector++;
//                        if (prestamosLector == 3) {
//                            break;
//                        }
//                    }
//                }
//            } while (prestamosLector >= 3);
//            prestamo.setLector(lectores.get(dni));
//            Libro libroPrestado;
//            do {
//                libroPrestado = libros.get(MyUtils.getInstance().getRandomNumber(1, 300).toString());
//            } while (libroPrestado.getEjemplaresDisponibles() > 0);
//            prestamo.setLibro(libroPrestado);
//            libros.get(libroPrestado.getISBN()).setEjemplaresDisponibles(libroPrestado.getEjemplaresDisponibles() - 1);
//            prestamos.put(prestamo.getId(), prestamo);
//        }
        //Realizamos los 150 prestamos
        //Para realizar esto recorremos a los 50 lectores y le hacemos 3 prestamos a cada uno
        lectores.forEach((k, v) -> {
            for (int i = 0; i < 3; i++) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(getNextPrestamo());
                prestamo.setLector(v);
                Libro libroPrestado;
                do {
                    libroPrestado = libros.get(MyUtils.getInstance().getRandomNumber(1, 300).toString());
                } while (libroPrestado.getEjemplaresDisponibles() > 0);
                prestamo.setLibro(libroPrestado);
                libros.get(libroPrestado.getISBN()).setEjemplaresDisponibles(libroPrestado.getEjemplaresDisponibles() - 1);
                prestamos.put(prestamo.getId(), prestamo);
                v.setNumeroLibrosPrestados(v.getNumeroLibrosPrestados() + 1);
            }
        });
    }

    private Integer getNextISBN() {
        return nextISBN++;
    }

    private Integer getNextDNI() {
        return nextDNI++;
    }

    private Integer getNextPrestamo() {
        return nextPrestamo++;
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

    //realizar préstamo, devolver libro, relación de préstamos (número de préstamo, título del libro y nombre del lector).
    public static final int OPCION_SALIR = 0;
    public static final int OPCION_REALIZAR_PRESTAMO = 1;
    public static final int OPCION_DEVOLVER_LIBRO = 2;
    public static final int OPCION_RELACION_DE_PRESTAMOS = 3;

    public static void main(String[] args) throws IOException {
        //Primero leemos el archivo XML
        Biblioteca b = new Biblioteca();
        int opcion = -1;
        while (opcion != OPCION_SALIR) {
            opcion = b.readOpcion("Introduzca " + OPCION_SALIR + " para salir"
                    + "\nIntroduzca " + OPCION_REALIZAR_PRESTAMO + " para realizar un prestamo"
                    + "\nIntroduzca " + OPCION_DEVOLVER_LIBRO + " para devolver un libro"
                    + "\nIntroduzca " + OPCION_RELACION_DE_PRESTAMOS + " para mostrar relación de prestamos",
                    OPCION_SALIR, OPCION_RELACION_DE_PRESTAMOS);
            switch (opcion) {
                case OPCION_SALIR:
                    System.out.println("adios");
                    break;
                case OPCION_REALIZAR_PRESTAMO:
                    b.realizarPrestamo();
                    break;
                case OPCION_DEVOLVER_LIBRO:
                    b.devolverLibro();
                    break;
                case OPCION_RELACION_DE_PRESTAMOS:
                    b.relacionPrestamos();
                    break;
            }
        }
    }

    /**
     * Metodo para mostrar los datos de un lector
     *
     * @param lector
     */
    private void showLector(Lector lector) {
        System.out.println("-------------------------------------------------");
        System.out.println("Datos del lector:");
        System.out.println("DNI: " + lector.getDni());
        System.out.println("Nombre: " + lector.getNombre());
        System.out.println("Cantidad de libros prestados: " + lector.getNumeroLibrosPrestados());
//        if (lector.getNumeroLibrosPrestados() > 0) {
//            System.out.println("Libros prestados:");
//            prestamos.forEach((k, v) -> {
//                if (v.getLector().getDni().equals(lector.getDni())) {
//                    showLibro(v.getLibro());
//                }
//            });
//        }
    }

    /**
     * Metodo para mostrar los datos de un libro
     *
     * @param libro
     */
    private void showLibro(Libro libro) {
        System.out.println("\tTitulo: " + libro.getTitulo());
        System.out.println("\tEditorial: " + libro.getEditorial());
        System.out.println("\tISBN: " + libro.getISBN());
        System.out.println("\tPaginas: " + libro.getPaginas());
        System.out.println("\tAutores: ");
        for (String a : libro.getAutores()) {
            System.out.println("\t\t" + a);
        }
//        System.out.println("\t-----------------------------------------");
    }

    /**
     * Método para mostrar los libros que le han prestado a un lector
     *
     * @param lector
     * @return devuelve un mapa en donde la llave es ISBN y el valor es el id
     * del prestamo
     */
    private Map<String, Integer> showLibrosPrestadosLector(Lector lector) {
        Map<String, Integer> m = new HashMap();
        prestamos.forEach((k, v) -> {
            if (v.getLector().getDni().equals(lector.getDni())) {
                showLibro(v.getLibro());
                m.put(v.getLibro().getISBN(), v.getId());
                System.out.println("-----------------------------------------");
            }
        });
        return m;
    }

    /**
     * Metodo que contiene la rutnica para realizar un prestamo
     *
     * @throws IOException
     */
    public void realizarPrestamo() throws IOException {
        String dni, isbn;
        do {
            System.out.print("Introduzca el dni del lector: ");
            dni = br.readLine();
            if (!lectores.containsKey(dni)) {
                System.out.println("El dni no fue encontrado");
            } else {
                if (lectores.get(dni).getNumeroLibrosPrestados() > 3) {
                    System.out.println("El lector ya cuenta con " + lectores.get(dni).getNumeroLibrosPrestados() + " y solo se peermiten hasta 3 prestamos");
                    return;
                }
                showLector(lectores.get(dni));
            }
        } while (!lectores.containsKey(dni));
        showLector(lectores.get(dni));

        do {
            System.out.print("Introduzca el isbn: ");
            isbn = br.readLine();
            if (!libros.containsKey(isbn)) {
                System.out.println("El ISBN no fue encontrado");
            } else {
                if (libros.get(isbn).getEjemplaresDisponibles() < 1) {
                    System.out.println("El libro " + libros.get(isbn).getTitulo() + " no tiene disponibilidad");
                } else {
                    showLibro(libros.get(isbn));
                }
            }
        } while (!libros.containsKey(isbn) || libros.get(isbn).getEjemplaresDisponibles() < 1);

        Prestamo prestamo = new Prestamo();
        prestamo.setId(getNextPrestamo());
        prestamo.setLector(lectores.get(dni));
        prestamo.setLibro(libros.get(isbn));
        lectores.get(dni).setNumeroLibrosPrestados(lectores.get(dni).getNumeroLibrosPrestados() + 1);
        libros.get(isbn).setEjemplaresDisponibles(libros.get(isbn).getEjemplaresDisponibles() - 1);
        prestamos.put(prestamo.getId(), prestamo);
        System.out.println("Prestamo realizado");
    }

    /**
     * Método que contiene la rutina para devolver un libro
     *
     * @throws IOException
     */
    public void devolverLibro() throws IOException {
        String dni, isbn;
        do {
            System.out.print("Introduzca el dni del lector: ");
            dni = br.readLine();
            if (!lectores.containsKey(dni)) {
                System.out.println("El dni no fue encontrado");
            }
        } while (!lectores.containsKey(dni));
        showLector(lectores.get(dni));
        Map<String, Integer> m_prestamo = showLibrosPrestadosLector(lectores.get(dni));
        do {
            System.out.print("Introduzca el isbn: ");
            isbn = br.readLine();
            if (!m_prestamo.containsKey(isbn)) {
                System.out.println("El ISBN no fue encontrado");
            } else {
                prestamos.remove(m_prestamo.get(isbn));
                libros.get(isbn).setEjemplaresDisponibles(libros.get(isbn).getEjemplaresDisponibles() + 1);
                lectores.get(dni).setNumeroLibrosPrestados(lectores.get(dni).getNumeroLibrosPrestados() - 1);

            }
        } while (!m_prestamo.containsKey(isbn));
    }

    /**
     * Método que contiene la rutina para mostrar la relación de prestamos
     */
    public void relacionPrestamos() {
        System.out.println("Relación de prestamos:");
        prestamos.forEach((k, v) -> {
            System.out.println("Prestamo: " + k);
            System.out.println("Titulo: " + v.getLibro().getTitulo());
            System.out.println("Lector: " + v.getLector().getNombre() + " (" + v.getLector().getDni() + ")");
            System.out.println("---------------------------------------------");
        });
    }

}
