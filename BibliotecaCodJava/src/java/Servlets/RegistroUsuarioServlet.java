package Servlets;

import Persistencia.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import modelo.Credencial;

import java.io.IOException;

@WebServlet("/RegistroUsuarioServlet")
public class RegistroUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Crear objetos de Usuario y Credencial
            Usuario usuario = new Usuario();
            Credencial credencial = new Credencial();

            // Obtener datos del formulario
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Validar datos del formulario
            if (nombre == null || nombre.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
                return;
            }

            // Configurar los datos del usuario
            usuario.setNombre(nombre);
            usuario.setRol(Usuario.RolUsuario.USUARIO); // Rol fijo como USUARIO

            // Configurar los datos de la credencial
            credencial.setEmail(email);
            credencial.setPasswordHash(password); // El hash se realiza en UsuarioDAO
            usuario.setCredencial(credencial);

            // Registrar el usuario en la base de datos
            usuarioDAO.registrarUsuario(usuario);

            // Redirigir al login tras el registro exitoso
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
        }
    }
}
