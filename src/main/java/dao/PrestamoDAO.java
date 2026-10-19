package dao;

import config.Conexion;
import models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    public void insertar(PrestamoBean p) throws SQLException {
        String insert = "INSERT INTO prestamos(id_estudiante,id_libro,fecha_prestamo,fecha_devolucion,estado) VALUES(?,?,?,?, 'Activo')";
        try (Connection cn=Conexion.obtenerConexion()) {
            cn.setAutoCommit(false);
            try {
                if (!new LibroDAO().actualizarDisponibilidad(cn, p.getIdLibro(), -1)) throw new SQLException("El libro seleccionado no tiene ejemplares disponibles.");
                try (PreparedStatement ps=cn.prepareStatement(insert)) { ps.setInt(1,p.getIdEstudiante()); ps.setInt(2,p.getIdLibro()); ps.setDate(3,p.getFechaPrestamo()); ps.setDate(4,p.getFechaDevolucion()); ps.executeUpdate(); }
                cn.commit();
            } catch (SQLException ex) { cn.rollback(); throw ex; }
        }
    }
    public List<PrestamoBean> listar() throws SQLException {
        List<PrestamoBean> lista=new ArrayList<>();
        String sql="SELECT p.*, e.carnet,e.nombre_estudiante,e.carrera,e.telefono,l.titulo,l.autor,l.isbn,l.id_categoria,l.cantidad_disponible, c.nombre_categoria, CASE WHEN p.estado='Activo' AND p.fecha_devolucion<CURDATE() THEN 'Vencido' ELSE p.estado END AS estado_calculado FROM prestamos p JOIN estudiantes e ON e.id_estudiante=p.id_estudiante JOIN libros l ON l.id_libro=p.id_libro LEFT JOIN categorias c ON c.id_categoria=l.id_categoria ORDER BY p.id_prestamo DESC";
        try(Connection cn=Conexion.obtenerConexion(); PreparedStatement ps=cn.prepareStatement(sql); ResultSet rs=ps.executeQuery()) {
            while(rs.next()) { PrestamoBean p=new PrestamoBean(); p.setIdPrestamo(rs.getInt("id_prestamo")); p.setIdEstudiante(rs.getInt("id_estudiante")); p.setIdLibro(rs.getInt("id_libro")); p.setFechaPrestamo(rs.getDate("fecha_prestamo")); p.setFechaDevolucion(rs.getDate("fecha_devolucion")); p.setEstado(rs.getString("estado_calculado")); p.setEstudiante(new EstudianteBean(rs.getInt("id_estudiante"),rs.getString("carnet"),rs.getString("nombre_estudiante"),rs.getString("carrera"),rs.getString("telefono"))); LibroBean l=new LibroBean(rs.getInt("id_libro"),rs.getString("titulo"),rs.getString("autor"),rs.getString("isbn"),rs.getInt("id_categoria"),rs.getInt("cantidad_disponible")); l.setCategoria(new CategoriaBean(rs.getInt("id_categoria"),rs.getString("nombre_categoria"))); p.setLibro(l); lista.add(p); }
        }
        return lista;
    }
    public void devolver(int id) throws SQLException {
        String update="UPDATE prestamos SET estado='Devuelto' WHERE id_prestamo=? AND estado='Activo'";
        try(Connection cn=Conexion.obtenerConexion()) { cn.setAutoCommit(false); try { int libro; try(PreparedStatement q=cn.prepareStatement("SELECT id_libro FROM prestamos WHERE id_prestamo=? AND estado='Activo' FOR UPDATE")){q.setInt(1,id); try(ResultSet rs=q.executeQuery()){if(!rs.next()) throw new SQLException("El préstamo ya fue devuelto o no existe."); libro=rs.getInt(1);}} if(!new LibroDAO().actualizarDisponibilidad(cn,libro,1)) throw new SQLException("No se pudo actualizar la disponibilidad."); try(PreparedStatement ps=cn.prepareStatement(update)){ps.setInt(1,id);ps.executeUpdate();} cn.commit(); } catch(SQLException ex){cn.rollback();throw ex;} }
    }
}
