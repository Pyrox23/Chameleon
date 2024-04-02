package app;
import java.util.Scanner; //probando
public class Programa {
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
}
