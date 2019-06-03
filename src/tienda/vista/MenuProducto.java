package tienda.vista;

public enum MenuProducto {
    VER_PRODUCTOS("Ver la lista de productos"),
    CAMBIAR_CODIGO("Cambiar código de producto"),
    CAMBIAR_NOMBRE("Cambiar nombre de producto"),
    CAMBIAR_PRECIO("Cambiar precio de producto"),
    CANCELAR("Cancelar");

    private final String texto;

    private MenuProducto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }

    public static String[] toArray() {
        String[] opciones = new String[values().length];
        int i = 0;
        for (MenuProducto opcion : values()) {
            opciones[i] = opcion.toString();
            i++;
        }
        return opciones;
    }
}
