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

	public void imprimirVentas(){
		for(producto x : this.ventas){
			System.out.println(x.toStringVenta());
		}
	}

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
	

	public void gestionarRegistro(Scanner sin, ArrayList<producto> productos) {
		boolean seguir = true;
		int i = 0, opcion = 0, cantCambio = 0, cantidadVenta;
		String cambio = "";
		String nombre = "";

		if (ventas.isEmpty())
			System.out.println("No hay ventas registradas");
		else {
			do {
				boolean check = false;
				System.out.println("\n ----- VENTAS ACTUALES: -----");
				for (i = 0; i < ventas.size(); i++) {
					System.out.println(i + ". " + ventas.get(i).toStringVenta());
				}
				
				System.out.print("\n Indique el producto a modificar: ");
				opcion = sin.nextInt();
				check = opcion<ventas.size();
				sin.nextLine();
				if (check) {
					System.out.println(" \n Indique el cambio a realizar: \n a) Cambiar cantidad \n b) Eliminar");
					cambio = sin.nextLine();
					nombre = ventas.get(opcion).getNombre();
					cantidadVenta = ventas.get(opcion).getCantidad();
					switch (cambio.toLowerCase()) {
						case "a":
							System.out.print("\n Ingrese la nueva cantidad de " + nombre + ": ");
							i = sin.nextInt();
							cantCambio = cantidadVenta-i;
							ventas.get(opcion).setCantidad(i);
							break;

						case "b":
							System.out.print("\n Eliminando " + nombre + " de ventas... ");
							cantCambio = cantidadVenta;
							ventas.remove(opcion);
							break;

						default:
							break;
					}
					for(i = 0; i<productos.size()&&productos.get(i).getNombre()!=nombre; i++);
					productos.get(i).setCantidad(cantidadVenta + cantCambio);
				}
				System.out.print("\n ¿Desea modificar otra venta? (S/N): ");
				if (sin.next().equalsIgnoreCase("n")) {
					seguir = false;
				}
			} while (seguir);
		}
	}

}
