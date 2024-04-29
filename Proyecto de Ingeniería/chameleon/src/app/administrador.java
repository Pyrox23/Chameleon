package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class administrador extends gerente {

	public administrador(String id, String contraseña, String nombre, String apellido) {
		super(id, contraseña, nombre, apellido);
	}

	
	public void agregarNuevoUsuario(Scanner sin) {
		usuario u;
		ArrayList<usuario> usuarios = new ArrayList<usuario>();
		String r[] = new String[5];

		System.out.print("\nEscribe el id del nuevo usuario: ");
		r[0] = sin.nextLine();
		System.out.print("Escribe la contraseña: ");
		r[1] = sin.nextLine();
		System.out.print("Nombre del usuario: ");
		r[2] = sin.nextLine();
		System.out.print("Apellido del usuario: ");
		r[3] = sin.nextLine();
		System.out.print("Perfil del usuario: ");
		r[4] = sin.nextLine();

		if (r[4].equalsIgnoreCase("administrador"))
			u = new administrador(r[0], r[1], r[2], r[3]);
		else if (r[4].equalsIgnoreCase("gerente"))
			u = new gerente(r[0], r[1], r[2], r[3]);
		else if (r[4].equalsIgnoreCase("empleado"))
			u = new empleado(r[0], r[1], r[2], r[3]);
		else {
			System.out.println("El perfil indicado no existe y el usuario fue asignado a empleado.");
			u = new empleado(r[0], r[1], r[2], r[3]);
		}
		usuarios = gf.ListaUsuarios();
		usuarios.add(u);
		gf.EscribirUsuarios(usuarios);
	}

	public void eliminarUsuario(String idUsuario) {
		ArrayList<usuario> listaUsuarios = gf.ListaUsuarios();
		int i = 0;
		for (; i<listaUsuarios.size()&&!listaUsuarios.get(i).getId().equals(idUsuario); i++) {
		}
		if(i != listaUsuarios.size()){
			listaUsuarios.remove(i);
			System.out.println("¡Usuario eliminado correctamente!");
			gf.EscribirUsuarios(listaUsuarios);
		}
		else
			System.out.println("¡No se encontró ningún usuario con el ID especificado!");

	}

	public void gestionarVentas(Scanner sin, ArrayList<producto> productos, File registro) { //No encuentra nada en el inventario, corregir
		boolean seguir = true;
		int i = 0, opcion = 0, cantCambio = 0, cantidadVenta = 0, cantInventario = 0;
		String cambio = "";
		String nombre = "";
		this.ventas = gf.lecturaFicheroVenta(registro);

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
					System.out.println(" \n Indique el cambio a realizar: \n a) Cambiar cantidad \n b) Eliminar \n c) Cancelar");
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
						case "c":
							System.out.print("\n Cancelando Operación..");
							seguir = false;
							break;
						default:
							System.out.println("Opcion no valida, intente de nuevo.");
							break;
					}
					if (!cambio.equalsIgnoreCase("c")) {
						gf.escribirFicheroVenta(registro, this.ventas, false);
						for (i = 0; i < productos.size() && !productos.get(i).getNombre().equals(nombre); i++) {}
						if (i != productos.size()) {
							cantInventario = productos.get(i).getCantidad();
							productos.get(i).setCantidad(cantInventario + cantidadVenta - cantCambio);
						} else {
							System.out.println("El inventario no pudo ser manejado de manera automática debido a un error al buscar el producto " + nombre + " en el inventario.");
						}
					}
					if (seguir && !cambio.equalsIgnoreCase("c")) {
						System.out.print("\n ¿Desea modificar otra venta? (S/N): ");
						if (!sin.next().equalsIgnoreCase("s")) {
							seguir = false;
						}
					}
				}
					
			else
				System.out.println("Indique un producto dentro del rango de numeros mostrado.");
			} while (seguir);
		}
	}
}
