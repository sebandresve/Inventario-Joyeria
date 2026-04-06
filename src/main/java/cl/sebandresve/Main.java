package cl.sebandresve;

import cl.sebandresve.controller.JoyaController;
import cl.sebandresve.controller.VentaController;
import cl.sebandresve.view.InventarioView;
import cl.sebandresve.view.VentaView;

public class Main {
    public static void main(String[] args) {
        InventarioView view = new InventarioView();
        new JoyaController(view);
        view.setVisible(true);

        VentaView ventaView = new VentaView();
        new VentaController(ventaView);
        ventaView.setVisible(true);
    }
}
