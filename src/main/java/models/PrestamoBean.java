package models;

import java.io.Serializable;
import java.sql.Date;

public class PrestamoBean implements Serializable {
    private int idPrestamo;
    private int idEstudiante;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;
    private LibroBean libro;
    private EstudianteBean estudiante;

    public PrestamoBean() { }
    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int v) { idPrestamo = v; }
    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int v) { idEstudiante = v; }
    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int v) { idLibro = v; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(Date v) { fechaPrestamo = v; }
    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date v) { fechaDevolucion = v; }
    public String getEstado() { return estado; }
    public void setEstado(String v) { estado = v; }
    public LibroBean getLibro() { return libro; }
    public void setLibro(LibroBean v) { libro = v; }
    public EstudianteBean getEstudiante() { return estudiante; }
    public void setEstudiante(EstudianteBean v) { estudiante = v; }
}
