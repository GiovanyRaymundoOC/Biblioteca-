package Servlets;

import Persistencia.UsuarioDAO;
import modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String nombre = request.getParameter("nombre");
            String rol = request.getParameter("rol");

            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario); // Implementar este método
            if (usuario != null) {
                usuario.setNombre(nombre);
                usuario.setRol(Usuario.RolUsuario.valueOf(rol));
                usuarioDAO.actualizarUsuario(usuario); // Implementar este método
            }

            response.sendRedirect("gestionUsuarios.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Error al editar usuario: " + e.getMessage());
            request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
        }
    }
}
