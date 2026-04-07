package cl.sebandresve.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventarioView extends JFrame {

    public JTable tabla;

    public JTextField txtNombre;
    public JTextField txtMaterial;
    public JTextField txtPeso;
    public JTextField txtPrecio;
    public JTextField txtStock;

    public JButton btnAgregar;
    public JButton btnActualizar;
    public JButton btnEliminar;

    public InventarioView() {
        setTitle("Inventario de Joyas");
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tabla = new JTable(new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Material", "Peso", "Precio", "Stock"}, 0
        ));
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelForm = new JPanel(new GridLayout(2, 5));

        txtNombre = new JTextField();
        txtMaterial = new JTextField();
        txtPeso = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();

        panelForm.add(new JLabel("Nombre"));
        panelForm.add(new JLabel("Material"));
        panelForm.add(new JLabel("Peso"));
        panelForm.add(new JLabel("Precio"));
        panelForm.add(new JLabel("Stock"));

        panelForm.add(txtNombre);
        panelForm.add(txtMaterial);
        panelForm.add(txtPeso);
        panelForm.add(txtPrecio);
        panelForm.add(txtStock);

        add(panelForm, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);
    }
}
