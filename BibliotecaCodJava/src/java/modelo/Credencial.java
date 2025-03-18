package modelo;

import java.util.Date;

public class Credencial {
    private int idCredencial;
    private String direccion;
    private String numContacto;
    private Date vigencia;
    private int edad;
    private EstatusCredencial estatus;

    public enum EstatusCredencial { ACTIVO, INACTIVO, BLOQUEADO }

    // Getters y Setters
    public int getIdCredencial() { return idCredencial; }
    public void setIdCredencial(int idCredencial) { this.idCredencial = idCredencial; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getNumContacto() { return numContacto; }
    public void setNumContacto(String numContacto) { this.numContacto = numContacto; }

    public Date getVigencia() { return vigencia; }
    public void setVigencia(Date vigencia) { this.vigencia = vigencia; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public EstatusCredencial getEstatus() { return estatus; }
    public void setEstatus(EstatusCredencial estatus) { this.estatus = estatus; }
}