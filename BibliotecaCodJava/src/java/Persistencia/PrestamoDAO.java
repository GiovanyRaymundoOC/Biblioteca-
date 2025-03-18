package Persistencia;

import modelo.Prestamo;
import modelo.Libro;
import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASS = "password";

    public List<Prestamo> obtenerPrestamosPorFecha(java.sql.Date fecha) throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT p.*, l.Titulo, u.Nombre "
                   + "FROM Prestamo p "
                   + "JOIN Libro l ON p.ISBN = l.ISBN "
                   + "JOIN Credencial c ON p.IdCredencial = c.IdCredencial "
                   + "JOIN Usuario u ON u.IdCredencial = c.IdCredencial " // Relación corregida
                   + "WHERE DATE(p.FechaPrestamo) = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            
            // Con el tipo java.sql.Date, es compatible con el método setDate de PreparedStatement.
            ps.setDate(1, fecha);  // Aquí se asigna la fecha de la consulta

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setIdPrestamo(rs.getInt("IdPrestamo"));
                    
                    // Mapear Libro
                    Libro libro = new Libro();
                    libro.setTitulo(rs.getString("Titulo"));
                    prestamo.setLibro(libro);
                    
                    // Mapear Usuario directamente
                    Usuario usuario = new Usuario();
                    usuario.setNombre(rs.getString("Nombre"));
                    prestamo.setUsuario(usuario);
                    
                    // Mapear las fechas (si están en el formato correcto en la base de datos)
                    prestamo.setFechaPrestamo(rs.getTimestamp("FechaPrestamo"));
                    prestamo.setFechaLimite(rs.getDate("FechaLimite"));
                    prestamo.setFechaEntrega(rs.getDate("FechaEntrega"));
                    
                    // El estado del préstamo, se asume que el valor es un String y se convierte a Enum
                    prestamo.setEstado(Prestamo.EstadoPrestamo.valueOf(rs.getString("Estado")));
                    
                    prestamos.add(prestamo);
                }
            }
        }
        return prestamos; // Retorna la lista de préstamos
    }
}
