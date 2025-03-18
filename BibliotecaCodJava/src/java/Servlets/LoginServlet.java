package Servlets;

import Persistencia.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO(); // Instancia del DAO para manejar la autenticación

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros enviados desde el formulario de login
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Llamar al método autenticar del UsuarioDAO
            boolean autenticado = usuarioDAO.autenticar(email, password);

            if (autenticado) {
                // Si las credenciales son válidas, iniciar sesión y redirigir al dashboard
                request.getSession().setAttribute("email", email); // Guardar el email en la sesión
                response.sendRedirect("dashboard.jsp"); // Redirigir al panel principal
            } else {
                // Si las credenciales no son válidas, regresar al login con un mensaje de error
                request.setAttribute("error", "Credenciales incorrectas. Intenta de nuevo.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // En caso de error, mostrar mensaje de error
            request.setAttribute("error", "Ocurrió un error al autenticar al usuario: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
