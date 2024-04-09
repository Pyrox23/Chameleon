package app;
import java.io.Serializable;

public class gerente extends empleado implements Serializable{

	//protected (para q el padre de gerente e hijo de gerente puedas acceder)

	//Atributos
	//No tiene propios porque hereda; Duda solo hay un registro de datos o creo otro para el gerente ???

	//Constructor 1
	public gerente(String nombre, String id, String contraseña) {  //heredado 
		super(nombre, id, contraseña); //heredado
	}
	
}
