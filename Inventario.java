import estructuras.listas.*;

/**
 * Clase concreta {@code Inventario}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 */
public class Inventario {

    /** Atributos */
    ArbolBinarioOrdenado<Producto> inventario = new ArbolBinarioOrdenado<>();

    /** Constructor por omision */

    /** metodos */
    /**
     * Verifica si un producto se encuentra en el inventario.
     * 
     * @param p producto a buscar
     * @return {true} si el producto existe, {false} en caso contrario
     */
    public boolean seEncuentraEnElInventario(Producto p) {
        return inventario.buscar(p);
    }

    /**
     * Agrega un producto al inventario.
     * 
     * @param p producto a agregar
     */
    public void agregarUnProducto(Producto p) {
        this.inventario.agregar(p);
    }

    /**
     * Elimina un producto del inventario.
     * 
     * @param p producto a eliminar
     */
    public void eliminarUnProducto(Producto p) {
        this.inventario.eliminar(p);
    }

    /**
     * Obtiene una lista de productos cuyo precio se encuentra dentro de un rango
     * dado.
     * 
     * @param inicio limite inferior del precio
     * @param fin    limite superior del precio
     * @return lista de productos con precio dentro del rango
     */
    public ListaDoblementeLigada<Producto> productosEnUnRango(double inicio, double fin) {

        ListaDoblementeLigada<Producto> listaDelArbol = inventario.devolverRecorrido();
        ListaDoblementeLigada<Producto> listaProductosEnPrecio = new ListaDoblementeLigada<>();

        for (Producto pro : listaDelArbol) {

            if (pro.getPrecio() >= inicio && pro.getPrecio() <= fin) {
                listaProductosEnPrecio.agregar(pro);
            }

        }

        return listaProductosEnPrecio;

    }

    @Override
    public String toString() {
        return inventario.toString();
    }

}