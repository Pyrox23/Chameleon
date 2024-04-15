package app;


public class Menus {

    

    //--------------------------------------------------------------------------------------
    public static void mostrarMenuInicial() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║          BIENVENIDO A LA TIENDA DE DON PEPE          ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    MENÚ INICIAL                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t\t1. Iniciar sesión                      ║");
        System.out.println("║\t\t2. Salir del programa                  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }
    
    
    public static void mostrarMenuAdministrador() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║           BIENVENIDO USUARIO ADMINISTRADOR           ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                     MENÚ ADMIN                       ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t\t1. Agregar nuevo usuario               ║");
        System.out.println("║\t\t2. Ver lista de usuarios               ║");
        System.out.println("║\t\t3. Salir                               ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }

    public static void mostrarMenuGerente() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║             BIENVENIDO USUARIO GERENTE               ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    MENÚ GERENTE                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Agregar producto al inventario              ║");
        System.out.println("║\t2. Ver inventario                              ║");
        System.out.println("║\t3. Salir                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }

    public static void mostrarMenuEmpleado() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║             BIENVENIDO USUARIO EMPLEADO              ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    MENÚ EMPLEADO                     ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Realizar venta                              ║");
        System.out.println("║\t2. Ver historial de ventas                     ║");
        System.out.println("║\t3. Salir                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }




    public static void mostrarIngresarSesion() {
        System.out.println("\n-------------------------------------------");
        System.out.println("       \tINGRESE SUS CREDENCIALES");
        System.out.println("-------------------------------------------");
    }

    public static void mostrarIngresarNuevUsuario() {
        System.out.println("\n---------------------------------------------");
        System.out.println("   \tINGRESE UN NUEVO USUARIO");
        System.out.println("---------------------------------------------");
    }
 
    public static void mostrarIngresarProducto() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("\tINGRESE UN PRODUCTO AL INVENTARIO");
        System.out.println("--------------------------------------------------\n");
    }


}
