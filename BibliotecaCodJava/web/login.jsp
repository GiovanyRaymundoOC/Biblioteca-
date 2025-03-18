<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesi�n</title>
</head>
<body>
    <h1>Iniciar Sesi�n</h1>
    <form action="LoginServlet" method="post">
        <label for="email">Correo Electr�nico:</label>
        <input type="email" name="email" required><br>
        <label for="password">Contrase�a:</label>
        <input type="password" name="password" required><br>
        <button type="submit">Iniciar Sesi�n</button>
    </form>
    <p>�No tienes una cuenta? <a href="registroUsuario.jsp">Reg�strate aqu�</a></p>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>
