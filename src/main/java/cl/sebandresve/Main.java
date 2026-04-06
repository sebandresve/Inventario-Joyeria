package cl.sebandresve;

import cl.sebandresve.controller.JoyaController;
import cl.sebandresve.view.InventarioView;

public class Main {
    public static void main(String[] args) {
        InventarioView view = new InventarioView();
        new JoyaController(view);
        view.setVisible(true);
    }
}
