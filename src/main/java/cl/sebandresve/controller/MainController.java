package cl.sebandresve.controller;

import cl.sebandresve.view.InventarioView;
import cl.sebandresve.view.MainView;
import cl.sebandresve.view.ReporteView;
import cl.sebandresve.view.VentaView;

public class MainController {

    private MainView view;
    private ReporteView reporteView;

    public MainController(MainView view) {
        this.view = view;

        init();
    }

    private void init() {

        view.menuInventario.addActionListener(e -> abrirInventario());

        view.menuVentas.addActionListener(e -> abrirVentas());

        view.menuReportes.addActionListener(e -> abrirReportes());
    }

    private InventarioView inventarioView;
    private JoyaController joyaController;

    private void abrirInventario() {
        if (inventarioView == null) {
            inventarioView = new InventarioView();
            joyaController = new JoyaController(inventarioView);
        }
        inventarioView.setVisible(true);
    }

    private VentaView ventaView;

    private void abrirVentas() {
        if (ventaView == null) {
            ventaView = new VentaView();
            new VentaController(ventaView, joyaController);
        }
        ventaView.setVisible(true);
    }

    private void abrirReportes() {
        if (reporteView == null) {
            reporteView = new ReporteView();
            new ReporteController(reporteView);
        }
        reporteView.setVisible(true);
        reporteView.toFront();
    }

}
