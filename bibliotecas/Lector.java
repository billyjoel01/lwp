package lwp.bibliotecas;

/**
 *
 * @author billy.johnson
 */
public class Lector {

    private String nombre;
    private String dni;
    private Integer numeroLibrosPrestados;

    /**
     * Genera un lector aleatorio
     *
     * @param dni
     * @return
     */
    public static Lector generarLectorAleatorio(String dni) {
        Lector l = new Lector();
        l.setNombre(MyUtils.getInstance().getNombreRandom());
        l.setDni(dni);
        l.setNumeroLibrosPrestados(0);
        return l;
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
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the numeroLibrosPrestados
     */
    public Integer getNumeroLibrosPrestados() {
        return numeroLibrosPrestados;
    }

    /**
     * @param numeroLibrosPrestados the numeroLibrosPrestados to set
     */
    public void setNumeroLibrosPrestados(Integer numeroLibrosPrestados) {
        this.numeroLibrosPrestados = numeroLibrosPrestados;
    }

}
