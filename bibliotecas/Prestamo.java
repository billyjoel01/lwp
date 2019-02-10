package lwp.bibliotecas;

/**
 *
 * @author billy.johnson
 */
public class Prestamo {
    private Integer id;
    private Libro libro;
    private Lector lector;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @param libro the libro to set
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * @return the lector
     */
    public Lector getLector() {
        return lector;
    }

    /**
     * @param lector the lector to set
     */
    public void setLector(Lector lector) {
        this.lector = lector;
    }
}
