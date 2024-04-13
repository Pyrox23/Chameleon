package app;
import java.io.Serializable;
import java.util.ArrayList;

public class usuario implements Serializable{
    protected String id;
    protected String contraseña;
    protected String nombre;

    public usuario(String id, String contraseña, String nombre){
        this.id = id;
        this.contraseña = contraseña;
        this.nombre = nombre;
    }

    public usuario login(){
        GestorDatosFichero gf = new GestorDatosFichero();
        ArrayList <usuario> u = gf.ListaUsuarios();
        usuario res = null;
        for(usuario x : u){
            res = this.id.equals(x.id)&&this.contraseña.equals(x.contraseña) ? x : res;
        }
        return res;
    }

    public String getId() {
        return id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre(){
        return nombre;
    }
    
}
