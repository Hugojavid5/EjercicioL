package Model;

public class ModelAvion {
    private int id;
    private String modelo;
    private int numeroAsientos;
    private int velocidadMaxima;
    private boolean activado;
    private int idAeropuerto;
    public ModelAvion(int id, String modelo, int numeroAsientos, int velocidadMaxima, boolean activado, int idAeropuerto)
    {
        this.id = id;
        this.modelo = modelo;
        this.numeroAsientos = numeroAsientos;
        this.velocidadMaxima = velocidadMaxima;
        this.activado = activado;
        this.idAeropuerto = idAeropuerto;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public int getNumeroAsientos()
    {
        return numeroAsientos;
    }

    public void setNumeroAsientos(int numeroAsientos)
    {
        this.numeroAsientos = numeroAsientos;
    }

    public int getVelocidadMaxima()
    {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima)
    {
        this.velocidadMaxima = velocidadMaxima;
    }

    public boolean isActivado()
    {
        return activado;
    }

    public void setActivado(boolean activado)
    {
        this.activado = activado;
    }

    public int getIdAeropuerto()
    {
        return idAeropuerto;
    }

    public void setIdAeropuerto(int idAeropuerto)
    {
        this.idAeropuerto = idAeropuerto;
    }
}
