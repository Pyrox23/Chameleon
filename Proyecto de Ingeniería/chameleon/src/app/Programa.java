package app;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.filechooser.FileSystemView;

public class Programa {
	public static void main(String[] args){
		boolean log;
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
			log = true;
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
								System.out.print("\nId: ");
								r[0] = sin.nextLine();
								System.out.print("Contraseña: ");
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
								if(!productos.isEmpty()){
									for(producto x : productos)
										System.out.println(x.toStringInventario());
								}
								else
									System.out.println("El inventario esta vacio.");
								break;
							case 6:
								Menus.menuModificarInventario();
								a.modificarInventario(sin, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 7: 
								rVenta = new File(gf.seleccionarArchivo(ruta));
								String nombreCheck[] = rVenta.getName().split("_");
								continuarEjecucionRegistro = true;
								boolean check = rVenta.getName().contains("Registro_Venta.csv")&&nombreCheck.length>3;
								if(rVenta.exists()&&check) {
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
								}
								else if(!check)
									System.out.println("El registro indicado no es valido.");
								else
									System.out.println("El registro indicado no existe.");
								a.ventas.clear();
								break;
							case 8: 
								rVenta = new File(gf.seleccionarArchivo(ruta));
								boolean continuarEjecucionMetricas = true;
								check = rVenta.getName().contains("Registro_Venta.csv");
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
														FileSystemView view = FileSystemView.getFileSystemView();
														String rutaExportar = view.getHomeDirectory().getPath();
														// Crear un fichero vacío en descargas
														File exportar = new File(rutaExportar, "Metricas.txt");
														// Llenarlo con los datos del fichero a descargar
														gf.escribirFicheroExportar(exportar, rVenta, rInventario, a);
														System.out.println("Archivo exportado correctamente en: "  + rutaExportar);
														break;
													case 0: 
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
							case 10: 
								rVenta = new File(gf.seleccionarArchivo(ruta));
								if(rVenta.getName().contains("Registro_Venta.csv") || rVenta.getName().equalsIgnoreCase("Registro_Inventario.csv") || rVenta.getName().equalsIgnoreCase("credenciales.bin")){
									// Obtener la ruta de la carpeta de descargas
									FileSystemView view = FileSystemView.getFileSystemView();
									String rutaExportar = view.getHomeDirectory().getPath();
									// Crear un fichero vacío en descargas
									File exportar = new File(rutaExportar, "copia_de_" + rVenta.getName());
									// Llenarlo con los datos del fichero a descargar
									gf.copyFile(rVenta.getPath(), exportar.getPath());
									System.out.println("Archivo exportado correctamente en: "  + rutaExportar);
								}
								else
									System.out.println("El archivo indicano no es valido.");
								break;
							case 11:
								rVenta = new File(gf.seleccionarArchivo(ruta));
								check = rVenta.getName().contains("Registro_Venta.csv");
								if(check){
									System.out.println("Desea elminiar el registro? (S/N)"); 
									if(sin.nextLine().trim().equalsIgnoreCase("s"))
										rVenta.delete();
								}
								else if(rVenta.getName().contains("Registro_Inventario.csv"))
									System.out.println("El registro de inventario no puede ser eliminado, solo modificado.");
								else if(rVenta.getName().contains("credendiales"))
									System.out.println("No puede eliminar las credenciales del sistema.");
								else
									System.out.println("El registro indicado es invalido.");
								break;
							case 12:
								rVenta = new File(gf.seleccionarArchivo(ruta));
								check = rVenta.getName().contains("Registro_Venta.csv");
								if(check){
									System.out.println("Ventas del registro: " + rVenta.getName());
									productos = gf.lecturaFicheroVenta(rVenta);
									for(producto x : productos)
										System.out.println(x.toStringVenta());
								}
								else
									System.out.println("El registro indicado es invalido.");
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
								if(!productos.isEmpty()){
									for(producto x : productos)
										System.out.println(x.toStringInventario());
								}
								else
									System.out.println("El inventario esta vacio.");
								break;
							case 4: 
								Menus.menuModificarInventario();
								g.modificarInventario(sin, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 5: 
								rVenta = new File(gf.seleccionarArchivo(ruta));
								boolean continuarEjecucionMetricas = true, check = rVenta.getName().contains("Registro_Venta.csv");
								if(rVenta.exists()&&check){
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
														break;
													case 0: 
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
							case 0: 
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

			else if (u instanceof empleado && continuarEjecucion) {
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
							case 0:
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
		} while(continuarEjecucion);
	}

	public static void registroVenta(Scanner sin, ArrayList<producto> productos, empleado e){
		String input = "";
		do{
			e.agregarVenta(sin, productos);
			sin.nextLine();
			System.out.println("Presione 's' para realizar otra venta.\nPulse cualquier otro boton para salir");
			input = sin.nextLine().trim();
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
			File rVentaGeneral = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/" + dateFormat.format(new Date()) + "_Registro_Venta.csv");
			
			if(!gf.checkFichero(rVenta))
				System.out.println("Ya has creado un registro para este dia y tus ventas han sido agregadas.\nNombre del registro: " + rVenta.getName());
			gf.checkFichero(rVentaGeneral);
			gf.escribirFicheroVenta(rVenta, e.ventas, rVenta.exists());
			gf.escribirFicheroVenta(rVentaGeneral, e.ventas, rVentaGeneral.exists());
			gf.escribirFichero(rInventario, productos, false);
			e.ventas.clear();
		}
	}

	public static void cerrarRegistroMod(GestorDatosFichero gf, empleado e, ArrayList<producto> productos, File rInventario, File rVenta){
			String fecha[] = rVenta.getName().split("_");
			File rVentaGeneral = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/" + fecha[2] + "_Registro_Venta.csv");
			File carpeta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros");
			File[] archivos;
			boolean sobreescribir = false;
			gf.escribirFicheroVenta(rVenta, e.ventas, false);
			gf.escribirFichero(rInventario, productos, false);
			if(carpeta.exists()){
				if(carpeta.isDirectory()){
					archivos = carpeta.listFiles();
					for(int i = 0; i<archivos.length; i++){
						if(archivos[i].getName().contains("Registro_Venta.csv")&&!archivos[i].getName().equals(rVentaGeneral.getName())){
							gf.escribirFicheroVenta(rVentaGeneral, gf.lecturaFicheroVenta(archivos[i]), sobreescribir);
							sobreescribir = true;
						}
					}
				}
			}
			e.ventas.clear();
	}
}
