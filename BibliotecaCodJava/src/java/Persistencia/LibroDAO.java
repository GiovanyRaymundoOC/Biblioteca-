package Persistencia;

import modelo.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASS = "password";

    public void insertarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO Libro (ISBN, Titulo, Autor, AnioPublicacion, Editorial, "
                   + "UnidadesDisponibles, Descripcion, Tipo, portadaUrl) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, libro.getIsbn());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setInt(4, libro.getAnioPublicacion());
            stmt.setString(5, libro.getEditorial());
            stmt.setInt(6, libro.getUnidadesDisponibles());
            stmt.setString(7, libro.getDescripcion());
            stmt.setString(8, libro.getTipo().name());
            stmt.setString(9, libro.getPortadaUrl());
            
            stmt.executeUpdate();
        }
    }

    public List<Libro> obtenerTodosLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libro";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIsbn(rs.getString("ISBN"));
                libro.setTitulo(rs.getString("Titulo"));
                libro.setAutor(rs.getString("Autor"));
                libro.setPortadaUrl(rs.getString("portadaUrl"));
                libros.add(libro);
            }
        }
        return libros;
    }
}