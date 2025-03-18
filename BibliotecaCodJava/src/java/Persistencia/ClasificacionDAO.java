package Persistencia;

import modelo.Clasificacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASS = "password";

    public List<Clasificacion> obtenerTodas() throws SQLException {
        List<Clasificacion> clasificaciones = new ArrayList<>();
        String sql = "SELECT * FROM Clasificacion ORDER BY CodigoDewey";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Clasificacion c = new Clasificacion();
                c.setIdClasificacion(rs.getInt("IdClasificacion"));
                c.setCodigoDewey(rs.getString("CodigoDewey"));
                c.setNombre(rs.getString("Nombre"));
                c.setNivel(rs.getInt("Nivel"));
                clasificaciones.add(c);
            }
        }
        return clasificaciones;
    }
}