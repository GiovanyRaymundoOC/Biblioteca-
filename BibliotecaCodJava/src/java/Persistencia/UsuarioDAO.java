package Persistencia;

import java.security.MessageDigest;
import java.sql.*;

public class UsuarioDAO {
    private static final String PEPPER = "BIBLIO_PEPPER_123";

    // Método para convertir bytes a hexadecimal (requerido para el hash)
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/biblioteca?useSSL=false", 
            "root", 
            "password"
        );
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String saltedPassword = PEPPER + password;
        byte[] hash = md.digest(saltedPassword.getBytes());
        return bytesToHex(hash);
    }

    public boolean autenticar(String email, String password) {
        // Modificado para usar la tabla Credencial
        String sql = "SELECT * FROM Credencial WHERE Email = ? AND PasswordHash = ?";
        
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, hashPassword(password));
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // True si encuentra coincidencia
            }
        } catch (Exception e) {
            e.printStackTrace(); // Para debug
            return false;
        }
    }
}