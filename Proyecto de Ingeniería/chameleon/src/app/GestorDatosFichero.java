package app;
import java.io.*;
import java.util.ArrayList;

public class GestorDatosFichero implements GestorDatos, Serializable{

	public ArrayList<producto> LeerProducto() {
		InputStream is = null;
		ObjectInputStream ois = null;
		ArrayList<producto> p = new ArrayList<producto>();
			try {
				is = new FileInputStream(""); 
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
				os = new FileOutputStream("./Proyecto de Ingeniería/chameleon/src/ficheros/Registro_Inventario.bin");
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


    public ArrayList<usuario> ListaUsuarios() {
		InputStream is = null;
		ObjectInputStream ois = null;
		ArrayList<usuario> u = new ArrayList<usuario>();
			try {
				is = new FileInputStream("./Proyecto de Ingeniería/chameleon/src/ficheros/credenciales.bin");
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
		return u;
	}

    public boolean EscribirUsuario(usuario u) {
		OutputStream os = null;
		ObjectOutputStream oos = null;
		boolean c = true;
			try {
				os = new FileOutputStream("./Proyecto de Ingeniería/chameleon/src/ficheros/credenciales.bin");
				oos = new ObjectOutputStream(os);
				oos.writeObject(u);
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
    // public String buscarUsuario(String id, String contraseña){
    //     InputStream is = null;
	// 	DataInputStream dis = null;
    //     boolean found = false;
    //     String c[] = new String[3];
	// 		try {
	// 			is = new FileInputStream("credenciales.csv");
	// 			dis = new DataInputStream(is);
	// 			while(is.available()>0&&!found) {
	// 				String l = dis.readUTF();
    //                 c = l.split(";");
    //                 if(c[0].equals(id)&&c[1].equals(contraseña))
    //                     found = true;
	// 			}
	// 		} catch (FileNotFoundException e) {
	// 			e.printStackTrace();
	// 		} catch (IOException e) {
	// 			e.printStackTrace();
	// 		} finally {
	// 			try {
	// 				dis.close();
	// 				is.close();
	// 			} catch (IOException e) {
	// 				e.printStackTrace();
	// 			} catch (NullPointerException e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
    //     return found ? c[3] : "";
    // }
}
