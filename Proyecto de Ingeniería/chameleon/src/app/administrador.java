package app;
import java.util.Scanner;
import java.util.ArrayList;

public class administrador extends gerente {

	public administrador(String id, String contraseña, String nombre, String apellido) {
		super(id, contraseña, nombre, apellido);
	}
	
	public void agregarNuevoUsuario(Scanner sin) {
		usuario u; // Variable para almacenar el nuevo usuario
		ArrayList<usuario> usuarios = new ArrayList<usuario>(); // Lista para almacenar todos los usuarios
		String r[] = new String[5]; // cada dato esta separado por (;) y esos datos se guardan en el array

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
		usuarios = gf.ListaUsuarios(); 		 // obtiene la lista actual de usuarios
		usuarios.add(u);        		    // agrega el nuevo usuario a la lista de usuarios
		gf.EscribirUsuarios(usuarios);	   // escribe la lista actualizada de usuarios en el archivo binario
	}

	//Método para eliminar un usuario de la lista y actualizar el archivo binario
	public void eliminarUsuario(String idUsuario) {
		ArrayList<usuario> listaUsuarios = gf.ListaUsuarios(); // lista de usuarios del archivo bin
		int i = 0;
		//Buscar el usuario con el ID especificado en la lista
		for (; i<listaUsuarios.size()&&!listaUsuarios.get(i).getId().equals(idUsuario); i++) { //mientras no se alcanzo el final de la lista
		} 																	//y el ID del usuario en la posición actual no coincida con el ID especificado
		//Verificar si se encontró el usuario con el ID especificado
		if(i != listaUsuarios.size()){      // valor de i es diferente al tamaño de la lista de usuario
			listaUsuarios.remove(i);       // elimina el usuario de la lista
			System.out.println("¡Usuario eliminado correctamente!");
			gf.EscribirUsuarios(listaUsuarios);  // actualiza el archivo binario con la lista de usuarios actualizada
		}
		else
			System.out.println("¡No se encontró ningún usuario con el ID especificado!");

	}
}
