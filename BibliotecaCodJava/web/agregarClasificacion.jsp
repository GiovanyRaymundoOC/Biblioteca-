<!DOCTYPE html>
<html>
<head>
    <title>Agregar Clasificaci�n</title>
</head>
<body>
    <h1>Agregar Nueva Clasificaci�n</h1>
    <form action="AgregarClasificacionServlet" method="post">
        <label for="codigoDewey">C�digo Dewey:</label>
        <input type="text" name="codigoDewey" required><br>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" required><br>
        <label for="nivel">Nivel:</label>
        <input type="number" name="nivel" required><br>
        <button type="submit">Agregar</button>
    </form>
</body>
</html>
