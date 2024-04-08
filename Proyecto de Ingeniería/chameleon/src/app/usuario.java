package app;
import java.io.Serializable;
import java.util.ArrayList;

public class usuario implements Serializable{
    protected String id;
    protected String contraseña;

    public usuario(String id, String contraseña){
        this.id = id;
        this.contraseña = contraseña;
    }

    public usuario login(){
        GestorDatosFichero gf = new GestorDatosFichero();
        ArrayList <usuario> u = gf.ListaUsuarios();
        usuario res = new usuario(this.id, this.contraseña);
        for(usuario x : u){
            if(this.id.equals(x.id)&&this.contraseña.equals(x.contraseña))
                res = x;
        }
        return res;
    }

    public String getId() {
        return id;
    }

    public String getContraseña() {
        return contraseña;
    }
    
}
