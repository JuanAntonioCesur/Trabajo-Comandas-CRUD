/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tareacomandas;

import controlador.PedidoDAOmySQL;
import controlador.ProductoDAOmySQL;
import java.util.ArrayList;
import java.util.Scanner;

import modelos.Pedido;
import modelos.Producto;

/**
 *
 * @author Syzer
 */
public class TareaComandas {

    static ProductoDAOmySQL dao = new ProductoDAOmySQL();
    static PedidoDAOmySQL daoPedido = new PedidoDAOmySQL();
    
    public static void main(String[] args) {
        
        
        ProductoDAOmySQL p = new ProductoDAOmySQL();
        PedidoDAOmySQL ped = new PedidoDAOmySQL();
        
        int opcion= 5;
        while (opcion!=8) {
			imprimirMenu();
			opcion = leerNumero();
			switch (opcion) {
			case 1:
				limpiarPantalla();
				System.out.println("********* 1.- Nuevo pedido ***********************");
				System.out.println("Id del pedido (Int)");
				int idPed=leerNumero();
				System.out.println("fecha del pedido");
				String fecha= leerPalabra();
				System.out.println("cliente");
				String cliente= leerPalabra();
				System.out.println("estado");
				String estado=leerPalabra();
				System.out.println("producto");
				int idProd=leerNumero();
				
				Pedido pe1 = new Pedido();
				pe1.setIdPed(idPed);
		        pe1.setFecha(fecha);
		        pe1.setCliente(cliente);
		        pe1.setEstado(estado);
		        pe1.setProducto(idProd);

				
				System.out.println("");
				System.out.println("Pedido añadido");
		        System.out.println("");
//		        

				
		        daoPedido.añadir(pe1);
//		        

				
				pressEnter();
				limpiarPantalla();
				break;
				
			case 2:
				System.out.println("********* 2.- Eliminar pedido ********************");
				System.out.println("Por favor indique el Id del pedido que desea borrar.");
				int idPed2=leerNumero();
				System.out.println("La siguiente entrada ha sido borrada: ");
				System.out.println(ped.verPedido(idPed2).getIdPed()+" || "+ped.verPedido(idPed2).getFecha());
				System.out.println(ped.verPedido(idPed2).getCliente()+" || "+ped.verPedido(idPed2).getEstado());
				System.out.println(ped.verPedido(idPed2).getProducto());
				daoPedido.delete(idPed2);
				pressEnter();
				limpiarPantalla();
				break;
				
			case 3:
				System.out.println("********* 3.- Marcar un pedido como recogido *****");
				System.out.println("Introduzca la Id del pedido que desea marcar como");
				System.out.println("recogido.");
				int idPed3=leerNumero();
				Pedido Recogido = daoPedido.get(idPed3);
				Recogido.setEstado("Recogido");
				daoPedido.update(Recogido);
				System.out.println("El siguiente pedido a sido recogido: ");
				System.out.println(ped.verPedido(idPed3).getIdPed()+" || "+ped.verPedido(idPed3).getFecha());
				System.out.println(ped.verPedido(idPed3).getCliente()+" || "+ped.verPedido(idPed3).getEstado());
				System.out.println(ped.verPedido(idPed3).getProducto());
				pressEnter();
				limpiarPantalla();
				break;
				
			case 4:
				System.out.println("********* 4.- Ver comandas pendientes de hoy *****");
				String Hoy =""+ java.time.LocalDate.now();
				System.out.println("Hoy es: "+Hoy);
				System.out.println("___________________________________________________");
				ArrayList<Pedido> faena = new ArrayList<>();

		        for (Pedido pedido : daoPedido.getAllByFecha(Hoy)) {
		            faena.add(pedido);
		        }
		            for (Pedido pedido : faena) {
		            	System.out.println("-------------------");
		            	System.out.println( pedido.getIdPed()+" || "+pedido.getFecha());
						System.out.println(pedido.getCliente()+" || "+pedido.getEstado());
						System.out.println(pedido.getProducto());
						System.out.println("-------------------");
		        }

		            System.out.println("___________________________________________________");
		            pressEnter();
					limpiarPantalla();
					break;   
		            
			case 5:
				System.out.println("********* 5.- Ver carta y nueva comanda **********");
				ArrayList<Producto> carta = new ArrayList<>();

		        for (Producto producto : dao.verCarta()) {
		            carta.add(producto);
		        }
		            for (Producto producto : carta) {
		            	System.out.println("-------------------");
		            	System.out.println( producto.getIdPro()+" || "+producto.getNombre());
						System.out.println(producto.getTipo()+" || "+producto.getPrecio());
						System.out.println(producto.getDisponibilidad());
						System.out.println("-------------------");
		        }

		            System.out.println("___________________________________________________");
		            pressEnter();
					limpiarPantalla();
					break;
		            
		            
		            
			case 6:
				System.out.println("********* 6.- Pedidos de un alumno ***************");
				System.out.println("Escriba el nombre del alumno que desea buscar: ");
				String clientePedido= leerPalabra();
				
				ArrayList<Pedido> historiaCliente = new ArrayList<>();
				
		        for (Pedido pedido : daoPedido.getCliente(clientePedido)) {
		            historiaCliente.add(pedido);
		        }
		            for (Pedido pedido : historiaCliente) {
		            	System.out.println("-------------------");
		            	System.out.println( pedido.getIdPed()+" || "+pedido.getFecha());
						System.out.println(pedido.getCliente()+" || "+pedido.getEstado());
						System.out.println(pedido.getProducto());
						System.out.println("-------------------");
		        }

		            System.out.println("___________________________________________________");
		            pressEnter();
					limpiarPantalla();
					break;
		            
		            
		            
			case 7:
				System.out.println("********* 7.- Historico y estadistica ************");
				
				daoPedido.cuenta();
		        
				pressEnter();
				limpiarPantalla();
				break;
		          
			case 8:
				System.out.println("Fin del Programa");
				break;
			default:
				System.out.println("Opcion Incorrecta");
				pressEnter();
		            
			}
			
			
			
			
			
			
			}
        
        
        
        
        
        System.out.println(p.verProducto(1).getNombre()+" "+p.verProducto(1).getPrecio());
        
        ArrayList<Producto> carta = new ArrayList<>();
        
      
        for (Producto producto : dao.verCarta()) {
            System.out.println(producto.toString());
            carta.add(producto);
        }
        
        System.out.println("-------------------");
            for (Producto producto : carta) {
                System.out.println(producto);
        }
            
          
        Producto p1 = new Producto();
        p1.setNombre("Sandwich de pavo");
        p1.setTipo("Integral");
        p1.setPrecio(5);
        p1.setDisponibilidad("SI");
        
        dao.añadir(p1);
        p1.setPrecio(50);
        p1.setDisponibilidad("AGOTADO");
        dao.update(p1);
        
        
        
        
        
        
        
        
        
    }


    public static void imprimirMenu() {
		System.out.println("**************************************************");
		System.out.println("**** Aplicacion CRUD para gestion de comandas ****");
		System.out.println("**************************************************");
		System.out.println("********* 1.- Nuevo pedido ***********************");
		System.out.println("********* 2.- Eliminar pedido ********************");
		System.out.println("********* 3.- Marcar un pedido como recogido *****");
		System.out.println("********* 4.- Ver comandas pendientes de hoy *****");
		System.out.println("********* 5.- Ver carta y nueva comanda **********");
		System.out.println("********* 6.- Pedidos de un alumno ***************");
		System.out.println("********* 7.- Historico y estadistica ************");
		System.out.println("********* 8.- Salir ******************************");
		System.out.println("**************************************************");
		System.out.print("Introduce una opción: ");
	}

    public static int leerNumero() {
		Scanner sc = new Scanner(System.in);
		int numero=sc.nextInt();
		String Quitar_retorno=sc.nextLine();
		return numero; 
	}


    
    
    
	public static void limpiarPantalla() {
		//Rellena el contenido. No modifiques la cabecera.
		for(int i=0;i<60;i++) {
			System.out.println("");
		}
	}
	
	public static String leerPalabra() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	
	public static void pressEnter()
	{ 
		System.out.println("Presiona Enter para continuar.");
		try
		{
			System.in.read();
		}  
		catch(Exception e)
		{}  
	}
}




