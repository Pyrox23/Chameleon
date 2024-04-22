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

		for (int i = 0; i < inventario.size() && exit; i++) {
			p = inventario.get(i);
			if (p.getNombre().equalsIgnoreCase(nombreProducto) && p.getCantidad() >= cantidadVendida) {
				this.ventas.add(new producto(p.getNombre(), cantidadVendida, p.getPdv(), p.getId()));
				p.setCantidad(p.getCantidad() - cantidadVendida);
				inventario.set(i, p);
				System.out.println("Venta agregada correctamente");
				exit = false;
			}
		}
		if (exit) {
			System.out.println("El producto no está disponible en el inventario");
		}

	}
	
// Modificar inventario mientras no se haya cerrado
	public void gestionarInventario(Scanner sin) {
		boolean seguir = true;
		int posicionProducto = 0;
		String cambio = "";
		String nombre = "";
		String opcion = "";

		// Bucle para modificar venta
		do {
			sin.nextLine(); // Consumir linea suelta
			boolean encontrado = false;

			// Imprimir ventas
			System.out.println("\n ----- VENTAS ACTUALES: -----");
			for (int j = 0; j < ventas.size(); j++) {
				System.out.println(ventas.get(j));
			}
			
			System.out.print("\n Indique el producto a modificar: ");
			opcion = sin.nextLine();

			// Comprobar que el producto existe
			for (int i = 0; i < ventas.size(); i++) {
				if ((ventas.get(i).getNombre()).equalsIgnoreCase(opcion)) {
					posicionProducto = i;
					encontrado = true;
				}
			}

			if (encontrado == true) {
				System.out.println(
						" \n * Indique el cambio a realizar: \n \t a)Cambiar cantidad \n \t b)Eliminar");
				cambio = sin.nextLine();

				// Switchcito de cambios
				nombre = ventas.get(posicionProducto).getNombre();
				switch (cambio.toLowerCase()) {
					case "a":
						System.out.print("\n Ingrese la nueva cantidad de " + nombre + ": ");
						ventas.get(posicionProducto).setCantidad(sin.nextInt());
						break;

					case "b":
						System.out.print("\n Eliminando " + nombre + " de ventas... ");
						ventas.remove(posicionProducto);
						break;

					default:
						break;
				}
			}

			// Salir del bucle
			System.out.print("\n ¿Desea modificar otro producto?: ");
			if (sin.next().equalsIgnoreCase("No")) {
				seguir = false;
			}

		} while (seguir);

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
