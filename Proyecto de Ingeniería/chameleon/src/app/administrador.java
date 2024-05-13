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
		boolean check = false;
		int i;
		do{
			if(check)
				System.out.println("Los datos vacios no son aceptados.");
			System.out.print("\nEscribe el id del nuevo usuario: ");
			r[0] = sin.nextLine().trim();
			System.out.print("Escribe la contraseña: ");
			r[1] = sin.nextLine().trim();
			System.out.print("Nombre del usuario: ");
			r[2] = sin.nextLine().trim();
			System.out.print("Apellido del usuario: ");
			r[3] = sin.nextLine().trim();
			System.out.print("Perfil del usuario: ");
			r[4] = sin.nextLine().trim();
			check = r[0].equals("") || r[1].equals("") || r[2].equals("") || r[3].equals("");
		} while(check);

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
		for (i = 0; i<usuarios.size()&&!usuarios.get(i).getId().equals(r[0]); i++);
		if(i == usuarios.size()){
			usuarios.add(u);
			gf.EscribirUsuarios(usuarios);
		}
		else
			System.out.println("Ya existe un usuario con el id especificado");
	}

	public void eliminarUsuario(String idUsuario) {
		ArrayList<usuario> listaUsuarios = gf.ListaUsuarios();
		if(listaUsuarios.size() != 1){
			int i = 0;
			for (; i<listaUsuarios.size()&&!listaUsuarios.get(i).getId().equals(idUsuario); i++);
			if(i != listaUsuarios.size()){
				if(!this.getId().equals(listaUsuarios.get(i).getId())){
					listaUsuarios.remove(i);
					System.out.println("Usuario eliminado correctamente");
					gf.EscribirUsuarios(listaUsuarios);
				}
			else
				System.out.println("No puedes eliminar tu propio usuario");
			}
			
			else
				System.out.println("No se encontró ningún usuario con el ID especificado");
		}
		else
			System.out.println("No es posible eliminar el unico usuario en el sistema");

	}
}
