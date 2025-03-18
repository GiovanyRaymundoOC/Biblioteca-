package modelo;

public class Clasificacion {

    // Atributos
    private int idClasificacion;
    private String codigoDewey;
    private String nombre;
    private int nivel;

    // Getters y Setters
    public int getIdClasificacion() { 
        return idClasificacion; 
    }

    public void setIdClasificacion(int idClasificacion) { 
        this.idClasificacion = idClasificacion; 
    }

    public String getCodigoDewey() { 
        return codigoDewey; 
    }

    public void setCodigoDewey(String codigoDewey) { 
        this.codigoDewey = codigoDewey; 
    }

    public String getNombre() { 
        return nombre; 
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public int getNivel() { 
        return nivel; 
    }

    public void setNivel(int nivel) { 
        this.nivel = nivel; 
    }
}
