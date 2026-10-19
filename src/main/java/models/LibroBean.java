package models;

import java.io.Serializable;

public class LibroBean implements Serializable {
    private int idLibro;
    private String titulo;
    private String autor;
    private String isbn;
    private int idCategoria;
    private int cantidadDisponible;
    private CategoriaBean categoria;

    public LibroBean() { }
    public LibroBean(int idLibro, String titulo, String autor, String isbn, int idCategoria, int cantidadDisponible) {
        this.idLibro = idLibro; this.titulo = titulo; this.autor = autor; this.isbn = isbn; this.idCategoria = idCategoria; this.cantidadDisponible = cantidadDisponible;
    }
    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int v) { idLibro = v; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String v) { titulo = v; }
    public String getAutor() { return autor; }
    public void setAutor(String v) { autor = v; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String v) { isbn = v; }
    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int v) { idCategoria = v; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int v) { cantidadDisponible = v; }
    public CategoriaBean getCategoria() { return categoria; }
    public void setCategoria(CategoriaBean v) { categoria = v; }
}
