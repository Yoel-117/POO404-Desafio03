package dao;

import config.Conexion;
import models.EstudianteBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    public void insertar(EstudianteBean e) throws SQLException {
        String sql = "INSERT INTO estudiantes(carnet,nombre_estudiante,carrera,telefono) VALUES(?,?,?,?)";
        try (Connection cn = Conexion.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) { ps.setString(1,e.getCarnet()); ps.setString(2,e.getNombreEstudiante()); ps.setString(3,e.getCarrera()); ps.setString(4,e.getTelefono()); ps.executeUpdate(); }
    }
    public List<EstudianteBean> listar() throws SQLException {
        List<EstudianteBean> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes ORDER BY nombre_estudiante";
        try (Connection cn=Conexion.obtenerConexion(); PreparedStatement ps=cn.prepareStatement(sql); ResultSet rs=ps.executeQuery()) {
            while(rs.next()) lista.add(new EstudianteBean(rs.getInt("id_estudiante"),rs.getString("carnet"),rs.getString("nombre_estudiante"),rs.getString("carrera"),rs.getString("telefono")));
        }
        return lista;
    }
}
