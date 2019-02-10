package lwp.bibliotecas;

import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author billy.johnson
 */
public class MyUtils {

    private static MyUtils my = null;

    private MyUtils() {

    }

    public static MyUtils getInstance() {
        if (my == null) {
            my = new MyUtils();
        }
        return my;
    }

    /**
     * Devuelve un número aleatorio entre un rango
     *
     * @param min
     * @param max
     * @return
     */
    public Integer getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * Pone mayuscula en la primera letra
     *
     * @param s
     * @return
     */
    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * Devuelve un nombre
     * @return 
     */
    public String getNombreRandom() {
        int maxIndex = PalabrasAleatorias.getInstance().maxPalabras();
        int i_primerNombre, i_segundoNombre, i_primerApellido, i_segundoApellido;
        i_primerNombre = getRandomNumber(0, maxIndex);
        do {
            i_segundoNombre = getRandomNumber(0, maxIndex);
        } while (i_segundoNombre == i_primerNombre);
        do {
            i_primerApellido = getRandomNumber(0, maxIndex);
        } while (i_primerApellido == i_primerNombre || i_primerApellido == i_segundoNombre);

        do {
            i_segundoApellido = getRandomNumber(0, maxIndex);
        } while (i_segundoApellido == i_primerNombre || i_segundoApellido == i_segundoNombre || i_segundoApellido == i_primerApellido);

        String primerNombre = capitalize(PalabrasAleatorias.getInstance().getPalabra(i_primerNombre).toLowerCase());
        String segundoNombre = capitalize(PalabrasAleatorias.getInstance().getPalabra(i_segundoNombre).toLowerCase());
        String primerApellido = capitalize(PalabrasAleatorias.getInstance().getPalabra(i_primerApellido).toLowerCase());
        String segundoApellido = capitalize(PalabrasAleatorias.getInstance().getPalabra(i_segundoApellido).toLowerCase());

        return primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido;
    }

    /**
     * Devuelve un titulo random
     * @return 
     */
    public String getTituloRandom() {
        int cantidadPalabras = getRandomNumber(1, 7);
        String titulo = "";
        int maxIndex = PalabrasAleatorias.getInstance().maxPalabras();
        for (int i = 0; i < cantidadPalabras; i++) {
            titulo += PalabrasAleatorias.getInstance().getPalabra(getRandomNumber(0, maxIndex -1)) + (i + 1 < cantidadPalabras ? " " : "");
        }
        return titulo;
    }

    /**
     * Devuelve una cantitdad de nombres aleatorios
     * @return 
     */
    public String [] getAutoresRandom(){
        int cantidadAutores = getRandomNumber(1, 4);
        String nombres = "";
        for (int i = 0; i < cantidadAutores; i++) {
            nombres += getNombreRandom() + (i + 1 < cantidadAutores ? "_" : "");
        }
        return nombres.split("_");
    }
    
    /**
     * Devuelve un año random desde 1900 hasta el año actual
     * @return 
     */
    public int getAñoRandom(){
        return getRandomNumber(1900, Calendar.getInstance().get(Calendar.YEAR));
    }
}
