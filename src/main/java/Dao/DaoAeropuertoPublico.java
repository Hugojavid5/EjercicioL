package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BBDD.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.ModelAeropuertoPublico;
public class DaoAeropuertoPublico {

    private static Connection conection;
    public static ObservableList<ModelAeropuertoPublico> cargarListaAeropuertosPublicos(){
        ObservableList<ModelAeropuertoPublico>lst=FXCollections.observableArrayList();
        try {
            conection=ConexionBBDD.getConnection();
            String select="SELECT id,financiacion,num_trabajadores,nombre,anio_inauguracion,capacidad,id_direccion,imagen FROM aeropuertos_publicos,aeropuertos WHERE id=id_aeropuerto";
            PreparedStatement pstmt = conection.prepareStatement(select);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ModelAeropuertoPublico modelo=new ModelAeropuertoPublico(rs.getString("nombre"),rs.getInt("anio_inauguracion"),rs.getInt("capacidad"), DaoDireccion.crearModeloDireccionPorID(rs.getInt("id_direccion")), rs.getBlob("imagen"),rs.getFloat("financiacion"), rs.getInt("num_trabajadores"));
                modelo.setId(rs.getInt("id"));
                lst.add(modelo);
                //System.out.println("añadido objeto: "+modelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public static void insertarAeropuertoPublico(ModelAeropuertoPublico aeropuertoPublico) throws SQLException {
        String sql = "INSERT INTO aeropuertos_publicos (nombre, calle, ciudad, pais, numero, anio_inauguracion, capacidad, num_trabajadores, financiacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Configurar los parámetros de la consulta con los valores del objeto
            stmt.setString(1, aeropuertoPublico.getNombre());
            stmt.setString(2, aeropuertoPublico.getDireccion().getCalle());
            stmt.setString(3, aeropuertoPublico.getDireccion().getCiudad());
            stmt.setString(4, aeropuertoPublico.getDireccion().getPais());
            stmt.setInt(5, aeropuertoPublico.getDireccion().getNumero());
            stmt.setInt(6, aeropuertoPublico.getAnioInauguracion());
            stmt.setInt(7, aeropuertoPublico.getCapacidad());
            stmt.setInt(8, aeropuertoPublico.getNumTrabajadores());
            stmt.setDouble(9, aeropuertoPublico.getFinanciacion());
            // Ejecutar la inserción
            stmt.executeUpdate();
        }
    }



    public static void aniadir(int idAeropuerto,float financiacion,int numTrabajadoes) {
        conection=ConexionBBDD.getConnection();
        String insert="INSERT INTO aeropuertos_publicos VALUES (?,?,?)";
        try {
            PreparedStatement pstmt;
            pstmt=conection.prepareStatement(insert);
            pstmt.setInt(1,idAeropuerto);
            pstmt.setFloat(2,financiacion);
            pstmt.setInt(3,numTrabajadoes);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
