package app;

public class gerente extends empleado {

	//protected (para q el padre de gerente e hijo de gerente puedas acceder)

	//Atributos
	//No tiene propios porque hereda; Duda solo hay un registro de datos o creo otro para el gerente ???

	//Constructor 1
	public gerente(String id, String contraseña, String nombre) {  //heredado 
		super(id, contraseña, nombre); //heredado
	}
	
}
