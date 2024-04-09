package app;
import java.io.Serializable;

public class administrador extends gerente implements Serializable{

	//Atributos
	//No tiene propios, solo heredados

	//Constructor 1
	public administrador(String nombre, String usuario, producto[] ventasDia, String id, String contraseña) {
		super(nombre, usuario, ventasDia, id, contraseña);
		
	}
	
}
