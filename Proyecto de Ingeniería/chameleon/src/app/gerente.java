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
		boolean check = true;
		String error = null;

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

				// Chequeos:
				// Producto vacío
				check = r[0].equals("") || r[1].equals("");
				error = check ? "No se pueden agregar productos con nombre y descripcion vacíos" : null;
				if(!check){
				// Producto repetido
					for (int i = 0; i < prodInventario.size() && !check; i++) {
							check = prodInventario.get(i).getNombre().equalsIgnoreCase(r[0]);
							error = check ? "El producto con este nombre seleccionado ya existe" : null;
					}
				}
				// Datos negativos
				if(!check){
					check = cant < 0 || ppu < 0 || pdv < 0;
					error = check ? "Los números negativos o 0 no son válidos" : null;
				}

				if(!check)
					prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv));
				else
					System.out.println(error);
			} catch(InputMismatchException e){
				System.out.println("Por favor, ingrese un número entero válido.");
			}
			

			System.out.println("Desea añadir otro producto? \nPresione 's' para añadir otro producto\nPulse cualquier otro boton para salir");
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
		String cambio = "", nombre = "", descripcion = "";

		do {
			boolean check = false;
			String error = null;
			int cant;
			double pdv, ppu;
			for (i = 0; i < p.size(); i++)
				System.out.println(i + ". " + p.get(i).toStringInventario());
			if (p.isEmpty()){
				System.out.println("El registro de inventario esta vacio. Sera redirigido a agregar un producto al inventario.");
				producto.setSigId(0);
				this.agregarProductoAlInventario(sin, p, registro);
			}
			else {
				System.out.print("\n Indique el producto a modificar: ");
				opcion = sin.nextInt();
				check = (opcion < p.size()) && (opcion >= 0);
				sin.nextLine();

				if (check) {
					System.out.println("\nIndique el cambio a realizar:\n\ta)Cambiar nombre\n\tb)Cambiar descripción\n\tc)Cambiar cantidad\n\td)Cambiar PPU (Precio por Unidad)\n\te)Cambiar PDV (Precio de Venta)\n\tf)Eliminar producto\n\tg)Cancelar");
					cambio = sin.nextLine().trim();
					nombre = p.get(opcion).getNombre();
					switch (cambio.toLowerCase()) {
						case "a":
							System.out.print("\nIngrese el nuevo nombre de " + nombre + ": ");
							nombre = sin.nextLine();
							check = !nombre.equals("");
							error = !check ? "No se pueden agregar productos con nombre y descripcion vacíos" : null;
							for (int j = 0; j < p.size() && check; j++) {
								check = !p.get(j).getNombre().equals(nombre);
								error = !check ? "El producto con este nombre seleccionado ya existe" : null;
							}
							if(check)
								p.get(opcion).setNombre(nombre);
							break;

						case "b":
							System.out.print("\nIngrese la nueva descripción de " + nombre + ": ");
							descripcion = sin.nextLine();
							check = !descripcion.equals("");
							error = !check ? "No se pueden agregar productos con nombre y descripcion vacíos" : null;
							if(check)
								p.get(opcion).setDescripcion(descripcion);
							break;

						case "c":
							System.out.print("\nIngrese la nueva cantidad de " + nombre + ": ");
							cant = sin.nextInt();
							sin.nextLine();
							check = cant >= 0;
							error = !check ? "Los números negativos y 0 no son válidos" : null;
							if(check)
								p.get(opcion).setCantidad(cant);
							break;

						case "d":
							System.out.print("\nIngrese el nuevo PPU de " + nombre + ": ");
							ppu = sin.nextDouble();
							sin.nextLine();
							check = ppu > 0;
							error = !check ? "Los números negativos y 0 no son válidos" : null;
							if(check)
								p.get(opcion).setPpu(ppu);
							break;

						case "e":
							System.out.print("\nIngrese el nuevo PDV de " + nombre + ": ");
							pdv = sin.nextDouble();
							sin.nextLine();
							check = pdv > 0;
							error = !check ? "Los números negativos y 0 no son válidos" : null;
							if(check)
								p.get(opcion).setPdv(pdv);
							break;
									
						case "f":
							System.out.print("\nEliminando " + nombre + " del registro... ");
							p.remove(opcion);
							break;
							
						case "g":
							System.out.print("\nCancelando..");
							break;

						default:
							System.out.println("Opcion no valida, intente de nuevo.");
							break;
					}

					if (seguir && !cambio.equalsIgnoreCase("g") && check) {
						gf.escribirFichero(registro, p, false);
						System.out.println("\n******* Cambios realizados correctamente! ******* \n");
							
					}
					else if(error != null)
						System.out.println(error + "\n");
				}
				else
					System.out.println("Indique un valor numerico valido.");

				System.out.print("\n ¿Desea modificar otro producto? (S/N): ");
					if (!(sin.nextLine().trim().equalsIgnoreCase("S"))) 
						seguir = false;
			}
		} while (seguir);
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

		return "Producto mas vendido: " + x.get(0).getNombre() + "\nCantidad vendida: " + x.get(0).getCantidad() + " unidades";
	}

	public String totalVentas(File fichero){
		ArrayList<producto> prod = gf.lecturaFicheroVenta(fichero);
		double total = 0.0;
		for(producto x : prod)
			total += x.getPdv()*x.getCantidad();
		return "Total de ventas: " + total + "$";
	}

	public String productosVendidos(File fichero){
		ArrayList<producto> prod = gf.lecturaFicheroVenta(fichero);
		int cantidad = 0;
		for(int i = 0; i<prod.size(); i++)
			cantidad += prod.get(i).getCantidad();
		return "Productos vendidos: " + cantidad;
	}

}
