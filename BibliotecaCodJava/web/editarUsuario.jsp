<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
</head>
<body>
    <h1>Editar Usuario</h1>
    <form action="EditarUsuarioServlet" method="post">
        <label for="idUsuario">ID Usuario:</label>
        <input type="text" name="idUsuario" value="${usuario.idUsuario}" readonly><br>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" value="${usuario.nombre}" required><br>
        <label for="email">Email:</label>
        <input type="email" name="email" value="${usuario.credencial.email}" required><br>
        <label for="rol">Rol:</label>
        <select name="rol">
            <option value="ADMIN" ${usuario.rol == 'ADMIN' ? 'selected' : ''}>Administrador</option>
            <option value="BIBLIOTECARIO" ${usuario.rol == 'BIBLIOTECARIO' ? 'selected' : ''}>Bibliotecario</option>
            <option value="USUARIO" ${usuario.rol == 'USUARIO' ? 'selected' : ''}>Usuario</option>
        </select><br>
        <button type="submit">Actualizar</button>
    </form>
</body>
</html>
