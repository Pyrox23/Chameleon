package app;

public class producto {
	private int disponible;
	private String nombre;
	private String descripcion;
	private double ppu; //precio por unidad
	private double pdv; //precio de venta
	private int id;
	private static int sigId = 0;
	
	public producto(int cantidad, String nombre, double ppu, double pdv) {
		this.disponible = cantidad;
		this.nombre = nombre;
		this.ppu = ppu;
		this.pdv = pdv;
		this.id = sigId++;
		this.descripcion = "-";
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getDisponible() {
		return disponible;
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
		return id + ";" + nombre + ";" + descripcion + ";" + disponible + ";" + ppu + ";" + pdv;
	}
}
