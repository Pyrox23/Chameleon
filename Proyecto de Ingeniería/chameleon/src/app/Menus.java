package app;


public class Menus {

    

    //--------------------------------------------------------------------------------------
    public static void menuInicial() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║          BIENVENIDO AL SISTEMA CHAMELEON             ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    MENU INICIAL                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t\t1. Iniciar sesión                      ║");
        System.out.println("║\t\t2. Salir del programa                  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }
    
    
    public static void menuAdmin() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║           BIENVENIDO USUARIO ADMINISTRADOR           ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                     MENU ADMIN                       ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Agregar Nuevo usuario                       ║");
        System.out.println("║\t2. Agregar Producto al Inventario              ║");
        System.out.println("║\t3. Crear Registro de Venta                     ║");
        System.out.println("║\t4. Ver lista de usuarios                       ║");
        System.out.println("║\t5. Ver Inventario                              ║");
        System.out.println("║\t6. Modificar Inventario                        ║");
        System.out.println("║\t7. Modificar Registro de Venta#                ║");
        System.out.println("║\t8. Ver Metricas                                ║");
        System.out.println("║\t9. Eliminar Usuario                            ║");
        System.out.println("║\t0. Salir                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }

    public static void menuGerente() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║             BIENVENIDO USUARIO GERENTE               ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    MENU GERENTE                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Agregar producto al inventario              ║");
        System.out.println("║\t2. Crear Registro de Venta                     ║");
        System.out.println("║\t3. Ver Inventario                              ║");
        System.out.println("║\t4. Modificar Inventario                        ║");
        System.out.println("║\t5. Ver Metricas                                ║");
        System.out.println("║\t6. Salir                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }

    public static void menuRegistroVenta() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                 BIENVENIDO USUARIO                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                 MENU REGISTRO VENTA                  ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Agregar Venta                               ║");
        System.out.println("║\t2. Modificar Venta                             ║");
        System.out.println("║\t3. Ver Ventas                                  ║");
        System.out.println("║\t4. Guardar Registro                            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }

    public static void menuEmpleado() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║             BIENVENIDO USUARIO EMPLEADO              ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                     MENU EMPLEADO                    ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Crear Registro de Venta                     ║");
        System.out.println("║\t2. Salir                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝"); 
    }


public static void mostrarMenuVentas() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                   REGISTRO DE VENTAS                 ║");
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

    public static void menuModificarInventario() {
        System.out.println("\n-------------------------------------------");
        System.out.println("       \tMODIFICAR INVENTARIO");
        System.out.println("-------------------------------------------");
        System.out.println("       \tInventario actual: ");
        System.out.println("-------------------------------------------");

    }

    public static void mensajeError(){
        System.out.println("Opción no válida. Intente de nuevo.");
    }

}
