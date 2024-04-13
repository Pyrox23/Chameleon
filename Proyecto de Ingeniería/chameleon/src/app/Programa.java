package app;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
public class Programa {
	public static void main(String[] args) {
		boolean log = true;
		String r[] = new String[4];
		Scanner sin = new Scanner(System.in);
		GestorDatosFichero gf = new GestorDatosFichero();
		ArrayList<producto> prodInventario = new ArrayList<producto>();
		File rInventario = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Inventario.csv");
		File rVenta;
		usuario u;
		empleado e = null;
		gerente g = null;
		administrador a = null;
		// gf.EscribirUsuario(new administrador("admin", "admin", "Juan"));
		do {
			if(!log)
				System.out.println("El usuario indicado es incorrecto, intenta de nuevo.");
			System.out.print("Introduce tu usuario: ");
			r[0] = sin.nextLine();
			System.out.print("Introduce tu contraseña: ");
			r[1] = sin.nextLine();
			u = new usuario(r[0], r[1], "");
			u = u.login();
			log = u != null;
		}while(!log);
		
		if(u instanceof administrador)
			a = (administrador) u;
		else if(u instanceof gerente)
			g = (gerente) u;
		else
			e = (empleado) u;

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
				prodInventario.add(new producto(r[0], r[1], cant, ppu, pdv));
				System.out.println("Desea añadir otro producto? \nPresione s para añadir otro producto");
					r[0] = sin.next();
			}while (r[0].equalsIgnoreCase("s"));
			escribirFichero(rInventario, prodInventario);
		}
		
		if(a != null){
			System.out.print("Escribe el id del nuevo usuario: ");
				r[0] = sin.nextLine();
			System.out.print("Escribe la contraseña: ");
				r[1] = sin.nextLine();
			System.out.print("Nombre del usuario: ");
				r[2] = sin.nextLine();
			System.out.print("Perfil del usuario: ");
				r[3] = sin.nextLine();
			if(r[3].equalsIgnoreCase("administrador"))
				u = new administrador(r[0], r[1], r[2]);
			else if(r[3].equalsIgnoreCase("gerente"))
				u = new gerente(r[0], r[1], r[2]);
			else if(r[3].equalsIgnoreCase("empleado"))
				u = new empleado(r[0], r[1], r[2]);
			else{
				System.out.println("El perfil indicado no existe y el usuario fue asignado a empleado.");
				u = new empleado(r[0],r[1],r[2]);
			}
			gf.EscribirUsuario(u);
	}

		sin.close();
	}
	
	public static void escribirFichero(File fichero, ArrayList<producto> p){ 
        checkFichero(fichero);
		try{
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, true), "ISO-8859-1"));  
			PrintWriter pw = new PrintWriter(bw); 
			for(producto x : p)
				pw.println(x);
			pw.flush(); 
			pw.close(); 
			bw.close();
			System.out.println("\nFichero escrito con exito."); 
		} catch(IOException e){
			e.printStackTrace();
		}
    }
	
	public static void checkFichero(File fichero){ 
       try { 
			if (fichero.createNewFile()) 
			    System.out.println("\nEl fichero indicado, no existe y ha sido creado"); 
		} catch (IOException e) { 
			e.printStackTrace();
		} 
   }
	
//Codigo para luego, NO BORRAR

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
