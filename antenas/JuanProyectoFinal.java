package lwp.antenas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.lawebdelprogramador.com/foros/Java/1678383-Ayuda-con-arreglos.html
 *
 * @author billy.johnson
 */
public class JuanProyectoFinal {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int leerEnteroConRango(String mensaje, int min, int max) {
        return leerEnteroConRango(mensaje, min, max, "");
    }

    /**
     * Metodo utilizado para leer un número
     *
     * @param mensaje
     * @param min
     * @param max
     * @param error
     * @return
     */
    public static int leerEnteroConRango(String mensaje, int min, int max, String error) {
        int numero;
        try {
            System.out.print(error + mensaje);
            numero = Integer.parseInt(br.readLine());
            if (numero < min || numero > max) {
                numero = leerEnteroConRango(mensaje.toLowerCase(), min, max, "Valor fuera de rango. El valor debe ir entre " + min + " y " + max + ". ");
            }
        } catch (IOException | NumberFormatException ex) {
            numero = leerEnteroConRango(mensaje, min, max, "Por favor ");
        }
        return numero;
    }

    public static int radioCobertura(int diametro) {
        return (diametro / 2);
    }

    /**
     * Establece la cobertura de una antena
     *
     * @param matriz
     * @param x: coordenada x de la antena que se va a establecer
     * @param y: coordenada y de la antena que se va a establecer
     * @param L
     */
    public static void setearCoberturaAntena(String[][] matriz, int x, int y, int L) {
        int x_min, y_min, x_max, y_max;
        int radio = radioCobertura(L);
        x_min = x - radio < 0 ? 0 : x - radio;
        y_min = y - radio < 0 ? 0 : y - radio;
        x_max = x + radio < matriz.length - 1 ? x + radio : matriz.length - 1;
        y_max = y + radio < matriz[0].length - 1 ? y + radio : matriz[0].length - 1;
        for (int i = x_min; i <= x_max; i++) {
            for (int j = y_min; j <= y_max; j++) {
                matriz[i][j] = matriz[i][j].equals("1") || matriz[i][j].equals("2") ? matriz[i][j] : "0";
            }
        }
    }

    public static void mostrarMatriz(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }

    /**
     * Devuelve una serie de antenas que le darían cobertura a toda la matriz
     * con el menor número de antenas
     *
     * @param matriz
     * @param L
     * @return
     */
    public static Antena[] getAntenasIdeales(String[][] matriz, int L) {
        List<Antena> antenas = new ArrayList();
        int f, c;
        for (int i = 0; i < matriz.length; i += L) {
            f = i + radioCobertura(L) < matriz.length - 1 ? i + radioCobertura(L) : matriz.length - 1;
            for (int j = 0; j < matriz[0].length; j += L) {
                c = j + radioCobertura(L) < matriz[0].length - 1 ? j + radioCobertura(L) : matriz[0].length - 1;
                Antena antena = new Antena(f, c);
                setTotalPuntosSinCobertura(matriz, antena, L);
                if (antena.getPuntosCobertura() > 0) {
                    antenas.add(antena);
                }
            }
        }
        Antena[] s = new Antena[antenas.size()];
        antenas.toArray(s);
        return s;
    }

    /**
     * Evalúa cuantos puentos sin cobertura tiene la antena
     *
     * @param matriz
     * @param antena
     * @param L
     * @return
     */
    public static Antena setTotalPuntosSinCobertura(String[][] matriz, Antena antena, int L) {
        int c_min = antena.getC() - radioCobertura(L) < 0 ? 0 : antena.getC() - radioCobertura(L);
        int c_max = c_min + L - 1 > matriz[0].length - 1 ? matriz[0].length - 1 : c_min + L - 1;
        int f_min = antena.getF() - radioCobertura(L) < 0 ? 0 : antena.getF() - radioCobertura(L);
        int f_max = f_min + L - 1 > matriz.length - 1 ? matriz.length - 1 : f_min + L - 1;
        int contador = 0;
        for (int i = f_min; i <= f_max; i++) {
            for (int j = c_min; j <= c_max; j++) {
                if (matriz[i][j].equals("_")) {
                    contador++;
                }
            }
        }
        antena.setPuntosCobertura(contador);
        return antena;
    }

    /**
     * Devuelve las antenas sugeridas por el programa
     *
     * @param matriz
     * @return
     */
    public static Antena[] getAntenasSugerias(String[][] matriz) {
        List<Antena> antenas = new ArrayList();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j].equals("2")) {
                    antenas.add(new Antena(i, j));
                }
            }
        }
        Antena[] a = new Antena[antenas.size()];
        antenas.toArray(a);
        return a;
    }

    /**
     * Evalúa todos los puntos de la matriz en busqueda de algún punto sin
     * cobertura. Devuelve true si algún punto no tiene cobertura de otro modo
     * false
     *
     * @param matriz
     * @return
     */
    public static boolean isSinCoberturaTotal(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j].equals("_")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        int M = leerEnteroConRango("Introduzca el número de filas de la ciudad: ", 1, 100);
        int N = leerEnteroConRango("Introduzca el número de columnas de la ciudad: ", 1, 100);
        int L;
        do {
            L = leerEnteroConRango("Introduzca el largo del cuadrado que cubre una antena: ", 1, 19);
            if (L < 1 || L % 2 == 0) {
                System.out.println("ATENCION\nEl largo del cuadrado que cubre una antena debe ser impar. Vuelva a ingresar");
            }
        } while (L % 2 != 1);
        int A = leerEnteroConRango("Introduzca el numero de antenas colocadas actualmente: ", 0, 500);
        //Seteamos las antenas existentes
        Antena[] antenasExistentes = new Antena[A];
        for (int i = 0; i < A; i++) {
            antenasExistentes[i] = new Antena();
            antenasExistentes[i].setF(leerEnteroConRango("Fila de la antena " + (i + 1) + ": ", 1, M - 1));
            antenasExistentes[i].setC(leerEnteroConRango("Columna de la antena " + (i + 1) + ": ", 1, N - 1));
        }
        //Inicializamos la matriz
        String[][] matriz = new String[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matriz[i][j] = "_";
            }
        }
        //Colocamos las antenas existentes dentro de la matriz y seteamos la 
        //cobertura de cada antena dentro de la matriz
        for (int i = 0; i < A; i++) {
            matriz[antenasExistentes[i].getF()][antenasExistentes[i].getC()] = "1";
            setearCoberturaAntena(matriz, antenasExistentes[i].getF(), antenasExistentes[i].getC(), L);
        }

        //Ahora mostramos la matriz de la ciudad con sus coberturas
        mostrarMatriz(matriz);
        System.out.println("\n----------------------------------------------------");

        //Creamos un bucle para asignar nuevas antenas. 
        //Este proceso se repetira mientras la matriz 
        while (isSinCoberturaTotal(matriz)) {
            Antena[] antenas = getAntenasIdeales(matriz, L);
            //Evaluamos cual es la antena con mayor puntos sin cobertura disponibles para setearla
            if (antenas.length > 0) {
                Antena mayor = antenas[0];
                for (int i = 1; i < antenas.length; i++) {
                    if (antenas[i].getPuntosCobertura() > mayor.getPuntosCobertura()) {
                        mayor = antenas[i];
                    }
                }
                matriz[mayor.getF()][mayor.getC()] = "2";
                setearCoberturaAntena(matriz, mayor.getF(), mayor.getC(), L);
            }
            mostrarMatriz(matriz);
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||\n");
        }

        Antena[] antenasNuevasColocar = getAntenasSugerias(matriz);
        System.out.println("Numero de antenas nuevas a colocar: " + antenasNuevasColocar.length);
        for (int i = 0; i < antenasNuevasColocar.length; i++) {
            System.out.println("Coordenadas antena nueva " + (i + 1) + ": " + antenasNuevasColocar[i].getF() + ", " + antenasNuevasColocar[i].getC());
        }
    }
}
