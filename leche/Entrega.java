import java.util.Calendar;

/**
 *
 * @author billy.johnson
 */
public class Entrega {

    private int id;
    private int dia;
    private int mes;
    private int a�o;
    private int botellas;
    private String cliente;

    public Entrega() {
        Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH) + 1;
        a�o = c.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return id + ": " + (dia < 10 ? "0" + dia : dia) + "/" + (mes < 10 ? "0" + mes : mes) + "/" + a�o + " | cantidad botellas: " + botellas;
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
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the a�o
     */
    public int getA�o() {
        return a�o;
    }

    /**
     * @param a�o the a�o to set
     */
    public void setA�o(int a�o) {
        this.a�o = a�o;
    }

    /**
     * @return the botellas
     */
    public int getBotellas() {
        return botellas;
    }

    /**
     * @param botellas the botellas to set
     */
    public void setBotellas(int botellas) {
        this.botellas = botellas;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
