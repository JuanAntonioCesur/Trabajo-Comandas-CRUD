/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelos.Producto;

/**
 *
 * @author Syzer
 */
public interface ProductoDAO {
    
    Producto verProducto(Integer idPro);
    
    void update(Producto p);
    
    void añadir(Producto p);
    
    ArrayList<Producto> verCarta();

    @Override
    public String toString();
    
    
    
    
}
