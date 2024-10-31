package Model;
import java.sql.Blob;
import java.util.Objects;
public class ModelAeropuerto {
    int id;
    String nombre;
    int anioInauguracion;
    int capacidad;
    ModelDireccion direccion;
    Blob imagen;
    public ModelAeropuerto(String nombre, int anioInauguracion, int capacidad, ModelDireccion direccion, Blob imagen) {
        super();
        this.nombre = nombre;
        this.anioInauguracion = anioInauguracion;
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.imagen = imagen;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public int getAnioInauguracion() { return anioInauguracion; }
    public int getCapacidad() { return capacidad; }
    public ModelDireccion getDireccion() { return direccion; }
    public Blob getImagen() { return imagen; }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModelAeropuerto other = (ModelAeropuerto) obj;
        return anioInauguracion == other.anioInauguracion && capacidad == other.capacidad
                && Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(imagen, other.imagen)
                && Objects.equals(nombre, other.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(anioInauguracion, capacidad, direccion, id, imagen, nombre);
    }
    @Override
    public String toString() {
        return this.nombre;
    }
}
