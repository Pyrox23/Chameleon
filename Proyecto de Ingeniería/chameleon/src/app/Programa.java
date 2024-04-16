package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;
public class Programa {
	public static void main(String[] args){
		boolean log = true; //bandera para el bucle
		String r[] = new String[4]; //array para guardar datos del usuario
		Scanner sin = new Scanner(System.in); //teclado para ingresar datos por consola
		GestorDatosFichero gf = new GestorDatosFichero(); //objeto GestorDatosFichero para la lectura y escritura de datos desde y hacia un archivo**
		ArrayList<producto> productos = new ArrayList<producto>(); //arayList para guardar productos del inventario
		ArrayList<usuario> usuarios = new ArrayList<usuario>();
		File rInventario = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Inventario.csv"); //ruta del archivo de registro de inventario
		File rVenta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Venta.csv");
		//variables para los tipos de usuario
		usuario u = null;
		empleado e = null;
		gerente g = null;
		administrador a = null;
		// gf.EscribirUsuario(new administrador("admin", "admin", "Juan"));
		// Perfil Administrador: admin, admin
		// Perfil Gerente: gerente, 123
		// Perfil Empleado: empleado, 123
		boolean continuarEjecucion = true;
		int opcion;
		do {
			do {
				Menus.mostrarMenuInicial(); //Mostrar el menú inicial

				/*USANDO TRY CATH PARA EL MAJEO DE EXPECIONES  */
				try {
					System.out.print("Ingrese una opción: ");
					opcion = sin.nextInt(); // Intentar leer un entero
					sin.nextLine(); // Consumir el salto de línea

				switch (opcion) {
					case 1:
						//Bucle de inicio de sesión
						do {
							//Lógica de inicio de sesión
							if (!log) {
								System.out.println("\nEl usuario indicado es incorrecto, intenta de nuevo.");
							}
							Menus.mostrarIngresarSesion();
							System.out.print("\nIntroduce tu usuario: ");
							r[0] = sin.nextLine();
							System.out.print("Introduce tu contraseña: ");
							r[1] = sin.nextLine();

							//Lógica de verificación de inicio de sesión
							u = new usuario(r[0], r[1], "");
							u = u.login();
							log = (u != null);
						} while (!log);
						break;
					case 2:
						System.out.println("Saliendo del programa...");
						continuarEjecucion = false;
						break;
					default:
						System.out.println("Opción no válida. Intente de nuevo.");
						break;
				}

			} catch (InputMismatchException ex) { //excepción si el usuario ingresa un valor que no es un número entero
				System.out.println("Por favor, ingrese un número entero válido.");
				sin.nextLine(); //Limpia el buffer de entrada
			}
			} while (!log && continuarEjecucion);
			
			//Se asigna el tipo de usuario correspondiente según el tipo de objeto "u"
			//instanceof signifca un objeto creado a partir de esa clase o sus hijas de esta, con el costructor 
			// Se asigna el tipo de usuario correspondiente según el tipo de objeto "u"
			if (u instanceof administrador && continuarEjecucion) {
				// en caso lo sea, se hace un "casting"(convertir) para q "u" sea tratada como un obj del tipo "administrador"
				a = (administrador) u;  // y con ello tenga acceso a sus metodos y propiedades de esa clase
				boolean continuarEjecucionAdmin = true;
				int opcionAdmin;
				do {
					Menus.mostrarMenuAdministrador(); // Mostrar el menú del gerente
					System.out.print("Ingrese una opción: ");
					opcionAdmin = sin.nextInt(); // Leer la opción del gerente
					sin.nextLine(); // Consumir el salto de línea

					switch (opcionAdmin) {
						case 1:
							Menus.mostrarIngresarNuevUsuario();
							a.agregarNuevoUsuario(sin);
							break;
						case 2:
							usuarios = gf.ListaUsuarios();
							for(usuario x : usuarios){
								System.out.println(x);
							}
							break;
						case 3:
							System.out.println("Saliendo del programa...");
							continuarEjecucionAdmin = false;
						default:
							System.out.println("Opción no válida. Intente de nuevo.");
							break;
						}
					} while (continuarEjecucionAdmin);

			}
			
			else if (u instanceof gerente && continuarEjecucion) {

				g = (gerente) u;

				boolean continuarEjecucionGerente = true;
				int opcionGerente;
				do {
					Menus.mostrarMenuGerente(); // Mostrar el menú del gerente
					System.out.print("Ingrese una opción: ");
					opcionGerente = sin.nextInt(); // Leer la opción del gerente
					sin.nextLine(); // Consumir el salto de línea

					switch (opcionGerente) {
						case 1:
							agregarProductoAlInventario(sin, productos, rInventario);
							productos.clear();
							break;
						case 2:
							productos = lecturaFichero(rInventario);
							break;
						case 3:
							System.out.println("Saliendo del programa...");
							continuarEjecucionGerente = false;
						default:
							System.out.println("Opción no válida. Intente de nuevo.");
							break;
						}
				} while (continuarEjecucionGerente);

			} 

			else if (u instanceof empleado && continuarEjecucion) 
			{
				e = (empleado) u;
				boolean continuarEjecucionEmpleado = true;
				int opcionEmpleado;
				do {
					Menus.mostrarMenuEmpleado(); // Mostrar el menú del gerente
					System.out.print("Ingrese una opción: ");
					opcionEmpleado = sin.nextInt(); // Leer la opción del gerente
					sin.nextLine(); // Consumir el salto de línea

					switch (opcionEmpleado) {
						case 1:
						//realizarVenta(sin, r, prodInventario, rInventario);
					break;
						case 2:
							// ver historial de ventas 
							break;
						case 3:
							System.out.println("Saliendo del programa...");
							continuarEjecucionEmpleado = false;
						default:
							System.out.println("Opción no válida. Intente de nuevo.");
							break;
						}
				} while (continuarEjecucionEmpleado);
			}
	}while(continuarEjecucion);

	}
	
	public static void escribirFichero(File fichero, ArrayList<producto> p){ 
        checkFichero(fichero); //sino existe el fichero, se crea
		try{
			//"BufferedWriter" para escribir en el fichero , "PrintWriter" para escribir líneas en el BufferedWriter
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, true), "ISO-8859-1"));  
			PrintWriter pw = new PrintWriter(bw); 

			//Se recorre la lista de productos y se escribe cada uno en una línea del fichero
			for(producto x : p)
				pw.println(x);
			pw.flush(); //asegura la escritura de los datos en el fichero
			pw.close(); 
			bw.close();
			System.out.println("\nFichero escrito con exito."); 
		} catch(IOException e){
			e.printStackTrace();
		}
    }

	public static void checkFichero(File fichero){ 
       try { 
			if (fichero.createNewFile()) //se intenta crear el fichero si no existe
			    System.out.println("\nEl fichero indicado, no existe y ha sido creado");  //no exite y se crea con exito
		} catch (IOException e) { 
			e.printStackTrace();
		} 
   }


 //METODOS PARA LOS MENUS   

	// Método para agregar un producto al inventario
	public static void agregarProductoAlInventario(Scanner sin, ArrayList<producto> prodInventario, File rInventario) {
    	double pdv, ppu;
    	int cant;
		String r[] = new String[6];
       try{ 
           Scanner s = new Scanner(rInventario, "UTF-8");
           while (s.hasNextLine())
               r = s.nextLine().split(";");
           s.close();
       } catch(IOException ex){ 
			ex.printStackTrace();
       }
	   producto.setSigId(Integer.parseInt(r[0])+1);
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

       	 	// Se agrega el producto al inventario (según los datos ingresados)
        	prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv)); 
			
        	System.out.println("Desea añadir otro producto? \nPresione 's' para añadir otro producto");
        	r[0] = sin.next();

   	 	} while (r[0].equalsIgnoreCase("s"));
    	escribirFichero(rInventario, prodInventario); 
	}

   public static ArrayList<producto> lecturaFichero(File fichero){
	ArrayList<producto> p = new ArrayList<producto>();
	producto product;
	String prod[] = new String[6];
       try{ 
           Scanner s = new Scanner(fichero, "UTF-8");
		   System.out.println("Registro de Inventario\nID;NOMBRE;DESCRIPCION;CANTIDAD;PPU;PDV");
           while (s.hasNextLine()){ 
               prod = s.nextLine().split(";");
			   product = new producto(prod[1], prod[2], Integer.parseInt(prod[3]), Double.parseDouble(prod[4]), Double.parseDouble(prod[5]));
			   product.setId(Integer.parseInt(prod[0]));
				System.out.println(product);
			   p.add(product);
           }
           s.close();
       } catch(IOException ex){ 
			ex.printStackTrace();
       }
	   return p;
   }
}
