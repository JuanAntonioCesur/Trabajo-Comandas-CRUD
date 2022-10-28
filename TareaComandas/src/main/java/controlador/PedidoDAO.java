/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelos.Pedido;

/**
 *
 * @author yuan-
 */
public interface PedidoDAO {
    
    Pedido verPedido(Integer idPed);
    
    void update(Pedido p);
    
    void añadir(Pedido p);
    
    ArrayList<Pedido> verCarta();

    @Override
    public String toString();

	Pedido get(Integer idPed);

	void delete(Pedido p);

	void delete(Integer idPed);

	ArrayList<Pedido> getAllByFecha(String fecha);

	ArrayList<Pedido> getCliente(String cliente);

	void cuenta();
    
    
    
    
}
    