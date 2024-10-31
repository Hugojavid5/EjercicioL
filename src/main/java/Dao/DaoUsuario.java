package Dao;
import BBDD.ConexionBBDD;
import Model.ModelUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DaoUsuario {


    public static ModelUsuario getUsuario(String nombreUsuario) {
        Connection connection = null;
        ModelUsuario usuario = null;
        String sql = "SELECT * FROM usuarios  WHERE usuario = ?";
        try {
            ConexionBBDD cBD = new ConexionBBDD();
            connection = ConexionBBDD.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String usuarioNombre = rs.getString("usuario");
                String password = rs.getString("password");
                usuario = new ModelUsuario(usuarioNombre, password);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario: " + e.getMessage());
        } finally {
            // Cerrar la conexión después de usarla
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
            return usuario;
        }
    }
}
