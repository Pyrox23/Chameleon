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
        //Se crea una instancia del GestorDatosFichero para la lectura de datos desde algún archivo
        GestorDatosFichero gf = new GestorDatosFichero();

        //Se obtiene una lista de usuarios desde el archivo mediante el método ListaUsuarios()
        ArrayList <usuario> u = gf.ListaUsuarios();

        //Se crea una variable para almacenar el resultado del proceso de autenticación
        usuario res = null;
        for(usuario x : u){
            // Verificacion del ID y contraseña del usuario actual (this) coinciden con el usuario en la iteración actual (x)
            // Si es verdadera, se asigna el usuario actual (x) a la variable "res"
            // Si es falsa, "res" conserva su valor actual
            res = this.id.equals(x.id)&&this.contraseña.equals(x.contraseña) ? x : res;
        }
        //Devuelve el resultado del proceso de autenticación
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
    
    public String toString(){
        String l = "Nombre: " + this.nombre + ", ID: " + this.id + ", Contraseña: " + this.contraseña;
        if(this instanceof administrador)
            l += ", Perfil: " + "Administrador";
        else if(this instanceof gerente)
            l += ", Perfil: " + "Gerente";
        else
            l += ", Perfil: " + "Empleado";
        return l;
    }
}
