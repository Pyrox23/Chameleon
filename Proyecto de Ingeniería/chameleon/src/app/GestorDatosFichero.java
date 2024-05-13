package app;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.FileDialog;
import java.awt.Frame;


//Serializable significa que los objetos de esta clase pueden ser convertidos en bytes y luego guardados en archivos como binarios
public class GestorDatosFichero implements Serializable{ //Clase de tipo Interfaz  

	public void escribirFichero(File fichero, ArrayList<producto> p, boolean sobreescribir){ 
        if(fichero.exists()){
			try{
				//"BufferedWriter" para escribir en el fichero , "PrintWriter" para escribir líneas en el BufferedWriter
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, sobreescribir), "ISO-8859-1"));  
				PrintWriter pw = new PrintWriter(bw); 

				//Se recorre la lista de productos y se escribe cada uno en una línea del fichero
				for(producto x : p)
					pw.println(x);
				pw.flush(); //asegura la escritura de los datos en el fichero
				pw.close(); 
				bw.close();
				System.out.println("\nRegistro de Inventario escrito con exito."); 
			} catch(IOException e){
				e.printStackTrace();
			}
		}
    }

	public void escribirFicheroVenta(File fichero, ArrayList<producto> p, boolean sobreescribir){ 
        if(fichero.exists()){ 
			try{
				//"BufferedWriter" para escribir en el fichero , "PrintWriter" para escribir líneas en el BufferedWriter
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, sobreescribir), "ISO-8859-1"));  
				PrintWriter pw = new PrintWriter(bw); 

				//Se recorre la lista de productos y se escribe cada uno en una línea del fichero
				for(producto x : p)
					pw.println(x.toStringRegistro());
				pw.flush(); //asegura la escritura de los datos en el fichero
				pw.close(); 
				bw.close();
				System.out.println("\nRegistro de Venta escrito con exito."); 
			} catch(IOException e){
				e.printStackTrace();
			}
		}
    }

	public void escribirFicheroVentaDes(File fichero, ArrayList<producto> p, boolean sobreescribir){ 
        if(fichero.exists()){ 
			try{
				//"BufferedWriter" para escribir en el fichero , "PrintWriter" para escribir líneas en el BufferedWriter
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, sobreescribir), "ISO-8859-1"));  
				PrintWriter pw = new PrintWriter(bw); 
				pw.write("ID;Nombre;Cantidad;PDV");

				//Se recorre la lista de productos y se escribe cada uno en una línea del fichero
				for(producto x : p)
					pw.println(x.toStringRegistro());
				pw.flush(); //asegura la escritura de los datos en el fichero
				pw.close(); 
				bw.close();
				System.out.println("\nRegistro de Venta escrito con exito."); 
			} catch(IOException e){
				e.printStackTrace();
			}
		}
    }

	public void escribirFicheroExportar(File fichero, File rVenta, File rInventario, gerente g){
		checkFichero(fichero);
        if(fichero.exists()){ 
			try{
				//"BufferedWriter" para escribir en el fichero , "PrintWriter" para escribir líneas en el BufferedWriter
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, false), "ISO-8859-1"));  
				PrintWriter pw = new PrintWriter(bw); 
				pw.write("Metricas del fichero " + rVenta.getName() + "\n");
				pw.write(g.productoMasVendido(rVenta, rInventario) + "\n");
				pw.write(g.productosVendidos(rVenta) + "\n");
				pw.write(g.totalVentas(rVenta));

				//Se recorre la lista de productos y se escribe cada uno en una línea del fichero
				pw.flush(); //asegura la escritura de los datos en el fichero
				pw.close(); 
				bw.close();
				System.out.println("\nRegistro de Venta escrito con exito."); 
			} catch(IOException e){
				e.printStackTrace();
			}
		}
    }

	public void escribirFicheroVentaGeneral(File fichero, ArrayList<producto> p, boolean sobreescribir, String id){ 
        if(fichero.exists()){ 
			try{
				//"BufferedWriter" para escribir en el fichero , "PrintWriter" para escribir líneas en el BufferedWriter
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero, sobreescribir), "ISO-8859-1"));  
				PrintWriter pw = new PrintWriter(bw); 

				//Se recorre la lista de productos y se escribe cada uno en una línea del fichero
				for(producto x : p)
					pw.println(x.toStringRegistro() + ";" + id);
				pw.flush(); //asegura la escritura de los datos en el fichero
				pw.close(); 
				bw.close();
				System.out.println("\nRegistro de Venta escrito con exito."); 
			} catch(IOException e){
				e.printStackTrace();
			}
		}
    }

	public boolean checkFichero(File fichero){ 
       try { 
			if (fichero.createNewFile())
			    System.out.println("\nNuevo registro creado");
			return true;
		} catch (IOException e) { 
			return false;
		}
    }

   public ArrayList<producto> lecturaFicheroInv(File fichero){
		ArrayList<producto> p = new ArrayList<producto>(); // lista para almacenar los productos leídos del archivo
		if(fichero.exists()){
			producto product; // variable para almacenar temporalmente cada producto leído del archivo
			String prod[] = new String[6]; // array para almacenar temporalmente los datos de cada línea del archivo
			try{ 
				Scanner s = new Scanner(fichero, "UTF-8");
				while (s.hasNextLine()){ 
					prod = s.nextLine().split(";");
					//Crear un nuevo objeto Producto con los datos leídos y agregarlo a la lista
					product = new producto(prod[1], prod[2], Integer.parseInt(prod[3]), Double.parseDouble(prod[4]), Double.parseDouble(prod[5]));
					product.setId(Integer.parseInt(prod[0])); // establecer el ID del producto
					p.add(product); // agregar el producto a la lista
				}
				s.close();
			} catch(IOException ex){ 
					ex.printStackTrace();
			}
		}
		return p; // deveulve la lista de productos leídos del archivo
   }

   public ArrayList<producto> lecturaFicheroVenta(File fichero){
	ArrayList<producto> p = new ArrayList<producto>();
	if(fichero.exists()){
		producto product;
		//Array para almacenar temporalmente los datos de cada línea (;) del archivo
		String prod[] = new String[6];  // osea cada dato esta separado por (;) y esos datos se guardan en el array
		try{ 
			Scanner s = new Scanner(fichero, "UTF-8");
			while (s.hasNextLine()){ 
				prod = s.nextLine().split(";");
				//Crear un nuevo objeto Producto con los datos leídos y agregarlo a la lista
   				product = new producto(prod[1], Integer.parseInt(prod[2]), Double.parseDouble(prod[3]), Integer.parseInt(prod[0]));
				p.add(product);
			}
			s.close();
		} catch(IOException ex){ 
				ex.printStackTrace();
		}
	}
	return p; // devuelve la lista de productos leídos del archivo
}

public ArrayList<producto> lecturaFicheroVentaGeneral(File fichero){
	ArrayList<producto> p = new ArrayList<producto>();
	if(fichero.exists()){
		producto product;
		//Array para almacenar temporalmente los datos de cada línea (;) del archivo
		String prod[] = new String[6];  // osea cada dato esta separado por (;) y esos datos se guardan en el array
		try{ 
			Scanner s = new Scanner(fichero, "UTF-8");
			while (s.hasNextLine()){ 
				prod = s.nextLine().split(";");
				//Crear un nuevo objeto Producto con los datos leídos y agregarlo a la lista
   				product = new producto(prod[1], Integer.parseInt(prod[2]), Double.parseDouble(prod[3]), Integer.parseInt(prod[0]));
				product.setDescripcion(prod[4]);
				p.add(product);
			}
			s.close();
		} catch(IOException ex){ 
				ex.printStackTrace();
		}
	}
	return p; // devuelve la lista de productos leídos del archivo
}


    public ArrayList<usuario> ListaUsuarios() {
		InputStream is = null;  // para leer archivo binario
		ObjectInputStream ois = null;  // para leer objetos
		ArrayList<usuario> u = new ArrayList<usuario>();
		File fichero = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/credenciales.bin");
		if(fichero.exists()){
			try {
				is = new FileInputStream(fichero);
				ois = new ObjectInputStream(is);
				//Lee los objetos del archivo binario mientras haya objetos disponibles
				while(is.available()>0) {
					Object obj = ois.readObject();
					if(obj instanceof usuario) {    // verifica si el objeto leído es de tipo Usuario
						u.add((usuario)obj);       //  si es asi, se agrega a la lista de usuarios
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				//Se cierra los streams de entrada (ois y is) en el bloque finally para asegurar que se cierren correctamente
				try {
					ois.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		return u; // devuelve la lista de usuarios
	}

	public boolean EscribirUsuarios(ArrayList<usuario> u) {
		OutputStream os = null;
		ObjectOutputStream oos = null;
		boolean c = true;  // variable para indicar si la escritura se realizo bien

			try {
				os = new FileOutputStream("./Proyecto de Ingeniería/chameleon/src/ficheros/credenciales.bin");
				oos = new ObjectOutputStream(os);
				//Escribe cada usuario de la lista en un archivo binario
				for(usuario x : u)
					oos.writeObject(x); //escrbie el objeto en arch binario
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				c = false;
			} catch (IOException e) {
				e.printStackTrace();
				c = false;
			} finally {
				try {
					oos.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					c = false;
				}
			}
		return c; //devuelve true o false de la escritura, si fue exitosa o no respectivamente
	}

	public String seleccionarArchivo(String ruta){
        FileDialog dialog = new FileDialog((Frame)null, "Seleccionar archivo", FileDialog.LOAD);

        // Establecer el directorio inicial del diálogo en la carpeta del proyecto
        dialog.setDirectory(ruta);
        
        // Mostrar el diálogo
        dialog.setVisible(true);
        
        // Obtener la ruta del archivo seleccionado por el usuario
        String selectedFilePath = dialog.getDirectory() + dialog.getFile();
        
        // Imprimir la ruta del archivo seleccionado por consola
        return selectedFilePath;
	}

	public boolean copyFile(String fromFile, String toFile) {
        File origen = new File(fromFile);
        File destino = new File(toFile);
        if (origen.exists()) {
            try {
                InputStream in = new FileInputStream(origen);
                OutputStream out = new FileOutputStream(destino);
                byte[] buff = new byte[1024];
                int len;
                while ((len = in.read(buff)) > 0) {
                    out.write(buff, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

}
