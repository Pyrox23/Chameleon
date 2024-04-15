package app;
import java.util.Scanner;
public class Programa {
<<<<<<< HEAD
	public static void main(String[] args){
		boolean log = true; //bandera para el bucle
		String r[] = new String[4]; //array para guardar datos del usuario
		Scanner sin = new Scanner(System.in); //teclado para ingresar datos por consola
		GestorDatosFichero gf = new GestorDatosFichero(); //objeto GestorDatosFichero para la lectura y escritura de datos desde y hacia un archivo**
		ArrayList<producto> productos = new ArrayList<producto>(); //arayList para guardar productos del inventario
		File rInventario = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Inventario.csv"); //ruta del archivo de registro de inventario
		File rVenta;
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

				//System.out.print("Ingrese una opción: ");
				//opcion = sin.nextInt(); //Leer la opción del usuario
				//sin.nextLine(); //Consumir el salto de línea

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
				//System.out.print("\nHola Usuario del Tipo Administrador!!!!\n");
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
						agregarNuevoUsuario(sin, r, gf, u);
					break;
						case 2:
							// ver ilista usuarios
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
				//System.out.print("\nHola Usuario del Tipo Gerente!!!!\n");
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
							agregarProductoAlInventario(sin, r, productos, rInventario);
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



=======
	public static void main(String[] args) {
		boolean log = false;
		String r[] = new String[2];
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.print("Introduce tu usuario: ");
			r[0] = s.nextLine();
			System.out.print("Introduce tu contraseña: ");
			r[1] = s.nextLine();
			//checkeo de credenciales
		}while(log);
		
		
>>>>>>> 3f5b1aac62ea87e65ec639cf37ffcbb0ff44e0ba
	}
	
	public static File escribirFichero(String[][] datos_f, String fichero) throws IOException{ 
        File fichero_fuente = new File(_Ruta + fichero + "_Fuente.csv"); 
        File fichero_excel = new File(_Ruta + fichero + ".csv"); 
        fichero_fuente = checkFichero(fichero_fuente, fichero); 
        fichero_excel = checkFichero(fichero_excel, fichero);
        String linea;
        BufferedWriter bw;   
		  PrintWriter pw; 
        String formato;

        for(int j=0; j<=1; j++){
            if(j==0){
                formato = "ISO-8859-1"; //Formato excel
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero_excel, false), formato)); 
            }
            else{
                formato = "UTF-8"; //Formato para codigo
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero_fuente, false), formato)); 
            }
            
            pw = new PrintWriter(bw); 

            //Bucle para escribir el fichero
            for(int i = 0; i<datos_f.length; i++){
            linea = concatenarFichero(datos_f[i],";"); 
            pw.println(linea); 
            }

            pw.flush(); 
            pw.close(); 
            bw.close();
        }

        System.out.println("\nFichero escrito con exito."); 
        return fichero_excel; 
    }
	
<<<<<<< HEAD
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
	public static void agregarProductoAlInventario(Scanner sin, String[] r, ArrayList<producto> prodInventario, File rInventario) {
    	double pdv, ppu;
    	int cant;
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


	// Método para agregar un nuevo usuario
	public static void agregarNuevoUsuario(Scanner sin, String[] r, GestorDatosFichero gf, usuario u) {
		ArrayList<usuario> usuarios = new ArrayList<usuario>();
    	// Datos para el nuevo usuario
    	System.out.print("\nEscribe el id del nuevo usuario: ");
   		r[0] = sin.nextLine();
    	System.out.print("Escribe la contraseña: ");
    	r[1] = sin.nextLine();
    	System.out.print("Nombre del usuario: ");
    	r[2] = sin.nextLine();
    	System.out.print("Perfil del usuario: ");
    	r[3] = sin.nextLine();

		//se crea un nuevo usuario con los datos ingresados (depende del perfil admin,gerent,emple)
		if(r[3].equalsIgnoreCase("administrador"))
			u = new administrador(r[0], r[1], r[2]);
		else if(r[3].equalsIgnoreCase("gerente"))
			u = new gerente(r[0], r[1], r[2]);
		else if(r[3].equalsIgnoreCase("empleado"))
			u = new empleado(r[0], r[1], r[2]);
		else{
			System.out.println("El perfil indicado no existe y el usuario fue asignado a empleado.");
			u = new empleado(r[0],r[1],r[2]); //en caso no exsita, automaticamente será de perfil empleado
		}
    	usuarios = gf.ListaUsuarios();
		usuarios.add(u);
		gf.EscribirUsuarios(usuarios);
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
				System.out.println(product);
			   p.add(product);
           }
           s.close();
       } catch(IOException ex){ 
			ex.printStackTrace();
       }
	   return p;
   }
=======
//	public static File checkFichero(File fichero, String nombre_fichero){ 
//        
//        try { 
//			if (fichero.createNewFile()) 
//			    System.out.println("\nEl fichero indicado, no existe y ha sido creado uno nuevo con el nombre " + nombre_fichero + "\nEsta ubicado en " + fichero); 
//		} catch (IOException e) { 
//			e.printStackTrace();
//		} 
//        return fichero;
//
//    }
//
//
//    public static String concatenarFichero(String datos[], String separador) { 
//
//		String linea = ""; 
//		for(int i = 0; i < datos.length; i++) {
//			if(i!=datos.length-1) 
//				linea += datos[i] + separador;
//			else 
//				linea += datos[i];
//		}
//		return linea; 
//	}
	
//	public static int ficheroCheck(String nombre_fichero){ 
//
//        _Ruta = "./Grupo8_CalendarioConciemcia/ficheros/"; 
//        File fichero = new File(_Ruta + nombre_fichero + ".csv"); 
//        int lineas= 0; 
//        lineas = lecturaFichero(fichero); 
//
//        if(lineas == 0){ 
//            _Ruta = "./ficheros/"; 
//            fichero = new File(_Ruta + nombre_fichero + ".csv"); 
//            lineas = lecturaFichero(fichero);
//
//            if(lineas == 0) 
//                System.out.println("\nEl fichero no pudo ser encontrado. Inserte guardar el fichero dentro de la carpeta 'ficheros' en la carpeta del programa e intente otra vez.");
//
//        }
//        
//        return lineas;
//    }
//
//    public static int lecturaFichero(File fichero){ 
//        int lineas_numero = 0;
//        try{ 
//            Scanner reader = new Scanner(fichero, "UTF-8"); 
//            while (reader.hasNextLine()){ 
//                reader.nextLine();
//                lineas_numero++; 
//            }
//            System.out.println("\nRuta del fichero verificada con exito."); 
//            reader.close();
//        }
//
//        catch(IOException ex){ 
//        }
//        return lineas_numero; 
//    }
>>>>>>> 3f5b1aac62ea87e65ec639cf37ffcbb0ff44e0ba
}
