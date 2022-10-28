
package modelos;

import java.io.Serializable;


public class Pedido implements Serializable {
    
    int idPed;
    String fecha;
    String cliente;
    String estado;
    int producto_id;
//    Producto producto;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getProducto() {
        return producto_id;
    }

    public void setProducto(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }
    
    
}
