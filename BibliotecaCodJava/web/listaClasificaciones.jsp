<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Clasificaciones</title>
</head>
<body>
    <h1>Clasificaciones Dewey</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Código Dewey</th>
                <th>Nombre</th>
                <th>Nivel</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="clasificacion" items="${clasificaciones}">
                <tr>
                    <td>${clasificacion.codigoDewey}</td>
                    <td>${clasificacion.nombre}</td>
                    <td>${clasificacion.nivel}</td>
                    <td>
                        <a href="editarClasificacion.jsp?id=${clasificacion.idClasificacion}">Editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="agregarClasificacion.jsp">Agregar Nueva Clasificación</a>
</body>
</html>
