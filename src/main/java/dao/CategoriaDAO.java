package dao;

import config.Conexion;
import models.CategoriaBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<CategoriaBean> listar() throws SQLException {
        List<CategoriaBean> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre_categoria FROM categorias ORDER BY nombre_categoria";
        try (Connection cn = Conexion.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(new CategoriaBean(rs.getInt(1), rs.getString(2)));
        }
        return lista;
    }
}
