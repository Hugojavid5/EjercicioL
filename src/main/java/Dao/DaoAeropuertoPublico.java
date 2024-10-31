package Dao;
import Model.ModelAeropuertoPublico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DaoAeropuertoPublico {
    private Connection connection;
    public DaoAeropuertoPublico(Connection connection) {
        this.connection = connection;
    }
    public ModelAeropuertoPublico getAeropuertoPublico(int id) throws SQLException {
        String query = "SELECT * FROM aeropuertos_publicos WHERE id_aeropuerto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ModelAeropuertoPublico(
                        rs.getInt("id_aeropuerto"),
                        rs.getBigDecimal("financiacion"),
                        rs.getInt("num_trabajadores")
                );
            } else {
                return null;
            }
        }
    }
    public boolean insertarAeropuertoPublico(ModelAeropuertoPublico aeropuertoPublico) throws SQLException {
        String query = "INSERT INTO aeropuertos_publicos (id_aeropuerto, financiacion, num_trabajadores) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, aeropuertoPublico.getIdAeropuerto());
            stmt.setBigDecimal(2, aeropuertoPublico.getFinanciacion());
            stmt.setInt(3, aeropuertoPublico.getNumTrabajadores());
            return stmt.executeUpdate() > 0; // Retorna true si la inserción fue exitosa
        }
    }
    public boolean eliminarAeropuertoPublico(ModelAeropuertoPublico aeropuertoPublico) throws SQLException {
        String query = "DELETE FROM aeropuertos_publicos WHERE id_aeropuerto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, aeropuertoPublico.getIdAeropuerto());
            return stmt.executeUpdate() > 0; // Retorna true si la eliminación fue exitosa
        }
    }
    public boolean modificarAeropuertoPublico(ModelAeropuertoPublico aeropuertoActual, ModelAeropuertoPublico nuevoAeropuertoPublico) throws SQLException {
        String query = "UPDATE aeropuertos_publicos SET financiacion = ?, num_trabajadores = ? WHERE id_aeropuerto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBigDecimal(1, nuevoAeropuertoPublico.getFinanciacion());
            stmt.setInt(2, nuevoAeropuertoPublico.getNumTrabajadores());
            stmt.setInt(3, aeropuertoActual.getIdAeropuerto());
            return stmt.executeUpdate() > 0; // Retorna true si la modificación fue exitosa
        }
    }
    public List<ModelAeropuertoPublico> CargarListaPublicos() throws SQLException {
        List<ModelAeropuertoPublico> listaAeropuertosPublicos = new ArrayList<>();
        String query = "SELECT * FROM aeropuertos_publicos";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelAeropuertoPublico aeropuertoPublico = new ModelAeropuertoPublico(
                        rs.getInt("id_aeropuerto"),
                        rs.getBigDecimal("financiacion"),
                        rs.getInt("num_trabajadores")
                );
                listaAeropuertosPublicos.add(aeropuertoPublico);
            }
        }
        return listaAeropuertosPublicos;
    }
}
