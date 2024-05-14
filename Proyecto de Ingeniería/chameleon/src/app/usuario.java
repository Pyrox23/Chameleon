package app;

import java.io.Serializable;
import java.util.ArrayList;

public class usuario implements Serializable {
    protected String id;
    protected String contraseña;
    protected String nombre;
    protected String apellido;
    protected GestorDatosFichero gf = new GestorDatosFichero();

    public usuario(String id, String contraseña, String nombre, String apellido) {
        this.id = id;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public usuario login() {
        ArrayList<usuario> u = gf.ListaUsuarios(); // Obtiene la lista de usuarios del archivo binario
        usuario res = null;
        // Se itera sobre la lista de usuarios para encontrar el usuario con las
        // credenciales proporcionadas
        for (usuario x : u) {
            // Verifica si las credenciales del usuario actual coinciden con las
            // proporcionadas para iniciar sesión
            res = this.id.equals(x.id) && this.contraseña.equals(x.contraseña) ? x : res; // devuelve el usuario o el
                                                                                          // valor de res
        }
        return res; // devuelve el usuario encontrado (puede ser nulo si no se encontraron
                    // coincidencias)
    }

    public String getId() {
        return id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String toString() {
        String l = "[Nombre: " + this.nombre + " " + this.apellido + ", ID: " + this.id + ", Contraseña: "
                + this.contraseña;
        if (this instanceof administrador)
            l += ", Perfil: " + "Administrador]";
        else if (this instanceof gerente)
            l += ", Perfil: " + "Gerente]";
        else
            l += ", Perfil: " + "Empleado]";
        return l;
    }
}
