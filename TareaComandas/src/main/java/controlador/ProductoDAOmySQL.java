/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Producto;

/**
 *
 * @author Syzer
 */
public class ProductoDAOmySQL implements ProductoDAO {
    
//    private static final String USER = "root";
//    private static final String PASSWORD = "RePuBlIc0FG4MeS*";
//    private static final String URL = "jdbc:mysql://localhost:3306/comandas";
    
    private static final String GET_QUERY = "select * from producto where idPro=?";
    private static final String GETALL_QUERY = "SELECT * FROM producto ORDER BY idPro";
    private static final String INSERT_QUERY = """
                INSERT INTO producto 
                        (idPro, nombre, tipo, disponibilidad, precio) 
                VALUES  (NULL, ?, ?, ?, ?);""";
    private static final String UPDATE_QUERY = """
                UPDATE producto SET
                                              
                    nombre = ?,
                    tipo = ?,                                    
                    disponibilidad = ?, 
                    precio = ?                           
                WHERE producto.idPro = ?""";
    private static final String GETALL_BY_CURSO_QUERY = "SELECT * FROM producto WHERE carta = ?";
    
    private static Connection conexion = Conexion.getConexion();
    
    
    

    @Override
    public Producto verProducto(Integer idPro) {
        
        try(var pst = conexion.prepareStatement(GET_QUERY)){
            
            pst.setInt(1, idPro);
            
            ResultSet resultado = pst.executeQuery();
            
            if(resultado.next()){
                var producto = new Producto();
                producto.setIdPro( resultado.getInt("idPro") );
                producto.setNombre( resultado.getString("nombre") );
                producto.setTipo( resultado.getString("tipo") );
                producto.setDisponibilidad(resultado.getString("disponibilidad") );
                producto.setPrecio(resultado.getInt("precio") );
                return producto;
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public void update(Producto p) {
        try(var pst = conexion.prepareStatement(UPDATE_QUERY)){
            
            
            
            pst.setString(1, p.getNombre());
            pst.setString(2, p.getTipo());
            pst.setString(3, p.getDisponibilidad());
            pst.setInt(4, p.getPrecio());
            pst.setInt(5, p.getIdPro());
            
            
            if( pst.executeUpdate()== 0){
                Logger.getLogger(ProductoDAOmySQL.class.getName()).severe("Alumno no existe.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public ArrayList<Producto> verCarta() {
        
        var salida = new ArrayList<Producto>();
        
        try( var pst = conexion.prepareStatement(GETALL_QUERY)){
            
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                var producto = new Producto();
                producto.setIdPro( resultado.getInt("idPro") );
                producto.setNombre( resultado.getString("nombre") );
                producto.setTipo( resultado.getString("tipo") );
                producto.setDisponibilidad(resultado.getString("disponibilidad") );
                producto.setPrecio(resultado.getInt("precio") );
                salida.add(producto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salida;
    }

    @Override
    public void añadir(Producto p) {
        try( var pst = conexion.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)){
            
            pst.setString(1, p.getNombre() );
            pst.setString(2, p.getTipo() );
            pst.setString(3, p.getDisponibilidad());
            pst.setInt(4, p.getPrecio() );
            
            
            if (pst.executeUpdate() > 0){

                var keys = pst.getGeneratedKeys();
                keys.next();
                
                p.setIdPro(keys.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
    }
    

