package cl.sebandresve.controller;

import cl.sebandresve.model.service.JoyaService;
import cl.sebandresve.model.vo.JoyaVO;
import cl.sebandresve.view.InventarioView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class JoyaController {

    private InventarioView view;
    private JoyaService service;

    public JoyaController(InventarioView view) {
        this.view = view;
        this.service = new JoyaService();

        init();
    }

    private void init() {
        cargarTabla();

        view.btnAgregar.addActionListener(e -> agregar());

        view.btnActualizar.addActionListener(e -> actualizar());

        view.btnEliminar.addActionListener(e -> eliminar());

        view.tabla.getSelectionModel().addListSelectionListener(e -> seleccionarFila());
    }

    private void agregar() {
        try {
            JoyaVO joya = obtenerDatosFormulario(0);
            service.registrarJoya(joya);
            limpiarFormulario();
            cargarTabla();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void actualizar() {
        int fila = view.tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
            return;
        }

        int id = (int) view.tabla.getValueAt(fila, 0);

        try {
            JoyaVO joya = obtenerDatosFormulario(id);
            service.actualizarJoya(joya);
            limpiarFormulario();
            cargarTabla();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void eliminar() {
        int fila = view.tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
            return;
        }

        int id = (int) view.tabla.getValueAt(fila, 0);

        service.eliminarJoya(id);
        cargarTabla();
    }

    private void seleccionarFila() {
        int fila = view.tabla.getSelectedRow();

        if (fila >= 0) {
            view.txtNombre.setText(view.tabla.getValueAt(fila, 1).toString());
            view.txtMaterial.setText(view.tabla.getValueAt(fila, 2).toString());
            view.txtPeso.setText(view.tabla.getValueAt(fila, 3).toString());
            view.txtPrecio.setText(view.tabla.getValueAt(fila, 4).toString());
            view.txtStock.setText(view.tabla.getValueAt(fila, 5).toString());
        }
    }

    private JoyaVO obtenerDatosFormulario(int id) {
        return new JoyaVO(
                id,
                view.txtNombre.getText(),
                view.txtMaterial.getText(),
                Double.parseDouble(view.txtPeso.getText()),
                Double.parseDouble(view.txtPrecio.getText()),
                Integer.parseInt(view.txtStock.getText())
        );
    }

    private void limpiarFormulario() {
        view.txtNombre.setText("");
        view.txtMaterial.setText("");
        view.txtPeso.setText("");
        view.txtPrecio.setText("");
        view.txtStock.setText("");
    }

    public void cargarTabla() {
        DefaultTableModel model = (DefaultTableModel) view.tabla.getModel();
        model.setRowCount(0);

        List<JoyaVO> lista = service.obtenerJoyas();

        for (JoyaVO j : lista) {
            model.addRow(new Object[]{
                    j.getId(),
                    j.getNombre(),
                    j.getMaterial(),
                    j.getPeso(),
                    j.getPrecio(),
                    j.getStock()
            });
        }
    }
}
