/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelos.Pedido;
import modelos.Producto;

/**
 *
 * @author yuan-
 */
public class PedidoDAOmySQL implements PedidoDAO {
    
//    private static final String USER = "root";
//    private static final String PASSWORD = "jumaca";
//    private static final String URL = "jdbc:mysql://localhost:3306/comandas";
    
    private static final String GET_QUERY = "select * from pedido where idPed=?";
    private static final String GET_QUERY_CLIENTE = "SELECT * FROM pedido WHERE cliente LIKE ?";
    private static final String GETALL_QUERY = "SELECT * FROM pedido ORDER BY idPed";
    private static final String INSERT_QUERY = "INSERT INTO `pedido` (`idPed`, `fecha`, `cliente`, `estado`,`producto_id`) VALUES  (NULL, ?, ?, ?,?);";
    private static final String UPDATE_QUERY = "UPDATE pedido SET fecha = ?, cliente = ?, estado = ?, producto_id = ? WHERE pedido.idPed = ?";
    private static final String GETALL_BY_FECHA_QUERY = "SELECT * FROM pedido WHERE fecha = ? AND estado != ?";
    private static final String DELETE_ID_QUERY = "DELETE FROM pedido WHERE idPed = ?";
    private static final String COUNT_PEDIDOS = "SELECT * FROM pedido";
    
    private static Connection conexion = Conexion.getConexion();
    
    public PedidoDAOmySQL() {
    	
    }
    
    @Override
    public Pedido get(Integer idPed) {
    	
    	try(var pst = conexion.prepareStatement(GET_QUERY)){
            
            pst.setInt(1, idPed);
            
            ResultSet resultado = pst.executeQuery();
            
            if(resultado.next()){
                var pedido = new Pedido();
                pedido.setIdPed(resultado.getInt("idPed"));
                pedido.setFecha(resultado.getString("fecha"));
                pedido.setCliente( resultado.getString("cliente") );
                pedido.setEstado( resultado.getString("estado") );
                pedido.setProducto(resultado.getInt("producto_id"));
                return pedido;
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    	
    }
    
    
    

    @Override
    public Pedido verPedido(Integer idPed) {
        
        try(var pst = conexion.prepareStatement(GET_QUERY)){
            
            pst.setInt(1, idPed);
            
            ResultSet resultado = pst.executeQuery();
            
            if(resultado.next()){
                var pedido = new Pedido();
                pedido.setIdPed( resultado.getInt("idPed") );
                pedido.setFecha( resultado.getString("fecha") );
                pedido.setCliente( resultado.getString("cliente") );
                pedido.setEstado(resultado.getString("estado") );
                pedido.setProducto(resultado.getInt("producto_id") );
                return pedido;
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }




	@Override
	public void update(Pedido p) {
		// TODO Auto-generated method stub
			try(var pst = conexion.prepareStatement(UPDATE_QUERY)){
            
            
            
            pst.setString(1, p.getFecha());
            pst.setString(2, p.getCliente());
            pst.setString(3, p.getEstado());
            pst.setInt(4, p.getProducto());
            pst.setInt(5,p.getIdPed());
            
            
            
            if( pst.executeUpdate()== 0){
                Logger.getLogger(PedidoDAOmySQL.class.getName()).severe("El pedido no existe");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
		
	}




	@Override
	public void añadir(Pedido p) {
		// TODO Auto-generated method stub

		  try( var pst = conexion.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)){
	            
	            pst.setString(1, p.getFecha() );
	            pst.setString(2, p.getCliente() );
	            pst.setString(3, p.getEstado());
	            pst.setInt(4, p.getProducto());

	            if (pst.executeUpdate() > 0){

	                var keys = pst.getGeneratedKeys();
	                keys.next();
//	                pst.setInt(1, keys.getInt(1));
	                p.setIdPed(keys.getInt(1));

	            }
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
	        }
		
	}




	@Override
	public ArrayList<Pedido> verCarta() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(Pedido p) {
		delete(p.getIdPed());
	}
	
	
	@Override
	public void delete(Integer idPed) {
		
		try( var pst = conexion.prepareStatement(DELETE_ID_QUERY)){
	        
            pst.setInt(1, idPed);
            
            if(pst.executeUpdate()==0){
                Logger.getLogger(PedidoDAOmySQL.class.getName()).warning("El pedido no existe");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }  
	}

	
	@Override
    public ArrayList<Pedido> getAllByFecha(String fecha) {
        var salida = new ArrayList<Pedido>();
        
        try( var pst = conexion.prepareStatement(GETALL_BY_FECHA_QUERY)){
            
            pst.setString(1, fecha);
            pst.setString(2, "Recogido");
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                var pedido = new Pedido();
                pedido.setIdPed(resultado.getInt("idPed"));
                pedido.setFecha(resultado.getString("fecha"));
                pedido.setCliente( resultado.getString("cliente") );
                pedido.setEstado( resultado.getString("estado") );
                pedido.setProducto(resultado.getInt("producto_id"));
                salida.add(pedido);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salida;
    }

	@Override
	public ArrayList<Pedido> getCliente(String cliente) {
		var salida = new ArrayList<Pedido>();
    	
    	try(var pst = conexion.prepareStatement(GET_QUERY_CLIENTE)){
            
            pst.setString(1, cliente);
            
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                var pedido = new Pedido();
                pedido.setIdPed(resultado.getInt("idPed"));
                pedido.setFecha(resultado.getString("fecha"));
                pedido.setCliente( resultado.getString("cliente") );
                pedido.setEstado( resultado.getString("estado") );
                pedido.setProducto(resultado.getInt("producto_id"));
                salida.add(pedido);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salida;
    	
    }

	@Override
	public void cuenta() {
		var salida = new ArrayList<Pedido>();
    	
    	try(var pst = conexion.prepareStatement(COUNT_PEDIDOS)){
            
//    		Producto producto = new Producto();
    		ProductoDAOmySQL p = new ProductoDAOmySQL();
    		
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                var pedido = new Pedido();
                pedido.setIdPed(resultado.getInt("idPed"));
                pedido.setFecha(resultado.getString("fecha"));
                pedido.setCliente( resultado.getString("cliente") );
                pedido.setEstado( resultado.getString("estado") );
                pedido.setProducto(resultado.getInt("producto_id"));
                salida.add(pedido);
            }
           
           System.out.print("Cantidad total de pedidos realizados: ");
           System.out.println( salida.size());
           
           int total=0;
           int price=0;
           int idPro=0;
           for (Pedido pedido : salida) {
        	   idPro=pedido.getProducto();
        	   price=p.verProducto(idPro).getPrecio();
        	   total=total+price;
           }
           System.out.print("Total recaudado: ");
           System.out.println(total);
        
            
        } catch(SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
		
		
	}
    
    }
    