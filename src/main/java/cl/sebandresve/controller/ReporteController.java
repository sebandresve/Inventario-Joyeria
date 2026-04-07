package cl.sebandresve.controller;

import cl.sebandresve.model.dao.VentaDAO;
import cl.sebandresve.view.ReporteView;

import javax.swing.table.DefaultTableModel;

public class ReporteController {

    private ReporteView view;
    private VentaDAO dao = new VentaDAO();

    public ReporteController(ReporteView view) {
        this.view = view;

        cargarDatos();
    }

    private void cargarDatos() {

        double totalMonto = dao.obtenerTotalVentas();
        view.lblTotalVentasMonto.setText("Total Ventas (Monto): $" + totalMonto);

        int totalCantidad = dao.obtenerCantidadVentas();
        view.lblTotalVentasCantidad.setText("Total Ventas (Cantidad): " + totalCantidad);

        DefaultTableModel modelJoyas = (DefaultTableModel) view.tablaJoyas.getModel();
        modelJoyas.setRowCount(0);

        for (Object[] row : dao.topJoyas()) {
            modelJoyas.addRow(row);
        }

        DefaultTableModel modelClientes = (DefaultTableModel) view.tablaClientes.getModel();
        modelClientes.setRowCount(0);

        for (Object[] row : dao.topClientes()) {
            modelClientes.addRow(row);
        }
    }
}
