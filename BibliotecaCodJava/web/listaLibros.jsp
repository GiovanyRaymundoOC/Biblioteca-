<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Libros</title>
</head>
<body>
    <h1>Libros Disponibles</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ISBN</th>
                <th>Título</th>
                <th>Autor</th>
                <th>Portada</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="libro" items="${libros}">
                <tr>
                    <td>${libro.isbn}</td>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                    <td><img src="${libro.portadaUrl}" alt="Portada" width="50"></td>
                    <td>
                        <a href="detalleLibro.jsp?isbn=${libro.isbn}">Ver Detalle</a> |
                        <a href="editarLibro.jsp?isbn=${libro.isbn}">Editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="agregarLibro.jsp">Agregar Nuevo Libro</a>
</body>
</html>
