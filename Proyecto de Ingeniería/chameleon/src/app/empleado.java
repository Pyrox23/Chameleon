package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class empleado extends usuario {
	protected ArrayList<producto> ventas;

	public empleado(String id, String contraseña, String nombre, String apellido) {  //heredado 
		super(id, contraseña, nombre, apellido); //heredado
		this.ventas = new ArrayList<producto>();
	}

	public ArrayList<producto> getVentas() {
		return ventas;
	}

	public void imprimirVentas(){
		//Verifica si la lista de ventas está vacía
		if(this.ventas.isEmpty())
			System.out.println("No hay ventas realizadas.");
		//Itera sobre la lista de ventas e imprimir cada venta
		for(producto x : this.ventas){
			System.out.println(x.toStringVenta());
		}
	}

    public void agregarVenta(Scanner sin, ArrayList<producto> inventario) {
		String nombreProducto;
		int cantidadVendida;
		producto p;
		boolean exit = true;
		System.out.println("Ingrese los detalles de la venta:");
		System.out.print("Nombre del producto: ");
		try{
			nombreProducto = sin.nextLine().trim();
			System.out.print("Cantidad vendida: ");
			cantidadVendida = sin.nextInt();
			if(cantidadVendida > 0){
				for (int i = 0; i < inventario.size() && exit; i++) {
					p = inventario.get(i);
					if (p.getNombre().equalsIgnoreCase(nombreProducto) && p.getCantidad() >= cantidadVendida) {
						this.ventas.add(new producto(p.getNombre(), cantidadVendida, p.getPdv(), p.getId()));
						p.setCantidad(p.getCantidad() - cantidadVendida);
						inventario.set(i, p);
						System.out.println("Venta agregada correctamente");
						exit = false;
					}
				}
				if (exit) {
					System.out.println("El producto no está disponible en el inventario");
				}
			}
			else
				System.out.println("No se aceptan ventas con cantidades negativas o con 0");
		} catch(InputMismatchException e){
			System.out.println("Por favor, ingrese un número entero válido.");
		}

	}
	

	public void gestionarVentas(Scanner sin, ArrayList<producto> productos) {
		boolean seguir = true;
		int i = 0, opcion = 0, cantCambio = 0, cantidadVenta = 0, cantInventario = 0, numeroValido;
		String cambio = "";
		String nombre = "";

		if (ventas.isEmpty())
			System.out.println("No hay ventas registradas");
		else {
			do {
				boolean check = false;
				System.out.println("\n ----- VENTAS ACTUALES: -----");
				for (i = 0; i < ventas.size(); i++) {
					System.out.println(i + ". " + ventas.get(i).toStringVenta());
				}
				
				System.out.print("\n Indique el producto a modificar: ");
				opcion = sin.nextInt();
				check = (opcion<ventas.size()) && (opcion>=0);
				sin.nextLine();
				if (check) {
					System.out.println("\nIndique el cambio a realizar:\na) Cambiar cantidad\nb) Eliminar\nc) Cancelar");
					cambio = sin.nextLine().trim();
					nombre = ventas.get(opcion).getNombre();
					cantidadVenta = ventas.get(opcion).getCantidad();
					switch (cambio.toLowerCase()) {
						case "a":
							System.out.println("Indique que operacion quiere hacer:\na) Sumar cantidad\nb) Restar cantidad\nc) Introducir nueva cantidad\nd) Cancelar");
							cambio = sin.nextLine().trim();
							switch(cambio.toLowerCase()){
								case "a":
									System.out.print("\nIngrese la cantidad a sumar para la venta de " + nombre + ": ");
									cantCambio = sin.nextInt();
									sin.nextLine();
									if(cantCambio > 0){
										for(i = 0; i<productos.size()&&!productos.get(i).getNombre().equalsIgnoreCase(nombre); i++);
										if(i != productos.size()){
										cantInventario = productos.get(i).getCantidad();
										numeroValido = cantInventario - cantCambio;
										if(numeroValido > 0 && cantidadVenta + cantCambio > 0){
											productos.get(i).setCantidad(numeroValido);
											ventas.get(opcion).setCantidad(cantidadVenta + cantCambio);
										}
										else
											System.out.println("La cantidad indicada no es posible.");
										}
									}
									else
										System.out.println("No se aceptan valores negativos o 0");
									break;
								case "b":
									System.out.print("\nIngrese la cantidad a restar para la venta de " + nombre + ": ");
									cantCambio = sin.nextInt();
									sin.nextLine();
									if(cantCambio > 0){
										for(i = 0; i<productos.size()&&!productos.get(i).getNombre().equalsIgnoreCase(nombre); i++);
										if(i != productos.size()){
										cantInventario = productos.get(i).getCantidad();
										numeroValido = cantInventario + cantCambio;
										if(numeroValido > 0 && cantidadVenta - cantCambio > 0){
											productos.get(i).setCantidad(numeroValido);
											ventas.get(opcion).setCantidad(cantidadVenta - cantCambio);
										}
										else
											System.out.println("La cantidad indicada no es posible.");
										}
									}
									else
										System.out.println("No se aceptan valores negativos o 0");
									break;
								case "c":
									System.out.print("\nIngrese la nueva cantidad de " + nombre + ": ");
									cantCambio = sin.nextInt();
									sin.nextLine();
									if(cantCambio > 0){
										for(i = 0; i<productos.size()&&!productos.get(i).getNombre().equalsIgnoreCase(nombre); i++);
										if(i != productos.size()){
										cantInventario = productos.get(i).getCantidad();
										numeroValido = cantInventario + cantidadVenta - cantCambio;
										if(numeroValido > 0){
											productos.get(i).setCantidad(numeroValido);
											ventas.get(opcion).setCantidad(cantCambio);
										}
										else
											System.out.println("La cantidad indicada no es posible.");
										}
									}
									else
										System.out.println("No se aceptan valores negativos o 0");
									break;
								case "d":
									System.out.println("Cancelando cambio...");
									break;
								default:
									System.out.println("Opcion no valida, intente de nuevo.");
									break;
							}
							break;
						case "b":
							System.out.print("\nEliminando " + nombre + " de ventas... ");
							for(i = 0; i<productos.size()&&!productos.get(i).getNombre().equalsIgnoreCase(nombre); i++);
							if(i != productos.size()){
								cantInventario = productos.get(i).getCantidad();
								productos.get(i).setCantidad(cantInventario + cantidadVenta);
								ventas.remove(opcion);
							}
							break;
						case "c":
							System.out.println("Cancelando cambio...");
							break;
						default:
							System.out.println("Opcion no valida, intente de nuevo.");
							break;
					}

					System.out.print("\n ¿Desea modificar otra venta? (S/N): ");
					seguir = sin.nextLine().trim().equalsIgnoreCase("s");
				}
			else
				System.out.println("Indique un producto dentro del rango de numeros mostrado.");
			} while (seguir);
		}
	}

	public void verProductos(ArrayList<producto> p){ // Cambito
		if(!p.isEmpty()){
			System.out.println("Nombre | Cantidad disponible");
			for(producto x : p) {
				System.out.println(x.getNombre() + " " + x.getCantidad());
			}
		}
		else
			System.out.println("El inventario esta vacio");
	}

}
