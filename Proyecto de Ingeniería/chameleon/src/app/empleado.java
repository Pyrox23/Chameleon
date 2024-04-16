package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class empleado extends usuario {
	//Atributos protected (propios)
	protected ArrayList<producto> ventas;
	protected GestorDatosFichero gf = new GestorDatosFichero();

	
	//Constructor 1
	public empleado(String id, String contraseña, String nombre) {  //heredado 
		super(id, contraseña, nombre); //heredado
		this.ventas = new ArrayList<producto>();
	}

	public ArrayList<producto> getVentasDia() {
		return ventas;
	}

	//Metodo para verificar si el producto esta disponible en el inventario (por el nombre)
	private int verificarProductDis (String nombreProducto, ArrayList<producto> inventario) {
		producto p = new producto("Default", 0);
		for (producto x : inventario) {
			if (x.getNombre().equalsIgnoreCase(nombreProducto))
				p = x;
		}
		return p.getCantidad() == 0 ? -1 : p.getCantidad();
	}

	//Metodo para agregar una venta a la lista de ventas del día
    public void agregarVenta(Scanner sin, ArrayList<producto> ventasDia, ArrayList<producto> inventario) {
		// Obtener los detalles de la venta
		String nombreProducto;
		int cantidadVendida;

		System.out.println("Ingrese los detalles de la venta:");
    	System.out.print("Nombre del producto: ");
    	nombreProducto = sin.next();
		System.out.print("Cantidad vendida: ");
        cantidadVendida = sin.nextInt();
	
		//Verificar la disponibilidad del producto en el inventario
		int disponible = verificarProductDis(nombre, inventario);
	//Si el producto está disponible, se agrega la venta a la lista de ventas del día
		if (disponible >= cantidadVendida) {
			ventasDia.add(new producto(nombreProducto, cantidadVendida));
			System.out.println("Venta agregada correctamente");
		} else {
			System.out.println("El producto no está disponible en el inventario");
		}
	}


  //Metodo para modificar las ventas del día
    public void modificarVentas() {
		if (ventas.isEmpty()) {
			System.out.println("No hay ventas registradas");
		} else {
			System.out.println("Ventas actuales del empleado:");
			for (int i = 0; i < ventas.size(); i++) {
				System.out.println((i + 1) + ". " + ventas.get(i));
			}
		}

    }
	
	//Metodo para registrar las ventas y cerrarlo
	public void registrarVentas(File fichero){
		
	}

}
