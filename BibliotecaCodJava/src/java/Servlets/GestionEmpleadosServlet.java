package Servlets;

import Persistencia.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import modelo.Usuario;

@WebServlet("/GestionEmpleadosServlet")
public class GestionEmpleadosServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            List<Usuario> empleados = usuarioDAO.obtenerTodosUsuarios();
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("gestionEmpleados.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar empleados: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}