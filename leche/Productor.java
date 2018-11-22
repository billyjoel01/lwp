import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author billy.johnson
 */
public class Productor {

    private int id;
    private String nombre;
    private int cantidadEntregas;
    private final Map<Integer, Entrega> entregas;
    private int totalBotellasEntregadas;

    public Productor() {
        entregas = new HashMap();
        cantidadEntregas = 0;
        totalBotellasEntregadas = 0;
    }

    /**
     * Metodo utilizado para registrar una nueva entrega al productor
     *
     * @param entrega
     */
    public void addEntrega(Entrega entrega) {
        cantidadEntregas++;
        totalBotellasEntregadas += entrega.getBotellas();
        entregas.put(entrega.getId(), entrega);
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
     * @return the cantidadEntregas
     */
    public int getCantidadEntregas() {
        return cantidadEntregas;
    }

    /**
     * @param cantidadEntregas the cantidadEntregas to set
     */
    public void setCantidadEntregas(int cantidadEntregas) {
        this.cantidadEntregas = cantidadEntregas;
    }

    /**
     * @return the entregas
     */
    public Map<Integer, Entrega> getEntregas() {
        return entregas;
    }

    /**
     * @return the totalBotellasEntregadas
     */
    public int getTotalBotellasEntregadas() {
        return totalBotellasEntregadas;
    }
}
