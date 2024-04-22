package app;
import java.util.Scanner;
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
}
