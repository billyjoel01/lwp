package lwp.arbol_huffman;

/**
 *
 * @author billy.johnson
 */
public class Simbolo {
    private char caracter;
    private int frecuencia;
//    private double frecuenciaRelativa;
    private String codificacion;
    
    public Simbolo(char caracter, int frecuencia){
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    /**
     * @return the caracter
     */
    public char getCaracter() {
        return caracter;
    }

    /**
     * @param caracter the caracter to set
     */
    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    /**
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    public Simbolo addFrecuencia(int add){
        this.frecuencia += add;
        return this;
    }

    /**
     * @return the codificacion
     */
    public String getCodificacion() {
        return codificacion;
    }

    /**
     * @param codificacion the codificacion to set
     */
    public void setCodificacion(String codificacion) {
        this.codificacion = codificacion;
    }
}
