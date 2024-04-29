package app;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorDatosFichero implements Serializable{

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
		ArrayList<producto> p = new ArrayList<producto>();
		if(fichero.exists()){
			producto product;
			String prod[] = new String[6];
			try{ 
				Scanner s = new Scanner(fichero, "UTF-8");
				while (s.hasNextLine()){ 
					prod = s.nextLine().split(";");
					product = new producto(prod[1], prod[2], Integer.parseInt(prod[3]), Double.parseDouble(prod[4]), Double.parseDouble(prod[5]));
					product.setId(Integer.parseInt(prod[0]));
					p.add(product);
				}
				s.close();
			} catch(IOException ex){ 
					ex.printStackTrace();
			}
		}
		return p;
   }

   public ArrayList<producto> lecturaFicheroVenta(File fichero){
	ArrayList<producto> p = new ArrayList<producto>();
	if(fichero.exists()){
		producto product;
		String prod[] = new String[6];
		try{ 
			Scanner s = new Scanner(fichero, "UTF-8");
			while (s.hasNextLine()){ 
				prod = s.nextLine().split(";");
   				product = new producto(prod[1], Integer.parseInt(prod[2]), Double.parseDouble(prod[3]), Integer.parseInt(prod[0]));
				p.add(product);
			}
			s.close();
		} catch(IOException ex){ 
				ex.printStackTrace();
		}
	}
	return p;
}


    public ArrayList<usuario> ListaUsuarios() {
		InputStream is = null;
		ObjectInputStream ois = null;
		ArrayList<usuario> u = new ArrayList<usuario>();
		File fichero = new File("./Proyecto de Ingeniería/chameleon/src/ficheros/credenciales.bin");
		if(fichero.exists()){
			try {
				is = new FileInputStream(fichero);
				ois = new ObjectInputStream(is);
				while(is.available()>0) {
					Object obj = ois.readObject();
					if(obj instanceof usuario) {
						u.add((usuario)obj);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
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
		return u;
	}

	public boolean EscribirUsuarios(ArrayList<usuario> u) {
		OutputStream os = null;
		ObjectOutputStream oos = null;
		boolean c = true;
			try {
				os = new FileOutputStream("./Proyecto de Ingeniería/chameleon/src/ficheros/credenciales.bin");
				oos = new ObjectOutputStream(os);
				for(usuario x : u)
					oos.writeObject(x);
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
		return c;
	}
}
