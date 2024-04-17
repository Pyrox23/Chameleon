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
		// gf.EscribirUsuario(new administrador("admin", "admin", "Juan", "Moral"));
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
							System.out.println("Opción no válida. Intente de nuevo.");
							break;
						}

				} catch (InputMismatchException ex) { 
					System.out.println("Por favor, ingrese un número entero válido.");
					sin.nextLine(); 
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
					Menus.mostrarMenuAdministrador(); 
					System.out.print("Ingrese una opción: ");
					opcionAdmin = sin.nextInt();
					sin.nextLine();

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
					Menus.mostrarMenuGerente(); 
					System.out.print("Ingrese una opción: ");
					opcionGerente = sin.nextInt();
					sin.nextLine();

					switch (opcionGerente) {
						case 1:
							g.agregarProductoAlInventario(sin, productos, rInventario);
							productos.clear();
							break;
						case 2:
							productos = gf.lecturaFichero(rInventario);
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
					Menus.mostrarMenuEmpleado1(); 
					System.out.print("Ingrese una opción: ");
					opcionEmpleado = sin.nextInt();
					sin.nextLine();
					switch (opcionEmpleado) {
						case 1:
							Menus.mostrarMenuEmpleado2();
							System.out.print("Ingrese una opción: ");
							opcionEmpleado = sin.nextInt();
							sin.nextLine();
							switch (opcionEmpleado) {
								case 1:
									System.out.println("Creando Registro para guardar las Ventas..");
									gf.checkFichero(rVenta);
									e.agregarVenta(sin, gf.lecturaFichero(rInventario));
									// e.registrarVentas(sin);
									break;
								case 2:
									e.mostrarVentas();
									break;
								case 3:    
									System.out.println("Guardando el Registro y Saliendo");
									continuarEjecucionEmpleado = false;
									break;
								default:
									break;
							}
					    	break;
						case 2:
							//e.modificarVentas();
							System.out.println("Saliendo del Programa");
							continuarEjecucionEmpleado = false;
							break;
						default:
							System.out.println("Opción no válida. Intente de nuevo.");
							break;
						}
				} while (continuarEjecucionEmpleado);
			}
	}while(continuarEjecucion);

	}
}
