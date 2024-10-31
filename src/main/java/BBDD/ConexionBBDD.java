package BBDD;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {
    private final Connection connection;
    public ConexionBBDD() throws SQLException {
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "mypass");
        connection = DriverManager.getConnection(
                "jdbc:mariadb://127.0.0.1:33066/aeropuertos?serverTimezone=Europe/Madrid",
                connConfig
        );
        connection.setAutoCommit(true);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
    }
    public Connection getConnection() {
        return connection;
    }
    public Connection CloseConexion() throws SQLException {
        connection.close(); // Cierra la conexión
        return connection;
    }
    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("configuration.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
