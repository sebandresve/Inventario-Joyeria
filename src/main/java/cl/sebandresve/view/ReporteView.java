package cl.sebandresve.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReporteView extends JFrame {

    public JLabel lblTotalVentasMonto;
    public JLabel lblTotalVentasCantidad;

    public JTable tablaJoyas;
    public JTable tablaClientes;

    public ReporteView() {
        setTitle("Reporte de Ventas");
        setSize(900, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTop = new JPanel(new GridLayout(2, 1));

        lblTotalVentasMonto = new JLabel("Total Ventas (Monto): $0");
        lblTotalVentasMonto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalVentasMonto.setHorizontalAlignment(SwingConstants.CENTER);

        lblTotalVentasCantidad = new JLabel("Total Ventas (Cantidad): 0");
        lblTotalVentasCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTotalVentasCantidad.setHorizontalAlignment(SwingConstants.CENTER);

        panelTop.add(lblTotalVentasMonto);
        panelTop.add(lblTotalVentasCantidad);

        add(panelTop, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2));

        JPanel panelJoyas = new JPanel(new BorderLayout());

        JLabel lblJoyas = new JLabel("Joyas más vendidas");
        lblJoyas.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoyas.setFont(new Font("Segoe UI", Font.BOLD, 14));

        tablaJoyas = new JTable(new DefaultTableModel(
                new Object[]{"Joya", "Cantidad Vendida"}, 0
        ));

        panelJoyas.add(lblJoyas, BorderLayout.NORTH);
        panelJoyas.add(new JScrollPane(tablaJoyas), BorderLayout.CENTER);

        JPanel panelClientes = new JPanel(new BorderLayout());

        JLabel lblClientes = new JLabel("Clientes con más compras");
        lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
        lblClientes.setFont(new Font("Segoe UI", Font.BOLD, 14));

        tablaClientes = new JTable(new DefaultTableModel(
                new Object[]{"Cliente", "Compras"}, 0
        ));

        panelClientes.add(lblClientes, BorderLayout.NORTH);
        panelClientes.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

        panelCentro.add(panelJoyas);
        panelCentro.add(panelClientes);

        add(panelCentro, BorderLayout.CENTER);
    }
}
