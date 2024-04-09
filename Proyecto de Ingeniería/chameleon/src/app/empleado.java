package app;
import java.io.Serializable;

public class empleado extends usuario implements Serializable{
	//Atributos protected (propios)
	protected String nombre;
	protected String usuario;
	protected producto [] ventasDia;

	
	//Constructor 1
	public empleado(String nombre, String usuario, producto[] ventasDia, 
					String id, String contraseña) {  //heredado 
		super(id, contraseña); //heredado
		this.nombre = nombre;
		this.usuario = usuario;
		this.ventasDia = ventasDia;
	}
	
}
