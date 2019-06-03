package tienda.vista;

public enum MenuPedido {
    AGREGAR_PRODUCTO("Añadir producto a la cesta"),
    VER_PRECIO_CESTA("Ver el precio total de la cesta"),
    VER_FACTURA("Ver factura del pedido"),
    TERMINAR_PEDIDO("Terminar pedido");

    private final String texto;

    private MenuPedido(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }

    public static String[] toArray() {
        String[] opciones = new String[values().length];
        int i = 0;
        for (MenuPedido opcion : values()) {
            opciones[i] = opcion.toString();
            i++;
        }
        return opciones;
    }
}
