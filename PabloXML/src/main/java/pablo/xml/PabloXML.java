package pablo.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author Billy Joel
 */
public class PabloXML {

    public static final String ARCHIVO_FUNCIONES = "funciones.xml";

    /**
     * Lee el archivo con las funciones y retorna el contenido en un String
     *
     * @return String con el contenido de las funciones
     * @throws IOException
     */
    public static String cargarXML() throws IOException {
        String content = "";
        try ( FileReader fr = new FileReader(ARCHIVO_FUNCIONES)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                content += linea;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return content;
    }

    public static void main(String[] arg) {

        String funciones = null;

        try {
            funciones = cargarXML();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        if (funciones != null && !funciones.isEmpty()) {

            //Convierte el contenido xml a json para manejarlos como un Map
            JSONObject o = XML.toJSONObject(funciones);

            System.out.println("Edad maxima: " + o.getJSONObject("funciones")
                    .getJSONObject("list")
                    .getJSONObject("simm.reglas.EdadMaxima")
                    .getInt("edad"));

            System.out.println("Edad minima: " + o.getJSONObject("funciones")
                    .getJSONObject("list")
                    .getJSONObject("simm.reglas.EdadMinima")
                    .getInt("edad"));
        }
    }
}
