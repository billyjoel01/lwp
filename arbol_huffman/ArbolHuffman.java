package lwp.arbol_huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author billy.johnson
 */
public class ArbolHuffman {

    /**
     * Metodo utilizado para cargar el archivo Lorem ipsus
     *
     * @param path ubicación del archivo que tiene texto a codificar
     * @return
     */
    public static String loadArchivo(String path) {
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        String contenido = "";
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(path);
            if (archivo.exists()) {
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;

                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    contenido += linea;
                }
            }

        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace(System.out);
            }
        }
        return contenido;
    }

    /**
     * Devuelve un Mapa con los caracteres y frecuencias de un texto
     *
     * @param texto
     * @return
     */
    public static Map<Character, Simbolo> getMapaCaracteresFrecuencias(String texto) {
        Map<Character, Simbolo> map = new LinkedHashMap();
        for (int i = 0; i < texto.length(); i++) {
            map.put(texto.charAt(i), map.containsKey(texto.charAt(i)) ? map.get(texto.charAt(i)).addFrecuencia(1) : new Simbolo(texto.charAt(i), 1));
        }
        return map;
    }

    /**
     * Devuelve un array de objetos Simbolo ordenados por frecuencia y orden de
     * aparición
     *
     * @param map
     * @return
     */
    public static Simbolo[] getSimbolosOrdenados(Map<Character, Simbolo> map) {
        Simbolo[] simbolos = new Simbolo[map.size()];
        int contador = -1;
        for (Character key : map.keySet()) {
            contador++;
            simbolos[contador] = map.get(key);
        }

        //ordenamiento por burbuja invertido
        for (int i = simbolos.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (simbolos[j].getFrecuencia() > simbolos[j + 1].getFrecuencia()) {
                    Simbolo aux = simbolos[j];
                    simbolos[j] = simbolos[j + 1];
                    simbolos[j + 1] = aux;
                }
            }
        }
        return simbolos;
    }

    /**
     * Devuelve una lista de nodos por cada simbolo en el array de simbolos
     *
     * @param simbolos
     * @return
     */
    public static List<Nodo> getListaNodos(Simbolo[] simbolos) {
        List<Nodo> nodos = new ArrayList();
        for (Simbolo simbolo : simbolos) {
            nodos.add(new Nodo(simbolo.getCaracter(), simbolo.getFrecuencia()));
        }
        return nodos;
    }

    /**
     * Procesa la lista de nodos para generar un unico nodo arbol
     *
     * @param nodos
     * @return
     */
    public static Nodo getArbol(List<Nodo> nodos) {
        while (nodos.size() > 1) {
            Nodo primero = nodos.get(0);
            nodos.remove(0);
            Nodo segundo = nodos.get(0);
            nodos.remove(0);
            Nodo arbol = new Nodo();
            arbol.setValue(primero.getValue() + segundo.getValue());
            arbol.setIzq(primero);
            arbol.setDer(segundo);
            nodos.add(arbol);
        }
        return nodos.get(0);
    }

    /**
     * Recorre el arbol binario y setea la codificación de cada simbolo en base
     * a su ruta dentro del arbol (izquierda o derecha)
     *
     * @param n
     * @param m
     */
    public static void recorrerArbol(Nodo n, Map<Character, Simbolo> m) {
        if (isHoja(n)) {
            m.get(n.getCaracter()).setCodificacion(n.getCamino());
        } else {
            Nodo izq = n.getIzq();
            Nodo der = n.getDer();
            izq.setCamino(n.getCamino() + "0");
            der.setCamino(n.getCamino() + "1");
            recorrerArbol(izq, m);
            recorrerArbol(der, m);
        }
    }

    /**
     * Evalúa si el nodo es o no una hoja
     *
     * @param n
     * @return
     */
    private static boolean isHoja(Nodo n) {
        return n.getIzq() == null && n.getDer() == null;
    }

    /**
     * Con la finalidad de poder decodificar el mensaje necesitamos que la
     * codificación de cada letra vaya con cierta cantidad de digitos (bits) por
     * letra. Además devuelve un mapa con el diccionario de bits por caracter
     *
     * @param mapa
     * @return
     */
    public static Map<String, Character> getDiccionario(Map<Character, Simbolo> mapa) {
        int max = 0;
        for (Character key : mapa.keySet()) {
            max = max < mapa.get(key).getCodificacion().length() ? mapa.get(key).getCodificacion().length() : max;
        }

        Map<String, Character> diccionario = new HashMap();
        for (Character key : mapa.keySet()) {
            String cod = "";
            for (int i = mapa.get(key).getCodificacion().length(); i < max; i++) {
                cod += "0";
            }
            mapa.get(key).setCodificacion(cod + mapa.get(key).getCodificacion());
            diccionario.put(mapa.get(key).getCodificacion(), key);
        }
        return diccionario;
    }

    /**
     * Decodifica el código Huffman con el diccionario
     *
     * @param codigoHuffman
     * @param diccionario
     * @return
     */
    public static String decodificar(String codigoHuffman, Map<String, Character> diccionario) {
        int max = 0;
        for (String key : diccionario.keySet()) {
            max = key.length();
            break;
        }

        char[] a = codigoHuffman.toCharArray();
        String decode = "";
        for (int i = 0; i < a.length; i += max) {
            String key = "";
            for (int j = i; j < i + max; j++) {
                key += a[j];
            }
            decode += diccionario.get(key);
        }
        return decode;
    }

    public static void main(String[] args) {
        //Primero cargamos el archivo que tiene la frase que queremos codificar
        //String frase = loadArchivo("D:\\descargas\\loremIpsus.txt");
        //Si no tienemos el archivo con la frase a codificar entonces podemos 
        //comentar la declaración de la variable frase que está arriba y 
        //descomentar la variable frase que está abajo
        String frase = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sed.";
        Map<Character, Simbolo> map = getMapaCaracteresFrecuencias(frase);
        Simbolo[] simbolos = getSimbolosOrdenados(map);

//        System.out.println("\nSimbolods ordenados: ");
//        for (int i = 0; i < simbolos.length; i++) {
//            System.out.println(i + ": " + simbolos[i].getCaracter() + ": " + simbolos[i].getFrecuencia());
//        }
        //Creamos una lista de nodos, un nodo por cada simbolo
        List<Nodo> nodos = getListaNodos(simbolos);

        //Procesamos la lista de nodos para transformarla en un arbol binario
        Nodo arbol = getArbol(nodos);

        //recorremos el arbol para setear la codificación en base a la ruta
        recorrerArbol(arbol, map);
        Map<String, Character> diccionario = getDiccionario(map);
        System.out.println("\nCaracter\tFrecuencia\tCódigo");
        map.forEach((k, v) -> {
            System.out.println((k.equals(' ') ? "[Espacio]\t" : k + "\t\t") + v.getFrecuencia() + "\t\t" + v.getCodificacion());
        });

        String codigoHuffman = "";
        for (int i = 0; i < frase.length(); i++) {
            codigoHuffman += map.get(frase.charAt(i)).getCodificacion();
        }

        System.out.println("frase: " + frase);
        System.out.println("Código Huffman: " + codigoHuffman);
        System.out.println("Decodificado: " + decodificar(codigoHuffman, diccionario));
    }
}
