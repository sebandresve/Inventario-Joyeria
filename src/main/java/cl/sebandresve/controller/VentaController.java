package cl.sebandresve.controller;

import cl.sebandresve.model.dao.ClienteDAO;
import cl.sebandresve.model.dao.JoyaDAO;
import cl.sebandresve.model.dao.VentaDAO;
import cl.sebandresve.model.service.VentaService;
import cl.sebandresve.model.vo.ClienteVO;
import cl.sebandresve.model.vo.JoyaVO;
import cl.sebandresve.model.vo.VentaVO;
import cl.sebandresve.view.VentaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentaController {

    private VentaView view;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private JoyaDAO joyaDAO = new JoyaDAO();
    private VentaService service = new VentaService();
    private VentaDAO ventaDAO = new VentaDAO();
    private JoyaController joyaController;

    public VentaController(VentaView view, JoyaController joyaController) {
        this.view = view;
        this.joyaController = joyaController;

        cargarCombos();
        cargarTabla(null, null);

        view.btnVender.addActionListener(e -> vender());
        view.btnFiltrar.addActionListener(e -> filtrar());
    }

    private void cargarCombos() {
        List<ClienteVO> clientes = clienteDAO.listar();
        for (ClienteVO c : clientes) {
            view.comboCliente.addItem(c);
            view.comboFiltroCliente.addItem(c.getNombre());
        }

        List<JoyaVO> joyas = joyaDAO.listar();
        for (JoyaVO j : joyas) {
            view.comboJoya.addItem(j);
        }
    }

    private void vender() {
        try {
            ClienteVO cliente = (ClienteVO) view.comboCliente.getSelectedItem();
            JoyaVO joya = (JoyaVO) view.comboJoya.getSelectedItem();

            int cantidad = Integer.parseInt(view.txtCantidad.getText());

            VentaVO venta = new VentaVO(cliente.getId(), joya.getId(), cantidad);

            service.realizarVenta(venta);

            if (joyaController != null) {
                joyaController.cargarTabla();
            }

            JOptionPane.showMessageDialog(null, "Venta realizada");

            cargarTabla(null, null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cargarTabla(String cliente, java.sql.Date fecha) {

        DefaultTableModel model = (DefaultTableModel) view.tablaVentas.getModel();
        model.setRowCount(0);

        var lista = ventaDAO.listarVentas(cliente, fecha);

        for (Object[] row : lista) {
            model.addRow(row);
        }
    }

    private void filtrar() {
        String cliente = null;
        java.sql.Date fecha = null;

        // Cliente
        if (view.comboFiltroCliente.getSelectedItem() != null) {
            cliente = view.comboFiltroCliente.getSelectedItem().toString();
        }

        // Fecha
        if (!view.txtFechaFiltro.getText().isEmpty()) {
            try {
                fecha = java.sql.Date.valueOf(view.txtFechaFiltro.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido (YYYY-MM-DD)");
                return;
            }
        }

        cargarTabla(cliente, fecha);
    }
}
