package lwp.arbol_huffman;

/**
 *
 * @author billy.johnson
 */
public class Nodo {
     private Character caracter;
     private Integer value;
     private Nodo izq;
     private Nodo der;
     private String camino;
     
     public Nodo(){
         value = null;
         izq = null;
         der = null;
         camino = "";
     }
     
     public Nodo(Character caracter, Integer value){
         this.caracter = caracter;
         this.value = value;
         izq = null;
         der = null;
         camino = "";
     }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return the izq
     */
    public Nodo getIzq() {
        return izq;
    }

    /**
     * @param izq the izq to set
     */
    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    /**
     * @return the der
     */
    public Nodo getDer() {
        return der;
    }

    /**
     * @param der the der to set
     */
    public void setDer(Nodo der) {
        this.der = der;
    }

    /**
     * @return the caracter
     */
    public Character getCaracter() {
        return caracter;
    }

    /**
     * @param caracter the caracter to set
     */
    public void setCaracter(Character caracter) {
        this.caracter = caracter;
    }

    /**
     * @return the camino
     */
    public String getCamino() {
        return camino;
    }

    /**
     * @param camino the camino to set
     */
    public void setCamino(String camino) {
        this.camino = camino;
    }
}
