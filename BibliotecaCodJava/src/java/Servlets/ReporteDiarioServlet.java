package Servlets;

import Persistencia.PrestamoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import modelo.Prestamo;

@WebServlet("/ReporteDiarioServlet")
public class ReporteDiarioServlet extends HttpServlet {
    
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Verificar si se pasa una fecha específica desde la solicitud
            String fechaParam = request.getParameter("fecha");
            java.sql.Date fechaSQL;
            
            if (fechaParam != null) {
                // Si se proporciona una fecha, parsearla
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaUtil = sdf.parse(fechaParam); 
                fechaSQL = new java.sql.Date(fechaUtil.getTime());
            } else {
                // Si no se proporciona, usar la fecha actual
                java.util.Date fechaUtil = new java.util.Date();
                fechaSQL = new Date(fechaUtil.getTime());
            }
            
            // Obtener los préstamos de la fecha proporcionada
            List<Prestamo> prestamosHoy = prestamoDAO.obtenerPrestamosPorFecha(fechaSQL);
            
            // Pasar los datos al JSP
            request.setAttribute("prestamos", prestamosHoy);
            request.getRequestDispatcher("reporteDiario.jsp").forward(request, response);
            
        } catch (Exception e) {
            // En caso de error, redirigir a una página de error
            request.setAttribute("error", "Error al generar reporte: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
