package cl.sebandresve.controller;

import cl.sebandresve.model.service.JoyaService;
import cl.sebandresve.model.vo.JoyaVO;
import cl.sebandresve.view.InventarioView;

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

        view.btnAgregar.addActionListener(e -> {
            JoyaVO joya = new JoyaVO(0, "Anillo", "Oro", 10, 50000, 5);
            service.registrarJoya(joya);
            cargarTabla();
        });
    }

    private void cargarTabla() {
        DefaultTableModel model = (DefaultTableModel) view.tabla.getModel();
        model.setRowCount(0);

        List<JoyaVO> lista = service.obtenerJoyas();

        for (JoyaVO j : lista) {
            model.addRow(new Object[]{
                    j.getId(),
                    j.getNombre(),
                    j.getMaterial(),
                    j.getPrecio(),
                    j.getStock()
            });
        }
    }
}
