package app;

public class Menus {

    // --------------------------------------------------------------------------------------
    public static void menuInicial() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║          Bienvenido al Sistema Chameleon             ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    Menú Inicial                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Iniciar sesión                              ║");
        System.out.println("║\t0. Salir del programa                          ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public static void menuAdmin() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║           Bienvenido Usuario Administrador           ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                     Menú Admin                       ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Agregar Nuevo Usuario                       ║");
        System.out.println("║\t2. Agregar Producto al Inventario              ║");
        System.out.println("║\t3. Registrar Ventas                            ║");
        System.out.println("║\t4. Ver Lista de Usuarios                       ║");
        System.out.println("║\t5. Ver Inventario                              ║");
        System.out.println("║\t6. Modificar Inventario                        ║");
        System.out.println("║\t7. Modificar Registro de Venta                 ║");
        System.out.println("║\t8. Ver Métricas                                ║");
        System.out.println("║\t9. Eliminar Usuario                            ║");
        System.out.println("║\t10. Exportar Registro                          ║");
        System.out.println("║\t11. Eliminar Registro                          ║");
        System.out.println("║\t12. Ver Ventas de Registros Pasados            ║");
        System.out.println("║\t0. Cerrar Sesión                               ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public static void menuGerente() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║             Bienvenido Usuario Gerente               ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                    Menú Gerente                      ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Agregar Producto al Inventario              ║");
        System.out.println("║\t2. Registrar Ventas                            ║");
        System.out.println("║\t3. Ver Inventario                              ║");
        System.out.println("║\t4. Modificar Inventario                        ║");
        System.out.println("║\t5. Ver Métricas                                ║");
        System.out.println("║\t0. Cerrar Sesión                               ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public static void menuRegistroVenta() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                 Bienvenido Usuario                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                 Menú Registro Venta                  ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Agregar Venta                               ║");
        System.out.println("║\t2. Modificar Venta                             ║");
        System.out.println("║\t3. Ver Ventas Actuales                         ║");
        System.out.println("║\t4. Ver Productos Disponibles                   ║");
        System.out.println("║\t5. Salir y Guardar                             ║");
        System.out.println("║\t6. Salir Sin Guardar                           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public static void menuEmpleado() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║             Bienvenido Usuario Empleado              ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║                     Menú Empleado                    ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Registrar Ventas                            ║");
        System.out.println("║\t0. Cerrar Sesión                               ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public static void mostrarMenuVentas() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                   Registro De Ventas                 ║");
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

    public static void mensajeError() {
        System.out.println("Opción no válida. Intente de nuevo.");
    }

    public static void mostrarMetricas() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                       Métricas                       ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║\t1. Mostrar Producto Más Vendido                     ║");
        System.out.println("║\t2. Total de Ventas                                  ║");
        System.out.println("║\t3. Cantidad de Productos Vendidos                   ║");
        System.out.println("║\t4. Exportar Métricas Actuales                       ║");
        System.out.println("║\t0. Salir                                            ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

}
