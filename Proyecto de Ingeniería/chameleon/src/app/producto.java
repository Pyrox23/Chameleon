package app;
import java.io.Serializable;

public class producto implements Serializable{
	private int cantidad;
	private String nombre;
	private String descripcion;
	private double ppu; //precio por unidad
	private double pdv; //precio de venta
	private int id;
	private static int sigId = 0;
	
	public producto(String nombre, String descripcion, int cantidad, double ppu, double pdv) {
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.ppu = ppu;
		this.pdv = pdv;
		this.id = sigId++;
		this.descripcion = descripcion;
	}

	public producto(String nombre, int cantidad){
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setCantidad(int disponible) {
		this.cantidad = disponible;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPpu(double ppu) {
		this.ppu = ppu;
	}

	public void setPdv(double pdv) {
		this.pdv = pdv;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static void setSigId(int id){
		sigId = id;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public double getPpu() {
		return ppu;
	}
	
	public double getPdv() {
		return pdv;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return id + ";" + nombre + ";" + descripcion + ";" + cantidad + ";" + ppu + ";" + pdv;
	}
}
