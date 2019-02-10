package lwp.bibliotecas;

/**
 *
 * @author billy.johnson
 */
public class Libro {
    
    private String titulo;
    private String [] autores;
    private String editorial;
    private Integer añoPublicacion;
    private Integer paginas;
    private Integer ejemplaresDisponibles;
    private String ISBN;
    
    public static Libro generarLibroAleatorio(String ISBN){
        Libro l = new Libro();
        l.setTitulo(MyUtils.getInstance().getTituloRandom());
        l.setAutores(MyUtils.getInstance().getAutoresRandom());
        l.setEditorial(MyUtils.getInstance().getNombreRandom());
        l.setAñoPublicacion(MyUtils.getInstance().getAñoRandom());
        l.setPaginas(MyUtils.getInstance().getRandomNumber(10, 1000));
        l.setEjemplaresDisponibles(MyUtils.getInstance().getRandomNumber(0, 10));
        l.setISBN(ISBN);
        return l;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autores
     */
    public String[] getAutores() {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the añoPublicacion
     */
    public Integer getAñoPublicacion() {
        return añoPublicacion;
    }

    /**
     * @param añoPublicacion the añoPublicacion to set
     */
    public void setAñoPublicacion(Integer añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    /**
     * @return the paginas
     */
    public Integer getPaginas() {
        return paginas;
    }

    /**
     * @param paginas the paginas to set
     */
    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    /**
     * @return the ejemplaresDisponibles
     */
    public Integer getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    /**
     * @param ejemplaresDisponibles the ejemplaresDisponibles to set
     */
    public void setEjemplaresDisponibles(Integer ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    /**
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    
}
