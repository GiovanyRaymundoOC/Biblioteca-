package modelo;

import org.json.JSONObject;

public class Usuario {
    private int idUsuario;
    private Institucion institucion;
    private Credencial credencial;
    private String nombre;
    private RolUsuario rol;
    private JSONObject configuracionAccesibilidad;

    public enum RolUsuario { ADMIN, BIBLIOTECARIO, USUARIO }

    // Getters y Setters CORRECTOS
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public Institucion getInstitucion() { return institucion; }
    public void setInstitucion(Institucion institucion) { this.institucion = institucion; }

    public Credencial getCredencial() { return credencial; }
    public void setCredencial(Credencial credencial) { this.credencial = credencial; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }

    public JSONObject getConfiguracionAccesibilidad() { return configuracionAccesibilidad; }
    public void setConfiguracionAccesibilidad(JSONObject configuracionAccesibilidad) {
        this.configuracionAccesibilidad = configuracionAccesibilidad;
    }
}