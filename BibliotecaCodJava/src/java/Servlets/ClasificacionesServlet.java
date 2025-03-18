package Servlets;

import Persistencia.ClasificacionDAO;
import modelo.Clasificacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ClasificacionesServlet")
public class ClasificacionesServlet extends HttpServlet {
    private ClasificacionDAO clasificacionDAO = new ClasificacionDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Clasificacion clasificacion = new Clasificacion();
            clasificacion.setCodigoDewey(request.getParameter("codigoDewey"));
            clasificacion.setNombre(request.getParameter("nombre"));
            clasificacion.setNivel(Integer.parseInt(request.getParameter("nivel")));

            clasificacionDAO.insertarClasificacion(clasificacion); // Implementar si no está
            response.sendRedirect("listaClasificaciones.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Error al gestionar clasificación: " + e.getMessage());
            request.getRequestDispatcher("agregarClasificacion.jsp").forward(request, response);
        }
    }
}
