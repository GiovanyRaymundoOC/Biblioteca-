<!DOCTYPE html>
<html>
<head>
    <title>Agregar Libro</title>
</head>
<body>
    <h1>Agregar Nuevo Libro</h1>
    <form action="AddLibroServlet" method="post" enctype="multipart/form-data">
        <label for="isbn">ISBN:</label>
        <input type="text" name="isbn" required><br>
        <label for="titulo">Título:</label>
        <input type="text" name="titulo" required><br>
        <label for="autor">Autor:</label>
        <input type="text" name="autor" required><br>
        <label for="anioPublicacion">Año de Publicación:</label>
        <input type="number" name="anioPublicacion" required><br>
        <label for="editorial">Editorial:</label>
        <input type="text" name="editorial"><br>
        <label for="unidades">Unidades Disponibles:</label>
        <input type="number" name="unidades" required><br>
        <label for="portada">Portada:</label>
        <input type="file" name="portada" accept="image/*"><br>
        <button type="submit">Guardar</button>
    </form>
</body>
</html>
