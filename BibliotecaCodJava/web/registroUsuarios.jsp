<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h1>Registrar Nuevo Usuario</h1>
    <form action="RegistroUsuarioServlet" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" required><br>
        <label for="email">Email:</label>
        <input type="email" name="email" required><br>
        <label for="password">Contraseña:</label>
        <input type="password" name="password" required><br>
        <label for="rol">Rol:</label>
        <select name="rol">
            <option value="ADMIN">Administrador</option>
            <option value="BIBLIOTECARIO">Bibliotecario</option>
            <option value="USUARIO">Usuario</option>
        </select><br>
        <button type="submit">Registrar</button>
    </form>
</body>
</html>
