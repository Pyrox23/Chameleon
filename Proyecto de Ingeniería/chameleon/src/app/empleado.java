package app;
import java.io.Serializable;
import java.util.ArrayList;

public class empleado extends usuario implements Serializable{
	//Atributos protected (propios)
	protected ArrayList<producto> ventas;
	protected GestorDatosFichero gf = new GestorDatosFichero();

	
	//Constructor 1
	public empleado(String id, String contraseña, String nombre) {  //heredado 
		super(id, contraseña, nombre); //heredado
		this.ventas = new ArrayList<producto>();
	}

	public ArrayList<producto> getVentasDia() {
		return ventas;
	}
	
	public void registrarVentas(){

	}

}
