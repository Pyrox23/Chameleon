package app;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
// import java.io.Serializable;

public class gerente extends empleado {

	//protected (para q el padre de gerente e hijo de gerente puedas acceder)

	//Atributos
	//No tiene propios porque hereda; Duda solo hay un registro de datos o creo otro para el gerente ???

	//Constructor 1
	public gerente(String id, String contraseña, String nombre, String apellido) {  //heredado 
		super(id, contraseña, nombre, apellido); //heredado
	}
	
	public void agregarProductoAlInventario(Scanner sin, ArrayList<producto> prodInventario, File rInventario) {
    	double pdv, ppu;
    	int cant;
		String r[] = new String[6];
       try{ 
           Scanner s = new Scanner(rInventario, "UTF-8");
           while (s.hasNextLine())
               r = s.nextLine().split(";");
           s.close();
       } catch(IOException ex){ 
			ex.printStackTrace();
       }
	   producto.setSigId(Integer.parseInt(r[0])+1);
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
    	gf.escribirFichero(rInventario, prodInventario); 
	}
}
