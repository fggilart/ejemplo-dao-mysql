package tienda.vista;

public enum MenuPrincipal {
    HACER_PEDIDO("Hacer pedido"),
    MODIFICAR_PRODUCTO("Modificar producto"),
    CAMBIAR_PASSWORD("Cambiar contraseña de empleado"),
    CERRAR_SESION("Cerrar sesión");

    private final String texto;

    private MenuPrincipal(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }

    public static String[] toArray() {
        String[] opciones = new String[values().length];
        int i = 0;
        for (MenuPrincipal opcion : values()) {
            opciones[i] = opcion.toString();
            i++;
        }
        return opciones;
    }

}
