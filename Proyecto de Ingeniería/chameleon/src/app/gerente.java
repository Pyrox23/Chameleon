package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class gerente extends empleado {

	public gerente(String id, String contraseña, String nombre, String apellido) {  
		super(id, contraseña, nombre, apellido); 
	}
	
	public void agregarProductoAlInventario(Scanner sin, ArrayList<producto> prodInventario, File rInventario) {
    	double pdv, ppu;
    	int cant;
		String r[] = new String[6];
       try{ 
           Scanner s = new Scanner(rInventario, "UTF-8");
           while (s.hasNextLine())
               r = s.nextLine().split(";");
			producto.setSigId(Integer.parseInt(r[0])+1);
           s.close();
        } catch(IOException ex){ 
			ex.printStackTrace();
        } catch(NumberFormatException e){
		}
    	do {
			Menus.mostrarIngresarProducto();
        	System.out.print("Nombre del producto: ");
        	r[0] = sin.next();
        	System.out.print("Descripcion del producto: ");
        	r[1] = sin.next();
        	System.out.print("Cantidad disponible: ");
        	cant = sin.nextInt();
        	System.out.print("Precio de Unidad: ");
        	ppu = sin.nextDouble();
        	System.out.print("Precio de Venta: ");
        	pdv = sin.nextDouble();

        	prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv)); 
			
        	System.out.println("Desea añadir otro producto? \nPresione 's' para añadir otro producto");
        	r[0] = sin.next();

   	 	} while (r[0].equalsIgnoreCase("s"));
    	gf.escribirFichero(rInventario, prodInventario, true); 
	}

	
	public void modificarRegistros(Scanner sin, File registro) {
		GestorDatosFichero gf = new GestorDatosFichero();
		ArrayList<producto> p = gf.lecturaFichero(registro);
		System.out.println();

		boolean seguir = true;
		int posicionProducto = 0;
		String cambio = "";
		String nombre = "";
		String opcion = "";

		do {
			boolean encontrado = false;

			for (int i = 0; i < p.size(); i++) {
				System.out.println(p.get(i));
			}

			System.out.print("\n Indique el nombre producto a modificar: ");
			opcion = sin.nextLine();

			// Comprobar que el producto existe
			for (int i = 0; i < p.size(); i++) {
				if ((p.get(i).getNombre()).equalsIgnoreCase(opcion)) {
					posicionProducto = i;
					encontrado = true;
				}
			}

			if (encontrado == true) {
				System.out.println(" \nIndique el cambio a realizar: \n \t a)Cambiar nombre \n \t b)Cambiar descripción \n \t c)Cambiar cantidad \n \t d)Cambiar PPU (Precio por Unidad) \n \t e)Cambiar PDV (Precio de Venta) \n \t f)Eliminar producto");
				cambio = sin.nextLine();

				nombre = p.get(posicionProducto).getNombre();
				switch (cambio.toLowerCase()) {
					case "a":
						System.out.print("\n Ingrese el nuevo nombre de " + nombre + ": ");
						p.get(posicionProducto).setNombre(sin.nextLine().toUpperCase());
						break;

					case "b":
						System.out.print("\n Ingrese la nueva descripción de " + nombre + ": ");
						p.get(posicionProducto).setDescripcion(sin.nextLine().toUpperCase());
						break;

					case "c":
						System.out.print("\n Ingrese la nueva cantidad de " + nombre + ": ");
						p.get(posicionProducto).setCantidad(sin.nextInt());
						break;

					case "d":
						System.out.print("\n Ingrese el nuevo PPU de " + nombre + ": ");
						p.get(posicionProducto).setPpu(sin.nextDouble());
						break;

					case "e":
						System.out.print("\n Ingrese el nuevo PDV de " + nombre + ": ");
						p.get(posicionProducto).setPdv(sin.nextDouble());
						break;
						
					case "f":
						System.out.print("\n Eliminando" + nombre + " de ventas... ");
						p.remove(posicionProducto);
						break;

					default:
						break;
				}

				// Actualizar inventario
				gf.escribirFichero(registro, p, false);

				System.out.println("\n******* Cambios realizados correctamente! ******* \n");

				// Salir del bucle
				System.out.print("\n ¿Desea modificar otro producto? (S/N): ");
				if (sin.nextLine().equalsIgnoreCase("N")) {
					seguir = false;
				}
			}
			
		} while (seguir);

	}

	public String productoMasVendido(File fichero){
		ArrayList<producto> prod = gf.lecturaFichero(fichero);
		ArrayList<producto> contador = new ArrayList<producto>();
		String nombreFinal = "";
		int cantidadFinal = 0;
		if(!prod.isEmpty())
			contador.add(new producto(prod.get(0)));
		for(int i = 1; i<prod.size(); i++){
			for(int j = 0; j<contador.size(); j++){
				if(contador.get(j).getNombre().equals(prod.get(i).getNombre())){
					producto p = new producto(contador.get(j));
					p.setCantidad(p.getCantidad()+prod.get(i).getCantidad());
				}
				else if(j==contador.size()-1)
					contador.add(new producto(prod.get(i)));
			}
		}
		for(producto x : contador){
			if(cantidadFinal<x.getCantidad()){
				nombreFinal = x.getNombre();
				cantidadFinal = x.getCantidad();
			}
		}
		return nombreFinal;
	}

	public double totalVentas(File fichero){
		ArrayList<producto> prod = gf.lecturaFichero(fichero);
		producto p;
		double total = 0.0;
		for(int i = 0; i<prod.size(); i++){
			p = prod.get(i);
			total += p.getPdv()*p.getCantidad();
		}
		return total;
	}

	public int productosVendidos(File fichero){
		ArrayList<producto> prod = gf.lecturaFichero(fichero);
		int cantidad = 0;
		for(int i = 0; i<prod.size(); i++)
			cantidad += prod.get(i).getCantidad();
		return cantidad;
	}

}
