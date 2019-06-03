package tienda.vista;

import empleado.dominio.Empleado;
import java.util.Scanner;
import util.Color;

public class VistaTienda {

    private static Empleado empleadoAutenticado;

    public static void setEmpleadoAutenticado(Empleado empleadoAutenticado) {
        VistaTienda.empleadoAutenticado = empleadoAutenticado;
    }

    public static MenuPrincipal opcionDesdeMenuPrincipal() {

        borrarPantalla();
        muestraEmpleadoAutenticado();
        construirMenu("Menu principal", MenuPrincipal.toArray());

        int numeroElegido = pedirOpcionEnRango(1, MenuPrincipal.values().length);

        MenuPrincipal menuElegido = null;

        int contador = 1;
        for (MenuPrincipal menuContador : MenuPrincipal.values()) {
            if (numeroElegido == contador) {
                menuElegido = menuContador;
                break;
            }
            contador++;
        }

        return menuElegido;

    }

    public static MenuProducto opcionDesdeMenuProducto() {

        borrarPantalla();
        muestraEmpleadoAutenticado();
        construirMenu("Menu modificar producto", MenuProducto.toArray());

        int numeroElegido = pedirOpcionEnRango(1, MenuProducto.values().length);

        MenuProducto menuElegido = null;

        int contador = 1;
        for (MenuProducto menuContador : MenuProducto.values()) {
            if (numeroElegido == contador) {
                menuElegido = menuContador;
                break;
            }
            contador++;
        }

        return menuElegido;
    }

    public static MenuPedido opcionDesdeMenuPedido() {

        borrarPantalla();
        muestraEmpleadoAutenticado();
        construirMenu("Menu hacer pedido", MenuPedido.toArray());

        int numeroElegido = pedirOpcionEnRango(1, MenuPedido.values().length);

        MenuPedido menuElegido = null;

        int contador = 1;
        for (MenuPedido menuContador : MenuPedido.values()) {
            if (numeroElegido == contador) {
                menuElegido = menuContador;
                break;
            }
            contador++;
        }

        return menuElegido;
    }

    private static int pedirOpcionEnRango(int min, int max) {

        Scanner leerTeclado = new Scanner(System.in);
        int opcion = 0;
        boolean hayError = true;

        while (hayError) {
            System.out.print("Seleccione una opción: ");
            if (leerTeclado.hasNextInt()) {
                opcion = leerTeclado.nextInt();
                hayError = opcion < min || opcion > max;
                if (hayError) {
                    muestraMensaje("Error, opción no válida. Debe ser entre [" + min + "] y [" + max + "]", Color.ERROR);
                }
            } else {
                muestraMensaje("Error, opción no válida. Debe ser entre [" + min + "] y [" + max + "]", Color.ERROR);
                leerTeclado.next();
            }
        }

        System.out.println();

        return opcion;
    }

    private static void muestraEmpleadoAutenticado() {
        String empeadoConFormato = creaTextoColor(empleadoAutenticado.getNombre(), Color.INFO);
        muestraMensaje("Empleado: " + empeadoConFormato);
    }

    public static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void construirMenu(String titulo, String[] opciones) {
        System.out.printf("%s%n", "*********************************************");
        System.out.printf("%s%n%n", titulo);
        for (int i = 0, total = opciones.length; i < total; i++) {
            System.out.printf("%s%n", "[" + (i + 1) + "] " + opciones[i]);
        }
        System.out.printf("%n%s%n", "*********************************************");
    }

    public static String pedirDatoString(String mensaje) {
        System.out.print(mensaje);
        Scanner leerTeclado = new Scanner(System.in);
        String texto = leerTeclado.next();
        return texto;
    }

    public static int pedirDatoInt(String mensaje) {

        int dato = 0;
        boolean esCodigoCorrecto = false;
        while (!esCodigoCorrecto) {
            try {
                dato = Integer.parseInt(VistaTienda.pedirDatoString(mensaje));
                esCodigoCorrecto = true;
            } catch (NumberFormatException e) {
                VistaTienda.muestraMensaje("Debe escribir un valor numérico", Color.ERROR);
            }
        }

        return dato;
    }

    public static double pedirDatoDouble(String mensaje) {

        double dato = 0;
        boolean esCodigoCorrecto = false;
        while (!esCodigoCorrecto) {
            try {
                dato = Double.parseDouble(VistaTienda.pedirDatoString(mensaje));
                esCodigoCorrecto = true;
            } catch (NumberFormatException e) {
                VistaTienda.muestraMensaje("Debe escribir un valor numérico", Color.ERROR);
            }
        }

        return dato;
    }

    public static void muestraMensaje(String mensaje, Color color) {
        System.out.println(color + mensaje + Color.DEFAULT);
    }

    public static void muestraMensaje(String mensaje) {
        muestraMensaje(mensaje, Color.DEFAULT);
    }

    public static String creaTextoColor(String texto, Color color) {
        return color + texto + Color.DEFAULT;
    }

    public static void esperarTeclaEnterContinuar() {
        muestraMensaje("Presiona la tecla ENTER para continuar ...", Color.INFO);
        Scanner leerTeclado = new Scanner(System.in);
        leerTeclado.nextLine();
    }

}
