package models;

import java.io.Serializable;

public class CategoriaBean implements Serializable {
    private int idCategoria;
    private String nombreCategoria;

    public CategoriaBean() { }
    public CategoriaBean(int idCategoria, String nombreCategoria) { this.idCategoria = idCategoria; this.nombreCategoria = nombreCategoria; }
    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
}
