package app;
import java.io.Serializable;

public class usuario implements Serializable{
    protected String id;
    protected String contraseña;

    public usuario(String id, String contraseña){
        this.id = id;
        this.contraseña = contraseña;
    }

    public boolean login(){
        GestorDatosFichero gf = new GestorDatosFichero();
        return gf.buscarUsuario(this.id, this.contraseña);
    }

    public String getId() {
        return id;
    }

    public String getContraseña() {
        return contraseña;
    }
    
}
