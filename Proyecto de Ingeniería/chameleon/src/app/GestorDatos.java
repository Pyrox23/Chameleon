package app;
import java.util.ArrayList;
public interface GestorDatos {
    public ArrayList<producto> LeerProducto();
	public boolean EscribirProducto(ArrayList<producto> p);
}
