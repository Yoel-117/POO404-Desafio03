package dao;

import config.Conexion;
import models.CategoriaBean;
import models.LibroBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    public void insertar(LibroBean l) throws SQLException {
        String sql = "INSERT INTO libros(titulo, autor, isbn, id_categoria, cantidad_disponible) VALUES(?,?,?,?,?)";
        try (Connection cn = Conexion.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, l.getTitulo()); ps.setString(2, l.getAutor()); ps.setString(3, l.getIsbn()); ps.setInt(4, l.getIdCategoria()); ps.setInt(5, l.getCantidadDisponible()); ps.executeUpdate();
        }
    }
    public List<LibroBean> listar() throws SQLException {
        List<LibroBean> lista = new ArrayList<>();
        String sql = "SELECT l.*, c.nombre_categoria FROM libros l LEFT JOIN categorias c ON c.id_categoria=l.id_categoria ORDER BY l.titulo";
        try (Connection cn = Conexion.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LibroBean l = new LibroBean(rs.getInt("id_libro"), rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"), rs.getInt("id_categoria"), rs.getInt("cantidad_disponible"));
                l.setCategoria(new CategoriaBean(rs.getInt("id_categoria"), rs.getString("nombre_categoria"))); lista.add(l);
            }
        }
        return lista;
    }
    public LibroBean obtenerPorId(Connection cn, int id) throws SQLException {
        String sql = "SELECT * FROM libros WHERE id_libro=?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) { ps.setInt(1, id); try (ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) return null;
            return new LibroBean(rs.getInt("id_libro"), rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"), rs.getInt("id_categoria"), rs.getInt("cantidad_disponible"));
        }}
    }
    public boolean actualizarDisponibilidad(Connection cn, int id, int cambio) throws SQLException {
        String sql = "UPDATE libros SET cantidad_disponible=cantidad_disponible+? WHERE id_libro=? AND cantidad_disponible+? >= 0";
        try (PreparedStatement ps = cn.prepareStatement(sql)) { ps.setInt(1, cambio); ps.setInt(2, id); ps.setInt(3, cambio); return ps.executeUpdate() == 1; }
    }
}
