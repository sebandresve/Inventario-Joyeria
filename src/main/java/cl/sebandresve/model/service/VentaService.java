package cl.sebandresve.model.service;

import cl.sebandresve.config.ConexionDB;
import cl.sebandresve.model.dao.VentaDAO;
import cl.sebandresve.model.vo.VentaVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentaService {

    private VentaDAO ventaDAO = new VentaDAO();

    public void realizarVenta(VentaVO venta) {

        try (Connection conn = ConexionDB.getConnection()) {

            conn.setAutoCommit(false); // 💥 TRANSACCIÓN

            // 1. Obtener stock actual
            String sqlStock = "SELECT stock FROM joya WHERE id=?";
            PreparedStatement psStock = conn.prepareStatement(sqlStock);
            psStock.setInt(1, venta.getJoyaId());

            ResultSet rs = psStock.executeQuery();

            if (rs.next()) {
                int stockActual = rs.getInt("stock");

                if (stockActual < venta.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente");
                }

                // 2. Descontar stock
                String updateStock = "UPDATE joya SET stock = stock - ? WHERE id=?";
                PreparedStatement psUpdate = conn.prepareStatement(updateStock);
                psUpdate.setInt(1, venta.getCantidad());
                psUpdate.setInt(2, venta.getJoyaId());
                psUpdate.executeUpdate();

                // 3. Registrar venta
                ventaDAO.registrar(venta);

                conn.commit(); // ✅ TODO OK

            } else {
                throw new RuntimeException("Joya no encontrada");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
