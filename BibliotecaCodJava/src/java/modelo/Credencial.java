package modelo;

import java.util.Date;

public class Credencial {

    // Atributos
    private int idCredencial;
    private String email;
    private String passwordHash;
    private String direccion;
    private String numContacto;
    private Date vigencia;
    private int edad;
    private EstatusCredencial estatus;

    // Enumeración para el estatus de la credencial
    public enum EstatusCredencial { 
        ACTIVO, 
        INACTIVO, 
        BLOQUEADO 
    }

    // Getters y Setters
    public int getIdCredencial() { 
        return idCredencial; 
    }

    public void setIdCredencial(int idCredencial) { 
        this.idCredencial = idCredencial; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPasswordHash() { 
        return passwordHash; 
    }

    public void setPasswordHash(String passwordHash) { 
        this.passwordHash = passwordHash; 
    }

    public String getDireccion() { 
        return direccion; 
    }

    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }

    public String getNumContacto() { 
        return numContacto; 
    }

    public void setNumContacto(String numContacto) { 
        this.numContacto = numContacto; 
    }

    public Date getVigencia() { 
        return vigencia; 
    }

    public void setVigencia(Date vigencia) { 
        this.vigencia = vigencia; 
    }

    public int getEdad() { 
        return edad; 
    }

    public void setEdad(int edad) { 
        this.edad = edad; 
    }

    public EstatusCredencial getEstatus() { 
        return estatus; 
    }

    public void setEstatus(EstatusCredencial estatus) { 
        this.estatus = estatus; 
    }
}
