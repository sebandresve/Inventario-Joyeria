package cl.sebandresve.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventarioView extends JFrame {

    public JTable tabla;
    public JButton btnAgregar;

    public InventarioView() {
        setTitle("Inventario de Joyas");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabla = new JTable(new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Material", "Precio", "Stock"}, 0
        ));

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar = new JButton("Agregar Joya");
        add(btnAgregar, BorderLayout.SOUTH);
    }
}
