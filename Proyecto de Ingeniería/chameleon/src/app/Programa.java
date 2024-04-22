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
		// Perfil Administrador: admin, admin
		// Perfil Gerente: gerente, 123
		// Perfil Empleado: empleado, 123
		boolean continuarEjecucion = true;
		int opcion;
		do {
			do {
				Menus.mostrarMenuInicial();
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
				// en caso lo sea, se hace un "casting"(convertir) para q "u" sea tratada como un obj del tipo "administrador"
				a = (administrador) u;  // y con ello tenga acceso a sus metodos y propiedades de esa clase
				boolean continuarEjecucionAdmin = true;
				int opcionAdmin;
				do {
					Menus.mostrarMenuAdministrador(); 
					System.out.print("Ingrese una opción: ");
					opcionAdmin = sin.nextInt();
					sin.nextLine();

					switch (opcionAdmin) {
						case 1:  //Agregar Nuevo usuario
							Menus.mostrarIngresarNuevUsuario();
							a.agregarNuevoUsuario(sin);
							break;
						case 2: //Agregar Prodcuto Inventario
							g.agregarProductoAlInventario(sin, productos, rInventario);
							productos.clear();
							break;
						case 3: //Agregar Venta
							String input = "";
							do{
								e.agregarVenta(sin, productos);
								System.out.println("Presione 's' para realizar otra venta.");
								try{
									input = sin.next();
								}catch(InputMismatchException ex){
									System.out.println("Opcion no valida.");
								}
								sin.nextLine();
							}while(input.equalsIgnoreCase("s"));
							DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
							System.out.println("Creando Registro para guardar las Ventas..");
							rVenta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/" + e.getNombre() + "_" + e.getApellido() + "_" + dateFormat.format(new Date()) + "_Registro_Venta.csv");
							gf.checkFichero(rVenta);
							gf.escribirFicheroVenta(rVenta, e.ventas, false);
							gf.escribirFichero(rInventario, productos, false);
							break;
						case 4: //Ver Lista usuario
							usuarios = gf.ListaUsuarios();
							for(usuario x : usuarios){
								System.out.println(x);
							}
							break;
						case 5: //Ver Inventario
							productos = gf.lecturaFichero(rInventario);
							for(producto x : productos)
							System.out.println(x);
							break;
						case 6: //Modificar Inventario
							Menus.mdificarInvMenu();
							a.modificarRegistros(sin, rInventario);
							break;
						case 7: //Modificar Venta
							if(!e.ventas.isEmpty())
								e.mostrarVentas();
							break;
						case 8: //Guardar Registro
							System.out.println("Guardando el Registro..");
							break;
						case 9: //Salir
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
				boolean continuarEjecucionGerente = true;
				int opcionGerente;
				do {
					Menus.mostrarMenuGerente(); 
					System.out.print("Ingrese una opción: ");
					opcionGerente = sin.nextInt();
					sin.nextLine();

					switch (opcionGerente) {
						case 1: //Agregar Producto Invet
							g.agregarProductoAlInventario(sin, productos, rInventario);
							productos.clear();
							break;
						case 2: //Agregar Venta
							String input = "";
							do{
								e.agregarVenta(sin, productos);
									System.out.println("Presione 's' para realizar otra venta.");
									try{
										input = sin.next();
									}catch(InputMismatchException ex){
										System.out.println("Opcion no valida.");
									}
									sin.nextLine();
								}while(input.equalsIgnoreCase("s"));
								DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
								System.out.println("Creando Registro para guardar las Ventas..");
								rVenta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/" + e.getNombre() + "_" + e.getApellido() + "_" + dateFormat.format(new Date()) + "_Registro_Venta.csv");
								gf.checkFichero(rVenta);
								gf.escribirFicheroVenta(rVenta, e.ventas, false);
								gf.escribirFichero(rInventario, productos, false);
								break;
						case 3: //Ver Inventario
							productos = gf.lecturaFichero(rInventario);
							for(producto x : productos)
								System.out.println(x);
							break;
						case 4: //Modificar Venta
							if(!e.ventas.isEmpty())
								e.mostrarVentas();
							break;
						case 5: //Guardar Registro
							System.out.println("Guardando el Registro..");
							break;
						case 6: //Salir
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
				boolean continuarEjecucionEmpleado = true;
				int opcionEmpleado;
				do {
					Menus.mostrarMenuEmpleado1(); 
					System.out.print("Ingrese una opción: ");
					opcionEmpleado = sin.nextInt();
					sin.nextLine();
					switch (opcionEmpleado) {
						case 1:
							do{
								Menus.mostrarMenuEmpleado2();
								System.out.print("Ingrese una opción: ");
								opcionEmpleado = sin.nextInt();
								sin.nextLine();
								switch (opcionEmpleado) {
									case 1:	
										String input = "";
										do{
											e.agregarVenta(sin, productos);
											System.out.println("Presione 's' para realizar otra venta.");
											try{
												input = sin.next();
											}catch(InputMismatchException ex){
												System.out.println("Opcion no valida.");
											}
											sin.nextLine();
										}while(input.equalsIgnoreCase("s"));
										DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
										System.out.println("Creando Registro para guardar las Ventas..");
										rVenta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/" + e.getNombre() + "_" + e.getApellido() + "_" + dateFormat.format(new Date()) + "_Registro_Venta.csv");
										gf.checkFichero(rVenta);
										gf.escribirFicheroVenta(rVenta, e.ventas, false);
										gf.escribirFichero(rInventario, productos, false);
										break;
									case 2:
										if(!e.ventas.isEmpty())
											e.mostrarVentas();
										break;
									case 3:    
										System.out.println("Guardando el Registro y Saliendo");
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
}
