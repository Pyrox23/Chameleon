package app;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Programa {
	public static void main(String[] args){
		boolean log = true;
		String r[] = new String[4]; 
		Scanner sin = new Scanner(System.in);
		GestorDatosFichero gf = new GestorDatosFichero(); 
		String ruta = "./Proyecto de Ingeniería/chameleon/src/ficheros/";
		File rInventario = new File(ruta + "Registro_Inventario.csv"); 
		File rVenta;
		ArrayList<producto> productos = gf.lecturaFicheroInv(rInventario); 
		ArrayList<usuario> usuarios = gf.ListaUsuarios();
		if(usuarios.isEmpty()){
			usuarios.add(new administrador("admin", "admin", "admin", "admin"));
			gf.EscribirUsuarios(usuarios);
		}	
		if(!rInventario.exists())
			gf.checkFichero(rInventario);
		boolean continuarEjecucion = true;
		int opcion;
		do {
			usuario u = null;
			empleado e = null;
			gerente g = null;
			administrador a = null;
			do {
				Menus.menuInicial();
				try {
					System.out.print("Ingrese una opción: ");
					opcion = sin.nextInt();
					sin.nextLine();

					switch (opcion) {
						case 1:
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
								if (!log) {
									System.out.println("\nEl usuario indicado es incorrecto, intenta de nuevo.");
								}
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
				productos = gf.lecturaFicheroInv(rInventario);
				boolean continuarEjecucionAdmin = true;
				int opcionAdmin;
				do {
					Menus.menuAdmin(); 
					try{
						System.out.print("Ingrese una opción: ");
						opcionAdmin = sin.nextInt();
						sin.nextLine();

						switch (opcionAdmin) {
							case 1:  
								Menus.mostrarIngresarNuevUsuario();
								a.agregarNuevoUsuario(sin);
								break;
							case 2: 
								a.agregarProductoAlInventario(sin, productos, rInventario);
								break;
							case 3:
								boolean continuarEjecucionRegistro = true;
								productos = gf.lecturaFicheroInv(rInventario);
								do{
									Menus.menuRegistroVenta();
									try{
										System.out.print("Ingrese una opción: ");
										opcionAdmin = sin.nextInt();
										sin.nextLine();
										switch (opcionAdmin) {
											case 1:	
												registroVenta(sin, productos, a);
												break;
											case 2:
												a.gestionarVentas(sin, productos);
												break;
											case 3:
												a.imprimirVentas();
												break;
											case 4:    
												cerrarRegistro(gf, a, productos, rInventario);
												continuarEjecucionRegistro = false;
												break;
											case 5:
												System.out.println("Saliendo sin guardar registro...");
												continuarEjecucionRegistro = false;
												break;
											default:
												Menus.mensajeError();
												break;
										}
									} catch (InputMismatchException ex) { 
										System.out.println("Por favor, ingrese un número entero válido.");
										sin.nextLine(); 
									}
								} while(continuarEjecucionRegistro);
								a.ventas.clear();
								break;
							case 4: 
								usuarios = gf.ListaUsuarios();
								for(usuario x : usuarios)
									System.out.println(x);
								break;
							case 5: 
								productos = gf.lecturaFicheroInv(rInventario);
								if(productos.isEmpty())
									System.out.println("El registro de inventario esta vacio.");
								for(producto x : productos)
									System.out.println(x.toStringInventario());
								break;
							case 6:
								Menus.menuModificarInventario();
								a.modificarInventario(sin, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 7: 
								rVenta = new File(gf.seleccionarArchivo(ruta)); // Cambio con gestor
								if(rVenta.exists()) {
									a.ventas = gf.lecturaFicheroVenta(rVenta);
									continuarEjecucionRegistro = true;
									productos = gf.lecturaFicheroInv(rInventario);
									do{
										Menus.menuRegistroVenta();
										try{
											System.out.print("Ingrese una opción: ");
											opcionAdmin = sin.nextInt();
											sin.nextLine();
											switch (opcionAdmin) {
												case 1:	
													registroVenta(sin, productos, a);
													break;
												case 2:
													a.gestionarVentas(sin, productos);
													break;
												case 3:
													a.imprimirVentas();
													break;
												case 4:    
													cerrarRegistroMod(gf, a, productos, rInventario, rVenta);
													continuarEjecucionRegistro = false;
													break;
												case 5:
													System.out.println("Desea elminiar el registro? (S/N)");
													continuarEjecucionRegistro = !sin.nextLine().equalsIgnoreCase("s");
													if(!continuarEjecucionRegistro)
														rVenta.delete();
													break;
												default:
													Menus.mensajeError();
													break;
											}
										} catch (InputMismatchException ex) { 
											System.out.println("Por favor, ingrese un número entero válido.");
											sin.nextLine(); 
										}
									} while(continuarEjecucionRegistro);
								}
								else
									System.out.println("El registro indicado no existe.");
								a.ventas.clear();
								break;
							case 8: 
								rVenta = new File(gf.seleccionarArchivo(ruta)); // Cambio con gestor
								boolean continuarEjecucionMetricas = true, check = rVenta.getName().contains("Registro_Venta.csv");
								if(rVenta.exists()&&check){
									do {
										Menus.mostrarMetricas();
											try{
												System.out.print("Ingrese una opción: ");
												opcionAdmin = sin.nextInt();
												sin.nextLine();
												switch (opcionAdmin) {
													case 1:
														System.out.println(a.productoMasVendido(rVenta, rInventario));
														break;
													case 2:
														System.out.println(a.totalVentas(rVenta));
														break;
													case 3:
														System.out.println(a.productosVendidos(rVenta));
														break;
													case 4: 
														continuarEjecucionMetricas = false;
														break;
													default:
														Menus.mensajeError();
														break;
												}
											} catch (InputMismatchException ex) { 
												System.out.println("Por favor, ingrese un número entero válido.");
												sin.nextLine(); 
											}
									} while (continuarEjecucionMetricas);
								}
								else if(!check)
									System.out.println("El registro indicado no es valido.");
								else
									System.out.println("El registro indicado no existe.");
								break;
							case 9:
								System.out.print("Indique la id del usuario a eliminar: ");
								a.eliminarUsuario(sin.nextLine());
								break;
							case 0: 
								System.out.println("Saliendo del programa...");
								continuarEjecucionAdmin = false;
								break;
							default:
								Menus.mensajeError();
								break;
							}
						} catch (InputMismatchException ex) { 
							System.out.println("Por favor, ingrese un número entero válido.");
							sin.nextLine();
						}
					} while (continuarEjecucionAdmin);

			}
			
			else if (u instanceof gerente && continuarEjecucion) {

				g = (gerente) u;
				productos = gf.lecturaFicheroInv(rInventario);
				boolean continuarEjecucionGerente = true;
				int opcionGerente;
				do {
					Menus.menuGerente(); 
					try{
						System.out.print("Ingrese una opción: ");
						opcionGerente = sin.nextInt();
						sin.nextLine();

						switch (opcionGerente) {
							case 1: 
								g.agregarProductoAlInventario(sin, productos, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 2: 
								boolean continuarEjecucionRegistro = true;
								do{
									Menus.menuRegistroVenta();
									try{
										System.out.print("Ingrese una opción: ");
										opcionGerente = sin.nextInt();
										sin.nextLine();
										switch (opcionGerente) {
											case 1:	
												registroVenta(sin, productos, g);
												break;
											case 2:
												g.gestionarVentas(sin, productos);
												break;
											case 3:
												g.imprimirVentas();
												break;
											case 4:    
												cerrarRegistro(gf, g, productos, rInventario);
												continuarEjecucionRegistro = false;
												break;
											case 5:
												System.out.println("Saliendo sin guardar registro...");
												continuarEjecucionRegistro = false;
												break;
											default:
												Menus.mensajeError();
												break;
										}
									} catch (InputMismatchException ex) { 
										System.out.println("Por favor, ingrese un número entero válido.");
										sin.nextLine(); 
									}
								} while(continuarEjecucionRegistro);
								g.ventas.clear();
								break;
							case 3:
								productos = gf.lecturaFicheroInv(rInventario);
								for(producto x : productos)
									System.out.println(x.toStringInventario());
								break;
							case 4: 
								Menus.menuModificarInventario();
								g.modificarInventario(sin, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 5: 
								rVenta = new File(ruta + nombreVenta(sin) + "_Registro_Venta.csv");
								boolean continuarEjecucionMetricas = true;
								if(rVenta.exists()){
									do {
										Menus.mostrarMetricas();
										try{
											System.out.print("Ingrese una opción: ");
											opcionGerente = sin.nextInt();
											sin.nextLine();
											switch (opcionGerente) {
												case 1:
													System.out.println(g.productoMasVendido(rVenta, rInventario));
													break;
												case 2:
													System.out.println(g.totalVentas(rVenta));
													break;
												case 3:
													System.out.println(g.productosVendidos(rVenta));
													break;
												case 4: 
													continuarEjecucionMetricas = false;
													break;
												default:
													Menus.mensajeError();
													break;
											}
										} catch (InputMismatchException ex) { 
											System.out.println("Por favor, ingrese un número entero válido.");
											sin.nextLine(); 
										}
									} while (continuarEjecucionMetricas);
								}
								else
									System.out.println("El registro indicado no existe.");
								break;
							case 6: 
								System.out.println("Saliendo del programa...");
								continuarEjecucionGerente = false;
								break;
							default:
								Menus.mensajeError();
								break;
						}
					} catch (InputMismatchException ex) { 
						System.out.println("Por favor, ingrese un número entero válido.");
						sin.nextLine(); 
					}
				} while (continuarEjecucionGerente);

			} 

			else if (u instanceof empleado && continuarEjecucion) 
			{
				e = (empleado) u;
				productos = gf.lecturaFicheroInv(rInventario);
				boolean continuarEjecucionEmpleado = true;
				int opcionEmpleado;
				do {
					Menus.menuEmpleado();
					try{
						System.out.print("Ingrese una opción: ");
						opcionEmpleado = sin.nextInt();
						sin.nextLine();
						switch (opcionEmpleado) {
							case 1:
								do{
									Menus.menuRegistroVenta();
									try{
										System.out.print("Ingrese una opción: ");
										opcionEmpleado = sin.nextInt();
										sin.nextLine();
										switch (opcionEmpleado) {
											case 1:	
												registroVenta(sin, productos, e);
												break;
											case 2:
												e.gestionarVentas(sin, productos);
												break;
											case 3:
												e.imprimirVentas();
												break;
											case 4:    
												cerrarRegistro(gf, e, productos, rInventario);
												continuarEjecucionEmpleado = false;
												break;
											case 5:
												System.out.println("Saliendo sin guardar registro...");
												continuarEjecucionEmpleado = false;
												break;
											default:
												Menus.mensajeError();
												break;
										}
									} catch (InputMismatchException ex) { 
										System.out.println("Por favor, ingrese un número entero válido.");
										sin.nextLine(); 
									}
								} while(continuarEjecucionEmpleado);
								e.ventas.clear();
								break;
							case 2:
								System.out.println("Saliendo del Programa");
								continuarEjecucionEmpleado = false;
								break;
							default:
								Menus.mensajeError();
								break;
							}
					} catch (InputMismatchException ex) { 
						System.out.println("Por favor, ingrese un número entero válido.");
						sin.nextLine(); 
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
				} catch(InputMismatchException ex){
					System.out.println("Opcion no valida.");
					check = false;
				}
			} while(!check);
			sin.nextLine();
		}while(input.equalsIgnoreCase("s"));
										
									
	}

	public static void cerrarRegistro(GestorDatosFichero gf, empleado e, ArrayList<producto> productos, File rInventario){
		if (e.getVentas().isEmpty()) {
			System.out.println("No hay ventas registradas");
		} 
		else {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("Creando Registro para guardar las Ventas..");
			File rVenta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/" 
							+ e.getNombre() + "_" + e.getApellido() + "_" + dateFormat.format(new Date()) + "_Registro_Venta.csv");
			
			gf.checkFichero(rVenta);
			gf.escribirFicheroVenta(rVenta, e.ventas, rVenta.exists());
			gf.escribirFichero(rInventario, productos, false);
			e.ventas.clear();
		}
	}

	public static void cerrarRegistroMod(GestorDatosFichero gf, empleado e, ArrayList<producto> productos, File rInventario, File rVenta){
			gf.checkFichero(rVenta);
			gf.escribirFicheroVenta(rVenta, e.ventas, false);
			gf.escribirFichero(rInventario, productos, false);
			e.ventas.clear();
	}

	public static String nombreVenta(Scanner sin){
		String nombreRegVenta;
		System.out.print("Indique el nombre y apellido del creador del registro en el formato Nombre_Apellido: ");
		nombreRegVenta = sin.nextLine();
		System.out.print("Indique la fecha del registro a ver en el formato dd-mm-yyyy: ");
		nombreRegVenta += "_" + sin.nextLine();
		return nombreRegVenta;
	}
}
