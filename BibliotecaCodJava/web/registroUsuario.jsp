<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h1>Registro de Nuevo Usuario</h1>
    <form action="RegistroUsuarioServlet" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" required><br>
        <label for="email">Correo Electrónico:</label>
        <input type="email" name="email" required><br>
        <label for="password">Contraseña:</label>
        <input type="password" name="password" required><br>
        <input type="hidden" name="rol" value="USUARIO"> <!-- Rol fijo como 'USUARIO' -->
        <button type="submit">Registrarse</button>
    </form>
    <p>¿Ya tienes una cuenta? <a href="login.jsp">Inicia sesión aquí</a></p>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>
