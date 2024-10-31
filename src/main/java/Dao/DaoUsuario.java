package Dao;
import Model.ModelUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DaoUsuario {

    private Connection conexion;
    public DaoUsuario(Connection conexion) {
        this.conexion = conexion;
    }

    public ModelUsuario getUsuario(String nombreUsuario) {
        ModelUsuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE usuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String usuarioNombre = rs.getString("usuario");
                String password = rs.getString("password");
                usuario = new ModelUsuario(usuarioNombre, password);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario: " + e.getMessage());
        }
        return usuario;
    }
}
