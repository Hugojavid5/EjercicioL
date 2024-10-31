package Dao;
import Model.ModelAeropuertoPrivado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoAeropuertoPrivado {
    private Connection connection;
    public DaoAeropuertoPrivado(Connection connection) {
        this.connection = connection;
    }
    public ModelAeropuertoPrivado getAeropuertoPrivado(int id) throws SQLException {
        String query = "SELECT * FROM aeropuertos_privados WHERE id_aeropuerto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ModelAeropuertoPrivado(
                        rs.getInt("id_aeropuerto"),
                        rs.getInt("numero_socios")
                );
            } else {
                return null; // Si no se encuentra el aeropuerto
            }
        }
    }
    public boolean ModelAeropuertoPrivado(ModelAeropuertoPrivado aeropuertoPrivado) throws SQLException {
        String query = "INSERT INTO aeropuertos_privados (id_aeropuerto, numero_socios) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, aeropuertoPrivado.getIdAeropuerto());
            stmt.setInt(2, aeropuertoPrivado.getNumeroSocios());
            return stmt.executeUpdate() > 0;
        }
    }
    public boolean eliminarAeropuertoPrivado(ModelAeropuertoPrivado aeropuertoPrivado) throws SQLException {
        String query = "DELETE FROM aeropuertos_privados WHERE id_aeropuerto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, aeropuertoPrivado.getIdAeropuerto());
            return stmt.executeUpdate() > 0;
        }
    }
    public boolean modificarAeropuertoPrivado(ModelAeropuertoPrivado aeropuertoActual, ModelAeropuertoPrivado nuevoAeropuertoPrivado) throws SQLException {
        String query = "UPDATE aeropuertos_privados SET numero_socios = ? WHERE id_aeropuerto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nuevoAeropuertoPrivado.getNumeroSocios());
            stmt.setInt(2, aeropuertoActual.getIdAeropuerto());
            return stmt.executeUpdate() > 0;
        }
    }
    public List<ModelAeropuertoPrivado> CargarListaPrivados() throws SQLException {
        List<ModelAeropuertoPrivado> listaAeropuertosPrivados = new ArrayList<>();
        String query = "SELECT * FROM aeropuertos_privados";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelAeropuertoPrivado aeropuertoPrivado = new ModelAeropuertoPrivado(
                        rs.getInt("id_aeropuerto"),
                        rs.getInt("numero_socios")
                );
                listaAeropuertosPrivados.add(aeropuertoPrivado);
            }
        }
        return listaAeropuertosPrivados;
    }
}
