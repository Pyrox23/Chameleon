package app;
import java.util.Scanner;
import java.util.ArrayList;

public class administrador extends gerente {

	//Atributos
	//No tiene propios, solo heredados

	//Constructor 1
	public administrador(String id, String contraseña, String nombre) {
		super(id, contraseña, nombre);
	}
	
	// Método para agregar un nuevo usuario
	public void agregarNuevoUsuario(Scanner sin) {
		usuario u;
		ArrayList<usuario> usuarios = new ArrayList<usuario>();
		String r[] = new String[4];
    	// Datos para el nuevo usuario
    	System.out.print("\nEscribe el id del nuevo usuario: ");
   		r[0] = sin.nextLine();
    	System.out.print("Escribe la contraseña: ");
    	r[1] = sin.nextLine();
    	System.out.print("Nombre del usuario: ");
    	r[2] = sin.nextLine();
    	System.out.print("Perfil del usuario: ");
    	r[3] = sin.nextLine();

		//se crea un nuevo usuario con los datos ingresados (depende del perfil admin,gerent,emple)
		if(r[3].equalsIgnoreCase("administrador"))
			u = new administrador(r[0], r[1], r[2]);
		else if(r[3].equalsIgnoreCase("gerente"))
			u = new gerente(r[0], r[1], r[2]);
		else if(r[3].equalsIgnoreCase("empleado"))
			u = new empleado(r[0], r[1], r[2]);
		else{
			System.out.println("El perfil indicado no existe y el usuario fue asignado a empleado.");
			u = new empleado(r[0],r[1],r[2]); //en caso no exsita, automaticamente será de perfil empleado
		}
    	usuarios = gf.ListaUsuarios();
		usuarios.add(u);
		gf.EscribirUsuarios(usuarios);
	}

}
