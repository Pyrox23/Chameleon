package app;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class administrador extends gerente {

	// Atributos
	// No tiene propios, solo heredados

	// Constructor 1
	public administrador(String id, String contraseña, String nombre, String apellido) {
		super(id, contraseña, nombre, apellido);
	}

	// Método para agregar un nuevo usuario
	public void agregarNuevoUsuario(Scanner sin) {
		usuario u;
		ArrayList<usuario> usuarios = new ArrayList<usuario>();
		String r[] = new String[5];
		// Datos para el nuevo usuario
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
		for (usuario usuario : listaUsuarios) {
			if (usuario.getId().equals(idUsuario)) {
				listaUsuarios.remove(usuario);
				System.out.println("¡Usuario eliminado correctamente!");
				gf.EscribirUsuarios(listaUsuarios);
			}
		}
		System.out.println("¡No se encontró ningún usuario con el ID especificado!");

	}

	// Modificar inventario
	public void modificarInventario(Scanner sin) {
		// Lectura del fichero e impresión por pantalla
		File rInventario = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Inventario.csv");
		Menus.mdificarInvMenu();
		GestorDatosFichero gf = new GestorDatosFichero();
		ArrayList<producto> p = gf.lecturaFichero(rInventario);
		System.out.println();

		// Bucle para modificar producto
		boolean seguir = true;
		int posicionProducto = 0;
		String cambio = "";
		String nombre = "";
		String opcion = "";

		do {
			boolean encontrado = false;

			for (int i = 0; i < p.size(); i++) {
				System.out.println(p.get(i));
			}

			System.out.print("\n Indique el producto a modificar: ");
			opcion = sin.nextLine();

			// Comprobar que el producto existe
			for (int i = 0; i < p.size(); i++) {
				if ((p.get(i).getNombre()).equalsIgnoreCase(opcion)) {
					posicionProducto = i;
					encontrado = true;
				}
			}

			if (encontrado == true) {
				System.out.println(
						" \n * Indique el cambio a realizar: \n \t a)Cambiar nombre \n \t b)Cambiar descripción \n \t c)Cambiar cantidad \n \t d)Cambiar PPU \n \t e)Cambiar PDV");
				cambio = sin.nextLine();

				// Switchcito de cambios
				nombre = p.get(posicionProducto).getNombre();
				switch (cambio.toLowerCase()) {
					case "a":
						System.out.print("\n Ingrese el nuevo nombre de " + nombre + ": ");
						p.get(posicionProducto).setNombre(sin.nextLine().toUpperCase());
						break;

					case "b":
						System.out.print("\n Ingrese la nueva descripción de " + nombre + ": ");
						p.get(posicionProducto).setDescripcion(sin.nextLine().toUpperCase());
						break;

					case "c":
						System.out.print("\n Ingrese la nueva cantidad de " + nombre + ": ");
						p.get(posicionProducto).setCantidad(sin.nextInt());
						break;

					case "d":
						System.out.print("\n Ingrese el nuevo PPU de " + nombre + ": ");
						p.get(posicionProducto).setPpu(sin.nextDouble());
						break;

					case "e":
						System.out.print("\n Ingrese el nuevo PDV de " + nombre + ": ");
						p.get(posicionProducto).setPdv(sin.nextDouble());
						break;

					default:
						break;
				}

				// Actualizar inventario
				gf.escribirFichero(rInventario, p, false);

				System.out.println("\n\\n******* Cambios realizados correctamente! ******* \n");

				// Salir del bucle
				System.out.print("\n ¿Desea modificar otro producto?: ");
				if (sin.nextLine().equalsIgnoreCase("No")) {
					seguir = false;
				}
			}
			
		} while (seguir);

	}
}
