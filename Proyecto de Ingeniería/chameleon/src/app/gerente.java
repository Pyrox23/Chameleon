package app;

public class gerente extends empleado {

	//protected (para q el padre de gerente e hijo de gerente puedas acceder)

	//Atributos
	//No tiene propios porque hereda; Duda solo hay un registro de datos o creo otro para el gerente ???

	//Constructor 1
	public gerente(String nombre, String usuario, producto[] ventasDia, String id, String contraseña) {
		super(nombre, usuario, ventasDia, id, contraseña);
		
	}
	
}
