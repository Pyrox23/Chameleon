package app;
import java.util.Scanner;
import java.util.ArrayList;
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

    	gf.escribirFichero(rInventario, prodInventario, false);  //escribir el inventario actualizado en el archivo de inventario
	}

	
	public void modificarRegistro(Scanner sin, File registro) {
		GestorDatosFichero gf = new GestorDatosFichero();
		ArrayList<producto> p = gf.lecturaFicheroInv(registro);

		boolean seguir = true;
		int i = 0, opcion = 0;
		String cambio = "";
		String nombre = "";

		if (p.isEmpty()){
			System.out.println("El registro especificado esta vacio. Sera redirigido a agregar un producto al inventario.");
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
						if (!(sin.next().equalsIgnoreCase("S"))) {
							seguir = false;
						}
						
					}
				}
				else
					System.out.println("Indique un valor numerico valido.");
			
			} while (seguir);
		}

	}

	public String productoMasVendido(File fichero){
		ArrayList<producto> prod = gf.lecturaFicheroVenta(fichero); // lista de ventas del archivo
		ArrayList<producto> contador = new ArrayList<producto>(); // lista para contar la cantidad de ventas de cada producto
		//Variables para almacenar el nombre y la cantidad del producto más vendido
		String nombreFinal = "";
		int cantidadFinal = 0;

		//Verificar si la lista de ventas no está vacía
		if(!prod.isEmpty())
			contador.add(new producto(prod.get(0))); // agregar el primer producto de la lista de ventas al contador

		//Itera sobre la lista de ventas para contar la cantidad de ventas de cada producto	
		for(int i = 1; i<prod.size(); i++){
			for(int j = 0; j<contador.size(); j++){
				//Verifica si el producto actual ya está en el contador
				if(contador.get(j).getNombre().equals(prod.get(i).getNombre())){
					//Si el producto ya está en el contador, incrementar la cantidad de ventas
					producto p = new producto(contador.get(j));
					p.setCantidad(p.getCantidad()+prod.get(i).getCantidad());
				}
				else if(j==contador.size()-1)
					//Si el producto no está en el contador, agregarlo al contador
					contador.add(new producto(prod.get(i)));
			}
		}
		//Itera sobre el contador para encontrar el producto más vendido
		for(producto x : contador){
			if(cantidadFinal<x.getCantidad()){
				//Actualiza el nombre y la cantidad del producto más vendido
				nombreFinal = x.getNombre();
				cantidadFinal = x.getCantidad();
			}
		}
		return nombreFinal; //Devuelve el nombre del producto más vendido
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
