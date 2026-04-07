package cl.sebandresve;

import cl.sebandresve.controller.MainController;
import cl.sebandresve.view.MainView;

public class Main {

    public static void main(String[] args) {

        MainView view = new MainView();
        new MainController(view);

        view.setVisible(true);
    }
}
