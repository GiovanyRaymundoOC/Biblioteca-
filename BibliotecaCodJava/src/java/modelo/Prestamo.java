package modelo;

import java.sql.Timestamp;
import java.util.Date;

public class Prestamo {
    
    // Atributos
    private int idPrestamo;
    private Libro libro;
    private Credencial credencial;
    private Usuario usuario; // Nueva relación directa con Usuario si es necesario
    private Timestamp fechaPrestamo;
    private Date fechaLimite;
    private Date fechaEntrega;
    private EstadoPrestamo estado;

    // Enumeración para los estados del préstamo
    public enum EstadoPrestamo {
        ACTIVO, 
        DEVUELTO, 
        RETRASADO
    }

    // Getters y Setters
    public int getIdPrestamo() { 
        return idPrestamo; 
    }

    public void setIdPrestamo(int idPrestamo) { 
        this.idPrestamo = idPrestamo; 
    }

    public Libro getLibro() { 
        return libro; 
    }

    public void setLibro(Libro libro) { 
        this.libro = libro; 
    }

    public Credencial getCredencial() { 
        return credencial; 
    }

    public void setCredencial(Credencial credencial) { 
        this.credencial = credencial; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }

    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }

    public Timestamp getFechaPrestamo() { 
        return fechaPrestamo; 
    }

    public void setFechaPrestamo(Timestamp fechaPrestamo) { 
        this.fechaPrestamo = fechaPrestamo; 
    }

    public Date getFechaLimite() { 
        return fechaLimite; 
    }

    public void setFechaLimite(Date fechaLimite) { 
        this.fechaLimite = fechaLimite; 
    }

    public Date getFechaEntrega() { 
        return fechaEntrega; 
    }

    public void setFechaEntrega(Date fechaEntrega) { 
        this.fechaEntrega = fechaEntrega; 
    }

    public EstadoPrestamo getEstado() { 
        return estado; 
    }

    public void setEstado(EstadoPrestamo estado) { 
        this.estado = estado; 
    }
}
