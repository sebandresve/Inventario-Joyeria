package cl.sebandresve.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentaView extends JFrame {

    public JComboBox comboCliente;
    public JComboBox comboJoya;
    public JTextField txtCantidad;
    public JButton btnVender;

    public JComboBox comboFiltroCliente;
    public JTextField txtFechaFiltro;
    public JButton btnFiltrar;

    public JTable tablaVentas;

    public VentaView() {
        setTitle("Ventas");
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(2, 4));

        comboCliente = new JComboBox();
        comboJoya = new JComboBox();
        txtCantidad = new JTextField();
        btnVender = new JButton("Realizar Venta");

        panelForm.add(new JLabel("Cliente"));
        panelForm.add(new JLabel("Joya"));
        panelForm.add(new JLabel("Cantidad"));
        panelForm.add(new JLabel(""));

        panelForm.add(comboCliente);
        panelForm.add(comboJoya);
        panelForm.add(txtCantidad);
        panelForm.add(btnVender);

        add(panelForm, BorderLayout.NORTH);

        tablaVentas = new JTable(new DefaultTableModel(
                new Object[]{"Cliente", "Joya", "Cantidad", "Fecha"}, 0
        ));

        add(new JScrollPane(tablaVentas), BorderLayout.CENTER);

        JPanel panelFiltro = new JPanel();

        comboFiltroCliente = new JComboBox();
        txtFechaFiltro = new JTextField(10);
        btnFiltrar = new JButton("Filtrar");

        panelFiltro.add(new JLabel("Cliente"));
        panelFiltro.add(comboFiltroCliente);

        panelFiltro.add(new JLabel("Fecha (YYYY-MM-DD)"));
        panelFiltro.add(txtFechaFiltro);

        panelFiltro.add(btnFiltrar);

        add(panelFiltro, BorderLayout.SOUTH);
    }
}
