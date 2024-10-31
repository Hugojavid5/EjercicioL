package Model;
import java.math.BigDecimal;
public class ModelAeropuertoPublico {
    private int idAeropuerto;
    private BigDecimal financiacion;
    private int numTrabajadores;
    public ModelAeropuertoPublico(int idAeropuerto, BigDecimal financiacion, int numTrabajadores)
    {
        this.idAeropuerto = idAeropuerto;
        this.financiacion = financiacion;
        this.numTrabajadores = numTrabajadores;
    }

    public int getIdAeropuerto()
    {
        return idAeropuerto;
    }

    public void setIdAeropuerto(int idAeropuerto)
    {
        this.idAeropuerto = idAeropuerto;
    }

    public BigDecimal getFinanciacion()
    {
        return financiacion;
    }

    public void setFinanciacion(BigDecimal financiacion)
    {
        this.financiacion = financiacion;
    }

    public int getNumTrabajadores()
    {
        return numTrabajadores;
    }

    public void setNumTrabajadores(int numTrabajadores)
    {
        this.numTrabajadores = numTrabajadores;
    }

}
