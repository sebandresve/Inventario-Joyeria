package cl.sebandresve.model.dao;

import cl.sebandresve.config.ConexionDB;
import cl.sebandresve.model.vo.JoyaVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoyaDAO {

    public void crear(JoyaVO joya) {
        String sql = "INSERT INTO joya(nombre, material, peso, precio, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, joya.getNombre());
            ps.setString(2, joya.getMaterial());
            ps.setDouble(3, joya.getPeso());
            ps.setDouble(4, joya.getPrecio());
            ps.setInt(5, joya.getStock());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JoyaVO> listar() {
        List<JoyaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM joya";

        try (Connection conn = ConexionDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                JoyaVO j = new JoyaVO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("material"),
                        rs.getDouble("peso"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                lista.add(j);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
