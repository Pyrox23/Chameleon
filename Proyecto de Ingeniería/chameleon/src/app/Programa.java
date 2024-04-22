package app;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Programa {
	public static void main(String[] args){
		boolean log = true; //bandera para el bucle
		String r[] = new String[4]; //array para guardar datos del usuario
		Scanner sin = new Scanner(System.in); //teclado para ingresar datos por consola
		GestorDatosFichero gf = new GestorDatosFichero(); //objeto GestorDatosFichero para la lectura y escritura de datos desde y hacia un archivo**
		File rInventario = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Inventario.csv"); //ruta del archivo de registro de inventario
		File rVenta;
		ArrayList<producto> productos = gf.lecturaFichero(rInventario); //arayList para guardar productos del inventario
		ArrayList<usuario> usuarios = new ArrayList<usuario>();
		//variables para los tipos de usuario
		usuario u = null;
		empleado e = null;
		gerente g = null;
		administrador a = null;
		usuarios.add(new administrador("admin", "admin", "Juan", "Moral"));
		usuarios.add(new gerente("gerente", "123", "Jose", "Picans"));
		usuarios.add(new empleado("empleado", "123", "Roberto", "Amo"));
		gf.EscribirUsuarios(usuarios);
		boolean continuarEjecucion = true;
		int opcion;
		do {
			do {
				Menus.menuInicial();
				try {
					System.out.print("Ingrese una opción: ");
					opcion = sin.nextInt();
					sin.nextLine();

					switch (opcion) {
						case 1:
							do {
								if (!log) {
									System.out.println("\nEl usuario indicado es incorrecto, intenta de nuevo.");
								}
								Menus.mostrarIngresarSesion();
								System.out.print("\nIntroduce tu usuario: ");
								r[0] = sin.nextLine();
								System.out.print("Introduce tu contraseña: ");
								r[1] = sin.nextLine();
								u = new usuario(r[0], r[1], "", "");
								u = u.login();
								log = (u != null);
							} while (!log);
							break;
						case 2:
							System.out.println("Saliendo del programa...");
							continuarEjecucion = false;
							break;
						default:
							Menus.mensajeError();
							break;
						}

				} catch (InputMismatchException ex) { 
					System.out.println("Por favor, ingrese un número entero válido.");
					sin.nextLine(); 
				}
			} while (!log && continuarEjecucion);
			
			if (u instanceof administrador && continuarEjecucion) {
				a = (administrador) u;
				productos = gf.lecturaFichero(rInventario);
				boolean continuarEjecucionAdmin = true;
				int opcionAdmin;
				do {
					Menus.menuAdmin(); 
					System.out.print("Ingrese una opción: ");
					opcionAdmin = sin.nextInt();
					sin.nextLine();

					switch (opcionAdmin) {
						case 1:  
							Menus.mostrarIngresarNuevUsuario();
							a.agregarNuevoUsuario(sin);
							break;
						case 2: 
							g.agregarProductoAlInventario(sin, productos, rInventario);
							productos.clear();
							break;
						case 3:
							boolean continuarEjecucionRegistro = true;
							do{
								Menus.menuRegistroVenta();
								System.out.print("Ingrese una opción: ");
								opcionAdmin = sin.nextInt();
								sin.nextLine();
								switch (opcionAdmin) {
									case 1:	
										registroVenta(sin, productos, a);
										break;
									case 2:
										a.gestionarRegistro(sin, productos);
										break;
									case 3:
										a.imprimirVentas();
									case 4:    
										cerrarRegistro(gf, a, productos, rInventario);
										continuarEjecucionRegistro = false;
										break;
									default:
										Menus.mensajeError();
										break;
								}
							} while(continuarEjecucionRegistro);
							break;
						case 4: 
							usuarios = gf.ListaUsuarios();
							for(usuario x : usuarios){
								System.out.println(x);
							}
							break;
						case 5: 
							productos = gf.lecturaFichero(rInventario);
							for(producto x : productos)
							System.out.println(x);
							break;
						case 6: 
							Menus.menuModificarInventario();
							a.modificarRegistros(sin, rInventario);
							productos = gf.lecturaFichero(rInventario);
							break;
						case 7: //Modificar Venta
							break;
						case 8: 
							System.out.println("Saliendo del programa...");
							continuarEjecucionAdmin = false;
							break;
						default:
							Menus.mensajeError();
							break;
						}
					} while (continuarEjecucionAdmin);

			}
			
			else if (u instanceof gerente && continuarEjecucion) {

				g = (gerente) u;
				productos = gf.lecturaFichero(rInventario);
				boolean continuarEjecucionGerente = true;
				int opcionGerente;
				do {
					Menus.menuGerente(); 
					System.out.print("Ingrese una opción: ");
					opcionGerente = sin.nextInt();
					sin.nextLine();

					switch (opcionGerente) {
						case 1: 
							g.agregarProductoAlInventario(sin, productos, rInventario);
							productos = gf.lecturaFichero(rInventario);
							break;
						case 2: 
							boolean continuarEjecucionRegistro = true;
							do{
								Menus.menuRegistroVenta();
								System.out.print("Ingrese una opción: ");
								opcionGerente = sin.nextInt();
								sin.nextLine();
								switch (opcionGerente) {
									case 1:	
										registroVenta(sin, productos, g);
										break;
									case 2:
										g.gestionarRegistro(sin, productos);
										break;
									case 3:
										g.imprimirVentas();
										break;
									case 4:    
										cerrarRegistro(gf, g, productos, rInventario);
										continuarEjecucionRegistro = false;
										break;
									default:
										Menus.mensajeError();
										break;
								}
							} while(continuarEjecucionRegistro);
							break;
						case 3:
							productos = gf.lecturaFichero(rInventario);
							for(producto x : productos)
								System.out.println(x);
							break;
						case 4: 
							Menus.menuModificarInventario();
							g.modificarRegistros(sin, rInventario);
							productos = gf.lecturaFichero(rInventario);
							break;
						case 5: 
							System.out.println("Saliendo del programa...");
							continuarEjecucionGerente = false;
							break;
						default:
							Menus.mensajeError();
							break;
						}
				} while (continuarEjecucionGerente);

			} 

			else if (u instanceof empleado && continuarEjecucion) 
			{
				e = (empleado) u;
				productos = gf.lecturaFichero(rInventario);
				boolean continuarEjecucionEmpleado = true;
				int opcionEmpleado;
				do {
					Menus.menuEmpleado(); 
					System.out.print("Ingrese una opción: ");
					opcionEmpleado = sin.nextInt();
					sin.nextLine();
					switch (opcionEmpleado) {
						case 1:
							do{
								Menus.menuRegistroVenta();
								System.out.print("Ingrese una opción: ");
								opcionEmpleado = sin.nextInt();
								sin.nextLine();
								switch (opcionEmpleado) {
									case 1:	
										registroVenta(sin, productos, e);
										break;
									case 2:
										e.gestionarRegistro(sin, productos);
										break;
									case 3:
										e.imprimirVentas();
										break;
									case 4:    
										cerrarRegistro(gf, e, productos, rInventario);
										continuarEjecucionEmpleado = false;
										break;
									default:
										Menus.mensajeError();
										break;
								}
							} while(continuarEjecucionEmpleado);
							break;
						case 2:
							System.out.println("Saliendo del Programa");
							continuarEjecucionEmpleado = false;
							break;
						default:
							Menus.mensajeError();
							break;
						}
				} while (continuarEjecucionEmpleado);
			}
		}while(continuarEjecucion);
	}

	public static void registroVenta(Scanner sin, ArrayList<producto> productos, empleado e){
		String input = "";
		boolean check = true;
		do{
			e.agregarVenta(sin, productos);
			do{
				System.out.println("Presione 's' para realizar otra venta.");
				try{
					input = sin.next();
					check = true;
				}catch(InputMismatchException ex){
					System.out.println("Opcion no valida.");
					check = false;
				}
			} while(!check);
			sin.nextLine();
			}while(input.equalsIgnoreCase("s"));
										
									
	}

	public static void cerrarRegistro(GestorDatosFichero gf, empleado e, ArrayList<producto> productos, File rInventario){
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		System.out.println("Creando Registro para guardar las Ventas..");
		File rVenta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/" 
							+ e.getNombre() + "_" + e.getApellido() + "_" + dateFormat.format(new Date()) + "_Registro_Venta.csv");
		gf.checkFichero(rVenta);
		gf.escribirFicheroVenta(rVenta, e.ventas, false);
		gf.escribirFichero(rInventario, productos, false);
	}
}
