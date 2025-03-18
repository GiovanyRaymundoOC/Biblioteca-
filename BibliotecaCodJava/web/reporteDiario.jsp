<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte Diario</title>
</head>
<body>
    <h1>Préstamos del Día</h1>
    <form method="get" action="ReporteDiarioServlet">
        <label for="fecha">Seleccionar Fecha:</label>
        <input type="date" name="fecha" value="${fecha}" required>
        <button type="submit">Generar Reporte</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>ID Préstamo</th>
                <th>Título</th>
                <th>Usuario</th>
                <th>Fecha Préstamo</th>
                <th>Fecha Límite</th>
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="prestamo" items="${prestamos}">
                <tr>
                    <td>${prestamo.idPrestamo}</td>
                    <td>${prestamo.libro.titulo}</td>
                    <td>${prestamo.usuario.nombre}</td>
                    <td>${prestamo.fechaPrestamo}</td>
                    <td>${prestamo.fechaLimite}</td>
                    <td>${prestamo.estado}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
