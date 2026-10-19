package models;

import java.io.Serializable;

public class EstudianteBean implements Serializable {
    private int idEstudiante;
    private String carnet;
    private String nombreEstudiante;
    private String carrera;
    private String telefono;

    public EstudianteBean() { }
    public EstudianteBean(int idEstudiante, String carnet, String nombreEstudiante, String carrera, String telefono) {
        this.idEstudiante = idEstudiante; this.carnet = carnet; this.nombreEstudiante = nombreEstudiante; this.carrera = carrera; this.telefono = telefono;
    }
    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int v) { idEstudiante = v; }
    public String getCarnet() { return carnet; }
    public void setCarnet(String v) { carnet = v; }
    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String v) { nombreEstudiante = v; }
    public String getCarrera() { return carrera; }
    public void setCarrera(String v) { carrera = v; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String v) { telefono = v; }
}
