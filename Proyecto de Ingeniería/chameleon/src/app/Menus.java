package app;


public class Menus {

    

    //--------------------------------------------------------------------------------------
    public static void mostrarMenuInicial() {
        System.out.println("Menú inicial:");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Salir del programa");
    }

    public static void mostrarMenuAdministrador() {
        System.out.println("\nMenú del administrador:");
        System.out.println("1. Agregar nuevo usuario");
        System.out.println("2. Ver lista de usuarios");
        System.out.println("3. Salir");
    }

    public static void mostrarMenuGerente() {
        System.out.println("\nMenú del gerente:");
        System.out.println("1. Agregar producto al inventario");
        System.out.println("2. Ver inventario");
        System.out.println("3. Salir");
    }

    public static void mostrarMenuEmpleado() {
        System.out.println("\nMenú del empleado:");
        System.out.println("1. Realizar venta");
        System.out.println("2. Ver historial de ventas");
        System.out.println("3. Salir");
    }

   


}
