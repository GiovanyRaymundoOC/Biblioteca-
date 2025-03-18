package Servlets;

import Persistencia.LibroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import modelo.Libro;
import modelo.Libro.TipoLibro;

@WebServlet("/AddLibroServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddLibroServlet extends HttpServlet {
    private LibroDAO libroDAO = new LibroDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Subir portada
            Part filePart = request.getPart("portada");
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + "portadas";
            new File(uploadPath).mkdirs();
            filePart.write(uploadPath + File.separator + fileName);

            // Crear libro
            Libro libro = new Libro();
            libro.setIsbn(request.getParameter("isbn"));
            libro.setTitulo(request.getParameter("titulo"));
            libro.setAutor(request.getParameter("autor"));
            libro.setAnioPublicacion(Integer.parseInt(request.getParameter("anioPublicacion")));
            libro.setEditorial(request.getParameter("editorial"));
            libro.setUnidadesDisponibles(Integer.parseInt(request.getParameter("unidades")));
            libro.setDescripcion(request.getParameter("descripcion"));
            libro.setTipo(TipoLibro.valueOf(request.getParameter("tipo")));
            libro.setPortadaUrl("portadas/" + fileName);

            libroDAO.insertarLibro(libro);
            response.sendRedirect("seleccionLibros.jsp");
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al agregar libro: " + e.getMessage());
            request.getRequestDispatcher("seleccionLibros.jsp").forward(request, response);
        }
    }
}