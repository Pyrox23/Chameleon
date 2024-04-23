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
	

	public void gestionarVentas(Scanner sin, ArrayList<producto> productos) {
		boolean seguir = true;
		int i = 0, opcion = 0, cantCambio = 0, cantidadVenta = 0, cantInventario = 0;
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
				check = (opcion<ventas.size()) && (opcion>=0);
				sin.nextLine();
				if (check) {
					System.out.println(" \n Indique el cambio a realizar: \n a) Cambiar cantidad \n b) Eliminar");
					cambio = sin.nextLine();
					nombre = ventas.get(opcion).getNombre();
					cantidadVenta = ventas.get(opcion).getCantidad();
					switch (cambio.toLowerCase()) {
						case "a":
							System.out.print("\n Ingrese la nueva cantidad de " + nombre + ": ");
							cantCambio = sin.nextInt();
							ventas.get(opcion).setCantidad(cantCambio);
							break;

						case "b":
							System.out.print("\n Eliminando " + nombre + " de ventas... ");
							cantCambio = cantidadVenta;
							ventas.remove(opcion);
							break;

						default:
							System.out.println("Opcion no valida, intente de nuevo.");
							break;
					}
					for(i = 0; i<productos.size()&&productos.get(i).getNombre()!=nombre; i++){
					}
					cantInventario = productos.get(i).getCantidad();
					productos.get(i).setCantidad(cantInventario + cantidadVenta - cantCambio);
				}
				System.out.print("\n ¿Desea modificar otra venta? (S/N): ");
				if (!sin.next().equalsIgnoreCase("s")) {
					seguir = false;
				}
			} while (seguir);
		}
	}

}
