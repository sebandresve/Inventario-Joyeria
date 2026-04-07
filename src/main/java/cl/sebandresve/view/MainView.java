package cl.sebandresve.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public JMenuItem menuInventario;
    public JMenuItem menuVentas;

    public MainView() {
        setTitle("Sistema Joyería");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Opciones");

        menuInventario = new JMenuItem("Inventario");
        menuVentas = new JMenuItem("Ventas");

        menu.add(menuInventario);
        menu.add(menuVentas);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        JLabel titulo = new JLabel("Sistema de Inventario y Ventas de Joyería");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(184, 134, 11));

        add(titulo, BorderLayout.CENTER);
    }
}
