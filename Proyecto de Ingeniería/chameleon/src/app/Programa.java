package app;

import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.filechooser.FileSystemView;

public class Programa {
	public static void main(String[] args) {
		boolean log;
		String r[] = new String[4];
		Scanner sin = new Scanner(System.in);
		GestorDatosFichero gf = new GestorDatosFichero();
		String ruta = "./Proyecto de Ingeniería/chameleon/src/ficheros/";
		File rInventario = new File(ruta + "Registro_Inventario.csv");
		File rVenta; // Ruta a las ventas por empleado
		ArrayList<producto> productos = gf.lecturaFicheroInv(rInventario);
		ArrayList<usuario> usuarios = gf.ListaUsuarios();
		if (usuarios.isEmpty()) {
			usuarios.add(new administrador("admin", "admin", "admin", "admin"));
			gf.EscribirUsuarios(usuarios);
		}
		if (!rInventario.exists())
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
						case 1: // Login
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
						case 0: // Salir
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

			// Opciones Admin
			if (u instanceof administrador && continuarEjecucion) {
				a = (administrador) u;
				productos = gf.lecturaFicheroInv(rInventario);
				boolean continuarEjecucionAdmin = true;
				int opcionAdmin;
				do {
					Menus.menuAdmin();
					try {
						System.out.print("Ingrese una opción: ");
						opcionAdmin = sin.nextInt();
						sin.nextLine();

						switch (opcionAdmin) {
							case 1: // Agregar nuevo usuario
								Menus.mostrarIngresarNuevUsuario();
								a.agregarNuevoUsuario(sin);
								break;
							case 2: // Agregar Producto
								a.agregarProductoAlInventario(sin, productos, rInventario);
								break;
							case 3: // Crear Registro de Venta
								boolean continuarEjecucionRegistro = true;
								productos = gf.lecturaFicheroInv(rInventario);
								do {
									Menus.menuRegistroVenta();
									try {
										System.out.print("Ingrese una opción: ");
										opcionAdmin = sin.nextInt();
										sin.nextLine();
										switch (opcionAdmin) {
											case 1: // Agregar Venta
												registroVenta(sin, productos, a);
												break;
											case 2: // Modificar Venta
												a.gestionarVentas(sin, productos);
												break;
											case 3: // Ver Ventas Actuales
												a.imprimirVentas();
												break;
											case 4:
												a.verProductos(productos);
												break;
											case 5: // Guardar y salir
												cerrarRegistro(gf, a, productos, rInventario);
												continuarEjecucionRegistro = false;
												break;
											case 6: // Salir sin guardar
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
								} while (continuarEjecucionRegistro);
								a.ventas.clear();
								break;
							case 4: // Ver Lista de Usuarios
								usuarios = gf.ListaUsuarios();
								for (usuario x : usuarios)
									System.out.println(x);
								break;
							case 5: // Ver Inventario
								productos = gf.lecturaFicheroInv(rInventario);
								if (!productos.isEmpty()) {
									for (producto x : productos)
										System.out.println(x.toStringInventario());
								} else
									System.out.println("El inventario esta vacío.");
								break;
							case 6: // Modificar Inventario
								Menus.menuModificarInventario();
								a.modificarInventario(sin, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 7: // Modificar Registro de Venta
								rVenta = new File(gf.seleccionarArchivo(ruta));
								String nombreCheck[] = rVenta.getName().split("_");
								continuarEjecucionRegistro = true;
								boolean check = rVenta.getName().contains("Registro_Venta.csv")
										&& nombreCheck.length > 3; // Verificar que existe el fichero de venta
								if (rVenta.exists() && check) {
									a.ventas = gf.lecturaFicheroVenta(rVenta);
									continuarEjecucionRegistro = true;
									productos = gf.lecturaFicheroInv(rInventario);
									do {
										Menus.menuRegistroVenta();
										try {
											System.out.print("Ingrese una opción: ");
											opcionAdmin = sin.nextInt();
											sin.nextLine();
											switch (opcionAdmin) {
												case 1: // Agregar Venta
													registroVenta(sin, productos, a);
													break;
												case 2: // Modificar Venta
													a.gestionarVentas(sin, productos);
													break;
												case 3: // Ver Ventas Actuales
													a.imprimirVentas();
													break;
												case 4:
													a.verProductos(productos);
													break;
												case 5: // Salir y Guardar
													cerrarRegistroMod(gf, a, productos, rInventario, rVenta);
													continuarEjecucionRegistro = false;
													break;
												case 6: // Salir sin Guardar
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
									} while (continuarEjecucionRegistro);
								} else if (!check)
									System.out.println("El registro indicado no es válido.");
								else
									System.out.println("El registro indicado no existe.");
								a.ventas.clear();
								break;
							case 8: // Ver Métricas
								rVenta = new File(gf.seleccionarArchivo(ruta));
								boolean continuarEjecucionMetricas = true;
								check = rVenta.getName().contains("Registro_Venta.csv");
								if (rVenta.exists() && check) {
									do {
										Menus.mostrarMetricas();
										try {
											System.out.print("Ingrese una opción: ");
											opcionAdmin = sin.nextInt();
											sin.nextLine();
											switch (opcionAdmin) {
												case 1: // Mostrar producto más vendido
													System.out.println(a.productoMasVendido(rVenta, rInventario));
													break;
												case 2: // Total de ventas
													System.out.println(a.totalVentas(rVenta));
													break;
												case 3: // Cantidad de productos vendidos
													System.out.println(a.productosVendidos(rVenta));
													break;
												case 4: // Exportar métricas actuales
													FileSystemView view = FileSystemView.getFileSystemView();
													String rutaExportar = view.getHomeDirectory().getPath();
													// Crear un fichero vacío en descargas
													File exportar = new File(rutaExportar, "Metricas.txt");
													// Llenarlo con los datos del fichero a exportar
													gf.escribirFicheroExportar(exportar, rVenta, rInventario, a);
													System.out.println(
															"Archivo exportado correctamente en: " + rutaExportar);
													break;
												case 0: // Salir
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
								} else if (!check)
									System.out.println("El registro indicado no es válido.");
								else
									System.out.println("El registro indicado no existe.");
								break;
							case 9: // Eliminar Usuario
								ArrayList<usuario> listaUsuarios = gf.ListaUsuarios();

								for (usuario x : listaUsuarios) { // Cambito
									System.out.println(x);
								}

								System.out.print("Indique la id del usuario a eliminar: ");
								a.eliminarUsuario(sin.nextLine());
								break;
							case 10: // Exportar Registro
								rVenta = new File(gf.seleccionarArchivo(ruta));
								if (rVenta.getName().contains("Registro_Venta.csv")
										|| rVenta.getName().equalsIgnoreCase("Registro_Inventario.csv")
										|| rVenta.getName().equalsIgnoreCase("credenciales.bin")) {
									// Obtener la ruta de la carpeta de escritorio
									FileSystemView view = FileSystemView.getFileSystemView();
									String rutaExportar = view.getHomeDirectory().getPath();
									// Crear un fichero vacío en escritorio
									File exportar = new File(rutaExportar, "copia_de_" + rVenta.getName());
									// Llenarlo con los datos del fichero a exportar
									gf.copyFile(rVenta.getPath(), exportar.getPath());
									System.out.println("Archivo exportado correctamente en: " + rutaExportar);
								} else
									System.out.println("El archivo indicado no es válido.");
								break;
							case 11: // Eliminar Registro
								rVenta = new File(gf.seleccionarArchivo(ruta));
								check = rVenta.getName().contains("Registro_Venta.csv");
								if (check) {
									System.out.println("Desea elminiar el registro? (S/N)");
									if (sin.nextLine().trim().equalsIgnoreCase("s"))
										rVenta.delete();
								} else if (rVenta.getName().contains("Registro_Inventario.csv"))
									System.out.println(
											"El registro de inventario no puede ser eliminado, sólo modificado.");
								else if (rVenta.getName().contains("credenciales"))
									System.out.println("No puede eliminar las credenciales del sistema.");
								else
									System.out.println("El registro indicado es inválido.");
								break;
							case 12: // Ver ventas de registros pasados
								rVenta = new File(gf.seleccionarArchivo(ruta));
								check = rVenta.getName().contains("Registro_Venta.csv");
								if (check) {
									System.out.println("Ventas del registro: " + rVenta.getName());
									productos = gf.lecturaFicheroVenta(rVenta);
									for (producto x : productos)
										System.out.println(x.toStringVenta());
								} else
									System.out.println("El registro indicado es inválido.");
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

			// Opciones gerente
			else if (u instanceof gerente && continuarEjecucion) {

				g = (gerente) u;
				productos = gf.lecturaFicheroInv(rInventario);
				boolean continuarEjecucionGerente = true;
				int opcionGerente;
				do {
					Menus.menuGerente();
					try {
						System.out.print("Ingrese una opción: ");
						opcionGerente = sin.nextInt();
						sin.nextLine();

						switch (opcionGerente) {
							case 1: // Agregar Producto al Inventario
								g.agregarProductoAlInventario(sin, productos, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 2: // Crear Registro de Venta
								boolean continuarEjecucionRegistro = true;
								do {
									Menus.menuRegistroVenta();
									try {
										System.out.print("Ingrese una opción: ");
										opcionGerente = sin.nextInt();
										sin.nextLine();
										switch (opcionGerente) {
											case 1: // Agregar Venta
												registroVenta(sin, productos, g);
												break;
											case 2: // Modificar Venta
												g.gestionarVentas(sin, productos);
												break;
											case 3: // Ver Ventas Actuales
												g.imprimirVentas();
												break;
											case 4:
												g.verProductos(productos);
												break;
											case 5: // Salir y Guardar
												cerrarRegistro(gf, g, productos, rInventario);
												continuarEjecucionRegistro = false;
												break;
											case 6: // Salir Sin Guardar
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
								} while (continuarEjecucionRegistro);
								g.ventas.clear();
								break;
							case 3: // Ver Inventario
								productos = gf.lecturaFicheroInv(rInventario);
								if (!productos.isEmpty()) {
									for (producto x : productos)
										System.out.println(x.toStringInventario());
								} else
									System.out.println("El inventario esta vacio.");
								break;
							case 4: // Modificar Inventario
								Menus.menuModificarInventario();
								g.modificarInventario(sin, rInventario);
								productos = gf.lecturaFicheroInv(rInventario);
								break;
							case 5: // Ver Métricas
								rVenta = new File(gf.seleccionarArchivo(ruta));
								boolean continuarEjecucionMetricas = true,
										check = rVenta.getName().contains("Registro_Venta.csv");
								if (rVenta.exists() && check) {
									do {
										Menus.mostrarMetricas();
										try {
											System.out.print("Ingrese una opción: ");
											opcionGerente = sin.nextInt();
											sin.nextLine();
											switch (opcionGerente) {
												case 1: // Mostrar Producto Más Vendido
													System.out.println(g.productoMasVendido(rVenta, rInventario));
													break;
												case 2: // Total de Ventas
													System.out.println(g.totalVentas(rVenta));
													break;
												case 3: // Cantidad de Productos Vendidos
													System.out.println(g.productosVendidos(rVenta));
													break;
												case 4: // Exportar Métricas Actuales
													FileSystemView view = FileSystemView.getFileSystemView();
													String rutaExportar = view.getHomeDirectory().getPath();
													// Crear un fichero vacío en descargas
													File exportar = new File(rutaExportar, "Metricas.txt");
													// Llenarlo con los datos del fichero a exportar
													gf.escribirFicheroExportar(exportar, rVenta, rInventario, g);
													System.out.println(
															"Archivo exportado correctamente en: " + rutaExportar);
													break;
												case 0: // Salir
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
								} else if (!check)
									System.out.println("El registro indicado no es válido.");
								else
									System.out.println("El registro indicado no existe.");
								break;
							case 0: // Salir
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

			// Opciones empleado
			else if (u instanceof empleado && continuarEjecucion) {
				e = (empleado) u;
				productos = gf.lecturaFicheroInv(rInventario);
				boolean continuarEjecucionEmpleado = true;
				int opcionEmpleado;
				do {
					Menus.menuEmpleado();
					try {
						System.out.print("Ingrese una opción: ");
						opcionEmpleado = sin.nextInt();
						sin.nextLine();
						switch (opcionEmpleado) {
							case 1: // Crear Registro de Venta
								do {
									Menus.menuRegistroVenta();
									try {
										System.out.print("Ingrese una opción: ");
										opcionEmpleado = sin.nextInt();
										sin.nextLine();
										switch (opcionEmpleado) {
											case 1: // Agregar Venta
												registroVenta(sin, productos, e);
												break;
											case 2: // Modificar Venta
												e.gestionarVentas(sin, productos);
												break;
											case 3: // Ver Ventas Actuales
												e.imprimirVentas();
												break;
											case 4:
												e.verProductos(productos);
												break;
											case 5: // Salir y Guardar
												cerrarRegistro(gf, e, productos, rInventario);
												continuarEjecucionEmpleado = false;
												break;
											case 6: // Salir Sin Guardar
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
								} while (continuarEjecucionEmpleado);
								e.ventas.clear(); // Limpiar atributo ArrayList ventas
								break;
							case 0: // Salir
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
		} while (continuarEjecucion);
		sin.close();
	}

	public static void registroVenta(Scanner sin, ArrayList<producto> productos, empleado e) {
		String input = "";
		do {
			e.agregarVenta(sin, productos);
			sin.nextLine();
			System.out.println("Presione 's' para realizar otra venta.\nPulse cualquier otro botón para salir");
			input = sin.nextLine().trim();
		} while (input.equalsIgnoreCase("s"));

	}

	// Para registro nuevo
	public static void cerrarRegistro(GestorDatosFichero gf, empleado e, ArrayList<producto> productos, File rInventario) {
		if (e.getVentas().isEmpty()) {
			System.out.println("No hay ventas registradas");
		} else {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("Creando Registro para guardar las Ventas..");
			File rVenta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/"
					+ e.getNombre() + "_" + e.getApellido() + "_" + dateFormat.format(new Date())
					+ "_Registro_Venta.csv"); // Venta de empleado
			File rVentaGeneral = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/"
					+ dateFormat.format(new Date()) + "_Registro_Venta.csv"); // Venta general de ese día

			if (!gf.checkFichero(rVenta))
				System.out.println(
						"Ya has creado un registro para este día y tus ventas han sido agregadas.\nNombre del registro: "
								+ rVenta.getName());
			gf.checkFichero(rVentaGeneral);
			gf.escribirFicheroVenta(rVenta, e.ventas, rVenta.exists());
			gf.escribirFicheroVenta(rVentaGeneral, e.ventas, rVentaGeneral.exists());
			gf.escribirFichero(rInventario, productos, false);
			e.ventas.clear();
		}
	}

	// Para registro modificado
	public static void cerrarRegistroMod(GestorDatosFichero gf, empleado e, ArrayList<producto> productos,
			File rInventario, File rVenta) {
		String fecha[] = rVenta.getName().split("_");
		File rVentaGeneral = new File(
				"./Proyecto de Ingeniería/chameleon/src/ficheros/" + fecha[2] + "_Registro_Venta.csv");
		File carpeta = new File("./Proyecto de Ingeniería/chameleon/src/ficheros");
		File[] archivos;
		boolean sobreescribir = false;
		// Reescribe el fichero del empleado
		gf.escribirFicheroVenta(rVenta, e.ventas, false);
		// Reescribe el fichero de inventario
		gf.escribirFichero(rInventario, productos, false);
		gf.checkFichero(rVentaGeneral);
		if (carpeta.exists()) {
			if (carpeta.isDirectory()) {
				archivos = carpeta.listFiles();
				// Proceso para reescribir el fichero de venta general
				for (int i = 0; i < archivos.length; i++) {
					if (archivos[i].getName().contains("Registro_Venta.csv") && !archivos[i].getName().equals(rVentaGeneral.getName()) && (archivos[i].getName().contains(fecha[2]))) {
						gf.escribirFicheroVenta(rVentaGeneral, gf.lecturaFicheroVenta(archivos[i]), sobreescribir);
						sobreescribir = true;
					}
				}
			}
		}
		e.ventas.clear();
	}
}
