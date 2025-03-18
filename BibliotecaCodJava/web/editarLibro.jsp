<!DOCTYPE html>
<html>
<head>
    <title>Editar Libro</title>
</head>
<body>
    <h1>Editar Libro</h1>
    <form action="EditarLibroServlet" method="post">
        <label for="isbn">ISBN:</label>
        <input type="text" name="isbn" value="${libro.isbn}" readonly><br>
        <label for="titulo">Título:</label>
        <input type="text" name="titulo" value="${libro.titulo}" required><br>
        <label for="autor">Autor:</label>
        <input type="text" name="autor" value="${libro.autor}" required><br>
        <label for="anioPublicacion">Año de Publicación:</label>
        <input type="number" name="anioPublicacion" value="${libro.anioPublicacion}" required><br>
        <label for="editorial">Editorial:</label>
        <input type="text" name="editorial" value="${libro.editorial}"><br>
        <label for="unidades">Unidades Disponibles:</label>
        <input type="number" name="unidades" value="${libro.unidadesDisponibles}" required><br>
        <button type="submit">Actualizar</button>
    </form>
</body>
</html>
