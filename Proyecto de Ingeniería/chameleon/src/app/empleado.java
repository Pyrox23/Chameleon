package app;
import java.util.ArrayList;

public class empleado extends usuario {
	//Atributos protected (propios)
	protected ArrayList<producto> ventas;

	
	//Constructor 1
	public empleado(String id, String contraseña, String nombre) {  //heredado 
		super(id, contraseña, nombre); //heredado
		this.ventas = new ArrayList<producto>();
	}

	public ArrayList<producto> getVentasDia() {
		return ventas;
	}
	
}
