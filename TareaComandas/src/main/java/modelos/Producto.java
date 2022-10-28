
package modelos;

import java.io.Serializable;


public class Producto implements Serializable {
    
    int idPro;
    String nombre;
    String tipo;
    int precio;
    String disponibilidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    @Override
    public String toString() {
        return "Producto " + "idPro: " + idPro + " || nombre: " + nombre + " || tipo: " + tipo + " || precio: " + precio +"€" + " || disponibilidad: " + disponibilidad;
    }
    
    
    
}
