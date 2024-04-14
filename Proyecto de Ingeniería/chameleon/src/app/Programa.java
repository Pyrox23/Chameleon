package app;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
public class Programa {
	public static void main(String[] args) {
		boolean log = true; //bandera para el bucle
		String r[] = new String[4]; //array para guardar datos del usuario
		Scanner sin = new Scanner(System.in); //teclado para ingresar datos por consola
		GestorDatosFichero gf = new GestorDatosFichero(); //objeto GestorDatosFichero para la lectura y escritura de datos desde y hacia un archivo**
		ArrayList<producto> prodInventario = new ArrayList<producto>(); //arayList para guardar productos del inventario
		File rInventario = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Inventario.csv"); //ruta del archivo de registro de inventario
		File rVenta;
		//variables para los tipos de usuario
		usuario u = null;
		empleado e = null;
		gerente g = null;
		administrador a = null;
		gf.EscribirUsuario(new administrador("admin", "admin", "Juan"));
		// Perfil Administrador: admin, admin
		// Perfil Gerente: gerente, 123
		// Perfil Empleado: empleado, 123
		boolean continuarEjecucion = true;
		int opcion;
		do {
			do {
				Menus.mostrarMenuInicial(); //Mostrar el menú inicial

				opcion = sin.nextInt(); //Leer la opción del usuario
				sin.nextLine(); //Consumir el salto de línea

				switch (opcion) {
					case 1:
						//Bucle de inicio de sesión
						do {
							//Lógica de inicio de sesión
							if (!log) {
								System.out.println("\nEl usuario indicado es incorrecto, intenta de nuevo.");
							}
							System.out.print("Introduce tu usuario: ");
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
			} while (!log && continuarEjecucion);
			
			//Se asigna el tipo de usuario correspondiente según el tipo de objeto "u"
			//instanceof signifca un objeto creado a partir de esa clase o sus hijas de esta, con el costructor 
			// Se asigna el tipo de usuario correspondiente según el tipo de objeto "u"
			if (u instanceof administrador && continuarEjecucion) {
				System.out.print("\nHola Usuario del Tipo Administrador!!!!\n");
				// en caso lo sea, se hace un "casting"(convertir) para q "u" sea tratada como un obj del tipo "administrador"
				a = (administrador) u;  // y con ello tenga acceso a sus metodos y propiedades de esa clase
				boolean continuarEjecucionAdmin = true;
				int opcionAdmin;
				do {
					Menus.mostrarMenuAdministrador(); // Mostrar el menú del gerente
					opcionAdmin = sin.nextInt(); // Leer la opción del gerente
					sin.nextLine(); // Consumir el salto de línea

					switch (opcionAdmin) {
						case 1:
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
				System.out.print("\nHola Usuario del Tipo Gerente!!!!\n");
				g = (gerente) u;

				boolean continuarEjecucionGerente = true;
				int opcionGerente;
				do {
					Menus.mostrarMenuGerente(); // Mostrar el menú del gerente
					opcionGerente = sin.nextInt(); // Leer la opción del gerente
					sin.nextLine(); // Consumir el salto de línea

					switch (opcionGerente) {
						case 1:
						agregarProductoAlInventario(sin, r, prodInventario, rInventario);
					break;
						case 2:
							// ver inventario
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
			}
	}while(continuarEjecucion);



	}


/* 
		//Si esl usuario es "gerente", agrega productos
		if(g != null){
			double pdv, ppu;
			int cant;
			do{
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

				//se agrega el producto al inventario(segund los datos ingresados)
				prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv)); // .add , añadir del arraylist ;)

				System.out.println("Desea añadir otro producto? \nPresione s para añadir otro producto");
					r[0] = sin.next();

			}while (r[0].equalsIgnoreCase("s"));

			//se escribe el inventario en el archivo
			escribirFichero(rInventario, prodInventario);
		}
		*/


        /* 
		//Si el usuario es "administrador", agrega nuevos usuarios
		if(a != null){
			//datos para el nuevo usuario
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

			//se escribe el nuevo usuario al archivo
			gf.EscribirUsuario(u);
	}

		sin.close(); //se cierra el scanner (teclado)
	}    */

	/*
		//Bucle de inicio de sesion hasta q el Usuario sea correcto
		do {
			//sino es correcto el inicio de sesion
			if(!log)
				System.out.println("El usuario indicado es incorrecto, intenta de nuevo.");
			//Si es correcto	
			System.out.print("Introduce tu usuario: ");
			r[0] = sin.nextLine();
			System.out.print("Introduce tu contraseña: ");
			r[1] = sin.nextLine();

			//se crea un objeto "usuario" con los datos ingresados
			u = new usuario(r[0], r[1], "");
			u = u.login(); //metodo para iniciar sesion (de la clase usuario)

			log = (u != null); //cambio de la variable log, si el inicio es corrrecto o no

		}while(!log);    
		
		*/


	
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
	public static void agregarProductoAlInventario(Scanner sin, String[] r, ArrayList<producto> prodInventario, File rInventario) {
    	double pdv, ppu;
    	int cant;
    	do {
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
        	prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv)); // .add , añadir del arraylist ;)

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



//Codigo para luego, NO BORRAR !!!  >:v

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
//    public static void lecturaFichero(File fichero){ 
//        try{ 
//            Scanner reader = new Scanner(fichero, "UTF-8"); 
//            while (reader.hasNextLine()){ 
//                reader.nextLine();
//            }
//            System.out.println("\nRuta del fichero verificada con exito."); 
//            reader.close();
//        }

//        catch(IOException ex){ 
//        }
//    }
}
