package app;
import java.io.Serializable;

public class administrador extends gerente implements Serializable{

	//Atributos
	//No tiene propios, solo heredados

	//Constructor 1
	public administrador(String id, String contraseña, String nombre) {
		super(id, contraseña, nombre);
	}
	
	// public void crearUsuario(usuario u){
	// 	gf.EscribirUsuario(u);
	// }

}
