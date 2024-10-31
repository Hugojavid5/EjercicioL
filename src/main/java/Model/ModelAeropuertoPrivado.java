package Model;

public class ModelAeropuertoPrivado {
    private int idAeropuerto;
    private int numeroSocios;
    public ModelAeropuertoPrivado(int idAeropuerto, int numeroSocios)
    {
        this.idAeropuerto = idAeropuerto;
        this.numeroSocios = numeroSocios;
    }

    public int getIdAeropuerto()
    {
        return idAeropuerto;
    }

    public void setIdAeropuerto(int idAeropuerto)
    {
        this.idAeropuerto = idAeropuerto;
    }

    public int getNumeroSocios()
    {
        return numeroSocios;
    }

    public void setNumeroSocios(int numeroSocios)
    {
        this.numeroSocios = numeroSocios;
    }

}
