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

    // Método para obtener todos los usuarios (empleados)
    public List<Usuario> obtenerTodosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.*, i.Nombre AS nombre_institucion, c.Email "
                   + "FROM Usuario u "
                   + "LEFT JOIN Institucion i ON u.IdInstitucion = i.IdInstitucion "
                   + "INNER JOIN Credencial c ON u.IdCredencial = c.IdCredencial";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = mapearUsuario(rs);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    // Método para mapear ResultSet a objeto Usuario
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        
        // Datos básicos
        usuario.setIdUsuario(rs.getInt("IdUsuario"));
        usuario.setNombre(rs.getString("Nombre"));
        usuario.setRol(Usuario.RolUsuario.valueOf(rs.getString("Rol")));

        // Institución
        Institucion institucion = new Institucion();
        institucion.setNombre(rs.getString("nombre_institucion"));
        usuario.setInstitucion(institucion);

        // Credencial
        Credencial credencial = new Credencial();
        credencial.setEmail(rs.getString("Email"));
        usuario.setCredencial(credencial);

        return usuario;
    }

    // Método de autenticación actualizado
    public boolean autenticar(String email, String password) {
        String sql = "SELECT * FROM Credencial WHERE Email = ? AND PasswordHash = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, hashPassword(password));
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para hashear contraseñas
    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String saltedPassword = PEPPER + password;
        byte[] hash = md.digest(saltedPassword.getBytes());
        return bytesToHex(hash);
    }

    // Conversor de bytes a hexadecimal
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Método para obtener conexión (redundante pero mantenido para compatibilidad)
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}