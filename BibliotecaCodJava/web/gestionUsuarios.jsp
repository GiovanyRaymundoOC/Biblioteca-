<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Usuarios</title>
</head>
<body>
    <h1>Gestión de Usuarios</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Rol</th>
                <th>Email</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${empleados}">
                <tr>
                    <td>${usuario.idUsuario}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.rol}</td>
                    <td>${usuario.credencial.email}</td>
                    <td>
                        <a href="editarUsuario.jsp?id=${usuario.idUsuario}">Editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="registroUsuario.jsp">Registrar Nuevo Usuario</a>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>
