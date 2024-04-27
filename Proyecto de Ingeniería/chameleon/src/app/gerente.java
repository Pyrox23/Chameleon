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
        	r[0] = sin.nextLine();
        	System.out.print("Descripcion del producto: ");
        	r[1] = sin.nextLine();
        	System.out.print("Cantidad disponible: ");
        	cant = sin.nextInt();
        	System.out.print("Precio de Unidad: ");
        	ppu = sin.nextDouble();
        	System.out.print("Precio de Venta: ");
        	pdv = sin.nextDouble();

        	prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv)); 
			
        	System.out.println("Desea añadir otro producto? \nPresione 's' para añadir otro producto");
			sin.next();
        	r[0] = sin.nextLine();

   	 	} while (r[0].equalsIgnoreCase("s"));
    	gf.escribirFichero(rInventario, prodInventario, false); 
	}

	
	public void modificarInventario(Scanner sin, File registro) {
		GestorDatosFichero gf = new GestorDatosFichero();
		ArrayList<producto> p = gf.lecturaFichero(registro);
		System.out.println();

		boolean seguir = true;
		int i = 0, opcion = 0;
		String cambio = "";
		String nombre = "";

		if (p.isEmpty()){
			System.out.println("No hay inventario registrado, sera redirigido a agregar un producto al inventario.");
			this.agregarProductoAlInventario(sin, p, registro);
		}
		else {
			do {
				boolean check = false;
				for (i = 0; i < p.size(); i++) {
					System.out.println(i + ". " + p.get(i).toStringInventario());
				}
				
				System.out.print("\n Indique el producto a modificar: ");
				opcion = sin.nextInt();
				check = (opcion < p.size()) && (opcion >= 0);
				sin.nextLine();

				if (check) {
					System.out.println(" \nIndique el cambio a realizar: \n \t a)Cambiar nombre \n \t b)Cambiar descripción \n \t c)Cambiar cantidad \n \t d)Cambiar PPU (Precio por Unidad) \n \t e)Cambiar PDV (Precio de Venta) \n \t f)Eliminar producto");
					cambio = sin.nextLine();

					nombre = p.get(opcion).getNombre();
					switch (cambio.toLowerCase()) {
						case "a":
							System.out.print("\n Ingrese el nuevo nombre de " + nombre + ": ");
							p.get(opcion).setNombre(sin.nextLine());
							break;

						case "b":
							System.out.print("\n Ingrese la nueva descripción de " + nombre + ": ");
							p.get(opcion).setDescripcion(sin.nextLine());
							break;

						case "c":
							System.out.print("\n Ingrese la nueva cantidad de " + nombre + ": ");
							p.get(opcion).setCantidad(sin.nextInt());
							break;

						case "d":
							System.out.print("\n Ingrese el nuevo PPU de " + nombre + ": ");
							p.get(opcion).setPpu(sin.nextDouble());
							break;

						case "e":
							System.out.print("\n Ingrese el nuevo PDV de " + nombre + ": ");
							p.get(opcion).setPdv(sin.nextDouble());
							break;
							
						case "f":
							System.out.print("\n Eliminando" + nombre + " de ventas... ");
							p.remove(opcion);
							break;

						default:
							break;
					}

					gf.escribirFichero(registro, p, false);

					System.out.println("\n******* Cambios realizados correctamente! ******* \n");

					System.out.print("\n ¿Desea modificar otro producto? (S/N): ");
					if (!(sin.nextLine().equalsIgnoreCase("S"))) {
						seguir = false;
					}
				}
				else
					System.out.println("Indique un valor numerico valido.");
			
			} while (seguir);
		}

	}

	public String productoMasVendido(File fichero){
		ArrayList<producto> prod = gf.lecturaFicheroVenta(fichero);
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
		ArrayList<producto> prod = gf.lecturaFicheroVenta(fichero);
		double total = 0.0;
		for(producto x : prod)
			total += x.getPdv()*x.getCantidad();
		return total;
	}

	public int productosVendidos(File fichero){
		ArrayList<producto> prod = gf.lecturaFicheroVenta(fichero);
		int cantidad = 0;
		for(int i = 0; i<prod.size(); i++)
			cantidad += prod.get(i).getCantidad();
		return cantidad;
	}

}
