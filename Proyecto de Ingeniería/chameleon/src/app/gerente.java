package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Collections;
import java.io.*;

public class gerente extends empleado {

	public gerente(String id, String contraseña, String nombre, String apellido) {  
		super(id, contraseña, nombre, apellido); 
	}
	
	public void agregarProductoAlInventario(Scanner sin, ArrayList<producto> prodInventario, File rInventario) {
		//Variables para almacenar los datos del nuevo producto
    	double pdv, ppu;
    	int cant;
		String r[] = new String[6]; //arreglo para almacenar temporalmente los datos del producto (;)
		boolean check;
       try{ 
		   //Leer el último ID de producto del archivo de inventario para asignar el siguiente ID disponible
           Scanner s = new Scanner(rInventario, "UTF-8");
           while (s.hasNextLine())
               r = s.nextLine().split(";");
			producto.setSigId(Integer.parseInt(r[0])+1); //establece el siguiente ID disponible
           s.close();
        } catch(IOException ex){ 
			ex.printStackTrace();
        } catch(NumberFormatException e){
		}
    	do {
			Menus.mostrarIngresarProducto();
			try{
				System.out.print("Nombre del producto: ");
				r[0] = sin.nextLine().trim();
				System.out.print("Descripcion del producto: ");
				r[1] = sin.nextLine().trim();
				System.out.print("Cantidad disponible: ");
				cant = sin.nextInt();
				System.out.print("Precio de Unidad: ");
				ppu = sin.nextDouble();
				System.out.print("Precio de Venta: ");
				pdv = sin.nextDouble();
				check = r[0].equals("") || r[1].equals("");
				if(!check)
					prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv));
				else
					System.out.println("Los datos vacios no son aceptados.");
			} catch(InputMismatchException e){
				System.out.println("Por favor, ingrese un número entero válido.");
			}
			System.out.println("Desea añadir otro producto? \nPresione 's' para añadir otro producto");
			sin.nextLine();
			r[0] = sin.nextLine().trim();
   	 	} while (r[0].equalsIgnoreCase("s"));

    	gf.escribirFichero(rInventario, prodInventario, false);  //escribir el inventario actualizado en el archivo de inventario
	}

	
	public void modificarInventario(Scanner sin, File registro) {
		GestorDatosFichero gf = new GestorDatosFichero();
		ArrayList<producto> p = gf.lecturaFicheroInv(registro);

		boolean seguir = true;
		int i = 0, opcion = 0;
		String cambio = "";
		String nombre = "";

		if (p.isEmpty()){
			System.out.println("El registro de inventario esta vacio. Sera redirigido a agregar un producto al inventario.");
			this.agregarProductoAlInventario(sin, p, registro);
		}
		else {
			do {
				boolean check = false;
				for (i = 0; i < p.size(); i++)
					System.out.println(i + ". " + p.get(i).toStringInventario());
				System.out.print("\n Indique el producto a modificar: ");
				opcion = sin.nextInt();
				check = (opcion < p.size()) && (opcion >= 0);
				sin.nextLine();

				if (check) {
					System.out.println(" \nIndique el cambio a realizar: \n \t a)Cambiar nombre \n \t b)Cambiar descripción \n \t c)Cambiar cantidad \n \t d)Cambiar PPU (Precio por Unidad) \n \t e)Cambiar PDV (Precio de Venta) \n \t f)Eliminar producto \n \t g)Cancelar");
					cambio = sin.nextLine().trim();
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
							System.out.print("\n Eliminando" + nombre + " del registro... ");
							p.remove(opcion);
							break;
						
						case "g":
							System.out.print("\n Cancelando..");
							seguir = false;
							break;

						default:
							System.out.println("Opcion no valida, intente de nuevo.");
							break;
					}

					if (seguir && !cambio.equalsIgnoreCase("g")) {
						gf.escribirFichero(registro, p, false);
	
						System.out.println("\n******* Cambios realizados correctamente! ******* \n");
	
						System.out.print("\n ¿Desea modificar otro producto? (S/N): ");
						if (!(sin.nextLine().trim().equalsIgnoreCase("S"))) {
							seguir = false;
						}
						
					}
				}
				else
					System.out.println("Indique un valor numerico valido.");
			
			} while (seguir);
		}

	}

	public String productoMasVendido(File ficheroVenta, File ficheroInv) {
		ArrayList<producto> prod = gf.lecturaFicheroVenta(ficheroVenta); // lista de ventas del archivo
		ArrayList<producto> inventario = gf
				.lecturaFicheroInv(ficheroInv);
		ArrayList<producto> x = new ArrayList<>();

		// Agregar todas las opciones del inventario
		for (producto p : inventario) {
			x.add(new producto(p.getNombre(), 0));
		}

		// Agregar cantidades
		for (int i = 0; i < x.size(); i++) {
			producto actual = x.get(i);
			for (int j = 0; j < prod.size(); j++) {
				if (prod.get(j).getNombre().equals(actual.getNombre())) {
					actual.setCantidad(actual.getCantidad() + prod.get(j).getCantidad());
				}
			}
		}
		
		// Sortear de mayor a menor cantidad
		Collections.sort(x);

		String masVendido = x.get(0).getNombre() + ": " + x.get(0).getCantidad(); // Como ya están ordenadas, simplemente se manda el primer index
		return masVendido;
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
