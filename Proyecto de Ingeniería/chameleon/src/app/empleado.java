package app;
import java.util.Scanner;
import java.util.ArrayList;

public class empleado extends usuario {
	protected ArrayList<producto> ventas;

	public empleado(String id, String contraseña, String nombre, String apellido) {  //heredado 
		super(id, contraseña, nombre, apellido); //heredado
		this.ventas = new ArrayList<producto>();
	}

	public ArrayList<producto> getVentas() {
		return ventas;
	}

	//Metodo para agregar una venta a la lista de ventas del día
    public void agregarVenta(Scanner sin, ArrayList<producto> inventario) {
		String nombreProducto;
		int cantidadVendida;
		producto p;
		boolean exit = true;
		System.out.println("Ingrese los detalles de la venta:");
		System.out.print("Nombre del producto: ");
		nombreProducto = sin.nextLine();
		System.out.print("Cantidad vendida: ");
		cantidadVendida = sin.nextInt();
		int ultimaPosicion = 1;

		for (int i = 0; i < inventario.size() && exit; i++) {
			p = inventario.get(i);
			if (p.getNombre().equalsIgnoreCase(nombreProducto) && p.getCantidad() >= cantidadVendida) {
				this.ventas.add(new producto(p.getNombre(), cantidadVendida, p.getPdv(), p.getId()));
				p.setCantidad(p.getCantidad() - cantidadVendida);
				inventario.set(i, p);
				System.out.println("Venta agregada correctamente");
				ultimaPosicion = i;
				// Modificar la venta
				for (int j = 0; j < ventas.size(); j++) {
					System.out.println(ventas.get(j));
				}
				System.out.print("¿Es correcta la venta?: ");

				if (sin.next().equalsIgnoreCase("No")) {
					System.out.print("Ingrese la nueva cantidad de " + ventas.get(ultimaPosicion).getNombre() + ": ");
					ventas.get(ultimaPosicion).setCantidad(sin.nextInt());

					System.out.println("***** Ventas modificada exitosamente *****");
					for (int k = 0; k < ventas.size(); k++) {
						System.out.println(ventas.get(k));
					}
				}
				exit = false;
			}
		}
		if (exit) {
			System.out.println("El producto no está disponible en el inventario");
		}

	}


  //Metodo para modificar las ventas del día
    public void mostrarVentas() {
		if (ventas.isEmpty()) {
			System.out.println("No hay ventas registradas");
		} else {
			System.out.println("Ventas actuales del empleado:");
			for (int i = 0; i < ventas.size(); i++) {
				System.out.println((i + 1) + ". " + ventas.get(i));
			}
		}
    }

}
