package Dao;
import BBDD.ConexionBBDD;
import Model.ModelAeropuerto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoAeropuerto {
    private static Connection connection;

    public static ModelAeropuerto getAeropuerto(int id) throws SQLException {
        connection = ConexionBBDD.getConnection();
        String query = "SELECT * FROM aeropuertos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ModelAeropuerto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("anio_inauguracion"),
                        rs.getInt("capacidad"),
                        rs.getInt("id_direccion"),
                        rs.getBytes("imagen")
                );
            } else {
                return null;
            }
        }
    }

    public static boolean insertarAeropuerto(ModelAeropuerto aeropuerto) throws SQLException {
        connection = ConexionBBDD.getConnection();
        String query = "INSERT INTO aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion, imagen) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, aeropuerto.getNombre());
            stmt.setInt(2, aeropuerto.getAnioInauguracion());
            stmt.setInt(3, aeropuerto.getCapacidad());
            stmt.setInt(4, aeropuerto.getIdDireccion());
            stmt.setBytes(5, aeropuerto.getImagen());
            return stmt.executeUpdate() > 0;
        }
    }
    public static boolean eliminarAeropuerto(ModelAeropuerto aeropuerto) throws SQLException {
        connection = ConexionBBDD.getConnection();
        String query = "DELETE FROM aeropuertos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, aeropuerto.getId());
            return stmt.executeUpdate() > 0;
        }
    }
    public static boolean modificarAeropuerto(ModelAeropuerto aeropuertoActual, ModelAeropuerto nuevoAeropuerto) throws SQLException {
        connection = ConexionBBDD.getConnection();
        String query = "UPDATE aeropuertos SET nombre = ?, anio_inauguracion = ?, capacidad = ?, id_direccion = ?, imagen = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevoAeropuerto.getNombre());
            stmt.setInt(2, nuevoAeropuerto.getAnioInauguracion());
            stmt.setInt(3, nuevoAeropuerto.getCapacidad());
            stmt.setInt(4, nuevoAeropuerto.getIdDireccion());
            stmt.setBytes(5, nuevoAeropuerto.getImagen());
            stmt.setInt(6, aeropuertoActual.getId());
            return stmt.executeUpdate() > 0;
        }
    }
    public static List<ModelAeropuerto> obtenerAeropuertosPublicos() throws SQLException {
        connection = ConexionBBDD.getConnection();
        List<ModelAeropuerto> aeropuertosPublicos = new ArrayList<>();
        String query = "SELECT * FROM aeropuertos WHERE id IN (SELECT id_aeropuerto FROM aeropuertos_publicos)";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ModelAeropuerto aeropuerto = new ModelAeropuerto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("anio_inauguracion"),
                        rs.getInt("capacidad"),
                        rs.getInt("id_direccion"),
                        rs.getBytes("imagen")
                );
                aeropuertosPublicos.add(aeropuerto);
            }
        }
        return aeropuertosPublicos;
    }

}
