package lwp.antenas;

/**
 *
 * @author billy.johnson
 */
public class Antena {

    private int c;
    private int f;
    private int puntosCobertura;

    public Antena() {
        c = -1;
        f = -1;
    }

    public Antena(int f, int c) {
        this.f = f;
        this.c = c;
    }

    /**
     * @return the puntosCobertura
     */
    public int getPuntosCobertura() {
        return puntosCobertura;
    }

    /**
     * @param puntosCobertura the puntosCobertura to set
     */
    public void setPuntosCobertura(int puntosCobertura) {
        this.puntosCobertura = puntosCobertura;
    }

    /**
     * @return the c
     */
    public int getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(int c) {
        this.c = c;
    }

    /**
     * @return the f
     */
    public int getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(int f) {
        this.f = f;
    }
}
