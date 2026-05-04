/**
 * Clase concreta {@code Producto}
 * Implementa la interfaz {@link Comparable}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 */

public class Producto implements Comparable<Producto> {

    /** atributos */
    /** Cadena que representa el nombre del producto */
    private final String producto;

    /** Real que representa el precio del producto */
    private double precio;

    /** Cadena que representa la categoria del producto */
    private final String categoria;

    /** Cadena que representa la marca del producto */
    private final String marca;

    /** Natural que representa las unidades en existencia del producto */
    private int existencia;

    /**
     * Constuctor por parametros de la clase Producto
     * 
     * @param producto   Cadena que representa el nombre del producto
     * @param precio     Real que representa el precio del producto
     * @param categoria  Cadena que representa la categoria del producto
     * @param marca      Cadena que representa la marca del producto
     * @param existencia Natural que representa las unidades en existencia del
     *                   producto
     */
    public Producto(String producto, double precio, String categoria, String marca, int existencia) {
        this.producto = producto;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
        this.existencia = existencia;
    }

    /** getters */

    /**
     * Metodo para obtener la cadena que representa el nombre del producto
     * 
     * @return String de el atributo producto
     */
    public String getProducto() {
        return this.producto;
    }

    /**
     * Metodo para obtener el precio del producto
     * 
     * @return double que representa el precio del producto
     */
    public double getPrecio() {
        return this.precio;
    }

    /**
     * Metodo para obtener la categoria del producto
     * 
     * @return String que representa la categoria del producto
     */
    public String getCategoria() {
        return this.categoria;
    }

    /**
     * Metodo para obtener la marca del producto
     * 
     * @return String que representa la marca del producto
     */
    public String getMarca() {
        return this.marca;
    }

    /**
     * Metodo para obtener la existencia del producto
     * 
     * @return int que representa el numero de unidades en existencia del producto
     */
    public int getExistencia() {
        return this.existencia;
    }

    /** setters */

    /**
     * Metodo para establecer el precio del producto
     * 
     * @param precio double que representa el precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Metodo para establecer la existencia del producto
     * 
     * @param existencia int que representa el numero de unidades en existencia del
     *                   producto
     */
    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    /**
     * Compara este producto con otro primero por nombre y, en caso de empate,
     * por marca
     *
     * @param p el producto a comparar
     * @return un entero negativo, cero o positivo si este producto es menor,
     *         igual o mayor que el producto dado
     */
    @Override
    public int compareTo(Producto p) {
        int resultadoNombre = this.producto.compareTo(p.producto);

        if (resultadoNombre != 0) {
            return resultadoNombre;
        }

        int resultadoMarca = this.marca.compareTo(p.marca);
        return resultadoMarca;

    }

    @Override
    public String toString() {

        String representacion = "----------------------------------------" + "\n";

        representacion += "Nombre: " + this.getProducto() + "\n" +
                "Precio: " + this.getPrecio() + "\n" +
                "Categoria: " + this.getCategoria() + "\n" +
                "Marca: " + this.getMarca() + "\n" +
                "Unidades en existencia: " + this.getExistencia() + "\n" +
                "----------------------------------------" + "\n";
        return representacion;

    }
}