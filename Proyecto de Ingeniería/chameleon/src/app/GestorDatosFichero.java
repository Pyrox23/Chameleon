package app;
import java.io.*;
import java.util.ArrayList;

public class GestorDatosFichero implements GestorDatos{

	public ArrayList<producto> LeerProducto() {
		InputStream is = null;
		ObjectInputStream ois = null;
		ArrayList<producto> p = new ArrayList<producto>();
			try {
				is = new FileInputStream("datos.bin");
				ois = new ObjectInputStream(is);
				while(is.available()>0) {
					Object obj = ois.readObject();
					if(obj instanceof producto) {
						p.add((producto)obj);
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
		return p;
	}

	
	public boolean EscribirProducto(ArrayList<producto> p) {
		OutputStream os = null;
		ObjectOutputStream oos = null;
		boolean c = true;
			try {
				os = new FileOutputStream("datos.bin");
				oos = new ObjectOutputStream(os);
				for(producto x : p) {
					oos.writeObject(x);
				}
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
