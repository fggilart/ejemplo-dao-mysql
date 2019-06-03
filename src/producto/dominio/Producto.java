package producto.dominio;

public class Producto {

    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;

    public static enum Atributo {
        NOMBRE, DESCRIPCION, PRECIO, TODOS_LOS_ATRIBUTOS;
    }

    public Producto(int codigo, String nombre, String descripcion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto() {
        this(0, null, null, 0);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("%-14s%d%n%-14s%s%n%-14s%s%n%-14s%.2f%n",
                "Código:", getCodigo(),
                "Nombre:", getNombre(),
                "Descripción:", getDescripcion(),
                "Precio", getPrecio());
    }

}
