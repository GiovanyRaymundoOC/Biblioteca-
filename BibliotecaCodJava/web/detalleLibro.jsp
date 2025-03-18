<!DOCTYPE html>
<html>
<head>
    <title>Detalle del Libro</title>
</head>
<body>
    <h1>Detalle del Libro</h1>
    <p><strong>ISBN:</strong> ${libro.isbn}</p>
    <p><strong>Título:</strong> ${libro.titulo}</p>
    <p><strong>Autor:</strong> ${libro.autor}</p>
    <p><strong>Año de Publicación:</strong> ${libro.anioPublicacion}</p>
    <p><strong>Editorial:</strong> ${libro.editorial}</p>
    <p><strong>Descripción:</strong> ${libro.descripcion}</p>
    <img src="${libro.portadaUrl}" alt="Portada" width="100">
    <br><a href="listaLibros.jsp">Volver a la Lista</a>
</body>
</html>
