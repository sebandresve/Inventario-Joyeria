package cl.sebandresve.model.vo;

import java.util.Date;

public class VentaVO {

    private int id;
    private int clienteId;
    private int joyaId;
    private int cantidad;
    private Date fecha;

    public VentaVO() {}

    public VentaVO(int clienteId, int joyaId, int cantidad) {
        this.clienteId = clienteId;
        this.joyaId = joyaId;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getJoyaId() {
        return joyaId;
    }

    public void setJoyaId(int joyaId) {
        this.joyaId = joyaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
