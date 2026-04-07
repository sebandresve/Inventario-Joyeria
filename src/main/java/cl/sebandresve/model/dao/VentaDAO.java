package cl.sebandresve.model.dao;

import cl.sebandresve.config.ConexionDB;
import cl.sebandresve.model.vo.VentaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDAO {

    public void registrar(VentaVO venta) {

        String sql = "INSERT INTO venta(cliente_id, joya_id, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, venta.getClienteId());
            ps.setInt(2, venta.getJoyaId());
            ps.setInt(3, venta.getCantidad());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> listarVentas(String cliente, Date fecha) {

        List<Object[]> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT c.nombre, j.nombre, v.cantidad, v.fecha
        FROM venta v
        JOIN cliente c ON v.cliente_id = c.id
        JOIN joya j ON v.joya_id = j.id
        WHERE 1=1
    """);

        List<Object> params = new ArrayList<>();

        if (cliente != null) {
            sql.append(" AND c.nombre = ?");
            params.add(cliente);
        }

        if (fecha != null) {
            sql.append(" AND DATE(v.fecha) = ?");
            params.add(fecha);
        }

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getTimestamp(4)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public double obtenerTotalVentas() {
        String sql = """
        SELECT SUM(v.cantidad * j.precio)
        FROM venta v
        JOIN joya j ON v.joya_id = j.id
    """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Object[]> topJoyas() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
        SELECT j.nombre, SUM(v.cantidad) as total
        FROM venta v
        JOIN joya j ON v.joya_id = j.id
        GROUP BY j.nombre
        ORDER BY total DESC
        LIMIT 10
    """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getString(1),
                        rs.getInt(2)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Object[]> topClientes() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
        SELECT c.nombre, COUNT(v.id) as total
        FROM venta v
        JOIN cliente c ON v.cliente_id = c.id
        GROUP BY c.nombre
        ORDER BY total DESC
        LIMIT 10
    """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getString(1),
                        rs.getInt(2)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public int obtenerCantidadVentas() {
        String sql = "SELECT SUM(cantidad) FROM venta";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
