package Persistencia;

import modelo.Usuario;
import modelo.Institucion;
import modelo.Credencial;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String PEPPER = "BIBLIO_PEPPER_123";
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "password";

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, c.Email, i.Nombre AS nombre_institucion FROM Usuario u " +
                     "LEFT JOIN Institucion i ON u.IdInstitucion = i.IdInstitucion " +
                     "INNER JOIN Credencial c ON u.IdCredencial = c.IdCredencial";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }
        return usuarios;
    }

    // Autenticar usuario
    public boolean autenticar(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Credencial WHERE Email = ? AND PasswordHash = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, hashPasswordSafe(password)); // Uso de hashPasswordSafe
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Retorna true si se encuentra el usuario
            }
        }
    }

    // Registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) throws SQLException {
        String sqlCredencial = "INSERT INTO Credencial (Email, PasswordHash) VALUES (?, ?)";
        String sqlUsuario = "INSERT INTO Usuario (Nombre, Rol, IdCredencial) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            conn.setAutoCommit(false);
            try (PreparedStatement psCredencial = conn.prepareStatement(sqlCredencial, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psUsuario = conn.prepareStatement(sqlUsuario)) {
                psCredencial.setString(1, usuario.getCredencial().getEmail());
                psCredencial.setString(2, hashPasswordSafe(usuario.getCredencial().getPasswordHash())); // Uso de hashPasswordSafe
                psCredencial.executeUpdate();

                try (ResultSet rs = psCredencial.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idCredencial = rs.getInt(1);
                        psUsuario.setString(1, usuario.getNombre());
                        psUsuario.setString(2, usuario.getRol().name());
                        psUsuario.setInt(3, idCredencial);
                        psUsuario.executeUpdate();
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    // Obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT u.*, c.Email, i.Nombre AS nombre_institucion FROM Usuario u " +
                     "LEFT JOIN Institucion i ON u.IdInstitucion = i.IdInstitucion " +
                     "INNER JOIN Credencial c ON u.IdCredencial = c.IdCredencial " +
                     "WHERE u.IdUsuario = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                } else {
                    return null; // Usuario no encontrado
                }
            }
        }
    }

    // Actualizar usuario
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET Nombre = ?, Rol = ? WHERE IdUsuario = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getRol().name());
            ps.setInt(3, usuario.getIdUsuario());
            ps.executeUpdate();
        }
    }

    // Mapear ResultSet a objeto Usuario
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("IdUsuario"));
        usuario.setNombre(rs.getString("Nombre"));
        usuario.setRol(Usuario.RolUsuario.valueOf(rs.getString("Rol")));

        Credencial credencial = new Credencial();
        credencial.setEmail(rs.getString("Email"));
        usuario.setCredencial(credencial);

        Institucion institucion = new Institucion();
        institucion.setNombre(rs.getString("nombre_institucion"));
        usuario.setInstitucion(institucion);

        return usuario;
    }

    // Método seguro para hashear contraseñas (con manejo de excepciones)
    private String hashPasswordSafe(String password) {
        try {
            return hashPassword(password);
        } catch (Exception e) {
            throw new RuntimeException("Error al hashear la contraseña", e); // Envuelve la excepción como RuntimeException
        }
    }

    // Hashear contraseñas con SHA-256
    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String saltedPassword = PEPPER + password;
        byte[] hash = md.digest(saltedPassword.getBytes());
        return bytesToHex(hash);
    }

    // Convertir bytes a hexadecimal
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
