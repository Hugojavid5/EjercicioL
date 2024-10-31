package Model;
import java.sql.Blob;
import java.util.Objects;

public class ModelAeropuertoPublico extends ModelAeropuerto{
    float financiacion;
    int numTrabajadores;
    public ModelAeropuertoPublico(String nombre, int anioInauguracion, int capacidad, ModelDireccion direccion, Blob imagen, float financiacion, int numTrabajadores) {
        super(nombre, anioInauguracion, capacidad, direccion, imagen);
        this.financiacion = financiacion;
        this.numTrabajadores = numTrabajadores;
    }
    public float getFinanciacion() { return financiacion; }
    public int getNumTrabajadores() { return numTrabajadores; }

    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(financiacion, numTrabajadores);
        return result;
    }
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelAeropuertoPublico other = (ModelAeropuertoPublico) obj;
        return Float.floatToIntBits(financiacion) == Float.floatToIntBits(other.financiacion)
                && numTrabajadores == other.numTrabajadores&&anioInauguracion == other.anioInauguracion && capacidad == other.capacidad
                && Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(imagen, other.imagen)
                && Objects.equals(nombre, other.nombre);
    }

}
