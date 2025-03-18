package Persistencia;

import modelo.Clasificacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasificacionDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASS = "password";

    // Obtener todas las clasificaciones
    public List<Clasificacion> obtenerTodas() throws SQLException {
        List<Clasificacion> clasificaciones = new ArrayList<>();
        String sql = "SELECT * FROM Clasificacion ORDER BY CodigoDewey";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Clasificacion clasificacion = new Clasificacion();
                clasificacion.setIdClasificacion(rs.getInt("IdClasificacion"));
                clasificacion.setCodigoDewey(rs.getString("CodigoDewey"));
                clasificacion.setNombre(rs.getString("Nombre"));
                clasificacion.setNivel(rs.getInt("Nivel"));
                clasificaciones.add(clasificacion);
            }
        }
        return clasificaciones;
    }

    // Insertar una nueva clasificación
    public void insertarClasificacion(Clasificacion clasificacion) throws SQLException {
        String sql = "INSERT INTO Clasificacion (CodigoDewey, Nombre, Nivel) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clasificacion.getCodigoDewey());
            ps.setString(2, clasificacion.getNombre());
            ps.setInt(3, clasificacion.getNivel());
            ps.executeUpdate();
        }
    }

    // Editar una clasificación existente
    public void editarClasificacion(Clasificacion clasificacion) throws SQLException {
        String sql = "UPDATE Clasificacion SET CodigoDewey = ?, Nombre = ?, Nivel = ? WHERE IdClasificacion = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clasificacion.getCodigoDewey());
            ps.setString(2, clasificacion.getNombre());
            ps.setInt(3, clasificacion.getNivel());
            ps.setInt(4, clasificacion.getIdClasificacion());
            ps.executeUpdate();
        }
    }
}
