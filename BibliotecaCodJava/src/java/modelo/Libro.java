package modelo;

import java.sql.Timestamp;
import java.util.List;

public class Libro {
    private String isbn;
    private Clasificacion clasificacion;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String editorial;
    private int unidadesDisponibles;
    private String descripcion;
    private TipoLibro tipo;
    private Timestamp fechaRegistro;
    private String portadaUrl;
    private List<Genero> generos;

    public enum TipoLibro { FISICO, DIGITAL, AUDIOVISUAL }

    // Getters y Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Clasificacion getClasificacion() { return clasificacion; }
    public void setClasificacion(Clasificacion clasificacion) { this.clasificacion = clasificacion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getUnidadesDisponibles() { return unidadesDisponibles; }
    public void setUnidadesDisponibles(int unidadesDisponibles) { this.unidadesDisponibles = unidadesDisponibles; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public TipoLibro getTipo() { return tipo; }
    public void setTipo(TipoLibro tipo) { this.tipo = tipo; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getPortadaUrl() { return portadaUrl; }
    public void setPortadaUrl(String portadaUrl) { this.portadaUrl = portadaUrl; }

    public List<Genero> getGeneros() { return generos; }
    public void setGeneros(List<Genero> generos) { this.generos = generos; }
}