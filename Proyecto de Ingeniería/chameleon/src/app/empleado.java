package app;
import java.io.Serializable;
import java.util.ArrayList;

public class empleado extends usuario implements Serializable{
	//Atributos protected (propios)
	protected String nombre;
	protected ArrayList<producto> ventas;

	
	//Constructor 1
	public empleado(String nombre, String id, String contraseña) {  //heredado 
		super(id, contraseña); //heredado
		this.nombre = nombre;
		this.ventas = new ArrayList<producto>();
	}


	public String getNombre() {
		return nombre;
	}


	public ArrayList<producto> getVentasDia() {
		return ventas;
	}
	
}
